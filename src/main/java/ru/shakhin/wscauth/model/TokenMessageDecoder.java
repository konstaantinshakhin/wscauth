package ru.shakhin.wscauth.model;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kshahin on 10/9/2015.
 */
public class TokenMessageDecoder implements Decoder.Text<TokenMessage> {
    @Override
    public TokenMessage decode(String jsonMessage) throws DecodeException {
        JsonObject jsonObject = Json
                .createReader(new StringReader(jsonMessage)).readObject();
        TokenMessage tmessage = new TokenMessage();
        tmessage.setType(jsonObject.getString("type"));
        tmessage.setSequenceId(jsonObject.getString("sequence_id"));
        Map<String,String> ms = new HashMap<String,String>();
        //JsonArray jarrray = jsonObject.getJsonArray("data");
        JsonObject jsonObj = jsonObject.getJsonObject("data");
        ms.put("email",jsonObj.getString("email"));
        //jsonObj = jarrray.getJsonObject(2);
        ms.put("password",jsonObj.getString("password"));
        tmessage.setData(ms);
        return tmessage;
    }

    @Override
    public boolean willDecode(String jsonMessage) {
        try {
            // Check if incoming message is valid JSON
            Json.createReader(new StringReader(jsonMessage)).readObject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        System.out.println("MessageDecoder -init method called");
    }

    @Override
    public void destroy() {
        System.out.println("MessageDecoder - destroy method called");
    }
}
