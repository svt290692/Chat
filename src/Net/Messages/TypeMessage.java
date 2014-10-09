package Net.Messages;


import java.io.Serializable;

/**
* Created by svt on 07.10.2014.
*/
public enum TypeMessage implements Serializable{
    ALLOW_REGISTRATION,
    DENIED_REGISTRATION,
    ALLOW_LOGIN,
    DENIED_LOGIN,
    CLIENTS_INFORMATION,
    HISTORY_LOG;

    TypeMessage() {
    }
}
