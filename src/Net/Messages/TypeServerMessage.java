package Net.Messages;


import java.io.Serializable;

/**
* Created by svt on 07.10.2014.
*/
public enum TypeServerMessage implements Serializable{
    ALLOW_LOGIN,
    DENIED_LOGIN,
    CLIENTS_INFORMATION,
    HISTORY_LOG;

    TypeServerMessage() {
    }
}
