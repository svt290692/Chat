package ClientSide.Interfaces.Gui;

import ClientSide.Interfaces.StartDialogListener;

/**
 * Created by svt on 28.09.2014.
 */
public interface StartDialog {
    /**
     * set listener to this window
     * @param Listener
     */
    public void setListener(StartDialogListener Listener);

    public String getNameField();
    public String getPasswordField();
    public String getIPField();
    public String getPortField();
    public void setLabel(String text);
}
