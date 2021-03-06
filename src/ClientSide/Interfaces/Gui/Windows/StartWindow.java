package ClientSide.Interfaces.Gui.Windows;

import ClientSide.Interfaces.Gui.Listeners.StartDialogListener;
import ClientSide.Interfaces.Gui.Showable;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by svt on 28.09.2014.
 */
public interface StartWindow {
    /**
     * set listener to this window
     * @param Listener
     */
    public void setListener(StartDialogListener Listener);

    public String getLoginField();
    public String getPasswordField();

    public JDialog getDialog();
    /**
     * set text to label output
     * @param text
     */
    public void setLabel(String text);
}