package ClientSide.Interfaces.Gui.Windows;

import java.awt.event.ActionListener;

/**
 * Created by svt on 28.09.2014.
 */
public interface StartWindow {
    /**
     * set listener to this window
     * @param Listener
     */
    public void setListener(ActionListener Listener);

    public String getLoginField();
    public String getPasswordField();


    /**
     * set text to label output
     * @param text
     */
    public void setLabel(String text);


}