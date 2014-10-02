import ClientSide.MyMessage;
import com.jme3.network.*;
import com.jme3.network.serializing.Serializer;

import java.io.IOException;

/**
 * Created by svt on 02.10.2014.
 */
public class MyClient implements ClientStateListener, MessageListener<Client>{
    Client client;

    static{
        Serializer.registerClass(MyMessage.class);
    }
    MyClient(){
        try {
            client = Network.connectToServer("185.8.200.186",7777,-1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        client.addClientStateListener(this);
        client.addMessageListener(this);
        client.start();
        System.out.println("client started");
    }

    @Override
    public void clientConnected(Client client) {
        MyMessage message = new MyMessage();
        message.string = "abc";
        client.send(message);
    }

    @Override
    public void clientDisconnected(Client client, DisconnectInfo disconnectInfo) {

    }

    @Override
    public void messageReceived(Client client, Message message) {
        System.out.println(message);
    }

    public static void main(String args[]) throws InterruptedException {

        MyClient client = new MyClient();

        Thread.currentThread().join();
    }
}