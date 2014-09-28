package ClientSide.Interfaces.Gui;

/**
 * Created by svt on 28.09.2014.
 */
public interface ChatWindow {
    /**
     * show message in Chat
     * @param message
     */
    void showMessage(String message);

    /**
     * clear chat from all messages
     */
    void clearChatArea();

    /**
     * close current window
     */
    void close();
}
