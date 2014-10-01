package ClientSide.Interfaces;

/**
 * Created by svt on 29.09.2014.
 */
public interface ChatClient {
    /**
     * send one public message to server
     * @param mgs message to send
     */
    public void sendPublicMessage(String mgs);

    /**
     * send private messsage to server to deliver it other client
     * @param msg message to send
     * @param when name of user to deliver
     */
    public void sendPrivateMessage(String msg,String when);

    /**
     * shutdown current client
     */
    public void closeSocket();

    /**
     * try connect to server
     * @param IPAddress
     * @param port
     * @return true if connect was successfully false if connect fail
     */
    public boolean tryConnect(String IPAddress,int port);
}
