package org.hasselman.opensmarthome;

/**
 * Monitoring server for DDS enabled sensors
 */

import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.PathParam;
import javax.websocket.OnOpen;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import java.io.IOException;

@ServerEndpoint(value = "/socket/{id}")
public final class SmartMonitor {
    private static Session session = null;
    private static Session smartMonitorClient = null;

    @OnOpen
    public void onOpen(final Session session, @PathParam("id") String id) {
        System.out.printf("A client is attempting to connect with id: %s...%n", id);
        if(id.equals("0")) {
            if(smartMonitorClient == null) {
                smartMonitorClient = session;
            } else {
                System.out.println("CLIENT CONNECTING WITH ID 0!!!");
                try {
                    session.close();
                } catch (IOException ex) {
                    System.err.println("Error, client attempted to connect using id 0!!!");
                    ex.printStackTrace();
                }
            }
        } else {
            this.session = session;
        }
    }

    @OnError
    public void onError(final Session session, Throwable t) {
        System.err.println("Some horrible error has occurred!!!");
        t.printStackTrace();
    }

    @OnMessage
    public void onMessage(String contactSensorJSON) {
        System.out.println("RECEIVING: " + contactSensorJSON);
        if(session != null) {
            try {
                session.getBasicRemote().sendText(contactSensorJSON);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClose
    public void onClose(final Session session) {
        this.session = null;
    }
}

