package rifai.achmad.mathshogun.util;

import android.os.Environment;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import rifai.achmad.mathshogun.beans.Catatan;

/**
 * Created by ai on 22/09/2017.
 */

public class Work {
    public static java.io.File f=new java.io.File(Environment.getExternalStorageDirectory().getAbsolutePath()+
            "/.MathShogun/posisi.xml");

    public static void setData(List<Catatan> l) throws ParserConfigurationException, TransformerException {
        Document d=javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        org.w3c.dom.Element e=d.createElement("highscore");
        for(Catatan c:l)e.appendChild(dataSiji(c,d));
        d.appendChild(e);
        simpanXML(d);
    }

    private static void simpanXML(Document d) throws TransformerException {
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        if(f.exists())f.delete();
        javax.xml.transform.dom.DOMSource ds=new javax.xml.transform.dom.DOMSource(d);
        javax.xml.transform.stream.StreamResult sr=new javax.xml.transform.stream.StreamResult(f);
        javax.xml.transform.Transformer t=javax.xml.transform.TransformerFactory.newInstance().newTransformer();
        t.transform(ds,sr);
    }

    private static Node dataSiji(Catatan c, Document d) {
        org.w3c.dom.Element e=d.createElement("data");
        e.setAttribute("nama",c.getNama());
        e.setAttribute("gold",""+c.getGold());
        e.setAttribute("lvl",""+c.getLevel());
        e.setAttribute("point",""+c.getPoint());
        return e;
    }
}