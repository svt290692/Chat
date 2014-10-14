package Net.Messages;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * Created by svt on 07.10.2014.
 */
@Serializable
public class RequestMessage extends AbstractMessage{
    private String message;

    private RequestType requestType;

    public static enum RequestType implements java.io.Serializable{
        CLIENT_LIST,
        HISTORY;

        RequestType() {
        }
    }

    public RequestMessage() {
    }

    public RequestMessage(String message) {
        this.message = message;
    }

    public RequestMessage(String message, RequestType requestType) {
        this.message = message;
        this.requestType = requestType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "message='" + message + '\'' +
                ", requestType=" + requestType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestMessage that = (RequestMessage) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (requestType != that.requestType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (requestType != null ? requestType.hashCode() : 0);
        return result;
    }
}
