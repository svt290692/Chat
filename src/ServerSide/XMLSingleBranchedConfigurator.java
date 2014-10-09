package ServerSide;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class XMLSingleBranchedConfigurator extends XMLSingleBranchedExplorer {
    @Override
    protected String makeElementName() {
        return "set";
    }

    public String readConfig(String file, String field) throws IOException {
        if((null == field) || (null == file)) {
            throw new NullPointerException();
        }

        List<Map<String, String>> list = read(file); // IOException
        String back = null;

        for(Map<String, String> m : list) {
            back = m.get(field);

            if(null != back) {
                break;
            }
        }

        return back;
    }

    public boolean writeConfig(String file, String field, String value) throws IOException {
        if((null == field) || (null == value) || (null == file)) {
            throw new NullPointerException();
        }

        Map<String, String> buf = new TreeMap<String, String>();
        buf.put(field, value);

        return write(file, buf); // IOException
    }
}


