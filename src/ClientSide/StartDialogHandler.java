package ClientSide;

import ClientSide.Interfaces.Gui.StartWindow;
import ClientSide.Interfaces.OkCliedListener;

/**
 * Created by svt on 29.09.2014.
 */
public class StartDialogHandler implements OkCliedListener {
    private final StartWindow mDialog;

    public StartDialogHandler(StartWindow mDialog) {
        this.mDialog = mDialog;
    }
    /**
     * Button was clicked with this Strings
     *
     */
    @Override
    public void onOK() {

    }
}
