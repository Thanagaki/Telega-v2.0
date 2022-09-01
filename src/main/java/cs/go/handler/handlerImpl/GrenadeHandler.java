package cs.go.handler.handlerImpl;

import cs.go.handler.interfaceHandler.Handler;
import cs.go.model.State;
import cs.go.model.User;
import cs.go.repository.JpaUserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

import static cs.go.handler.handlerImpl.MapHandler.MAP_CHOOSE;
import static cs.go.util.TelegramUtil.createInlineKeyboardButton;


@Service
public class GrenadeHandler implements Handler {

    public static final String SMOKES = "/SMOKES";

    public static String FLASHES = "/FLASHES";

    public  final static String MOLOTOVS = "/MOLOTOVS";

    public  final static  String CHOOSE_GRENADE = "/choose_grenade";

    private final JpaUserRepository userRepository;

    public GrenadeHandler (JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<SendMessage> handle(User user, String message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> inlineKeyboardButtons = List.of(
                createInlineKeyboardButton("Smokes", MAP_CHOOSE),
                createInlineKeyboardButton("Flashes", MAP_CHOOSE),
                createInlineKeyboardButton("Molotovs", MAP_CHOOSE));
        inlineKeyboardMarkup.setKeyboard(List.of(inlineKeyboardButtons));

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(user.getChatId());
        sendMessage.setText(String.format("" +
                "%s, Выбери гранату которую хочешь потренить ", user.getName()));
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return List.of(sendMessage);
    }

    @Override
    public State operatedBotState() {
        return null;
    }

    @Override
    public List<String> operatedCallBackQuery() {
        return List.of(SMOKES, FLASHES, MOLOTOVS, CHOOSE_GRENADE);
    }
}
