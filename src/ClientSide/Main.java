package ClientSide;

import Net.LogPass;
import Net.Messages.*;
import Net.NetworkClient;
import com.jme3.network.serializing.Serializer;

/**
 * Created by svt on 03.10.2014.
 */
public class Main {

    static{
        Serializer.registerClasses( PrivateMessage.class,
               ServerMessage.class, LogPass.class,
                ClientRequestMessage.class, NetworkClient.class);
    }

    public static void main(String args[]){

    }
}
