package util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.util.ArrayList;
import java.util.List;

public class SettingsInlineKeyboard {

    private final InlineKeyboardMarkup inlineKeyboardMarkup;

    public SettingsInlineKeyboard() {
        inlineKeyboardMarkup = new InlineKeyboardMarkup();
    }

    public InlineKeyboardMarkup InlineKeyboardChooseNumberOfDays() {
        List<InlineKeyboardButton> firstLayer = new ArrayList<>();
        List<InlineKeyboardButton> secondLayer = new ArrayList<>();
        List<InlineKeyboardButton> thirdLayer = new ArrayList<>();

        InlineKeyboardButton firstInlineKeyboardButton = new InlineKeyboardButton();
        InlineKeyboardButton secondInlineKeyboardButton = new InlineKeyboardButton();
        InlineKeyboardButton thirtInlineKeyboardButton = new InlineKeyboardButton();
        InlineKeyboardButton fourtInlineKeyboardButton = new InlineKeyboardButton();
        InlineKeyboardButton fifthInlineKeyboardButton = new InlineKeyboardButton();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        firstInlineKeyboardButton.setText("На сьогодні");
        firstInlineKeyboardButton.setCallbackData("1");
        firstLayer.add(firstInlineKeyboardButton);

        secondInlineKeyboardButton.setText("На 2 дні");
        secondInlineKeyboardButton.setCallbackData("2");
        secondLayer.add(secondInlineKeyboardButton);


        thirtInlineKeyboardButton.setText("На 3 дні");
        thirtInlineKeyboardButton.setCallbackData("3");
        secondLayer.add(thirtInlineKeyboardButton);


        fourtInlineKeyboardButton.setText("На 4 дні");
        fourtInlineKeyboardButton.setCallbackData("4");
        thirdLayer.add(fourtInlineKeyboardButton);

        fifthInlineKeyboardButton.setText("На 5 днів");
        fifthInlineKeyboardButton.setCallbackData("5");
        thirdLayer.add(fifthInlineKeyboardButton);

        rowList.add(firstLayer);
        rowList.add(secondLayer);
        rowList.add(thirdLayer);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
