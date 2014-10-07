package ServerSide;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLLogger implements XMLExplorer {
    public Document read(String file) throws IOException {
        FileReader fr = null;
        Document doc = null;

        try {
            fr = new FileReader(makeFile(file)); // IOException
            SAXBuilder parser = new SAXBuilder();

            try {
                doc = parser.build(fr); // IOException
            } catch (JDOMException e) {
                throw new IOException(e.getMessage());
            }
        } finally {
            if (null != fr) {
                fr.close(); // IOException
                // fr = null;
            }
        }
        return doc;
    }

    public List<String> readLog(String file) throws IOException {
        Document doc = read(file); // IOException
        ArrayList<String> back = new ArrayList<String>(128);
        StringBuffer buf = null;

        for(Element e : doc.getRootElement().getChildren()) {
            buf = new StringBuffer();

            buf.append('[');
            buf.append(e.getAttributeValue("date"));
            buf.append("][\'");
            buf.append(e.getAttributeValue("from"));
            buf.append("\'");
            buf.append("->");
            buf.append("\'");
            buf.append(e.getAttributeValue("to"));
            buf.append("\']:\"");
            buf.append(e.getAttributeValue("text"));
            buf.append("\"");

            back.add(buf.toString());
        }

        back.trimToSize();

        return back;
    }

    public boolean writeLog(String file, String from, String to, String text) throws IOException {
        String[] keys = {"date", "from", "to", "text"};
        String[] values = {makeDate(), from, to, text};

        return write(file, keys, values); // IOException
    }

    public boolean write(String file, String[] keys, String[] values) throws IOException {
        Document doc = read(file); // IOException
        FileWriter fw = null;

        try {
            fw = new FileWriter(makeFile(file)); // IOException
            Element rootElement = doc.getRootElement(); // log
            rootElement.addContent(makeElement(new String("msg"), keys, values));
            makeOutPutter().output(doc, fw);
        } finally {
            if (null != fw) {
                fw.close();
                // fw = null;
            }
        }

        return true;
    }

    protected String makeDate() {
        return
            new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").
                format(new Date(System.currentTimeMillis())).toString();
    }

    protected Element makeRootElement() {
        return new Element("log");
    }

    protected final Document makeDocument() { // no need to override
        return new Document(makeRootElement());
    }

    protected XMLOutputter makeOutPutter() {
        XMLOutputter out = new XMLOutputter();
        out.setFormat(Format.getPrettyFormat());

        return out;
    }

    private Element makeElement(String elementName, String[] keys, String[] values) {
        int len = (keys.length) < (values.length) ? (keys.length) : (values.length);
        Element newElement = new Element(elementName);

        for(int i = 0; i < len; ++i) {
            newElement.setAttribute(keys[i], values[i]);
        }

        return newElement;
    }

    private void writeRootElement(FileWriter fw) throws IOException {
        XMLOutputter xml = makeOutPutter();
        xml.output(makeDocument(), fw); // IOException
    }

    private File makeFile(String file) throws IOException {
        File f = new File(file);

        if (false == f.exists()) {
            f.createNewFile(); // IOException

            FileWriter fw = null;

            try {
                fw = new FileWriter(f); // IOException
                writeRootElement(fw);
            } finally {
                if (null != fw) {
                    fw.close();
                    // fw = null;
                }
            }
        }

        return f;
    }
}