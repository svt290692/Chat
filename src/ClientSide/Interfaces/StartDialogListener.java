package ClientSide.Interfaces;

import javax.swing.*;

/**
 * this is listener for start dialog window
 * Created by svt on 28.09.2014.
 */
public interface StartDialogListener {
    /**
     *  Button was clicked with this Strings
     */
    void okClicked(String name,String password,String ipAddress,String port);
}
