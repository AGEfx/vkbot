package com.max.tgbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    private final String botUsername;
    private final String botToken;
    private int id = 0;

    Bot(String username, String token) {
        botUsername = username;
        botToken = token;
    }

    public void onUpdateReceived(Update update) {
        SendMessage message = null;

        MessageData data = new MessageData();
        JSONParser parser = new JSONParser();
        Request request = new Request();
        if (update.getMessage().getText().equals("/start")) {
            while (true) {
                request.makeRequest();
                parser.parseJSON();

                message = new SendMessage().setChatId(update.getMessage().getChatId()).setText(data.getText());

                if (id == 0 || id != data.getId()) {
                    id = data.getId();
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }
}
