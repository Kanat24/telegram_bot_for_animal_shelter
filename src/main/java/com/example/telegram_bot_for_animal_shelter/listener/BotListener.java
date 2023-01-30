package com.example.telegram_bot_for_animal_shelter.listener;

import com.example.telegram_bot_for_animal_shelter.configuration.BotConstants;
import com.example.telegram_bot_for_animal_shelter.model.Person;
import com.example.telegram_bot_for_animal_shelter.repository.PersonRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.telegram_bot_for_animal_shelter.configuration.BotConstants.*;

@Service
public class BotListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(BotListener.class);

    private final TelegramBot telegramBot;
    private final PersonRepository personRepository;


    public BotListener(TelegramBot telegramBot,
                       PersonRepository personRepository) {
        this.telegramBot = telegramBot;
        this.telegramBot.setUpdatesListener(this);
        this.personRepository = personRepository;
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
        long chatId = update.message().chat().id();
        String name = update.message().chat().firstName();
        switch (userMessage) {
            case INITIAL_CMD:
                registerPerson(update.message());
                sendMessage(chatId, "Привет, " + name + "!" + GREETING_MSG);
                break;
            case "a":
                keyBoard(chatId, "dwedewd");

        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage sendMessage = new SendMessage(chatId, text);
        this.telegramBot.execute(sendMessage);

    }
private void keyBoard(long chatId, String text) {
    SendMessage sendMessage = new SendMessage(chatId, text);
    InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
    List<InlineKeyboardButton> rowInLine = new ArrayList<>();
    InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton("aaaa");
    var oneButton = new InlineKeyboardButton("sdsad");
    oneButton.callbackData("dmddndnd");
    rowInLine.add(oneButton);
    rowsInLine.add(rowInLine);
    markup.addRow(inlineKeyboardButton);
    sendMessage.replyMarkup(markup);
    this.telegramBot.execute(sendMessage);
}

    private void registerPerson(Message message){
        if (personRepository.findById(message.chat().id()).isEmpty()){
            var chatId = message.chat().id();
            var chat = message.chat();
            var phoneNumber = message.text();
            Person person = new Person();
            person.setId(chatId);
            person.setName(chat.firstName() + " " + chat.lastName());
            person.getPhoneNumber();
            personRepository.save(person);
        }
    }


}
