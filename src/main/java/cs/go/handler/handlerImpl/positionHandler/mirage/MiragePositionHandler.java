package cs.go.handler.handlerImpl.positionHandler.mirage;

import cs.go.handler.interfaceHandler.Handler;
import cs.go.model.State;
import cs.go.model.User;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.util.List;

import static cs.go.handler.handlerImpl.positionHandler.mirage.LinksMiragePositionHandler.*;
import static cs.go.util.TelegramUtil.createInlineKeyboardButton;



@Service
public class MiragePositionHandler implements Handler {

    public static final String MIRAGE_POS = "/mirage_pos";

    String [] positions = {"Дым на КТ дефолт",
            "Дым на КТ с Т (второй)",
            "Дым на КТ с тетриса",
            "Дым на А плэнт с крыши Т",
            "Дым на А стеирс дефолт",
            "Дым в кросс-джангл дефолт",
            "Дым в кросс-джангл с топ мида",
            "Дым в джангл с Т респ",
            "Дым в джангл с тетриса",
            "Дым в джангл с топ мида",
            "Дым в топ кон с Т респ",
            "Дым в топ кон с тетриса",
            "Дым в топ кон с топ мида",
            "Дым в шорт на старт с Т респ",
            "Дым в шорт на старт с Т респ (второй)",
            "Дым в шорт с аппер мид",
            "Дым в шорт с Т респ",
            "Дым в окно с Т респ",
            "Дым в окно с Т респ (второй)",
            "Дым в окно с аппер мида"

    };

    @Override
    public List<SendMessage> handle(User user, String message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> inlineKeyboardButtonRowOne= List.of(
                createInlineKeyboardButton(positions[0], FROM_T_TO_CT_DEFAULT));

        List<InlineKeyboardButton> inlineKeyboardButtonRowTwo= List.of(
                createInlineKeyboardButton(positions[1], FROM_T_TO_CT_2));

        List<InlineKeyboardButton> inlineKeyboardButtonRowThree = List.of(
                createInlineKeyboardButton(positions[2], FROM_TETRIS_TO_CT));

        List<InlineKeyboardButton> inlineKeyboardButtonRowFour = List.of(
                createInlineKeyboardButton(positions[3], FROM_ROOF_T_TO_A_PLANT));

        List<InlineKeyboardButton> inlineKeyboardButtonRowFive = List.of(
                createInlineKeyboardButton(positions[4], FROM_T_TO_STAIRS));

        List<InlineKeyboardButton> inlineKeyboardButtonRowSix = List.of(
                createInlineKeyboardButton(positions[5], FROM_T_TO_JUNGLE_DEFAULT));


        List<InlineKeyboardButton> inlineKeyboardButtonRowSeven = List.of(
                createInlineKeyboardButton(positions[6], FROM_TOP_MID_TO_JUNGLE));

        List<InlineKeyboardButton> inlineKeyboardButtonRowEight = List.of(
                createInlineKeyboardButton(positions[7],  FROM_T_TO_JUNGLE_DEEP));

        List<InlineKeyboardButton> inlineKeyboardButtonRowNine = List.of(
                createInlineKeyboardButton(positions[8], FROM_TETRIS_TO_JUNGLE_DEEP));

        List<InlineKeyboardButton> inlineKeyboardButtonRowTen= List.of(
                createInlineKeyboardButton(positions[9], FROM_TOP_MID_TO_JUNGLE_DEEP));

        List<InlineKeyboardButton> inlineKeyboardButtonRowEleven= List.of(
                createInlineKeyboardButton(positions[10], FROM_T_TO_TOP_CON));

        List<InlineKeyboardButton> inlineKeyboardButtonRowTwelve= List.of(
                createInlineKeyboardButton(positions[11], FROM_TETRIS_TO_TOP_CON));

        List<InlineKeyboardButton> inlineKeyboardButtonRowThirteen= List.of(
                createInlineKeyboardButton(positions[12], FROM_TOP_MID_TO_TOP_CON));

        List<InlineKeyboardButton> inlineKeyboardButtonRowFourteen= List.of(
                createInlineKeyboardButton(positions[13], FROM_T_TO_SHORT_START));

        List<InlineKeyboardButton> inlineKeyboardButtonRowFifteen= List.of(
                createInlineKeyboardButton(positions[14], FROM_T_TO_SHORT_START_V2));

        List<InlineKeyboardButton> inlineKeyboardButtonRowSixteen= List.of(
                createInlineKeyboardButton(positions[15], FROM_MID_TO_SHORT));

        List<InlineKeyboardButton> inlineKeyboardButtonRowSeventeen= List.of(
                createInlineKeyboardButton(positions[16], FROM_T_TO_SHORT));

        List<InlineKeyboardButton> inlineKeyboardButtonRowEighteen= List.of(
                createInlineKeyboardButton(positions[17], FROM_T_TO_WINDOW));

        List<InlineKeyboardButton> inlineKeyboardButtonRowNineteen= List.of(
                createInlineKeyboardButton(positions[18], FROM_T_TO_WINDOW_V2));

        List<InlineKeyboardButton> inlineKeyboardButtonRowTwenty= List.of(
                createInlineKeyboardButton(positions[19], FROM_UPPER_MID_TO_WINDOW));



        inlineKeyboardMarkup.setKeyboard(List.of(inlineKeyboardButtonRowOne,
                inlineKeyboardButtonRowTwo,
                inlineKeyboardButtonRowThree,
                inlineKeyboardButtonRowFour,
                inlineKeyboardButtonRowFive,
                inlineKeyboardButtonRowSix,
                inlineKeyboardButtonRowSeven,
                inlineKeyboardButtonRowEight,
                inlineKeyboardButtonRowNine,
                inlineKeyboardButtonRowTen,
                inlineKeyboardButtonRowEleven,
                inlineKeyboardButtonRowTwelve,
                inlineKeyboardButtonRowThirteen,
                inlineKeyboardButtonRowFourteen,
                inlineKeyboardButtonRowFifteen,
                inlineKeyboardButtonRowSixteen,
                inlineKeyboardButtonRowSeventeen,
                inlineKeyboardButtonRowEighteen,
                inlineKeyboardButtonRowNineteen,
                inlineKeyboardButtonRowTwenty));

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(user.getChatId());
        sendMessage.setText(String.format("%s, выбери позицию",  user.getName()));
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return List.of(sendMessage);
    }

    @Override
    public State operatedBotState() {
        return null;
    }

    @Override
    public List<String> operatedCallBackQuery() {
        return List.of(MIRAGE_POS);
    }
}
