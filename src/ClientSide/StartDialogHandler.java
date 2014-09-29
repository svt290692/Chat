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

    @Override
    public void okClicked(String name, String password, String ipAddress, String port) {

    }
}
