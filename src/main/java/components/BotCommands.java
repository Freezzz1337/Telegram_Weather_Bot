package components;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {

    List<BotCommand> LIST_OF_COMMANDS_FOR_MENU = List.of(
            new BotCommand("/start", "Старт"),
            new BotCommand("/help", "Список можливостів бота"),
            new BotCommand("/weather", "Дізнатися погоду в своєму місті"),
            new BotCommand("/auf", "Мотивуючі цитати над якими варто задуматися")
    );

}
