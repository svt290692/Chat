package ClientSide;

import ClientSide.Interfaces.ClientHandler;
import com.jme3.network.*;
import com.jme3.network.Client;

/**
 * Created by svt on 03.10.2014.
 */
public class MessagesClientHandler implements ClientHandler{
    /**
     * Called when the specified client is fully connected to
     * the remote server.
     *
     * @param c
     */
    @Override
    public void clientConnected(Client c) {

    }

    /**
     * Called when the client has disconnected from the remote
     * server.  If info is null then the client shut down the
     * connection normally, otherwise the info object contains
     * additional information about the disconnect.
     *
     * @param c
     * @param info
     */
    @Override
    public void clientDisconnected(Client c, DisconnectInfo info) {

    }

    @Override
    public void messageReceived(Client source, Message m) {

    }
}
