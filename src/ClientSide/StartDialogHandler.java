package ClientSide;

import ClientSide.GUI.RegisterDialog;
import ClientSide.GUI.SettingsDialog;
import ClientSide.Interfaces.Gui.Listeners.OkClickedListener;
import ClientSide.Interfaces.Gui.Listeners.StartDialogListener;
import ClientSide.Interfaces.Gui.Windows.StartWindow;
import Net.LogPass;
import com.jme3.network.ClientStateListener;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by svt on 29.09.2014.
 */
public class StartDialogHandler implements StartDialogListener {
    private final StartWindow mDialog;
    private final Client client;

    public StartDialogHandler(StartWindow mDialog) {
        this.mDialog = mDialog;
        client = new Client(GlobalConfig.getInstance().getIP(), GlobalConfig.getInstance().getPort());
    }

    /**
     * Button was clicked with this Strings
     */
    @Override
    public void onOK() {
        if (tryConnection() == true) {
            client.addStateListener(new ClientStateListener() {
                @Override
                public void clientConnected(com.jme3.network.Client c) {
                    client.addMessageListener(new MessagesClientHandler());

                }

                @Override
                public void clientDisconnected(com.jme3.network.Client c, DisconnectInfo info) {

                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "error connection to server info : " + client.getLastConnectError());
        }
    }

    @Override
    public void settingsClicked() {
        final SettingsDialog settings = new SettingsDialog();
        settings.pack();
        settings.setLocation(100, 100);
        settings.setListener(new OkClickedListener() {
            @Override
            public void onOK() {
                String ip = settings.getIP();
                int port = settings.getPort();

                if(ip == "" || port == 0)
                    JOptionPane.showMessageDialog(settings,"Please enter IP an port ");
                else {
                    GlobalConfig.getInstance().setIP(settings.getIP());
                    GlobalConfig.getInstance().setPort(settings.getPort());
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
        reg.setVisible(true);
        reg.setListener(new OkClickedListener() {
            @Override
            public void onOK() {
                String endMessage;
                try {
                    endMessage = Registrar.getInstance().registerClient(new LogPass(reg.getLogin(), reg.getPassword()));
                    JOptionPane.showMessageDialog(reg, endMessage);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(reg, "CanNot connect to Server for registration check settings to slove this problem");
                }
            }
        });
    }

    private boolean tryConnection() {
        return client.tryConnection();
    }
}