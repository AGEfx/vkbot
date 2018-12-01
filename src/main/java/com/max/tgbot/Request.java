package com.max.tgbot;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Request {
    private static final String query =
            "https://api.vk.com/method/messages.getHistory?peer_id={id}&count=1&v=5.52&access_token={token}";
    private String fileName = "file.json";

    private HttpURLConnection connection = null;


    public void makeRequest() {
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setReadTimeout(250);
            connection.setConnectTimeout(250);

            connection.connect();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                FileStream fileStream = new FileStream(fileName, connection);
                fileStream.writeFile();
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
