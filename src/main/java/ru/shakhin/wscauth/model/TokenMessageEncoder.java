package ru.shakhin.wscauth.model;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.Map;

/**
 * Created by kshahin on 10/9/2015.
 */
public class TokenMessageEncoder implements Encoder.Text<TokenMessage> {

    @Override
    public String encode(TokenMessage tmessage) throws EncodeException {
        JsonObject job = Json.createObjectBuilder().build();
        String[] name = new String[2];
        Map<String,String> ms = tmessage.getData();
        int i = 0;
        for(Map.Entry<String, String> entry : ms.entrySet()) {
            name[i] = entry.getKey();
            i++;
           // Json.createObjectBuilder().add(entry.getKey(), entry.getValue());
        }
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("type", tmessage.getType())
                .add("sequence_id", tmessage.getSequenceId())
                .add("data",Json.createObjectBuilder().add(name[0],ms.get(name[0])).add(name[1],ms.get(name[1])).build()).build();
        return jsonObject.toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
