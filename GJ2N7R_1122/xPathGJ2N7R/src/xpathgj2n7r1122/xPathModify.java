package xpathgj2n7r1122;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xPathModify {
    public static void main (String[] args) throws DOMException{
        try {
            File inputXML = new File("GJ2N7R_kurzusfelvetel.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputXML);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();

            //Ahol a kurzusnev = 'Webtechnológiák', ott a kredit változzon '10'-re
            // XPathExpression expression = xPath.compile("//kurzus[kurzusnev='Webtechnológiák']/kredit");

            // NodeList kreditek = (NodeList) expression.evaluate(document, XPathConstants.NODESET);

            // for(int i=0; i<kreditek.getLength(); i++){
            //     Node kredit = kreditek.item(i);

            //     kredit.setTextContent("10");
            //     System.out.println(kredit.getTextContent());
            // }


            XPathExpression expression = xPath.compile("//");

            saveXMLDocument(document, "GJ2N7R_kurzusfelvetel_modified.xml");
        } catch (Exception e){
            e.printStackTrace();
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
