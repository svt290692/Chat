package ServerSide;

import com.jme3.network.Network;
import com.jme3.network.Server;

import java.io.IOException;

/**
 * Created by svt on 29.09.2014.
 */
public class ChatServer {

    Server server;

    public ChatServer() {
        server = null;

        int port = 5511;

        try {
            server = Network.createServer(port, -1);
        } catch (IOException ex) {
            System.err.println("error");
            return;
        }
        ServerHandler handler = new ChatServerHandler(server);

        server.addConnectionListener(handler);
        server.addMessageListener(handler);

        server.start();
    }

    public static void main(String args[]){
        ChatServer server = new ChatServer();

    }
}
