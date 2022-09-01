package cs.go.handler.interfaceHandler;

import cs.go.model.State;
import cs.go.model.User;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;
import java.util.List;

@Component
public interface Handler {

    List<SendMessage> handle(User user, String message);

    State operatedBotState();

    List<String> operatedCallBackQuery();


}
