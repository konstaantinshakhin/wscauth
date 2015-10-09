package ru.shakhin.wscauth.websocket;

import ru.shakhin.wscauth.model.Token;
import ru.shakhin.wscauth.model.TokenMessage;
import ru.shakhin.wscauth.model.User;
import ru.shakhin.wscauth.service.UserTokenService;

import javax.websocket.Session;
import java.util.*;

/**
 * Created by kshahin on 10/9/2015.
 */
public class WebSocketManager {

    private UserTokenService userTokenService;

    public WebSocketManager(){
        userTokenService = new UserTokenService();
    }

    private static Map<Session,String> peers = Collections.synchronizedMap(new HashMap<Session, String>());
   // private static Set<TokenMessage> tokenMessageSet = Collections.synchronizedSet(new HashSet<TokenMessage>());

    public Token login(Session peer,TokenMessage tmessage){
//        if(tokenMessageSet.contains(tmessage)){
//            return false;
//        }

        String email = null;
        String password = null;
        Map<String,String> ms = tmessage.getData();

        for(Map.Entry<String, String> entry : ms.entrySet()) {
            if("email".equals(entry.getKey())){
                email = entry.getValue();
            } else if ("password".equals(entry.getKey())){
                password = entry.getValue();
            }
        }
        User user = userTokenService.getUserByEmail(email);
        if(user== null){
            return null;
        }
        //String token = UUID.randomUUID().toString();
        Token token = userTokenService.SaveUserToken(email,password);
        if(token != null){
            peers.put(peer,email);
            return token;
        }
        return null;
    }

    public TokenMessage getResponse(Token token){
        TokenMessage tm  = new TokenMessage();
        if(token != null){
            tm.setType("CUSTOMER_API_TOKEN");
            tm.setSequenceId("sequence_id");
            Map<String,String> mt = new HashMap<String, String>();
            mt.put("api_token",token.getToken());
            mt.put("api_token_expiration_date",token.getDate().toString());
            tm.setData(mt);
        }else{
            tm.setType("CUSTOMER_ERROR");
            tm.setSequenceId("sequence_id");
            Map<String,String> mt = new HashMap<String, String>();
            mt.put("error_description","Customer not found");
            mt.put("error_code","customerError");
            tm.setData(mt);
        }
        return tm;
    }

    public boolean logout(Session peer,TokenMessage tmessage){
        return true;
    }

    public static Map<Session, String> getPeers() {
        return peers;
    }

    public static void setPeers(Map<Session, String> peers) {
        WebSocketManager.peers = peers;
    }
}
