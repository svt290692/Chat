package ClientSide.Interfaces;

/**
 * Created by svt on 28.09.2014.
 */
public interface ChatMainWindow  extends ChatWindow{
    /**
     *
     * @param name name of user
     * @return
     */
    boolean addUser(String name);
    /**
     *
     * @param name
     */
    boolean removeUser(String name);
}
