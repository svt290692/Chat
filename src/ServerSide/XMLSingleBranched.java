package ServerSide;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface XMLSingleBranched {
    public boolean write(String file, Map<String, String> keysAndValues) throws IOException;
    List<Map<String, String>> read(String file) throws IOException;
}
