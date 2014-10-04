package ClientSide;

import ClientSide.GUI.MainWindow;
import ClientSide.GUI.StartDialog;
import ClientSide.Interfaces.Gui.Windows.PrivateWindow;
import ClientSide.Interfaces.PrivateMessagesReceiver;
import ClientSide.Interfaces.WindowsHandler;
import Net.Messages.PrivateMessage;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by svt on 03.10.2014.
 */
public class WindowsManager implements WindowsHandler,PrivateMessagesReceiver{

    private ClientSide.Interfaces.Gui.Windows.MainWindow mainWindow = null;
    private Map<String,PrivateWindow> mPrivateWindows = null;

    Client mClient;

    public WindowsManager(Client client) {
        mPrivateWindows = new TreeMap<String, PrivateWindow>();
        mClient = client;
    }

    @Override
    public void showMaiWindow() {
        if(mainWindow == null) {
            mainWindow = new MainWindow();
        }
        mainWindow.show();
    }


    @Override
    public void onMainWindowExit() {
        mClient.getConnectionClient().close();
        for(PrivateWindow w : mPrivateWindows.values()){
            w.getFrame().dispose();
        }
    }

    @Override
    public void onLogOut() {
        mainWindow.getFrame().dispose();
        StartDialog dialog = new StartDialog();
        dialog.setLocation(100,100);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setListener(new StartDialogHandler(dialog));
        mClient.getConnectionClient().close();
    }

    @Override
    public void requestPrivateChat(String login) {
        PrivateWindow win;
        if((win = mPrivateWindows.getOrDefault(login,null))== null){
            win = new ClientSide.GUI.PrivateWindow();
            mPrivateWindows.put(login,win);
        }
        win.show();
    }


    @Override
    public void receiveMessage(String message, String who) {
        requestPrivateChat(who);
        mPrivateWindows.get(who).addNewMessage(message,false,true,true);
    }

    @Override
    public void MessageWasInput(String message, String when) {
        mClient.getConnectionClient().send(new PrivateMessage(message,when));
    }
}
