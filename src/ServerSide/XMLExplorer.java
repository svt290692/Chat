package ServerSide;

import org.jdom2.Document;
import java.io.IOException;

public interface XMLExplorer {
    public boolean write(String file, String[] keys, String[] values) throws IOException;
    public Document read(String file) throws IOException;
}
