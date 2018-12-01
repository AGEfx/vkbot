package com.max.tgbot;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Scanner;

public class FileStream {
    private String fileName;
    private HttpURLConnection connection;
    FileStream(String fileName) {
        this.fileName = fileName;
    }

    FileStream(String fileName, HttpURLConnection connection) {
        this.fileName = fileName;
        this.connection = connection;
    }

    public String readFile() {
        StringBuilder str = new StringBuilder();
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            str.append(sc.next());
        }
        return str.toString();
    }

    public void writeFile() throws IOException {
        BufferedReader buff = null;
        FileWriter writer = null;
        try {
            buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            File file = new File(fileName);
            writer = new FileWriter(file);

            String string = buff.readLine();
            while (string != null) {
                writer.write(string);
                string = buff.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            buff.close();
            writer.flush();
            writer.close();
        }
    }
}
