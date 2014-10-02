package Net.Messages;

import com.jme3.network.AbstractMessage;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by svt on 02.10.2014.
 */
public class ConferenceMessage extends AbstractMessage{
    private String message;
    private ArrayList<String> recipients;

    public ConferenceMessage(String message, ArrayList<String> recipients) {
        this.message = message;
        this.recipients = recipients;
    }

    public ConferenceMessage(String message) {

        this.message = message;
    }

    public ConferenceMessage() {

    }
    public void addRecipient(String recipient){
        recipients.add(recipient);
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(ArrayList<String> recipients) {
        this.recipients = recipients;
    }

    @Override
    public String toString() {
        return "ConferenceMessage{" +
                "message='" + message + '\'' +
                ", recipients=" + recipients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConferenceMessage)) return false;

        ConferenceMessage that = (ConferenceMessage) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (recipients != null ? !recipients.equals(that.recipients) : that.recipients != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (recipients != null ? recipients.hashCode() : 0);
        return result;
    }
}
