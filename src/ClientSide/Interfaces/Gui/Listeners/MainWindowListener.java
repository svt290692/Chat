package ClientSide.Interfaces.Gui.Listeners;

import ClientSide.Interfaces.Gui.Windows.PrivateWindow;

/**
 * Created by svt on 02.10.2014.
 */
public interface MainWindowListener {
    void onMainWindowExit();
    void onLogOut();
    void requestPrivateChat(String when);
}
