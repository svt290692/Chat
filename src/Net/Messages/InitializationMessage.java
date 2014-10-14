package Net.Messages;

import Net.LogPass;
import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * Created by svt on 02.10.2014.
 */
@Serializable
public class InitializationMessage extends AbstractMessage {
    private LogPass logPass;

    public InitializationMessage(LogPass logPass) {
        this.logPass = logPass;
    }

    public InitializationMessage() {
    }

    public LogPass getLogPass() {
        return logPass;
    }

    public void setLogPass(LogPass logPass) {
        this.logPass = logPass;
    }

    @Override
    public String toString() {
        return "InitializationMessage{" +
                "logPass=" + logPass +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InitializationMessage)) return false;

        InitializationMessage that = (InitializationMessage) o;

        if (logPass != null ? !logPass.equals(that.logPass) : that.logPass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return logPass != null ? logPass.hashCode() : 0;
    }
}
