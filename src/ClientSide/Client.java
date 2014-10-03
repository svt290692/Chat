package ClientSide;

import ClientSide.Interfaces.ClientHandler;
import ClientSide.Interfaces.ConnectionClient;
import Net.Messages.PrivateMessage;
import com.jme3.network.Network;

/**
 * Created by svt on 03.10.2014.
 */
public class Client implements ConnectionClient{

    private String curIP;
    private int curPort;
    boolean isConnect;

    private String lastConnectError;

    private ClientHandler mHandler;

    private com.jme3.network.Client client;

    public Client(String curIP, int curPort) {
        this.curIP = curIP;
        this.curPort = curPort;
    }

    public Client(ClientHandler mHandler, int curPort, String curIP) {
        this.mHandler = mHandler;
        this.curPort = curPort;
        this.curIP = curIP;
    }

    @Override
    public boolean tryConnection() {

        try{
            client = Network.connectToServer(curIP,curPort);
            client.addMessageListener(mHandler);
            client.addClientStateListener(mHandler);
        }catch (Exception e){
            lastConnectError = e.getMessage();
            return false;
        }
        return true;
    }

    public String getLastConnectError() {
        return lastConnectError;
    }

    @Override
    public void sendMessage(String message, String where) {
        client.send(new PrivateMessage(message,where));
    }

    @Override
    public boolean isConnection() {
        return isConnect;
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
    public void setHandler(ClientHandler handler) {
        mHandler = handler;
    }

}
