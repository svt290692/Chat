package ServerSide;

import com.jme3.network.HostedConnection;

/**
 * abstract client in server side
 * Created by svt on 29.09.2014.
 */
public interface ExtendedClient {
    String getName();
    String getPasswd();
    String getConnectTime();
    boolean isRegistered();
    HostedConnection getMyConnection();
}