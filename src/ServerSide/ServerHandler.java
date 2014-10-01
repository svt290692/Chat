package ServerSide;

import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.MessageListener;

/**
 * just handler of servver that have all functiontal to network
 * Created by svt on 29.09.2014.
 */
public interface ServerHandler extends ConnectionListener,MessageListener<HostedConnection> {

}
