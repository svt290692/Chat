package ClientSide;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * Created by svt on 02.10.2014.
 */
@Serializable
public class MyMessage extends AbstractMessage{
    public String string;

//    public MyMessage(String string) {
//        this.string = string;
//    }

    public MyMessage() {
    }

    @Override
    public String toString() {
        return "MyMessage{" +
                "string='" + string + '\'' +
                '}';
    }
}
