package ClientSide;

import ClientSide.Interfaces.ConnectionClient;
import Net.Messages.PrivateMessage;
import com.jme3.network.ClientStateListener;
import com.jme3.network.MessageListener;
import com.jme3.network.Network;

/**
 * Created by svt on 03.10.2014.
 */
public class Client implements ConnectionClient{

    private String curIP;
    private int curPort;

    private String lastConnectError;

    private ClientStateListener mStateListener;
    private MessageListener mMessageListener;

    private com.jme3.network.Client client;

    public Client() {

    }

    public Client(ClientStateListener mHandler) {
        this.mStateListener = mHandler;
        this.curPort = curPort;
        this.curIP = curIP;
    }

    @Override
    public boolean tryConnection() {

        try{
            client = Network.connectToServer(GlobalConfig.getInstance().getIP(),GlobalConfig.getInstance().getPort(),-1);
        }catch (Exception e){
            lastConnectError = "Unable to connect to server. Check network connection status and settings";
            return false;
        }
        return true;
    }

    public String getLastConnectError() {
        return lastConnectError;
    }

    @Override
    public boolean isConnection() {
        return client.isConnected();
    }

    @Override
    public void setIP(String ip) {
        curIP = ip;
    }

    @Override
    public String getIP() {
        return curIP;
    }

    @Override
    public int getPort() {
        return curPort;
    }

    @Override
    public void setPort(int port) {
        curPort = port;
    }

    @Override
    public void start() {
        client.start();
    }

    @Override
    public com.jme3.network.Client getConnectionClient() {
        return client;
    }

    @Override
    public void addStateListener(ClientStateListener listener) {
        client.addClientStateListener(listener);
    }

    @Override
    public void addMessageListener(MessageListener<com.jme3.network.Client> listener) {
        client.addMessageListener(listener);
    }


}
