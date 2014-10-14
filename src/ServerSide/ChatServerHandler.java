package ServerSide;

import Net.Logging.XMLSingleBranchedLogger;
import Net.Logging.XMLSingleBranchedRegistrar;
import Net.Messages.*;
import Net.NetworkClient;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.Server;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by svt on 29.09.2014.
 */
public class ChatServerHandler implements ServerHandler {

    private final Server mServer;

    private final Map<String,HostedConnection> mActiveLogInClients = new TreeMap<String, HostedConnection>();

    private final Map<HostedConnection,String> mActiveHostedClients;
    private final List<String> mInactiveClients = new ArrayList<String>();
    private final XMLSingleBranchedRegistrar registrar = new XMLSingleBranchedRegistrar();

    private final XMLSingleBranchedLogger messageLogger = new XMLSingleBranchedLogger();
    private static final String REG_FILE_NAME = "registrationLog";
    private static String USERS_LOG = "usersLog";

    static{
        new File(USERS_LOG).mkdir();
    }

    public ChatServerHandler(Server mServer) {
        this.mServer = mServer;
        this.mActiveHostedClients = new TreeMap<HostedConnection, String>(new Comparator<HostedConnection>() {
            @Override
            public int compare(HostedConnection o1, HostedConnection o2) {
                return o1.getAddress().compareTo(o2.getAddress());
            }
        });
        messageLogger.setDir(USERS_LOG);
    }

    @Override
    public void connectionAdded(Server server, HostedConnection hostedConnection) {

    }

    @Override
    public void connectionRemoved(Server server, HostedConnection hostedConnection) {
        removeClient(hostedConnection);
    }

    @Override
    public void messageReceived(HostedConnection hostedConnection, Message message) {

        System.out.println("New Message receive to server: " + message);
        try {
            if (message instanceof ClientRequestMessage) {
                receiveRequestMessage((ClientRequestMessage)message,hostedConnection);

            } else if (message instanceof PrivateMessage) {
                receivePrivateMessage((PrivateMessage) message);
            }
        }catch (IOException ex){
            System.err.println("ERROR reading file or writing or opening file you have't rights");
            ex.printStackTrace();
        }
    }

//    private void receiveRegistrationMessage(RegistrationMessage message,HostedConnection connection)throws IOException{
//        ServerMessage msg = new ServerMessage();
//        boolean isLogCorrect = checkWithRegExp(message.getLogPass().getLogin(), message.getLogPass().getPassword());
//        boolean isLoginExists = registrar.isAccountExists(REG_FILE_NAME, message.getLogPass().getLogin());
//        if(isLogCorrect && !isLoginExists) {
//            registrar.writeAccount(REG_FILE_NAME, message.getLogPass().getLogin(), message.getLogPass().getPassword());
//
//            msg.setType(TypeServerMessage.ALLOW_REGISTRATION);
//        } else{
//            msg.setType(TypeServerMessage.DENIED_REGISTRATION);
//            if(!isLogCorrect){
//                msg.setMessage("You entered incorrect login or password. Try again.");
//            }
//            else{
//                msg.setMessage("this login already exists");
//            }
//        }
//
//        connection.send(msg);
//
//
//    }

//    private void receiveInitializationMessage(ClientRequestMessage message,HostedConnection connection) throws IOException{
//        ServerMessage servMsg = new ServerMessage();
//
//        if (!registrar.isAccountExists(REG_FILE_NAME,
//                message.getLogPass().getLogin(),
//                message.getLogPass().getPassword())) {
//
//            servMsg.setType(TypeServerMessage.DENIED_LOGIN);
//            servMsg.setMessage("This user is not registered");
//        } else {
//            servMsg.setType(TypeServerMessage.ALLOW_LOGIN);
//            addNewClient(message.getLogPass().getLogin(),connection);
//        }
//
//        connection.send(servMsg);
//        mServer.broadcast(createClientInformationMessage());
//    }

    private void receiveRequestMessage(ClientRequestMessage message,HostedConnection connection)throws IOException{
        if (message.getRequestType().equals(ClientRequestMessage.RequestType.CLIENT_LIST)) {
            mServer.broadcast(createClientInformationMessage());
        }
        else if(message.getRequestType().equals(ClientRequestMessage.RequestType.HISTORY)) {
            List<String> log = messageLogger.readLog(message.getMessage().split(" "));

//            StringBuffer sf = new StringBuffer();
//            for(String s : message.getMessage().split(" ")){
//                sf.append(s);
//                sf.append(' ');
//            }
//            sf.deleteCharAt(sf.length()-1);
            ServerMessage servMessage = new ServerMessage(message.getMessage(), TypeServerMessage.HISTORY_LOG, log);

            connection.send(servMessage);
        }
    }

    private void receivePrivateMessage(PrivateMessage message)  throws IOException{
        HostedConnection conn = mActiveLogInClients.get(message.getRecipient());

        messageLogger.writeLog(message.getWhoSend(),message.getMessage(),message.getRecipient());

        if (conn != null) {
            conn.send(message);
//            System.out.println("ERROR Connecton close when server try read message from him");
        }
    }

    private ServerMessage createClientInformationMessage(){
        List <NetworkClient> namesAndStatus = new ArrayList<NetworkClient>();

        try {
            for (String name : registrar.getExistsLogins(REG_FILE_NAME)) {
                namesAndStatus.add(new NetworkClient(name, mActiveLogInClients.get(name) != null));
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return new ServerMessage(null, TypeServerMessage.CLIENTS_INFORMATION,namesAndStatus);
    }

//    public static boolean checkWithRegExp(String ... str){
//        Pattern p = Pattern.compile("^\\w+$");
//        for(String s : str) {
//            if(!(p.matcher(s).matches()))
//            return false;
//        }
//        return true;
//    }

    private void addNewClient(String name,HostedConnection conn){
        mActiveLogInClients.put(name, conn);
        mActiveHostedClients.put(conn,name);
    }

    private void removeClient(String name){
        HostedConnection conn = mActiveLogInClients.get(name);
        if(null != conn) {
            mActiveHostedClients.remove(conn);
            mActiveLogInClients.remove(name);
        }
    }

    private void removeClient(HostedConnection conn){
        String name = mActiveHostedClients.get(conn);
        if(null != name) {
            mActiveLogInClients.remove(name);
            mActiveHostedClients.remove(conn);
        }

    }

}