package Net.Messages;

import Net.LogPass;
import com.jme3.network.AbstractMessage;

/**
 * Created by svt on 02.10.2014.
 */
public class RegistrationMessage extends AbstractMessage {
    private LogPass logPass;

    public RegistrationMessage() {
    }

    public RegistrationMessage(LogPass logPass) {
        this.logPass = logPass;
    }

    public LogPass getLogPass() {
        return logPass;
    }

    public void setLogPass(LogPass logPass) {
        this.logPass = logPass;
    }

    @Override
    public String toString() {
        return "RegistrationMessage{" +
                "logPass=" + logPass +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationMessage)) return false;

        RegistrationMessage that = (RegistrationMessage) o;

        if (logPass != null ? !logPass.equals(that.logPass) : that.logPass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return logPass != null ? logPass.hashCode() : 0;
    }
}
