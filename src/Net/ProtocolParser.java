package Net;

import Net.ExtendedMessage;

/**
 * abstract parser that can parse messages from other side
 * Created by svt on 28.09.2014.
 */
public interface ProtocolParser {

    /**
     * parse message that was resive
     * @param message message
     */
    void parse(ConnectSeance message);
}
