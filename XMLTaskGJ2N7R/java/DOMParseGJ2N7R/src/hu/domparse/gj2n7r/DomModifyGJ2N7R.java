package hu.domparse.gj2n7r;


import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyGJ2N7R {
    public static void main(String[] args){
        try {

            String filePath = "GJ2N7R_kurzusfelvetel.xml";
            File inputFile = new File(filePath);
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();

            modifyXMLFile(document);

            saveXMLDocument(document, filePath);
            

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void modifyXMLFile(Document document){
        Element rootElement = document.getDocumentElement();

        
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
