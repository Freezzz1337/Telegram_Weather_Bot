package emoticons;

import com.vdurmont.emoji.EmojiParser;

public enum OtherEmoticons {

    SAD_SMILE(":pensive:"),
    AUF(":point_up:"),
    HUGGING_FACE(":hugging:"),
    SUN(":sunny:"),
    UKRAINE(":ua:");

    private final String value;
    OtherEmoticons(String value){
        this.value = value;
    }

    public String get(){
        return EmojiParser.parseToUnicode(value);
    }


}
