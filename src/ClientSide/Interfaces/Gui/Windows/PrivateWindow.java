package ClientSide.Interfaces.Gui.Windows;

import ClientSide.Interfaces.Gui.Listeners.PrivateWindowListener;

/**
 * Created by svt on 02.10.2014.
 */
public interface PrivateWindow {
    String getUserName();
    void setUserName(String name);

    void addNewMessage(String message, boolean insertDate, boolean insertTime, boolean insertName);

    void clearChatArea();

    void setListener(PrivateWindowListener listener);

}
