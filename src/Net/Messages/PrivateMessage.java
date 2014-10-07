package Net.Messages;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * Created by svt on 02.10.2014.
 */
@Serializable
public class PrivateMessage extends AbstractMessage {
    private String whoSend;

    private String message;

    private String recipient;

    public PrivateMessage() {
    }

    public PrivateMessage(String message) {
        this.message = message;
    }

    public PrivateMessage(String message, String recipient) {
        this.message = message;
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getWhoSend() {
        return whoSend;
    }

    public void setWhoSend(String whoSend) {
        this.whoSend = whoSend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivateMessage that = (PrivateMessage) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (recipient != null ? !recipient.equals(that.recipient) : that.recipient != null) return false;
        if (whoSend != null ? !whoSend.equals(that.whoSend) : that.whoSend != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = whoSend != null ? whoSend.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (recipient != null ? recipient.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PrivateMessage{" +
                "whoSend='" + whoSend + '\'' +
                ", message='" + message + '\'' +
                ", recipient='" + recipient + '\'' +
                '}';
    }
}
