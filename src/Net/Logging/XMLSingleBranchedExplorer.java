package Net.Logging;

import org.jdom2.Attribute;
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
import java.util.*;

public abstract class XMLSingleBranchedExplorer implements XMLSingleBranched {
    protected String dir = "";

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public boolean write(String file, Map<String, String> keysAndValues) throws IOException {
        Document doc = makeDocument(file); // IOException
        FileWriter fw = null;

        try {
            fw = new FileWriter(makeFile(file)); // IOException
            Element rootElement = doc.getRootElement(); // log
            rootElement.addContent(makeElement(makeElementName(), keysAndValues));
            makeOutPutter().output(doc, fw);
        } finally {
            if (null != fw) {
                fw.close();
                // fw = null;
            }
        }

        return true;
    }

    public List<Map<String, String>> read(String file) throws IOException {
        Document doc = makeDocument(file); // IOException
        Map<String, String> buf;
        List<Map<String, String>> back = new ArrayList<Map<String, String>>();
        List<Element> listElems = doc.getRootElement().getChildren();
        List<Attribute> listAttributes;

        for(int i = 0; i < listElems.size(); ++i) {
            listAttributes = listElems.get(i).getAttributes();
            buf = new TreeMap<String, String>();
            back.add(buf);

            for(int j = 0; j < listAttributes.size(); ++j) {
                buf.put(listAttributes.get(j).getName(), listAttributes.get(j).getValue());
            }
        }

        return back;
    }

    protected String makeElementName() {
        return "unknown";
    }

    protected File makeFile(String file) throws IOException {
        if(dir != "")
        file = dir + File.separator + file;

        if(!(file.endsWith(".xml"))) {
            file += ".xml";
        }

        File f = new File(file);

        if (!(f.exists())) {
            if(f.createNewFile()) { // IOException
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
            } else {
                throw new IOException("can't create file");
            }
        }

        return f;
    }

    protected Element makeElement(String elementName, Map<String, String> keysAndValues) {
        Element newElement = new Element(elementName);

        for(Map.Entry<String, String> e : keysAndValues.entrySet()) {
            newElement.setAttribute(e.getKey(), e.getValue());
        }

        return newElement;
    }

    protected Document makeDocument(String file) throws IOException {
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

    protected String makeDate() {
        return
            new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").
                format(new Date(System.currentTimeMillis()));
    }

    protected void writeRootElement(FileWriter fw) throws IOException {
        XMLOutputter xml = makeOutPutter();
        xml.output(makeDocument(), fw); // IOException
    }

    protected String makeRootElementName() {
        return "root";
    }

    protected Element makeRootElement() {
        return new Element(makeRootElementName());
    }

    protected Document makeDocument() {
        return new Document(makeRootElement());
    }

    protected XMLOutputter makeOutPutter() {
        XMLOutputter out = new XMLOutputter();
        out.setFormat(Format.getPrettyFormat());

        return out;
    }
}
