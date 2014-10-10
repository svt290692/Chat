package ClientSide;

import ClientSide.GUI.RegisterDialog;
import ClientSide.GUI.SettingsDialog;
import ClientSide.Interfaces.Gui.Listeners.OkClickedListener;
import ClientSide.Interfaces.Gui.Listeners.StartDialogListener;
import ClientSide.Interfaces.Gui.Windows.StartWindow;
import Net.LogPass;
import Net.Messages.InitializationMessage;
import Net.Messages.RequestMessage;
import Net.Messages.ServerMessage;
import Net.Messages.TypeMessage;
import com.jme3.network.ClientStateListener;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by svt on 29.09.2014.
 */
public class StartDialogHandler implements StartDialogListener {
    private final StartWindow mDialog;
    private final Client client;

    private static String PassLogin = null;

    public StartDialogHandler(StartWindow mDialog) {
        this.mDialog = mDialog;
        client = new Client();
    }

    public static String getPassLogin() {
        return PassLogin;
    }

    /**
     * Button was clicked with this Strings
     */
    @Override
    public void onOK() {

        if(mDialog.getLoginField() == "" || mDialog.getPasswordField() == ""){
            JOptionPane.showMessageDialog(mDialog.getDialog(),"Fields can not be empty","Fields is empty",JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (tryConnection() == false) {
            JOptionPane.showMessageDialog(null, "error connection to server info : " + client.getLastConnectError());
        }


        final AtomicReference<Boolean> isReady = new AtomicReference<Boolean>();
        final AtomicReference<Boolean> isCanEnter = new AtomicReference<Boolean>();
        isReady.set(false);
        isCanEnter.set(false);

        final ClientStateListener clientStateListener = new ClientStateListener() {
            @Override
            public void clientConnected(com.jme3.network.Client c) {
                InitializationMessage message = new InitializationMessage();
                message.setLogPass(new LogPass(mDialog.getLoginField(), mDialog.getPasswordField()));
                client.getConnectionClient().send(message);
            }

            @Override
            public void clientDisconnected(com.jme3.network.Client c, DisconnectInfo info) {

            }
        };
        final MessageListener<com.jme3.network.Client> clientMessageListener = new MessageListener<com.jme3.network.Client>() {
            @Override
            public void messageReceived(com.jme3.network.Client source, Message m) {
                if(m instanceof ServerMessage) {
                    ServerMessage message = (ServerMessage) m;
                    if(message.getType().equals(TypeMessage.ALLOW_LOGIN)) {
                        isCanEnter.set(true);
                        isReady.set(true);
                    }
                    else if(message.getType().equals(TypeMessage.DENIED_LOGIN)) {
                        isCanEnter.set(false);
                        isReady.set(true);
                        JOptionPane.showMessageDialog(mDialog.getDialog(),"you can't enter because: " + message.getMessage(),
                                "Error log in",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };

        client.addStateListener(clientStateListener);
        client.addMessageListener(clientMessageListener);

        client.start();
        while(isReady.get() == false){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(isCanEnter.get() == false) return;

        PassLogin = mDialog.getLoginField();

        client.getConnectionClient().removeClientStateListener(clientStateListener);
        client.getConnectionClient().removeMessageListener(clientMessageListener);

        WindowsManager winMan = new WindowsManager(client);
        winMan.showMaiWindow();
        client.addMessageListener(winMan);
        RequestMessage request = new RequestMessage("get me list of your clients please",
                RequestMessage.RequestType.CLIENT_LIST);
        client.getConnectionClient().send(request);
        mDialog.getDialog().dispose();
    }

    @Override
    public void settingsClicked() {
        final SettingsDialog settings = new SettingsDialog();
        settings.pack();
        settings.setLocation(100, 100);
        settings.setListener(new OkClickedListener() {
            @Override
            public void onOK() {

                if(settings.getIP() == "" || settings.getPort() == -1){
                    JOptionPane.showMessageDialog(mDialog.getDialog(),"Fields can not be empty","Fields is empty",JOptionPane.WARNING_MESSAGE);
                    return;
                }

                else if(settings.getPort() < 1024 || settings.getPort() > 65536){
                    JOptionPane.showMessageDialog(mDialog.getDialog(),"Port incorrect please enter correct port","Port incorrect",JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String ip = settings.getIP();
                int port = settings.getPort();
                //TODO bug
                if(ip == "" || port == 0)
                    JOptionPane.showMessageDialog(settings,"Please enter IP an port ");
                else {
                    GlobalConfig.getInstance().setIP(settings.getIP());
                    GlobalConfig.getInstance().setPort(settings.getPort());
                    settings.dispose();
                }
            }
        });
        settings.setVisible(true);
    }

    @Override
    public void registrationClicked() {
        final RegisterDialog reg = new RegisterDialog();
        reg.pack();
        reg.setLocation(100, 100);
        reg.setListener(new OkClickedListener() {
            @Override
            public void onOK() {

                if(reg.getLogin().isEmpty()){
                    JOptionPane.showMessageDialog(mDialog.getDialog(),"Fields can not be empty","Error empty field",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                else if(reg.getPassword().isEmpty() || reg.getConfirmPassword().isEmpty()){
                    JOptionPane.showMessageDialog(mDialog.getDialog(),"Password can not be empty","Error empty field",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                else if(!reg.isPasswordsEquals()){
                    JOptionPane.showMessageDialog(mDialog.getDialog(),"Password can not be not equals","Passwords not equals",JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String endMessage;
                try {
                    endMessage = Registrar.getInstance().registerClient(new LogPass(reg.getLogin(), reg.getPassword()));
                    JOptionPane.showMessageDialog(reg, endMessage);
                    reg.dispose();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(reg, "CanNot connect to Server for registration check settings to slove this problem");
                }
            }
        });
        reg.setVisible(true);
    }

    private boolean tryConnection() {
        return client.tryConnection();
    }
}