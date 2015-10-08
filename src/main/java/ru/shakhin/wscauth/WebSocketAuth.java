package ru.shakhin.wscauth;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocketauthendpoint", encoders = { MessageEncoder.class }, decoders = { MessageDecoder.class })
public class WebSocketAuth {
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session peer) {

            System.out.println("Client connected");
            peers.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        System.out.println("Connection closed");
        peers.remove(peer);
    }

    @OnMessage
    public void onMessage(Message message, Session session) throws IOException, EncodeException {
        System.out.println("message: " + message);
        Message response = new Message();
        response.setSubject("Response to " + message.getSubject());
        response.setContent("echo " + message.getContent());
        session.getBasicRemote().sendObject(response);

        // session.getBasicRemote().sendObject(message);
        }
    }