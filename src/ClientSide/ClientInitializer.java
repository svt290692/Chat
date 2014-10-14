package ClientSide;

import ClientSide.Interfaces.Initializer;
import Net.LogPass;
import Net.Messages.ClientRequestMessage;
import Net.Messages.ServerMessage;
import Net.Messages.TypeServerMessage;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.Network;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by svt on 03.10.2014.
 */
public class ClientInitializer implements Initializer {
    private static Object mut = new Object();
    private static ClientInitializer ourInstance = new ClientInitializer();

    private static Logger log = Logger.getLogger(ClientInitializer.class.getName());

    Client curClient;
    boolean isAnswer = false;
    boolean isConnect = false;
    boolean interrupt = false;
    String informationForClient = "";

    Message messageToSendWhenConnect = null;

    public static ClientInitializer getInstance()
    {
        synchronized (mut) {
            return ourInstance;
        }
    }

    private ClientInitializer() {
    }

    /**
     * try to register a client
     * BLOCKED OPERATION
     *
     * @param logPass login and password of client
     * @return string that will contain "successfully" or reason of error
     */
    @Override
    public Client registerClient(final LogPass logPass) throws IOException {
        synchronized (mut) {
            isConnect = isAnswer = interrupt = false;
            informationForClient = "";
            String ip = GlobalConfig.getInstance().getIP();

            if(GlobalConfig.getInstance().getPort().isEmpty()){
                String err = "Error IP or Port is not correct, Please check settings file";
                informationForClient = err;
                JOptionPane.showMessageDialog(null,err,"error settings",JOptionPane.ERROR_MESSAGE);
                return null;
            }
            int port = Integer.parseInt(GlobalConfig.getInstance().getPort());

            ClientRequestMessage m = new ClientRequestMessage();
            m.setMessage("INITIALIZATION");
            m.setRequestType(ClientRequestMessage.RequestType.INITIALIZATION);
            m.setRestrictedObject(logPass);
            messageToSendWhenConnect = m;


            curClient = Network.connectToServer(ip,port,-1);
            curClient.addClientStateListener(this);
            curClient.addMessageListener(this);

            curClient.start();


            try {
            while(true) {
                if(isAnswer == true && isConnect == true){
                    break;
                }
                else if(interrupt == true){
                    curClient = null;
                    break;
                }
                Thread.sleep(100);
            }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(curClient != null){
                curClient.removeClientStateListener(this);
                curClient.removeMessageListener(this);
            }
            return curClient;
        }
    }

    public String getLastInfoMessage(){
        return informationForClient;
    }
    /**
     * Called when the specified client is fully connected to
     * the remote server.
     *
     * @param c
     */
    @Override
    public void clientConnected(Client c) {
        isConnect = true;
        curClient.send(messageToSendWhenConnect);
        messageToSendWhenConnect = null;
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
        isConnect = false;
        interrupt = true;
    }

    @Override
    public void messageReceived(Client source, Message m) {

        System.out.println("DEBUG MESSAGE : registrar resive  :" + m );

        if(m instanceof ServerMessage){
            ServerMessage msg = (ServerMessage)m;

            if(msg.getType().equals(TypeServerMessage.ALLOW_LOGIN)){
                informationForClient = "Registration succeeded, you may enter to you account now";
            }
            else if(msg.getType().equals(TypeServerMessage.DENIED_LOGIN)){
                informationForClient = "registration denied because:" + msg.getMessage();
            }
            else{
                log.log(Level.WARNING,"unsupported message type receive to registrar",m);
            }
            isAnswer = true;
        }
        else{
            log.log(Level.WARNING,"unsupported message receive to registrar",m);
        }
    }
}
