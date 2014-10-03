package Net.Messages;

import com.jme3.network.AbstractMessage;

/**
 * Created by svt on 02.10.2014.
 */
public class PrivateMessage extends AbstractMessage {
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

    @Override
    public String toString() {
        return "PrivateMessage{" +
                "message='" + message + '\'' +
                ", recipient='" + recipient + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrivateMessage)) return false;

        PrivateMessage that = (PrivateMessage) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (recipient != null ? !recipient.equals(that.recipient) : that.recipient != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (recipient != null ? recipient.hashCode() : 0);
        return result;
    }
}
