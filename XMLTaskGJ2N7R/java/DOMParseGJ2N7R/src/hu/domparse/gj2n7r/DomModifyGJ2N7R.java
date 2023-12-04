package hu.domparse.gj2n7r;


import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyGJ2N7R {
    public static void main(String[] args){
        try {

            String filePath = "XML/XMLGJ2N7RModify.xml";
            File inputFile = new File(filePath);
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();

            //5 adat módosítása az XML fájlban
            modifyXMLFile(document);

            //XML fájl mentése
            saveXMLDocument(document, filePath);

            document.getDocumentElement().normalize();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void modifyXMLFile(Document document) throws XPathExpressionException{

        try{
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();

            //1. Minden értékelés pontszámának növelése egy ponttal
            System.out.println("Minden értékelés pontszámának növelése egy ponttal!");
            XPathExpression expression = xPath.compile("//Értékelés/Pontszám");
            NodeList Pontszámok = (NodeList) expression.evaluate(document, XPathConstants.NODESET);

            for(int i=0; i<Pontszámok.getLength(); i++){
                Node Pontszám = Pontszámok.item(i);

                //Előzetes pontszám lekérdezése és kiíratása
                String pontszám = Pontszám.getTextContent().trim();

                System.out.println("A(z) " + (i+1) + ". jelenglegi pontszám: " + pontszám);
                
                //A pontszám konvertálása Double-re
                double currentValue = 0;
                try{
                    currentValue = Double.parseDouble(pontszám);
                }catch(NumberFormatException e){
                    e.printStackTrace();
                }

                //Pontszám növelése és visszakonvertálása Stringre
                Pontszám.setTextContent(String.valueOf(currentValue+1));

                System.out.println("A(z) " + (i+1) + ". módosítás utáni pontszám: " + Pontszám.getTextContent());
            }

            // 2. A díjak nyerteseihez egy-egy új Nyertes hozzáadása
            System.out.println("Minden díj nyerteséhez Csuja Imre hozzáadása!");
            XPathExpression expression2 = xPath.compile("//Díj");
            NodeList awards = (NodeList) expression2.evaluate(document, XPathConstants.NODESET);

            for(int i=0; i<awards.getLength(); i++){
                Element award = (Element) awards.item(i);

                //A díj Element meglévő struktúra kiíratása kiíratása
                System.out.println("A díj elem módosítás előtt:");
                consoleNodes(award);

                //Új nyertes létrehozása
                Element newWinner = document.createElement("Nyertes");

                newWinner.setTextContent("Csuja Imre");
                award.appendChild(newWinner);

                //Új díj Element új struktúra kiíratása
                System.out.println("A díj elem módosítás után:");
                consoleNodes(award);
                
            }

            // 3. 'Lara Worthington' nevű élettárs csere 'Sandra Bullock'-ra
            System.out.println("'Lara Worthington' nevű élettárs csere 'Sandra Bullock'-ra");
            XPathExpression expression3 = xPath.compile("//Élettárs");
            NodeList Partners = (NodeList) expression3.evaluate(document, XPathConstants.NODESET);

            for(int i=0; i<Partners.getLength(); i++){
                Node partner = Partners.item(i);

                NodeList partnerChildren = partner.getChildNodes();
                for(int j=0; j<partnerChildren.getLength(); j++){
                    Node partnerChild =  partnerChildren.item(j);

                    if(partnerChild.getNodeName().equals("Név")){
                        NodeList nameChildren = partnerChild.getChildNodes();
                        Node name = nameChildren.item(1);
                        Node lastName = nameChildren.item(3);

                        if(name.getTextContent().equals("Lara") && lastName.getTextContent().equals("Worthington")){
                            //Meglévő élettárs nevének kiíratása
                            System.out.println("Korábbi élettárs: " + name.getTextContent() + " " + lastName.getTextContent());

                            name.setTextContent("Sandra");
                            lastName.setTextContent("Bullock");

                            //Új élettárs nevének kiíratása
                            System.out.println("Új élettárs: " + name.getTextContent() + " " + lastName.getTextContent());
                        }
                    }
                }

            }

            // 4. A második filmre mutató 'Szerepel' Elementek első egyedének, a színészre mutató idegen kulcsának megváltoztatása a 4-esre
            System.out.println("A második filmre mutató 'Szerepel' Elementek első egyedének, a színészre mutató idegen kulcsának megváltoztatása a 4-esre");
            XPathExpression expression4 = xPath.compile("//Szerepel[@szerepel_id='1']");
            Node plays = (Node) expression4.evaluate(document, XPathConstants.NODE);

            System.out.println("A módosítás előtt:");
            consoleNodes(plays);

            NamedNodeMap playsAttributes = plays.getAttributes();
            for(int j=0; j<playsAttributes.getLength(); j++){
                Node attribute = playsAttributes.item(j);
                
                if(attribute.getNodeName().equals("film_id")){
                    attribute.setNodeValue("4");
                }
            }

            System.out.println("A módosítás után:");
            consoleNodes(plays);
            

            // 5. A 'user3' nevú felhasználó születési dátumának megváltoztatása
            System.out.println("A 'user3' nevú felhasználó születési dátumának megváltoztatása");
            XPathExpression expression5 = xPath.compile("//Felhasználó[Felhasználónév='user3']/Születési_dátum");
            Node userDateOfBirth = (Node) expression5.evaluate(document, XPathConstants.NODE);

            //A szülő Element meghatározása a kiíratáshoz
            Node user = userDateOfBirth.getParentNode();

            System.out.println("A módosítás előtt:");
            consoleNodes(user);
            
            //Születési dátum módosítása
            userDateOfBirth.setTextContent("2000-05-29");

            System.out.println("A módosítás után:");
            consoleNodes(user);

        }catch(XPathExpressionException e){
            e.printStackTrace();
        }
        
    }

    //Az Elementek kiíratása
    public static void consoleNodes(Node node){
        String structure = "";
        structure += "<" + node.getNodeName() + " ";
        
        NamedNodeMap nodeAttributes = node.getAttributes();
        for(int i=0; i<nodeAttributes.getLength(); i++){
            Node attribute = nodeAttributes.item(i);
            
            structure += attribute;
        }
        structure += ">\n";

        NodeList nodeChildren = node.getChildNodes();
        for(int i=0; i<nodeChildren.getLength(); i++){
            Node child = nodeChildren.item(i);

            if(child.getNodeType() == Node.ELEMENT_NODE){
                structure += "\t<" + child.getNodeName() + ">" + child.getTextContent() + "</" + child.getNodeName() + ">\n";
            }
        }

        structure += "</" + node.getNodeName() + ">";

        System.out.println(structure);


    }

    public static void saveXMLDocument(Document document, String filePath) {
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
