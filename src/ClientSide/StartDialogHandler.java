package ClientSide;

import ClientSide.Interfaces.Gui.StartDialog;
import ClientSide.Interfaces.StartDialogListener;

import javax.swing.*;

/**
 * Created by svt on 29.09.2014.
 */
public class StartDialogHandler implements StartDialogListener {
    private final StartDialog mDialog;

    public StartDialogHandler(StartDialog mDialog) {
        this.mDialog = mDialog;
    }

    /**
     * check input data from user input and create Client
     *
     * @param name the name of client that was input from user
     * @param password the password of client that was input from user
     * @param ipAddress the IP address to connetion with server that was input from user
     * @param port the port that was input from user(can be empty)
     */
    @Override
    public void okClicked(String name, String password, String ipAddress, String port) {
        //TODO check name and password, if they all is valid then create client or print t odialog error message
        //TODO if field Port is nullString then we try to connect to server with this IP and some standart ports
    }
}
