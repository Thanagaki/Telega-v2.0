package cs.go.handler.handlerImpl;

import cs.go.handler.interfaceHandler.Handler;
import cs.go.model.State;
import cs.go.model.User;
import cs.go.repository.JpaUserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

import static cs.go.handler.handlerImpl.GrenadeHandler.CHOOSE_GRENADE;
import static cs.go.util.TelegramUtil.createInlineKeyboardButton;

@Service
public class RegistrationHandler implements Handler {

    public static final String NAME_ACCEPT = "/enter_name_accept";
    public static final String NAME_CHANGE = "/enter_name";
    public static final String NAME_CHANGE_CANCEL = "/enter_name_cancel";
    private final JpaUserRepository userRepository;

    public RegistrationHandler(JpaUserRepository jpaUserRepository) {
        this.userRepository = jpaUserRepository;
    }

    @Override
    public List<SendMessage> handle(User user, String message) {
        if (message.equalsIgnoreCase(NAME_ACCEPT) || message.equalsIgnoreCase(NAME_CHANGE_CANCEL)) {
            return accept(user);
        } else if (message.equalsIgnoreCase(NAME_CHANGE)) {
            return changeName(user);
        }
        return checkName(user, message);
    }

    private List<SendMessage> accept(User user) {

        user.setBotState(State.NONE);
        userRepository.save(user);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> inlineKeyboardButtonList = List.of(
                createInlineKeyboardButton("Продолжить", CHOOSE_GRENADE));
        inlineKeyboardMarkup.setKeyboard(List.of(inlineKeyboardButtonList));

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(user.getChatId());
        sendMessage.setText(String.format(
                "Your name is saved as: %s", user.getName()));
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return List.of(sendMessage);
    }

    private List<SendMessage> checkName(User user, String message) {
        user.setName(message);
        userRepository.save(user);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> inlineKeyboardButtonList = List.of(
                createInlineKeyboardButton("Accept", NAME_ACCEPT));
        inlineKeyboardMarkup.setKeyboard(List.of(inlineKeyboardButtonList));

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(user.getChatId());
        sendMessage.setText(String.format(
                "You have entered: %s%nIf this is correct - press the button", user.getName()));
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return List.of(sendMessage);

    }

    private List<SendMessage> changeName(User user) {
        user.setBotState(State.ENTER_NAME);
        userRepository.save(user);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> inlineKeyboardButtonList = List.of(
                createInlineKeyboardButton("Cancel", NAME_CHANGE_CANCEL));
        inlineKeyboardMarkup.setKeyboard(List.of(inlineKeyboardButtonList));

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(user.getChatId());
        sendMessage.setText(String.format(
                "Your current name is: %s%nEnter new name or press the button to continue", user.getName()));
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return List.of(sendMessage);
    }

    @Override
    public State operatedBotState() {
        return State.ENTER_NAME;
    }

    @Override
    public List<String> operatedCallBackQuery() {
        return List.of(NAME_ACCEPT, NAME_CHANGE, NAME_CHANGE_CANCEL);
    }
}
