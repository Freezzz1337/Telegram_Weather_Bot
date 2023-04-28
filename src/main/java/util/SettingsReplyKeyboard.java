package util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SettingsReplyKeyboard {
    private final ReplyKeyboardMarkup replyKeyboardMarkup;

    public SettingsReplyKeyboard() {
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
    }

    public ReplyKeyboardMarkup ReplyKeyboardForStart() {
        KeyboardRow row = new KeyboardRow();

        row.add("/help");
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(row));

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup ReplyKeyboardForExitFromGetWeather() {
        KeyboardRow row = new KeyboardRow();

        row.add("/go_back");
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(row));

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup ReplyKeyboardForHelp() {
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add("/weather");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("/start");
        row.add("/help");
        row.add("/auf");
        keyboardRows.add(row);

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        return replyKeyboardMarkup;
    }
}
