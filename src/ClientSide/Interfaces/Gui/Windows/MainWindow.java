package ClientSide.Interfaces.Gui.Windows;

import ClientSide.Interfaces.Gui.Listeners.MainWindowListener;
import Net.NetworkClient;

import javax.swing.*;
import java.util.List;

/**
 * Created by svt on 11.10.2014.
 */
public interface MainWindow {
    void outsideMessage(String message, String nameFrom, boolean insertDate, boolean insertTime);

    void setListener(MainWindowListener mListener);

    void addUser(String name, boolean isOnline);

    void changeUserStatus(String name, boolean isOnline);

    void setUsersAndStatusesList(List<NetworkClient> users);

    JFrame getFrame();
}
