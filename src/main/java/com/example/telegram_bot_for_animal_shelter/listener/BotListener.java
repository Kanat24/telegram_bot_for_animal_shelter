package com.example.telegram_bot_for_animal_shelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.telegram_bot_for_animal_shelter.configuration.BotConstants.GREETING_MSG;
import static com.example.telegram_bot_for_animal_shelter.configuration.BotConstants.INITIAL_CMD;

@Service
public class BotListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(BotListener.class);

    private final TelegramBot telegramBot;


    public BotListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
        this.telegramBot.setUpdatesListener(this);
    }

    @PostConstruct
    public void init() {
        generalMenu();
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> list) {
        list.stream()
                .filter(update -> update.message() != null)
                .forEach(update -> {
                    logger.info("Processing update: {}", update);
                    processUpdate(update);
                });

        return CONFIRMED_UPDATES_ALL;
    }

    private void processUpdate(Update update) {
        String userMessage = update.message().text();

        switch (userMessage) {
            case INITIAL_CMD:
                telegramBot.execute(new SendMessage(update.message().chat().id(), GREETING_MSG));
                break;
            case "/info":
                telegramBot.execute(new SendMessage(update.message().chat().id(), "рассказать о приюте"));
                //вроде кнопки создавать так, нужно будет попробовать и заменить сообщения на кнопки и подменю
                // InlineKeyboardButton info = new InlineKeyboardButton("рассказать о приюте");
                break;
            case "/adopt":
                telegramBot.execute(new SendMessage(update.message().chat().id(), "Порядок действий что бы забрать животное"));

                break;
            case "/report":
                telegramBot.execute(new SendMessage(update.message().chat().id(), "Отчет"));

                break;
            case "/help":
                telegramBot.execute(new SendMessage(update.message().chat().id(), "Волонтер"));

                break;

        }
    }

    public void generalMenu() {
        SetMyCommands message = new SetMyCommands(
                new BotCommand("/start", "Запустить бота"),
                new BotCommand("/info", "Узнать информацию о приюте"),
                new BotCommand("/adopt", "Хочу взять питомца домой"),
                new BotCommand("/report", "Прислать отчет о питомце"),
                new BotCommand("/help", "Позвать волонтера"));
        telegramBot.execute(message);
    }
}
