package cs.go.handler.handlerImpl;

import cs.go.handler.interfaceHandler.Handler;
import cs.go.model.State;
import cs.go.model.User;
import cs.go.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Collections;
import java.util.List;


@Service
public class StartHandler implements Handler {
    @Value("${bot.name}")
    private String botName;

    private final JpaUserRepository userRepository;

    public StartHandler(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<SendMessage> handle(User user, String message) {
        if(message.startsWith("/start")) {
            SendMessage welcomeMessage = SendMessage.builder()
                    .chatId(user.getChatId())
                    .parseMode(ParseMode.MARKDOWN)
                    .text("Привет бро, рад тебя видеть!").build();
            welcomeMessage.enableMarkdown(true);
            user.setBotState(State.ENTER_NAME);
            userRepository.save(user);

            SendMessage registrationMessage = SendMessage.builder()
                    .chatId(user.getChatId())
                    .parseMode(ParseMode.MARKDOWN)
                    .text("Придумай себе ник").build();
            welcomeMessage.enableMarkdown(true);
            user.setBotState(State.ENTER_NAME);
            userRepository.save(user);
            return List.of(welcomeMessage, registrationMessage);
        } else
            return List.of(SendMessage
                    .builder()
                    .chatId(user.getChatId())
                    .text("Бро введи /start чтобы начать пользоваться ботом")
                    .build());

    }

    @Override
    public State operatedBotState() {
        return State.START;
    }

    @Override
    public List<String> operatedCallBackQuery() {
        return Collections.emptyList();
    }
}
