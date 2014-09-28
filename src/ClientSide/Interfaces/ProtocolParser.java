package ClientSide.Interfaces;

import ClientSide.ExtendedMessage;

/**
 * Created by svt on 28.09.2014.
 */
public interface ProtocolParser {

    void parse(ExtendedMessage message);
}
