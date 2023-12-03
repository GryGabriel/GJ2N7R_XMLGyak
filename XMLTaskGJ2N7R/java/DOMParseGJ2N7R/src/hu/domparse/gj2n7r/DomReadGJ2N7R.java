package hu.domparse.gj2n7r;

import java.io.File;

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

public class DomReadGJ2N7R {

    public static void main(String[] args){

        try{

            //XML dokumentum megnyitása
            File inputXML = new File("XML/XMLGJ2N7R.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document document = dbBuilder.parse(inputXML);
            document.getDocumentElement().normalize();

            //Dokumentum mentése új XML fájlba
            saveXMLDocument(document, "XMLReadGJ2N7R.xml");


            //Kimeneti változó létrehozása
            String documentStructure = "<?xml version=\"1.0\" encoding=\"UTF-8\">\n\n";

            //Gyökérelem beolvasása
            Node rootElement = document.getDocumentElement();
            
            //Gyökérelem és attribútumainak csatolása
            documentStructure += getRootAttributes(rootElement);

            //Gyökérelem összes gyerek elemének beolvasása
            NodeList rootChildren = rootElement.getChildNodes();
            
            //Gyökérelem elemeinek csatolása a struktúrához
            documentStructure += appendChildNodes(rootChildren);

            //Dokumentum lezárása
            documentStructure += "\n\n</" + rootElement.getNodeName() +  ">\n";
            System.out.println(documentStructure);



        } catch(Exception e) {
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

    public static String appendChildNodes(NodeList children) {
        String structure = "";
        
        for (int i = 0; i < children.getLength(); i++) {
            Node childNode = children.item(i);
    
            //Ha a csomó egy Element
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) childNode;

                //További gyerekelemek beolvasása
                NodeList childNodes = childElement.getChildNodes();
                boolean hasChildElements = false;
                boolean hasText = false;

                //Element gyerek csomópontjainak vizsgálása, van-e köztük Element
                for(int j=0; j<childNodes.getLength(); j++){
                    if(childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                        hasChildElements = true;
                    }else if(childNodes.item(j).getNodeType() == Node.TEXT_NODE){
                        hasText = true;
                    }
                }

                //Ha az Elementnek vannak további gyerekelemei
                if(hasChildElements){   
                    structure += "\n\t<" + childNode.getNodeName();

                    //Element attribútumainak lekérdezése és a struktúrához csatolása
                    NamedNodeMap attributes = childNode.getAttributes();
                    for(int j=0; j<attributes.getLength(); j++){
                        Node attribute = attributes.item(j);

                        structure+= " " + attribute;
                    }

                    structure += ">";

                    structure += appendChildNodes(childNodes);
                    structure += "\n\t</" + childNode.getNodeName() + ">\n";
                }else if(hasText){ //Nincsenek további gyerekelemek; szöveg van
                    structure += "\n\t\t<" + childElement.getNodeName() + ">" + childElement.getTextContent() + "</" + childElement.getNodeName() + ">";
                }else{ //Az az eset, ha nincs se szöveg, se további gyerekelem
                    structure += "\n\t<" + childNode.getNodeName();

                    NamedNodeMap attributes = childNode.getAttributes();
                    for(int j=0; j<attributes.getLength(); j++){
                        Node attribute = attributes.item(j);

                        structure+= " " + attribute;
                    }
                    structure += ">" + "</" + childNode.getNodeName() + ">";
                }


            } else if (childNode.getNodeType() == Node.COMMENT_NODE) { //Komment hozzáadása
                structure += "\n\t<!--" + childNode.getTextContent() + "-->";
            } else if (childNode.getNodeType() == Node.TEXT_NODE && !childNode.getTextContent().trim().isEmpty()) { //Üres csomó hozzáadása új sorként
                structure += childNode.getTextContent() + "\n";
            }
        }
    
        return structure;
    }

    //Gyökérelem attribútumainak kiolvasása
    public static String getRootAttributes(Node rootElement){

        String returnStructure = "<";

        NamedNodeMap rootElementAttributes = rootElement.getAttributes();
        returnStructure += rootElement.getNodeName();

        for(int i=0; i<rootElementAttributes.getLength(); i++){
            Node attribute = rootElementAttributes.item(i);
             returnStructure += " " + attribute;
        }

        returnStructure += ">\n";

        return returnStructure;
    }
        
}
