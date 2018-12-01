package com.max.tgbot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BotSettings {
    private static final String fileName = "settings.properties";
    private String botUsername;
    private String botToken;

    BotSettings() throws IOException {
        InputStream inputStream = null;
        try {
            Properties properties = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if (inputStream!=null){
                properties.load(inputStream);
            }else {
                throw new FileNotFoundException();
            }
            botToken = properties.getProperty("botToken");
            botUsername = properties.getProperty("botUsername");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (inputStream!=null){
                inputStream.close();
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
