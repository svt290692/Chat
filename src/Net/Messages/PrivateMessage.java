package Net.Messages;

import com.jme3.network.AbstractMessage;

/**
 * Created by svt on 02.10.2014.
 */
public class PrivateMessage extends AbstractMessage {
    private String message;

    public PrivateMessage() {
    }

    public PrivateMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PrivateMessage{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrivateMessage)) return false;

        PrivateMessage that = (PrivateMessage) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }
}
