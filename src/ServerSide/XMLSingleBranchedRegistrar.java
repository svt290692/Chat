package ServerSide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class XMLSingleBranchedRegistrar extends XMLSingleBranchedExplorer {
    @Override
    protected String makeElementName() {
        return "account";
    }

    public boolean isAccountExists(String file, String login, String password) throws IOException {
        if((null == file) || (null == login) || (null == password)) {
            throw new NullPointerException();
        }

        List<Map<String, String>> list = read(file); // IOException
        boolean back = false;

        for(Map<String, String> m : list) {
            if((m.get("login").equals(login)) && (m.get("password").equals(password))) {
                back = true;

                break;
            }
        }

        return back;
    }

    public List<String> getExistsLogins(String file) throws IOException {
        if(null == file) {
            throw new NullPointerException();
        }

        ArrayList<String> back = new ArrayList<String>(128);
        List<Map<String, String>> list = read(file); // IOException

        for(Map<String, String> m : list) {
            back.add(m.get("login"));
        }

        back.trimToSize();

        return back;
    }

    public boolean isAccountExists(String file, String login) throws IOException {
        if((null == file) || (null == login)) {
            throw new NullPointerException();
        }

        List<Map<String, String>> list = read(file); // IOException
        boolean back = false;

        for(Map<String, String> m : list) {
            if((m.get("login").equals(login))) {
                back = true;

                break;
            }
        }

        return back;
    }

    public boolean writeAccount(String file, String login, String password) throws IOException {
        if((null == file) || (null == login) || (null == password)) {
            throw new NullPointerException();
        }

        Map<String, String> buf = new TreeMap<String, String>();

        buf.put("date", makeDate());
        buf.put("login", login);
        buf.put("password", password);

        return write(file, buf); // IOException
    }
}
