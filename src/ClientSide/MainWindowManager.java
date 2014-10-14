package ClientSide;

import ClientSide.Interfaces.Gui.Listeners.MainWindowListener;
import ClientSide.Interfaces.Gui.Windows.MainWindow;
import Net.Messages.PrivateMessage;
import Net.Messages.ServerMessage;
import com.jme3.network.*;
import com.jme3.network.Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by svt on 03.10.2014.
 */
public class MainWindowManager implements MessageListener<com.jme3.network.Client>,ClientStateListener,MainWindowListener {

    private final Client mClient;
    private final MainWindow mainWindow;

    public MainWindowManager(Client mClient, MainWindow mainWindow) {
        this.mClient = mClient;
        this.mainWindow = mainWindow;
    }

    @Override
    public void clientConnected(Client c) {

    }

    @Override
    public void clientDisconnected(Client c, DisconnectInfo info) {

    }

    @Override
    public void messageReceived(Client source, Message m) {
//        System.out.println("message from server resive =" + m);
//        if(m instanceof ServerMessage){
//            ServerMessage servMsg = (ServerMessage)m;
//
//            switch(servMsg.getType()){
//                case CLIENTS_INFORMATION:
//                    List<Net.NetworkClient> names = (List<Net.NetworkClient>)servMsg.getDataObject();
//                    delMyLoginFrom(names);
//                    mainWindow.setUsersAndStatusesList(names);
//                    break;
//                case HISTORY_LOG:
//                    showHistory((List<String>)servMsg.getDataObject(),
//                            servMsg.getMessage().split(" "));
//                    break;
//                default:
//                    break;
//            }
//        }
//        else if( m instanceof PrivateMessage){
//            privateMessageReceived((PrivateMessage)m);
//        }
    }

    @Override
    public void sendMessageTo(String message, Collection<String> names) {

        List<String> realNames = new ArrayList<String>();
        for(String n : names){
            realNames.add(n.substring(0,n.length()-6));
        }
        for(String s : realNames){
            PrivateMessage m = new PrivateMessage(message,GlobalConfig.getInstance().getSeanceName(),s);
            mClient.send(m);
        }
    }
//
//    private ClientSide.Interfaces.Gui.Windows.MainWindow mainWindow = null;
//    private Map<String,PrivateWindow> mPrivateWindows = null;
//
//    Client mClient;
//
//
//    public WindowsManager(Client client) {
//        mPrivateWindows = new TreeMap<String, PrivateWindow>();
//        mClient = client;
//    }
//
//    @Override
//    public void showMaiWindow() {
//        if(mainWindow == null) {
//            mainWindow = new MainWindow();
//            mainWindow.setListener(this);
//        }
//
//        mainWindow.show();
//
//    }
//
//    @Override
//    public void onMainWindowExit() {
//        mClient.getConnectionClient().close();
//        for(PrivateWindow w : mPrivateWindows.values()){
//            w.getFrame().dispose();
//        }
//    }
//
//    @Override
//    public void onLogOut() {
//        mainWindow.getFrame().dispose();
//        mClient.getConnectionClient().close();
//        System.gc();
//        Main.main(new String[]{});
//    }
//
//    @Override
//    public void requestPrivateChat(String login) {
//        PrivateWindow win = mPrivateWindows.get(login);
//        if(win== null){
//            win = new ClientSide.GUI.PrivateWindow();
//            win.setUserName(login);
//            mPrivateWindows.put(login, win);
//            win.setListener(this);
//        }
//        win.show();
//    }
//
//    @Override
//    public void MessageWasInput(String message, String when) {
//        mClient.getConnectionClient().send(new PrivateMessage(message,StartDialogHandler.getPassLogin(),when));
//    }
//
//    @Override
//    public void historyRequest(String... names) {
//        RequestMessage message = new RequestMessage();
//        message.setRequestType(RequestMessage.RequestType.HISTORY);
//
//        StringBuffer sf = new StringBuffer();
//        for(String s : names){
//            sf.append(s);
//            sf.append(' ');
//        }
//        sf.deleteCharAt(sf.length()-1);
//
//        message.setMessage(sf.toString());
//
//        mClient.getConnectionClient().send(message);
//    }
//
//    @Override
//    public void messageReceived(com.jme3.network.Client source, Message m) {
//        System.out.println("message from server resive =" + m);
//        if(m instanceof ServerMessage){
//            ServerMessage servMsg = (ServerMessage)m;
//
//            switch(servMsg.getType()){
//                case CLIENTS_INFORMATION:
//                    List<Net.NetworkClient> names = (List<Net.NetworkClient>)servMsg.getDataObject();
//                    delMyLoginFrom(names);
//                    mainWindow.setUsersAndStatusesList(names);
//                    break;
//                case HISTORY_LOG:
//                    showHistory((List<String>)servMsg.getDataObject(),
//                            servMsg.getMessage().split(" "));
//                    break;
//                default:
//                    break;
//            }
//        }
//        else if( m instanceof PrivateMessage){
//            privateMessageReceived((PrivateMessage)m);
//        }
//    }
//
    private void delMyLoginFrom(List<Net.NetworkClient> list){
        for(Net.NetworkClient c : list){
            if(c.getName().equals(GlobalConfig.getInstance().getSeanceName())){
                list.remove(c);
                break;
            }
        }
    }
//
//    private void showHistory(List<String> history, String ... usersWindow){
//
//        for(String user : usersWindow){
//            String a = StartDialogHandler.getPassLogin();
//            if(!user.equals(StartDialogHandler.getPassLogin())){
//                requestPrivateChat(user);
//                for(String msg : history)
//                mPrivateWindows.get(user).addNewMessage(msg,false,false,"", Color.LIGHT_GRAY);
//            }
//        }
//    }
//
//    private void privateMessageReceived(final PrivateMessage message){
//        requestPrivateChat(message.getWhoSend());
//        PrivateWindow win = mPrivateWindows.get(message.getWhoSend());
//        if(null == win)
//            throw new IllegalStateException("Window requested is null");
//        win.addNewMessage(message.getMessage(),false,true,message.getWhoSend(), new Color(151,255,255));
//    }
//
//    @Override
//    public void clientConnected(com.jme3.network.Client c) {
//
//    }
//
//    @Override
//    public void clientDisconnected(com.jme3.network.Client c, DisconnectInfo info) {
//
//    }
}