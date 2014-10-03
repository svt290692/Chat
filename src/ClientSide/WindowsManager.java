package ClientSide;

import ClientSide.GUI.MainWindow;
import ClientSide.Interfaces.PrivateMessagesReceiver;
import ClientSide.Interfaces.WindowsHandler;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by svt on 03.10.2014.
 */
public class WindowsManager implements WindowsHandler,PrivateMessagesReceiver{

    private MainWindow mainWindow = null;
    private Map<String,JFrame> mPrivateWindows = null;

    public WindowsManager() {
        mPrivateWindows = new TreeMap<String, JFrame>();
    }

    @Override
    public void showMaiWindow() {

    }

    @Override
    public void showPrivateWindow(String login) {

    }

    @Override
    public void onMainWindowExit() {

    }

    @Override
    public void onLogOut() {

    }

    @Override
    public void requestPrivateChat(Object when) {

    }

    @Override
    public void receiveMessage(String message, String where) {

    }

    @Override
    public void sendMessage(String message, String when) {

    }
}
