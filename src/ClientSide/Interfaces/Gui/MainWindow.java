package ClientSide.Interfaces.Gui;

import ClientSide.Interfaces.MainWindowListener;

/**
 * Created by svt on 02.10.2014.
 */
public interface MainWindow {
    boolean addUser(Object user);
    boolean addUser(Object user,int insertIndex);

    boolean removeUser(Object user);
    boolean removeUser(int index);

    void setListener(MainWindowListener listener);
}
