package cs.go.service;

import cs.go.handler.interfaceHandler.Handler;
import cs.go.model.State;
import cs.go.model.User;
import cs.go.repository.JpaUserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;
import java.util.List;

@Service
public class UpdateReceiver {

    public final List<Handler> handlers;

    private final JpaUserRepository userRepository;

    public UpdateReceiver (List<Handler> handlers, JpaUserRepository userRepository) {
        this.handlers = handlers;
        this.userRepository = userRepository;
    }

    public List<SendMessage> handle(Update update) {
        try {
            if(isMessageWithText(update)) {

                final Message message = update.getMessage();
                final long chatId = message.getFrom().getId();
                final User user  = userRepository.getByChatId(chatId)
                        .orElseGet(() -> userRepository.save(new User(chatId)));
                return getHandlerByState(user.getBotState()).handle(user, message.getText());

            } else if(update.hasCallbackQuery()) {
                final CallbackQuery callbackQuery = update.getCallbackQuery();
                final long chatId = callbackQuery.getFrom().getId();
                final User user = userRepository.getByChatId(chatId)
                        .orElseGet(() -> userRepository.save(new User(chatId)));
                return getHandlerByCallBackQuery(callbackQuery.getData()).handle(user, callbackQuery.getData());
            }
            throw new UnsupportedOperationException();

        } catch (UnsupportedOperationException e) {
            return Collections.emptyList();
        }

    }

    private Handler getHandlerByState(State state) {
        return handlers.stream()
                .filter(h -> h.operatedBotState() != null)
                .filter(h -> h.operatedBotState().equals(state))
                .findAny()
                .orElseThrow(UnsupportedOperationException::new);
    }

    private Handler getHandlerByCallBackQuery(String query) {
        return handlers.stream()
                .filter(h->h.operatedCallBackQuery().stream().anyMatch(query :: startsWith))
                .findAny()
                .orElseThrow((UnsupportedOperationException::new));
    }

    private boolean isMessageWithText(Update update) {
        return !update.hasCallbackQuery() && update.hasMessage() && update.getMessage().hasText();
    }


}
