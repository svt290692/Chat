package ServerSide;

import Net.ConnectSeance;
import Net.ExtendedMessage;
import Net.ProtocolParser;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.Server;

/**
 * Created by svt on 29.09.2014.
 */
public class ChatServerHandler implements ServerHandler,ProtocolParser {

    private final Server mServer;

    public ChatServerHandler(Server mServer) {
        this.mServer = mServer;
    }

    @Override
    public void connectionAdded(Server server, HostedConnection hostedConnection) {
        //TODO add new Client
    }

    @Override
    public void connectionRemoved(Server server, HostedConnection hostedConnection) {
        //TODO remove client
    }

    @Override
    public void messageReceived(HostedConnection hostedConnection, Message message) {

        System.out.println(message);
        mServer.broadcast(message);
//        if(message instanceof ExtendedMessage){
//            ConnectSeance seance = (ConnectSeance)message;
//            //lets parse
//            //if registered hosted connection then parse else try to register else errorw
//            //TODO add code to try register if he unregistred
//            parse(seance);
//        }
    }

    @Override
    public void parse(ConnectSeance message) {
        final String x = message.getCmd().trim();

        switch (x.charAt(0)) {
            case '?': {
                //private message resived
                // getMsg() == private chat msg
                String distClientName = x.replace('?', ' ').trim();
                sendPrivateMessage(message.getMsg(),distClientName);
                break;
            } case '~': {
                // public message resived
                // getMsg() == main chat msg
                sendPublicMessage(message.getMsg());
                break;
            } case '@': {
                //registration message resived
                // getMsg() == ?
                //TODO add registration code behind this function
                String [] regInfo = x.replace('@', ' ').trim().split(":");
                String name = regInfo[0];
                String pass = regInfo[1];

                break;
            } case '!': {
                //Admin Command resived
                String[] commands = x.replace('!', ' ').split(";");

                for (String cmd : commands) {
                    //TODO comands handing
                }

                break;
            } default: {
                // unknown command resived
                // x == client's cmd
                // getMsg() == ?
                //TODO error handing

                // error ?

                break;
            }
        }
    }

    private void registerUser(String name,HostedConnection conn){

    }

    private void sendPrivateMessage(String msg,String when){
        //TODO find cliennt in base
        ExtendedClient client = null;
        HostedConnection conn = null;
        String command  = "?"+when+"?";
        ExtendedMessage message = new ExtendedMessage();
        message.setCommand(command);
        message.setMessage(msg);
        conn.send(message);
    }
    private void sendPublicMessage(String msg){
        //TODO find client in base
        ExtendedMessage message = new ExtendedMessage();
        message.setMessage(msg);
        message.setCommand("~~");
        message.setExtend(null);
        mServer.broadcast(message);
    }
}