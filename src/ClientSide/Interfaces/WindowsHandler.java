package ClientSide.Interfaces;

import ClientSide.Interfaces.Gui.Listeners.MainWindowListener;
import ClientSide.Interfaces.Gui.Listeners.PrivateWindowListener;

/**
 * Created by svt on 03.10.2014.
 */
public interface WindowsHandler extends PrivateWindowListener,MainWindowListener{
    void showMaiWindow();
}