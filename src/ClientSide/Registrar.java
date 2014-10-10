package ClientSide;

import ClientSide.Interfaces.RegistrarClients;
import Net.LogPass;
import Net.Messages.RegistrationMessage;
import Net.Messages.ServerMessage;
import Net.Messages.TypeMessage;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.Network;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by svt on 03.10.2014.
 */
public class Registrar implements RegistrarClients{
    private static Object mut = new Object();
    private static Registrar ourInstance = new Registrar();

    private static Logger log = Logger.getLogger(Registrar.class.getName());

    Client curClient;
    boolean isAnswer = false;
    boolean isConnect = false;
    boolean interrupt = false;
    String informationForClient;

    Message messageToSendWhenConnect = null;

    public static Registrar getInstance()
    {
        synchronized (mut) {
            return ourInstance;
        }
    }

    private Registrar() {
    }

    /**
     * try to register a client
     * BLOCKED OPERATION
     *
     * @param logPass login and password of client
     * @return string that will contain "successfully" or reason of error
     */
    @Override
    public String registerClient(final LogPass logPass) throws IOException {
        synchronized (mut) {
            isConnect = isAnswer = interrupt = false;
            informationForClient = "";
            String ip = GlobalConfig.getInstance().getIP();
            int port = GlobalConfig.getInstance().getPort();
            messageToSendWhenConnect = new RegistrationMessage(logPass);


            curClient = Network.connectToServer(ip,port,-1);
            curClient.addClientStateListener(this);
            curClient.addMessageListener(this);

            curClient.start();


            try {
            while(true) {
                if(isAnswer == true && isConnect == true){
                    curClient.close();
                    break;
                }
                else if(interrupt == true){
                    break;
                }

                Thread.sleep(10);
            }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return informationForClient;
        }
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

//        System.out.println("DEBUG MESSAGE : registrar resive  :" + m );

        if(m instanceof ServerMessage){
            ServerMessage msg = (ServerMessage)m;

            if(msg.getType().equals(TypeMessage.ALLOW_REGISTRATION)){
                informationForClient = "Registration succeeded, you may enter to you account now";
            }
            else if(msg.getType().equals(TypeMessage.DENIED_REGISTRATION)){
                informationForClient = "registration denied because:" + msg.getMessage();
            }
            else{
                log.log(Level.WARNING,"unsupported message type resive to registrar",m);
            }
            isAnswer = true;
        }
        else{
            log.log(Level.WARNING,"unsupported message type resive to registrar",m);
        }
    }
}
