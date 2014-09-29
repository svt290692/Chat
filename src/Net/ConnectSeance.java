package Net;

/**
 * Created by svt on 29.09.2014.
 */
public interface ConnectSeance {
    /**
     *
     * @return extra object
     */
    public Object getExtra();

    /**
     *
     * @return message
     */
    public String getMsg();

    /**
     *
     * @return get command
     */
    public String getCmd();
}
