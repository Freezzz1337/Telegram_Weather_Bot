import emoticons.OtherEmoticons;
import org.springframework.web.client.HttpClientErrorException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import util.SettingsInlineKeyboard;
import util.SettingsReplyKeyboard;

import java.util.*;

public class CommandProcessing {

    private final String[] arrCommand = {"/start", "/weather", "/help", "/auf"};

    private final SendMessage sendMessage = new SendMessage();

    private final SettingsReplyKeyboard settingsReplyKeyboard = new SettingsReplyKeyboard();

    private final SettingsInlineKeyboard settingsInlineKeyboard = new SettingsInlineKeyboard();

    public CommandProcessing() {
    }

    private final Map<Long, String> idAndCityMap = new HashMap<>();
    private final Map<Long, Boolean> idAndCheckMap = new HashMap<>();

    public void commandCheckWeather(Update update) {
        idAndCityMap.put(update.getMessage().getFrom().getId(), null);
        idAndCheckMap.put(update.getMessage().getFrom().getId(), false);
        sendMessage.setText("Введіть назву свого міста:");
        sendMessage.setReplyMarkup(settingsReplyKeyboard.ReplyKeyboardForExitFromGetWeather());
    }

    public SendMessage checkingIfUserIsWaitingForInput(Update update, long chatId) {

        if (update.getCallbackQuery() != null && idAndCityMap.containsKey(chatId) && idAndCheckMap.get(chatId)) {
            sendMessage.setText(new ShowWeather(idAndCityMap.get(chatId), update.getCallbackQuery().getData()).getResult());
            idAndCityMap.remove(chatId);
            sendMessage.setReplyMarkup(settingsReplyKeyboard.ReplyKeyboardForHelp());
        } else {
            try {
                new ShowWeather(update.getMessage().getText()).checkCity();
            } catch (HttpClientErrorException e) {
                sendMessage.setText("Не правильно введено назву міста!" + OtherEmoticons.SAD_SMILE.get()
                        + "\nПовторіть спробу знову");
                return sendMessage;
            }
            idAndCityMap.put(chatId, update.getMessage().getText());
            idAndCheckMap.put(chatId, true);
            sendMessage.setReplyMarkup(settingsInlineKeyboard.InlineKeyboardChooseNumberOfDays());
            sendMessage.setText("На скільки днів ви хочете дізнатися прогноз?" + OtherEmoticons.SUN.get());
        }

        return sendMessage;
    }


    public SendMessage commandExecution(Update update) {

        String textResponseFromUser = "";
        if (update.getMessage() != null && update.getMessage().hasText()) {
            textResponseFromUser = update.getMessage().getText();
        }
        sendMessage.setReplyMarkup(null);


        if (textResponseFromUser.equals("/weather") && !idAndCityMap.containsKey(update.getMessage().getFrom().getId())) {
            commandCheckWeather(update);
            return sendMessage;
        }


        switch (textResponseFromUser) {
            case "/start" -> {
                sendMessage.setText(
                        "Вас вітає WeatherBot!" + OtherEmoticons.HUGGING_FACE.get() +
                                "\nДаний бот створений з освітньою метою.\n" +
                                "Для отримання списку можливостей бота введіть команду /help, або натисніть на кнопку нижче");
                sendMessage.setReplyMarkup(settingsReplyKeyboard.ReplyKeyboardForStart());
            }
            case "/auf" -> {
                sendMessage.setText(QuotesOfWolf() + OtherEmoticons.AUF.get());
                sendMessage.setReplyMarkup(settingsReplyKeyboard.ReplyKeyboardForHelp());
            }

            case "/help" -> {
                sendMessage.setText("Список існуючих команд:\n"
                        + arrCommand[0] + " : Старт бота\n"
                        + arrCommand[1] + " : Дізнатися погоду\n"
                        + arrCommand[2] + " : Список всі доступних команд\n"
                        + arrCommand[3] + " : Мотивуючі цитати над якими варто задуматися");
                sendMessage.setReplyMarkup(settingsReplyKeyboard.ReplyKeyboardForHelp());
            }
            case "/go_back" -> {
                idAndCityMap.remove(update.getMessage().getFrom().getId());
                idAndCheckMap.remove(update.getMessage().getFrom().getId());
                sendMessage.setReplyMarkup(settingsReplyKeyboard.ReplyKeyboardForHelp());
                sendMessage.setText("Оберіть потрібну команду внизу\nЯкщо не па'ятаєте знаення команд оберіть команду /help, або скористайтесь меню");
            }


            case "Слава Україні!" -> {
                sendMessage.setText("Героям Слава!" + OtherEmoticons.UKRAINE.get());
            }
            case "Слава нації!" -> {
                sendMessage.setText("Смерть Ворогам!" + OtherEmoticons.UKRAINE.get());
            }
            case "Україна!" -> {
                sendMessage.setText("Понад усе!" + OtherEmoticons.UKRAINE.get());
            }


            default ->
                    sendMessage.setText("Невірна набрана команда\nЗа допомогою команди /help можно дізнатися про доступні команди");
        }


        if (update.getMessage() != null && (idAndCityMap.containsKey(update.getMessage().getFrom().getId())) ||
                (update.getCallbackQuery() != null && idAndCityMap.containsKey(update.getCallbackQuery().getMessage().getChatId()))) {
            long chatId;

            if (update.getMessage() != null && (idAndCityMap.containsKey(update.getMessage().getFrom().getId()))) {
                chatId = update.getMessage().getFrom().getId();
            } else {
                chatId = update.getCallbackQuery().getMessage().getChatId();
            }

            checkingIfUserIsWaitingForInput(update, chatId);
            return sendMessage;
        }
        return sendMessage;
    }

    private String QuotesOfWolf() {
        Random random = new Random();

        String[] magicList = {"Краще бути останнім першим, ніж першим останнім",
                "На випадок, якщо я буду потрібен, то я там же, де й був, коли був не потрібен",
                "Якщо вовк мовчить, то краще його не перебивай",
                "Легко вставати, коли ти не лягав",
                "За двома зайцями поженешся — рибку з ставка не виловиш, справі час, а відміряєш сім разів",
                "Ким би ти не був, ким би ти не став, пам'ятай де ти був і ким ти став",
                "Іноді життя — це життя, а ти в ньому іноді",
                "Ніколи не пізно, ніколи не рано – поміняти все пізно, якщо це зарано"
        };

        return magicList[random.nextInt(magicList.length)];
    }
}