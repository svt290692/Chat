package Net.Logging;

import java.io.IOException;
import java.util.*;

public class XMLSingleBranchedLogger extends XMLSingleBranchedExplorer {
    public static String makeLogName(String ... logUsers) {
        StringBuilder buf = new StringBuilder();

        for(String s : new ArrayList<String>(new TreeSet<String>(Arrays.asList(logUsers)))) {
            buf.append(s);
            buf.append('+');
        }

        buf.deleteCharAt(buf.length() - 1);

        return buf.toString();
    }

    @Override
    protected String makeElementName() {
        return "msg";
    }

    public List<String> readLog(String ... logUsers) throws IOException {
        List<Map<String, String>> data = read(makeLogName(logUsers)); // IOException
        ArrayList<String> back = new ArrayList<String>(128);
        StringBuffer buf;

        for(Map<String, String> m : data) {
            buf = new StringBuffer();

            buf.append('[');
            buf.append(m.get("date"));
            buf.append("][\'");
            buf.append(m.get("from"));
            buf.append("\'");
            buf.append("->");
            buf.append("\'");
            buf.append(m.get("to"));
            buf.append("\']:\"");
            buf.append(m.get("text"));
            buf.append("\"");

            back.add(buf.toString());
        }

        back.trimToSize();

        return back;
    }

    public boolean writeLog(String from, String text, String ... logUsers) throws IOException {
        if((null == logUsers) || (null == from) || (null == text)) {
            throw new NullPointerException();
        }

        Map<String, String> buf = new TreeMap<String, String>();

        buf.put("date", makeDate());
        buf.put("from", from);
        buf.put("to", makeLogName(logUsers));
        buf.put("text", text);

        String[] result = new String[((logUsers.length) + 1)];
        result[(logUsers.length)] = from;
        System.arraycopy(logUsers, 0, result, 0, (logUsers.length));

        return write(makeLogName(result), buf);
    }
}