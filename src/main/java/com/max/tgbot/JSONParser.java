package com.max.tgbot;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;

public class JSONParser {

    public void parseJSON() {
        MessageData data = new MessageData();
        FileStream reader = new FileStream("file.json");
        JSONObject obj = new JSONObject(reader.readFile());


        JSONArray arr = obj.getJSONObject("response").getJSONArray("items");

        for (int i = 0; i < arr.length(); i++) {
            data.setText(arr.getJSONObject(i).getString("body"));
            data.setUserId(String.valueOf(arr.getJSONObject(i).getBigInteger("user_id")));
            data.setId(arr.getJSONObject(i).getInt("id"));
        }
    }


}
