package ClientSide;

import ClientSide.GUI.MainWindow;
import ClientSide.GUI.StartDialog;
import ClientSide.Interfaces.Gui.Windows.PrivateWindow;
import ClientSide.Interfaces.WindowsHandler;
import Net.Messages.PrivateMessage;
import Net.Messages.ServerMessage;
import com.jme3.network.ClientStateListener;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import org.lwjgl.Sys;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by svt on 03.10.2014.
 */
public class WindowsManager implements WindowsHandler,MessageListener<com.jme3.network.Client>,ClientStateListener {

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
            mainWindow.setListener(this);
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
    public void MessageWasInput(String message, String when) {
        mClient.getConnectionClient().send(new PrivateMessage(message,when));
    }

    @Override
    public void messageReceived(com.jme3.network.Client source, Message m) {
        System.out.println("message from server resive =" + m);
        if(m instanceof ServerMessage){
            ServerMessage servMsg = (ServerMessage)m;

            switch(servMsg.getType()){
                case CLIENTS_INFORMATION:
                    List<String> names = (List<String>)servMsg.getDataObject();
//                    names.remove(StartDialogHandler.getPassLogin());
                    mainWindow.setUsersList(names);
                    break;
            }
        }
    }

    @Override
    public void clientConnected(com.jme3.network.Client c) {

    }

    @Override
    public void clientDisconnected(com.jme3.network.Client c, DisconnectInfo info) {

    }
}