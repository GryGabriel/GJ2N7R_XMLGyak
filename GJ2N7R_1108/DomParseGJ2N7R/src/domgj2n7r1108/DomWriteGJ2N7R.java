package domgj2n7r1108;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;

public class DomWriteGJ2N7R {
    
    public static void main(String[] args) throws DOMException{
        try {
            File inputFile = new File("GJ2N7R_kurzusfelvetel.xml");
            
            //Meglévő fájl megnyitása
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);    
            doc.getDocumentElement().normalize();

            //Új fájl létrehozása
            DocumentBuilderFactory db1Factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db1Builder = db1Factory.newDocumentBuilder();
            Document newDoc = db1Builder.newDocument();


            System.out.println("\nGyökérelem: " + doc.getDocumentElement().getNodeName());
            NodeList hallgatoList = doc.getElementsByTagName("hallgato");
            NodeList kurzusokList = doc.getElementsByTagName("kurzus");


            Element rootElement = newDoc.createElement("GJ2N7R_kurzusfelvétel");
            newDoc.appendChild(rootElement);

            NamedNodeMap rootAttributes = doc.getDocumentElement().getAttributes();
            for(int i=0; i<rootAttributes.getLength(); i++){
                Node attribute = rootAttributes.item(i);
                
                Attr newAttribute = newDoc.createAttribute(attribute.getNodeName());
                newAttribute.setNodeValue(attribute.getNodeValue());

                rootElement.setAttributeNode(newAttribute);
            }
            
            //Print elements of the "hallgato" element
             for(int i=0; i<hallgatoList.getLength(); i++){
                 Node hallgato = hallgatoList.item(i);

                 System.out.println("\nJelenlegi hallgató: " + hallgato.getNodeName());
 
                 if(hallgato.getNodeType() == Node.ELEMENT_NODE){ //Ha a a hallgató egy element, akkor írassa ki a gyerek elemeit
                    Element hallgatoElement = (Element) hallgato;
                    
                    Element newHallgato = newDoc.createElement("hallgato");
                    rootElement.appendChild(newHallgato);

                    //Set attributes of the hallgato element
                    NamedNodeMap hallgatoAttributes = hallgatoElement.getAttributes();
                    for(int j=0; j<hallgatoAttributes.getLength(); j++){
                        Node attribute = hallgatoAttributes.item(j);
                        
                        Attr newHallgatoAttribute = newDoc.createAttribute(attribute.getNodeName());
                        newHallgatoAttribute.setNodeValue(attribute.getNodeValue());

                        newHallgato.setAttributeNode(newHallgatoAttribute);
                    }

                    System.out.println("Hallgató neve: " + hallgatoElement.getElementsByTagName("hnev").item(0).getTextContent());
                    Element hallgatoNev = newDoc.createElement("hnev");
                    hallgatoNev.setTextContent(hallgatoElement.getElementsByTagName("hnev").item(0).getTextContent());
                    newHallgato.appendChild(hallgatoNev);
                     
                    System.out.println("Születési év: " + hallgatoElement.getElementsByTagName("szulev").item(0).getTextContent());
                    Element hallgatoSzulev = newDoc.createElement("szulev");
                    hallgatoSzulev.setTextContent(hallgatoElement.getElementsByTagName("szulev").item(0).getTextContent());
                    newHallgato.appendChild(hallgatoSzulev);
                     
                    System.out.println("Szak: " + hallgatoElement.getElementsByTagName("szak").item(0).getTextContent());
                    Element hallgatoSzak = newDoc.createElement("szak");
                    hallgatoSzak.setTextContent(hallgatoElement.getElementsByTagName("szak").item(0).getTextContent());
                    newHallgato.appendChild(hallgatoSzak);

                 }
             
             }   
 
             //Print elements of the "kurzusok" element
             for(int i=0; i<kurzusokList.getLength(); i++){
                 Node kurzus = kurzusokList.item(i);
                     
                 if(kurzus.getNodeType() == Node.ELEMENT_NODE){
                    Element kurzusElement = (Element) kurzus;
                    System.out.println("\nJelenlegi kurzus azonosító: " + kurzusElement.getAttribute("id"));

                    Element newKurzus = newDoc.createElement("kurzus");
                    rootElement.appendChild(newKurzus);

                    //Set attributes of the 'kurzus' element
                    NamedNodeMap kurzusAttributes = kurzusElement.getAttributes();
                    for(int j=0; j<kurzusAttributes.getLength(); j++){
                        Node attribute = kurzusAttributes.item(j);

                        Attr newKurzusAttribute = newDoc.createAttribute(attribute.getNodeName());
                        newKurzusAttribute.setNodeValue(attribute.getNodeValue());

                        newKurzus.setAttributeNode(newKurzusAttribute);
                    }

                    System.out.println("Kurzus neve: " + kurzusElement.getElementsByTagName("kurzusnev").item(0).getTextContent());
                    Element kurzusNeve = newDoc.createElement("kurzusnev");
                    kurzusNeve.setTextContent(kurzusElement.getElementsByTagName("kurzusnev").item(0).getTextContent());
                    newKurzus.appendChild(kurzusNeve);

                    System.out.println("Kreditszám: " + kurzusElement.getElementsByTagName("kredit").item(0).getTextContent());
                    Element kredit = newDoc.createElement("kredit");
                    kredit.setTextContent(kurzusElement.getElementsByTagName("kredit").item(0).getTextContent());
                    newKurzus.appendChild(kredit);

                    System.out.println("Helyszín: " + kurzusElement.getElementsByTagName("hely").item(0).getTextContent());
                    Element hely = newDoc.createElement("hely");
                    hely.setTextContent(kurzusElement.getElementsByTagName("hely").item(0).getTextContent());
                    newKurzus.appendChild(hely);
                    
                    System.out.println("Időpont: " + kurzusElement.getElementsByTagName("idopont").item(0).getTextContent());
                    Element idopont = newDoc.createElement("idopont");
                    idopont.setTextContent(kurzusElement.getElementsByTagName("idopont").item(0).getTextContent());
                    newKurzus.appendChild(idopont);
                    
                    System.out.println("Oktató: " + kurzusElement.getElementsByTagName("oktato").item(0).getTextContent());
                    Element oktato = newDoc.createElement("oktato");
                    oktato.setTextContent(kurzusElement.getElementsByTagName("oktato").item(0).getTextContent());
                    newKurzus.appendChild(oktato);
                    
                    if(kurzusElement.getElementsByTagName("oraado").item(0) != null){
                        System.out.println("Óraadó: " + kurzusElement.getElementsByTagName("oraado").item(0).getTextContent());
                        Element oraado = newDoc.createElement("oraado");
                    oraado.setTextContent(kurzusElement.getElementsByTagName("oraado").item(0).getTextContent());
                    newKurzus.appendChild(oraado);
                    }
                 }
             }

             File outputFile = new File("GJ2N7R_kurzusfelvetel1.xml");
             
             TransformerFactory transformerFactory = TransformerFactory.newInstance();
             Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(newDoc);
            StreamResult result = new StreamResult(outputFile);

             transformer.transform(source, result);

         } catch (Exception e) {
            e.printStackTrace();
         }
    }



}
