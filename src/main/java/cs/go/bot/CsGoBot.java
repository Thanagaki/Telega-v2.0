package cs.go.bot;

import cs.go.service.UpdateReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Component
public class CsGoBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    String botName;

    @Value("${bot.token}")
    String token;

    private final UpdateReceiver updateReceiver;

    public CsGoBot(UpdateReceiver updateReceiver) {
        this.updateReceiver = updateReceiver;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        List<SendMessage> messageToSend = updateReceiver.handle(update);

        if (messageToSend != null && !messageToSend.isEmpty()) {
            messageToSend.forEach(this::executeWithExceptionCheck);

            }
    }


    public void executeWithExceptionCheck(SendMessage sendmessage) {
        try {
            execute(sendmessage);
        } catch (TelegramApiException e) {
            log.error("oops");

        }
    }
}
