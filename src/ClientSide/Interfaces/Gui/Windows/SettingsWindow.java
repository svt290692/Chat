package ClientSide.Interfaces.Gui.Windows;

import java.awt.event.ActionListener;

/**
 * Created by svt on 02.10.2014.
 */
public interface SettingsWindow {
    String getIP();
    String getPort();
    void setTopLabel(String text);

    void setListener(ActionListener OkListener);
}
