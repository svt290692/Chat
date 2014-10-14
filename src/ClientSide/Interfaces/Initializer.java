package ClientSide.Interfaces;

import Net.LogPass;
import com.jme3.network.Client;
import com.jme3.network.ClientStateListener;
import com.jme3.network.MessageListener;

import java.io.IOException;

/**
 * Created by svt on 02.10.2014.
 */
public interface Initializer extends ClientStateListener, MessageListener<Client> {
    /**
     * try to register a client
     * @param logPass login and password of client
     * @return string that will contain "successfully" or reason of error
     */
    Client registerClient(final LogPass logPass) throws IOException;
}
