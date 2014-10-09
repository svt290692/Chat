package Net;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;


/**
 * Created by svt on 09.10.2014.
 */
@Serializable
public class NetworkClient {
    private String name;
    boolean isOnline;

    public NetworkClient() {

    }

    public NetworkClient(String name) {

        this.name = name;
    }

    public NetworkClient(String name, boolean isOnline) {
        this.name = name;
        this.isOnline = isOnline;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    @Override
    public String toString() {
        return "MessagerClient{" +
                "name='" + name + '\'' +
                ", isOnline=" + isOnline +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkClient that = (NetworkClient) o;

        if (isOnline != that.isOnline) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (isOnline ? 1 : 0);
        return result;
    }
}
