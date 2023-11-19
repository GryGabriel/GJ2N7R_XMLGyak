package domgj2n7r1108;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class DomReadGJ2N7R {
    public static void main(String[] args) {

        try {
           File inputFile = new File("GJ2N7R_kurzusfelvetel.xml");
           
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.parse(inputFile);
           
           doc.getDocumentElement().normalize();
           System.out.println("\nGyökérelem: " + doc.getDocumentElement().getNodeName());
           NodeList hallgatoList = doc.getElementsByTagName("hallgato");
           NodeList kurzusokList = doc.getElementsByTagName("kurzus");
           
           //Print elements of the "hallgato" element
            for(int i=0; i<hallgatoList.getLength(); i++){
                Node hallgato = hallgatoList.item(i);
                System.out.println("\nJelenlegi hallgató: " + hallgato.getNodeName());

                if(hallgato.getNodeType() == Node.ELEMENT_NODE){ //Ha a a hallgató egy element, akkor írassa ki a gyerek elemeit
                    Element hallgatoElement = (Element) hallgato;
                    System.out.println("Hallgató neve: " + hallgatoElement.getElementsByTagName("hnev").item(0).getTextContent());
                    System.out.println("Születési év: " + hallgatoElement.getElementsByTagName("szulev").item(0).getTextContent());
                    System.out.println("Szak: " + hallgatoElement.getElementsByTagName("szak").item(0).getTextContent());
                }
            
            }   

            //Print elements of the "kurzusok" element
            for(int i=0; i<kurzusokList.getLength(); i++){
                Node kurzus = kurzusokList.item(i);
                    
                if(kurzus.getNodeType() == Node.ELEMENT_NODE){
                    Element kurzusElement = (Element) kurzus;
                    System.out.println("\nJelenlegi kurzus azonosító: " + kurzusElement.getAttribute("id"));

                    System.out.println("Kurzus neve: " + kurzusElement.getElementsByTagName("kurzusnev").item(0).getTextContent());
                    System.out.println("Kreditszám: " + kurzusElement.getElementsByTagName("kredit").item(0).getTextContent());
                    System.out.println("Helyszín: " + kurzusElement.getElementsByTagName("hely").item(0).getTextContent());
                    System.out.println("Időpont: " + kurzusElement.getElementsByTagName("idopont").item(0).getTextContent());
                    System.out.println("Oktató: " + kurzusElement.getElementsByTagName("oktato").item(0).getTextContent());
                    if(kurzusElement.getElementsByTagName("oraado").item(0) != null){
                        System.out.println("Óraadó: " + kurzusElement.getElementsByTagName("oraado").item(0).getTextContent());
                    }
                }
            }
           
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
