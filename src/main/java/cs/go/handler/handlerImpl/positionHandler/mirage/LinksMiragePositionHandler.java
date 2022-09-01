package cs.go.handler.handlerImpl.positionHandler.mirage;

import cs.go.handler.interfaceHandler.Handler;
import cs.go.model.State;
import cs.go.model.User;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class LinksMiragePositionHandler implements Handler {

    public static final String FROM_T_TO_CT_DEFAULT = "/from_t_to_ct_default";
    public static String FROM_T_TO_CT_2 = "/from_t_to_ct_2" ;
    public static final String FROM_TETRIS_TO_CT = "/from_tetris_to_ct";
    public static final String FROM_ROOF_T_TO_A_PLANT = "/from_roof_t_to_a_plant";
    public static final String FROM_T_TO_STAIRS = "/from_t_to_stairs";
    public static final String FROM_T_TO_JUNGLE_DEFAULT = "/from_t_to_jungle_default";
    public static final String FROM_TOP_MID_TO_JUNGLE = "/from_top_mid_to_cross_jungle";
    public static final String FROM_T_TO_JUNGLE_DEEP = "/from_t_to_jungle_deep";
    public static final String FROM_TETRIS_TO_JUNGLE_DEEP = "/from_tetris_to_jungle_deep";
    public static final String FROM_TOP_MID_TO_JUNGLE_DEEP = "/from_top_mid_to_jungle_deep";
    public static final String FROM_T_TO_TOP_CON = "/from_t_to_top_con";
    public static final String FROM_TETRIS_TO_TOP_CON = "/from_tetris_to_top_con";
    public static final String FROM_TOP_MID_TO_TOP_CON = "/from_top_mid_to_top_con";
    public static final String FROM_T_TO_SHORT_START = "/from_t_to_short_start";
    public static final String FROM_T_TO_SHORT_START_V2 = "/from_t_to_short_start_V2";
    public static final String FROM_MID_TO_SHORT = "/from_mid_to_short";
    public static final String FROM_T_TO_SHORT = "/from_t_to_mid";
    public static final String FROM_T_TO_WINDOW = "/from_t_to_window";
    public static final String FROM_T_TO_WINDOW_V2 = "/from_t_to_window_v2";
    public static final String FROM_UPPER_MID_TO_WINDOW = "/from_upper_mid_to_window";


    @Override
    public List<SendMessage> handle(User user, String message) {
        String link = getRightLinkByCallBackQuery(message);
        SendMessage sendMessage = new SendMessage();
            sendMessage.enableMarkdown(true);
            sendMessage.setChatId(user.getChatId());
            sendMessage.setText(link);
            return List.of(sendMessage);
    }

    private String getRightLinkByCallBackQuery(String message) {
        String link;
        Map<String,String> linksMap = new HashMap<>();
        linksMap.put("https://www.youtube.com/watch?v=TQntl0gwacE", FROM_T_TO_CT_DEFAULT);
        linksMap.put("https://www.youtube.com/watch?v=cGFTcfDxmCk", FROM_T_TO_CT_2 );
        linksMap.put("https://www.youtube.com/watch?v=pSlT401Djgo", FROM_TETRIS_TO_CT);
        linksMap.put("https://www.youtube.com/watch?v=Dx7c4r6D5vo", FROM_ROOF_T_TO_A_PLANT);
        linksMap.put("https://www.youtube.com/watch?v=LcOLW7SJs4c", FROM_T_TO_STAIRS);
        linksMap.put("https://www.youtube.com/watch?v=b29F88axEFw", FROM_T_TO_JUNGLE_DEFAULT);
        linksMap.put("https://www.youtube.com/watch?v=uPxDGJUOgHE", FROM_TOP_MID_TO_JUNGLE);
        linksMap.put("https://www.youtube.com/watch?v=tRjZh0eQ18c", FROM_T_TO_JUNGLE_DEEP);
        linksMap.put("https://www.youtube.com/watch?v=VC5-YkjMHuw", FROM_TETRIS_TO_JUNGLE_DEEP );
        linksMap.put("https://www.youtube.com/watch?v=UiB9xUENlr4", FROM_TOP_MID_TO_JUNGLE_DEEP );
        linksMap.put("https://www.youtube.com/watch?v=jxkwpsmYgqw", FROM_T_TO_TOP_CON);
        linksMap.put("https://www.youtube.com/watch?v=AgrfTaLK4mY", FROM_TETRIS_TO_TOP_CON);
        linksMap.put("https://www.youtube.com/watch?v=kuA31Aklps4", FROM_TOP_MID_TO_TOP_CON);
        linksMap.put("https://www.youtube.com/watch?v=cH5X2BtN4Ws", FROM_T_TO_SHORT_START);
        linksMap.put("https://www.youtube.com/watch?v=HpavganXZI8", FROM_T_TO_SHORT_START_V2);
        linksMap.put("https://www.youtube.com/watch?v=6Wp0naSo-TA&t=2654s", FROM_MID_TO_SHORT);
        linksMap.put("https://www.youtube.com/watch?v=3QzhaBsWxjg", FROM_T_TO_SHORT);
        linksMap.put("https://www.youtube.com/watch?v=DZmoxgnZSsE", FROM_T_TO_WINDOW);
        linksMap.put("https://www.youtube.com/watch?v=wlQKKa7FjIY", FROM_T_TO_WINDOW_V2);
        linksMap.put("https://www.youtube.com/watch?v=QByW0Xb5UY0", FROM_UPPER_MID_TO_WINDOW);


        for(Map.Entry<String, String> items : linksMap.entrySet()) {
            if(items.getValue().equalsIgnoreCase(message)) {
                link = items.getKey();
                return link;
            }
        }
        return "Не существующая позиция";
    }

    @Override
    public State operatedBotState() {
        return null;
    }

    @Override
    public List<String> operatedCallBackQuery() {
        return List.of(FROM_T_TO_CT_DEFAULT, FROM_TETRIS_TO_CT, FROM_T_TO_CT_2, FROM_ROOF_T_TO_A_PLANT,FROM_T_TO_STAIRS,
                FROM_T_TO_JUNGLE_DEFAULT, FROM_TOP_MID_TO_JUNGLE, FROM_T_TO_JUNGLE_DEEP, FROM_TETRIS_TO_JUNGLE_DEEP,
                FROM_TOP_MID_TO_JUNGLE_DEEP,FROM_T_TO_TOP_CON, FROM_TETRIS_TO_TOP_CON, FROM_TOP_MID_TO_TOP_CON,
                FROM_T_TO_SHORT_START, FROM_T_TO_SHORT_START_V2, FROM_MID_TO_SHORT, FROM_T_TO_SHORT,
                FROM_T_TO_WINDOW, FROM_T_TO_WINDOW_V2, FROM_UPPER_MID_TO_WINDOW);
    }
}
