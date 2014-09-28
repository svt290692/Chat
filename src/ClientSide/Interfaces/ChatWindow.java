package ClientSide.Interfaces;

/**
 * Created by svt on 28.09.2014.
 */
public interface ChatWindow {
    /**
     *
     * @param message
     */
    void showMessage(String message);

    /**
     *
     */
    void clearChatArea();

    /**
     *
     */
    void close();
}
