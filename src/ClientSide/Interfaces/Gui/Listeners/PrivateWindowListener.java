package ClientSide.Interfaces.Gui.Listeners;

/**
 * Created by svt on 02.10.2014.
 */
public interface PrivateWindowListener {
    void MessageWasInput(String message, String when);

    /**
     *
     * @param names names with history request all except user name
     */
    void historyRequest(String ... names);
}
