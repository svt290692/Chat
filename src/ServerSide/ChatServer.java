package ServerSide;


import Net.LogPass;
import Net.Messages.*;
import Net.NetworkClient;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;

import java.io.IOException;

/**
 * Created by svt on 29.09.2014.
 */
public class ChatServer {
    static{
        Serializer.registerClasses(PrivateMessage.class,ServerMessage.class, LogPass.class,
                ClientRequestMessage.class, NetworkClient.class);
    }
    Server server;

    public ChatServer() {
        server = null;

        int port = 5511;

        try {
            server = Network.createServer(5511,-1);
        } catch (IOException ex) {
            System.err.println("error");
            return;
        }
        ServerHandler handler = new ChatServerHandler(server);

        server.addConnectionListener(handler);
        server.addMessageListener(handler);

        server.start();
        System.out.println("server started");
    }

    public static void main(String args[]) throws InterruptedException {

        ChatServer server = new ChatServer();

        Thread.currentThread().join();
    }
}
