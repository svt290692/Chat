package ClientSide.Interfaces.Gui.Windows;

import ClientSide.Interfaces.Gui.Listeners.OkClickedListener;
import ClientSide.Interfaces.Gui.Showable;

import java.awt.event.ActionListener;

/**
 * Created by svt on 02.10.2014.
 */
public interface RegisterWindow{
    String getLogin();
    boolean isPasswordsEquals();

    String getPassword();
    String getConfirmPassword();

    void setListener(OkClickedListener listener);
}
