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

import static cs.go.handler.handlerImpl.positionHandler.mirage.MiragePositionHandler.MIRAGE_POS;
import static cs.go.util.TelegramUtil.createInlineKeyboardButton;
import static cs.go.handler.handlerImpl.GrenadeHandler.CHOOSE_GRENADE;

@Service
public class MapHandler implements Handler {

    private final JpaUserRepository jpaUserRepository;
    public static final String MIRAGE = "/MIRAGE";
    public static final String INFERNO = "/INFERNO";
    public static final String NUKE = "/NUKE";
    public static final String DUST_2 = "/DUST_2";

    public static final String MAP_CHOOSE = "/choose_map";

    public MapHandler (JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public List<SendMessage> handle(User user, String message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> inlineKeyboardButtonsRowOne = List.of(
                createInlineKeyboardButton("Mirage",  MIRAGE_POS),
                createInlineKeyboardButton("Inferno", CHOOSE_GRENADE));
        List<InlineKeyboardButton> inlineKeyboardButtonsRowTwo = List.of(
                createInlineKeyboardButton("Nuke", CHOOSE_GRENADE),
                createInlineKeyboardButton("Dust_2", CHOOSE_GRENADE));
        inlineKeyboardMarkup.setKeyboard(List.of(inlineKeyboardButtonsRowOne, inlineKeyboardButtonsRowTwo));

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(user.getChatId());
        sendMessage.setText(String.format("" +
                "%s, выбери карту  на котрой хочешь потренировать smoke ", user.getName()));
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return List.of(sendMessage);
    }

    @Override
    public State operatedBotState() {
        return null;
    }

    @Override
    public List<String> operatedCallBackQuery() {
        return List.of(MIRAGE, INFERNO, NUKE, DUST_2, MAP_CHOOSE);
    }
}
