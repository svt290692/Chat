package ClientSide.Interfaces;

/**
 * this is listener for chatMainWindow
 * Created by svt on 28.09.2014.
 */
public interface ChatListener {
    /**
     * should invoke when user try send new message for all user
     */
    void newMessage(String message);

    /**
     * should invoke when user try to send private message
     */
    void requestToPrivateWindow(String who);
//    void newPrivateMessage(String forWho,String message);
}
