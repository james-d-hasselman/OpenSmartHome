/**
 * Mediator between DDS messages and the SmartMonitor clients
 */

package org.hasselman.opensmarthome;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.WebSocketContainer;
import javax.websocket.CloseReason;
import javax.websocket.Session;
import javax.websocket.ContainerProvider;
import java.net.URI;
import java.io.IOException;

@ClientEndpoint
public class SmartMonitorClient {
    private Session userSession;

    public SmartMonitorClient(URI endpointURI) {
        try {
            System.out.println("Attempting to open client websocket...");
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
            System.out.println("Successfully opened client websocket!");
        } catch(Exception e) {
            System.err.println("Error opening client websocket!!!");
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        this.userSession = userSession;
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        this.userSession = null;
    }

    public void forwardMessage(String contactSensorJSON) {
        try {
            this.userSession.getBasicRemote().sendText(contactSensorJSON);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
