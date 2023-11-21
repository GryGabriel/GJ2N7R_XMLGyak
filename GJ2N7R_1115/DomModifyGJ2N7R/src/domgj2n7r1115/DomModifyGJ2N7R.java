package domgj2n7r1115;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

public class DomModifyGJ2N7R {
    public static void main(String[] args){
        try {
            File inputFile = new File("GJ2N7R_kurzusfelvetel.xml");
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            modifyXMLFile(document);

            saveXMLDocument(document, "GJ2N7R_kurzusfelvetel.xml");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void modifyXMLFile(Document document){
        Element rootElement = document.getDocumentElement();

        Element kurzusokElement = (Element) rootElement.getElementsByTagName("kurzusok").item(0);
        if(kurzusokElement != null){
            NodeList kurzusok = kurzusokElement.getElementsByTagName("kurzus");

            for(int i=0; i<kurzusok.getLength(); i++){
                Node kurzus = kurzusok.item(i);

                NodeList kurzusChildren = kurzus.getChildNodes();
                Boolean exists = false;
                for(int j=0; j<kurzusChildren.getLength(); j++){
                    Node child = kurzusChildren.item(j);
                    if(child.getNodeType() == Node.ELEMENT_NODE && "óraadó".equals(child.getNodeName())){
                        exists = true;
                    }
                }

                if(!exists){
                    Element newElement = document.createElement("óraadó");
                    newElement.appendChild(document.createTextNode((i+1) + ". óraadó"));
                    kurzus.appendChild(newElement);
                }
            }
        }
    }


    private static void saveXMLDocument(Document document, String filePath) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
