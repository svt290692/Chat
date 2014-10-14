package ClientSide.Interfaces.Gui.Windows;

import ClientSide.Interfaces.Gui.Listeners.OkClickedListener;
import ClientSide.Interfaces.Gui.Showable;

import java.awt.event.ActionListener;

/**
 * Created by svt on 02.10.2014.
 */
public interface SettingsWindow {
    String getIP();
    int getPort();

    void setIP(String ip);
    void setPort(String port);

    void setTopLabel(String text);
    void setListener(OkClickedListener OkListener);
}
