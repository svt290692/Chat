package ClientSide;

import ClientSide.Interfaces.PrivateMessagesReceiver;
import com.jme3.network.*;
import com.jme3.network.Client;

/**
 * Created by svt on 03.10.2014.
 */
public class MessagesClientHandler implements MessageListener<Client>{
    PrivateMessagesReceiver mReceiver;

    public MessagesClientHandler() {
    }

    public MessagesClientHandler(PrivateMessagesReceiver mReceiver) {
        this.mReceiver = mReceiver;
    }

    public PrivateMessagesReceiver getReceiver() {
        return mReceiver;
    }

    public void setReceiver(PrivateMessagesReceiver mReceiver) {
        this.mReceiver = mReceiver;
    }

    @Override
    public void messageReceived(Client source, Message m) {

    }
}
