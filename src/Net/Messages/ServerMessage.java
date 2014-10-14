package Net.Messages;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * Created by svt on 02.10.2014.
 */
@Serializable
public class ServerMessage extends AbstractMessage{
    private String message;
    private TypeServerMessage type;

    private Object DataObject;
    public ServerMessage() {
    }

    public ServerMessage(String message, TypeServerMessage type) {
        this.message = message;
        this.type = type;
    }

    public ServerMessage(String message, TypeServerMessage type, Object dataObject) {
        this.message = message;
        this.type = type;
        DataObject = dataObject;
    }

    public ServerMessage(String message) {

        this.message = message;
    }

    public Object getDataObject() {
        return DataObject;
    }

    public void setDataObject(Object dataObject) {
        DataObject = dataObject;
    }

    public TypeServerMessage getType() {
        return type;
    }

    public void setType(TypeServerMessage type) {
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
                ", type=" + type +
                ", DataObject=" + DataObject +
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
