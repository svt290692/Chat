package ClientSide.Interfaces.Gui;

import ClientSide.Interfaces.ChatListener;

/**
 * Created by svt on 28.09.2014.
 */
public interface ChatMainWindow  extends ChatWindow{
    /**
     *  add new user in userList of this window
     * @param name name of the user
     * @return true if add the user is successfully
     */
    boolean addUser(String name);
    /**
     * remove user from list
     * @param name name of the user
     */
    boolean removeUser(String name);

    /**
     * add listener to this window
     */
    void addListener(ChatListener listener);
}
