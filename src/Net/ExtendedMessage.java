package Net;

import com.jme3.network.AbstractMessage;

import java.io.Serializable;

/**
 * Created by svt on 27.09.2014.
 */
@com.jme3.network.serializing.Serializable
public class ExtendedMessage extends AbstractMessage implements Serializable,ConnectSeance{

    private String command;

    private String message;

    private Object extend;

    public ExtendedMessage() {
    }

    public String getCmd() {
        return command;
    }

    public String getMsg() {
        return message;
    }

    public Object getExtra() {
        return extend;
    }


    public void setCommand(String command) {
        this.command = command;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExtendedMessage)) return false;

        ExtendedMessage that = (ExtendedMessage) o;

        if (command != null ? !command.equals(that.command) : that.command != null) return false;
        if (extend != null ? !extend.equals(that.extend) : that.extend != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = command != null ? command.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (extend != null ? extend.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Net.ExtendedMessage{" +
                "command='" + command + '\'' +
                ", message='" + message + '\'' +
                ", extend=" + extend +
                '}';
    }
}
