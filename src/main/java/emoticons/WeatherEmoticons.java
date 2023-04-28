package emoticons;

import com.vdurmont.emoji.EmojiParser;

public enum WeatherEmoticons {

    RAIN(":cloud_rain:"),
    CLEAR(":sunny:"),
    SNOW(":cloud_snow:"),
    CLOUDS(":cloud:");

    WeatherEmoticons(String value) {
        this.value = value;
    }

    private final String value;


    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

}
