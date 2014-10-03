package Net.Messages;

import java.lang.reflect.Type;

/**
 * Created by svt on 02.10.2014.
 */
public class ServerMessage {
    private String message;
    private TypeMessage type;

    public enum TypeMessage{
        ALLOW_REGISTRATION,
        DENIED_REGISTRATION;
    }

    public ServerMessage() {
    }

    public ServerMessage(String message) {

        this.message = message;
    }

    public TypeMessage getType() {
        return type;
    }

    public void setType(TypeMessage type) {
        this.type = type;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ServerMessage{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerMessage)) return false;

        ServerMessage that = (ServerMessage) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }
}
