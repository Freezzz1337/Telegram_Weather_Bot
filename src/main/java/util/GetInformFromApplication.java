package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GetInformFromApplication {
    public String getBotName() {
        String botName = "";
        try (FileInputStream fileInputStream = new FileInputStream("./src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);

            botName = properties.getProperty("bot.name");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botName;
    }

    public String getBotToken() {
        String botToken = "";
        try (FileInputStream fileInputStream = new FileInputStream("./src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);

            botToken = properties.getProperty("bot.token");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botToken;
    }

    public String getFirstPartOFLink(){
        String firstPartOfTheLink = "";
        try (FileInputStream fileInputStream = new FileInputStream("./src/main/resources/application.properties")){
            Properties properties = new Properties();
            properties.load(fileInputStream);

            firstPartOfTheLink = properties.getProperty("FIRST_PART_OF_THE_LINK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstPartOfTheLink;
    }

    public String getLastPartOFLink(){
        String lastPartOfTheLink = "";
        try (FileInputStream fileInputStream = new FileInputStream("./src/main/resources/application.properties")){
            Properties properties = new Properties();
            properties.load(fileInputStream);

            lastPartOfTheLink = properties.getProperty("LAST_PART_OF_THE_LINK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastPartOfTheLink;
    }
}
