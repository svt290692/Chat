package ClientSide.Interfaces.Gui.Windows;

import ClientSide.Interfaces.Gui.Listeners.PrivateWindowListener;
import ClientSide.Interfaces.Gui.Showable;

import javax.swing.*;

/**
 * Created by svt on 02.10.2014.
 */
public interface PrivateWindow extends Showable {
    String getUserName();
    void setUserName(String name);

    void addNewMessage(String message, boolean insertDate, boolean insertTime, boolean insertName);

    void clearChatArea();

    void setListener(PrivateWindowListener listener);

    JFrame getFrame();

}
