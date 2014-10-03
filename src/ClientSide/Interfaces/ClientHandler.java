package ClientSide.Interfaces;

import com.jme3.network.Client;
import com.jme3.network.ClientStateListener;
import com.jme3.network.MessageListener;

/**
 * Created by svt on 03.10.2014.
 */
public interface ClientHandler extends ClientStateListener,MessageListener<Client>{

}
