package cs.go.handler.handlerImpl;

import cs.go.handler.interfaceHandler.Handler;
import cs.go.model.State;
import cs.go.model.User;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;

import static cs.go.handler.handlerImpl.RegistrationHandler.NAME_CHANGE;
import static cs.go.util.TelegramUtil.createInlineKeyboardButton;

@Service
public class HelpHandler implements Handler {
    @Override

    public List<SendMessage> handle(User user, String message) {
        if (message.startsWith("/change_name")) {
            // Создаем кнопку для смены имени
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            List<InlineKeyboardButton> inlineKeyboardButtonsRowOne = List.of(
                    createInlineKeyboardButton("Change name", NAME_CHANGE));

            inlineKeyboardMarkup.setKeyboard(List.of(inlineKeyboardButtonsRowOne));

            SendMessage sendMessage = new SendMessage();
            sendMessage.enableMarkdown(true);
            sendMessage.setChatId(user.getChatId());
            sendMessage.setText(String.format("" +
                    "You've asked for help %s? Here it comes!", user.getName()));
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            return List.of(sendMessage);

        } else
            return List.of(SendMessage
                    .builder()
                    .chatId(user.getChatId())
                    .text("Бро введи /change_name чтобы сменить ник")
                    .build());
    }


    @Override
    public State operatedBotState() {
        return State.NONE;
    }

    @Override
    public List<String> operatedCallBackQuery() {
        return Collections.emptyList();
    }
}
