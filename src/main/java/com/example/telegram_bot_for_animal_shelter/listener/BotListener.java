package com.example.telegram_bot_for_animal_shelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BotListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(BotListener.class);

    private  final TelegramBot telegramBot;

    public BotListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
        this.telegramBot.setUpdatesListener(this);
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
        if (userMessage.equals("/start")) {
            this.telegramBot.execute(new SendMessage(update.message().chat().id(),
                    "Привет! я бот приюта для животных и тд"));
        }

    }
}
