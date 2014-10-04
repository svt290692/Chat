package ClientSide;

import ClientSide.GUI.StartDialog;
import ClientSide.Interfaces.Gui.Windows.StartWindow;

/**
 * Created by svt on 03.10.2014.
 */
public class Main {
    public static void main(String args[]){
        StartDialog start = new StartDialog();
        start.setListener(new StartDialogHandler(start));
        start.pack();
        start.setLocation(100,100);
        start.setVisible(true);
    }
}
