package ru.shakhin.wscauth.websocket;

import ru.shakhin.wscauth.model.*;

import java.io.IOException;
import java.util.*;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocketauthendpoint", encoders = { TokenMessageEncoder.class }, decoders = { TokenMessageDecoder.class })
public class WebSocketEndPoint {
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session peer) {

            System.out.println("Client connected "+peer.getId());
            WebSocketManager.getPeers().put(peer,null);
            //peers.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        System.out.println("Connection closed");
        WebSocketManager.getPeers().remove(peer);
        //peers.remove(peer);
    }

//    @OnMessage
//    public void onMessage(Message message, Session session) throws IOException, EncodeException {
//        System.out.println("message: " + message);
//        Message response = new Message();
//        response.setSubject("Response to " + message.getSubject());
//        response.setContent("echo " + message.getContent());
//        session.getBasicRemote().sendObject(response);
//
//        // session.getBasicRemote().sendObject(message);
//        }

    @OnMessage
    public void onMessage(TokenMessage tmessage, Session session) throws IOException, EncodeException {
        WebSocketManager wscm = null;
        TokenMessage tresponse = null;
        System.out.println("message: " + tmessage.getType());
        if("LOGIN_CUSTOMER".equals(tmessage.getType())){
            wscm = new WebSocketManager();
            Token token = wscm.login(session,tmessage);
            tresponse = wscm.getResponse(token);
        }

        //Message response = new Message();
//        response.setSubject("Response to " + message.getSubject());
//        response.setContent("echo " + message.getContent());
        //session.getBasicRemote().sendObject(response);

         session.getBasicRemote().sendObject(tresponse);
    }
    }