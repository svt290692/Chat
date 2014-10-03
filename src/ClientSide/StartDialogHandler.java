package ClientSide;

import ClientSide.Interfaces.Gui.Listeners.OkClickedListener;
import ClientSide.Interfaces.Gui.Listeners.StartDialogListener;
import ClientSide.Interfaces.Gui.Windows.StartWindow;

/**
 * Created by svt on 29.09.2014.
 */
public class StartDialogHandler implements StartDialogListener {
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

    @Override
    public void settingsClicked() {

    }

    @Override
    public void registrationClicked() {

    }
    private boolean tryConnection(){

        return false;
    }
}