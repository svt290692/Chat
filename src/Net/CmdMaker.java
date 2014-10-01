package Net;

/**
 * Created by svt on 29.09.2014.
 */
public class CmdMaker {
    private CmdMaker() { // final && abstract - class
        throw new AssertionError();
    }

    public static String makeServerCmd(String ... commands) throws NullPointerException {
        StringBuffer sbuf = new StringBuffer();

        if(null != commands) {
            sbuf.append('!');

            for (String x : commands) {
                if (null != x) {
                    sbuf.append(x);
                    sbuf.append(';');
                } else {
                    throw new NullPointerException ("makeServerCmd: arg == null");
                }
            }

            sbuf.append('!');
        } else {
            throw new NullPointerException ("makeServerCmd: arg == null");
        }

        return sbuf.toString();
    }

    public static String makeRegistrationCmd(String name, String pass) throws NullPointerException {
        StringBuffer sbuf = new StringBuffer();

        if((null != name) && (null != pass)) {
            sbuf.append('@');
            sbuf.append(name);
            sbuf.append(':');
            sbuf.append(pass);
            sbuf.append('@');

        } else {
            throw new NullPointerException ("makeRegistrationCmd: arg == null");
        }

        return sbuf.toString();
    }

    public static String makePrivateMsgCmd(String receiver) throws NullPointerException {
        StringBuffer sbuf = new StringBuffer();

        if(null != receiver) {
            sbuf.append('?');
            sbuf.append(receiver);
            sbuf.append('?');
        } else {
            throw new NullPointerException ("makePrivateMsgCmd: arg == null");
        }

        return sbuf.toString();
    }

    public static String makeMainChatMsgCmd() {
        return new String("~~");
    }
}