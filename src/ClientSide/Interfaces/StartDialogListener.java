package ClientSide.Interfaces;

import javax.swing.*;

/**
 * Created by svt on 28.09.2014.
 */
public interface StartDialogListener {
    void okClicked(JDialog dialog,String name,String password,String ipAddress,String port);
}
