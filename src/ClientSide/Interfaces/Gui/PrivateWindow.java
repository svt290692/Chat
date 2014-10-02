package ClientSide.Interfaces.Gui;

/**
 * Created by svt on 02.10.2014.
 */
public interface PrivateWindow {
    String getUserName();
    void setUserName();

    void addNewMessage(String message,boolean insertDate,boolean insertTime,boolean insertName);

}
