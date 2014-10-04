package ClientSide.Interfaces;

import ClientSide.Interfaces.Gui.Listeners.PrivateWindowListener;
import com.jme3.network.Client;
import com.jme3.network.ClientStateListener;
import com.jme3.network.MessageListener;

/**
 * Created by svt on 03.10.2014.
 */
public interface ConnectionClient{
    boolean tryConnection();
    boolean isConnection();

    void setIP(String ip);
    String getIP();

    int getPort();
    void setPort(int port);

    void start();
    Client getConnectionClient();

    void addStateListener(ClientStateListener listener);
    void addMessageListener(MessageListener<Client> listener);
}
