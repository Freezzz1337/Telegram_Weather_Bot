import components.BotCommands;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import util.GetInformFromApplication;

import java.io.FileNotFoundException;


public class Bot extends TelegramLongPollingBot implements BotCommands {

    private final GetInformFromApplication getInformFromApplication = new GetInformFromApplication();
    private final CommandProcessing commandProcessing = new CommandProcessing();

    public Bot() throws FileNotFoundException {
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS_FOR_MENU, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    public void onUpdateReceived(Update update) {
        SendMessage outMess;
        if (update.hasMessage() && update.getMessage().hasText()) { // message processing
            String chatId = update.getMessage().getChatId().toString();
            outMess = commandProcessing.commandExecution(update);
            outMess.setChatId(chatId);

            try {
                execute(outMess);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {   // button processing
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            outMess = commandProcessing.commandExecution(update);
            outMess.setChatId(chatId);
            try {
                execute(outMess);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }


    public String getBotUsername() {
        return getInformFromApplication.getBotName();
    }

    public String getBotToken() {
        return getInformFromApplication.getBotToken();
    }

}
