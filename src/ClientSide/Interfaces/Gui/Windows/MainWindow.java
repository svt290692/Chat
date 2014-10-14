package ClientSide.Interfaces.Gui.Windows;

import ClientSide.Interfaces.Gui.Listeners.MainWindowListener;
import ClientSide.Interfaces.Gui.Showable;
import Net.NetworkClient;

import javax.swing.*;
import java.util.List;

/**
 * Created by svt on 02.10.2014.
 */
public interface MainWindow extends Showable{
    boolean addUser(Object user);
    boolean addUser(Object user,int insertIndex);

    boolean removeUser(Object user);
    boolean removeUser(int index);

    void setUsersAndStatusesList(List<NetworkClient> users);

    void setListener(MainWindowListener listener);

    JFrame getFrame();
}
