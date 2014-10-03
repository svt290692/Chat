package ClientSide.Interfaces;

import ClientSide.Interfaces.Gui.Listeners.PrivateWindowListener;
import com.jme3.network.Client;

/**
 * Created by svt on 03.10.2014.
 */
public interface ConnectionClient extends PrivateWindowListener{
    boolean tryConnection();
    boolean isConnection();

    void setIP(String ip);
    String getIP();

    int getPort();
    void setPort(int port);

    void setHandler(ClientHandler handler);
}
