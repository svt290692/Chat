package ClientSide;

import ClientSide.GUI.StartDialog;
import Net.LogPass;
import Net.Messages.*;
import com.jme3.network.serializing.Serializer;

/**
 * Created by svt on 03.10.2014.
 */
public class Main {

    static{
        Serializer.registerClasses(RegistrationMessage.class, PrivateMessage.class, InitializationMessage.class,
                GlobalMessage.class, ConferenceMessage.class,ServerMessage.class, LogPass.class,
                RequestMessage.class);
    }

    public static void main(String args[]){
        StartDialog start = new StartDialog();
        start.setListener(new StartDialogHandler(start));
        start.pack();
        start.setLocation(100,100);
        start.setVisible(true);
    }
}
