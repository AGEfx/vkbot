package com.max.tgbot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();

        TelegramBotsApi api = new TelegramBotsApi();

        BotSettings settings;
        try {
            settings = new BotSettings();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            api.registerBot(new Bot(settings.getBotUsername(), settings.getBotToken()));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
