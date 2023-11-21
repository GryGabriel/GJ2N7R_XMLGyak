package domgj2n7r1115;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomQueryGJ2N7R {

       public static void main(String[] args) throws ParserConfigurationException, SAXException {
              try {
                     File inputFile = new File("GJ2N7R_kurzusfelvetel.xml");
                     
                     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                     
                     Document document = dBuilder.parse(inputFile);
                     document.getDocumentElement().normalize();
                     
                     //Kurzusok kiíratása
                     NodeList kurzusok = document.getElementsByTagName("kurzusnev");
                     System.out.println("\nKurzusok neve: ");
                     writeOutNodeList(kurzusok, "kurzus");

                     //A dokumentum első gyerek elemének lekérdezése
                     NodeList elemek = document.getDocumentElement().getChildNodes();
                     Node elsoElem = null;

                     for (int i = 0; i < elemek.getLength(); i++) {
                            Node child = elemek.item(i);

                            if (child.getNodeType() == Node.ELEMENT_NODE) {
                                   elsoElem = child;
                                   break;
                            }
                     }

                     if(elsoElem != null){
                            NodeList elsoElemGyerekei = elsoElem.getChildNodes();
                            System.out.println("\nElső gyerekelem elemei: ");
                            writeOutNodeList(elsoElemGyerekei, "elem");
                            writeToFile(elsoElemGyerekei);
                     }
                     
                     

                     //Oktatók kiíratása
                     NodeList oktatok = document.getElementsByTagName("oktató");
                     System.out.println("\nOktatók neve: ");
                     writeOutNodeList(oktatok, "oktató");


              } catch (Exception e) {
                     e.printStackTrace();
              }

       }

       public static void writeToFile(NodeList elemek) throws ParserConfigurationException, SAXException { 
              try{
                     File file = new File("GJ2N7R_new.xml");

                     DocumentBuilderFactory newFactory = DocumentBuilderFactory.newInstance();
                     DocumentBuilder newBuilder = newFactory.newDocumentBuilder();

                     Document newDocument = newBuilder.newDocument();

                     Node rootElement = newDocument.createElement("hallgato");
                     newDocument.appendChild(rootElement);

                     for(int i=0; i<elemek.getLength(); i++){
                            Node elem = elemek.item(i);

                            if(!(elem.getTextContent().trim().isEmpty())){
                                   Node newElem = newDocument.createElement((String) elem.getNodeName());
                                   newElem.appendChild(newDocument.createTextNode(elem.getTextContent().trim()));
                                   rootElement.appendChild(newElem);
                            }
                     }

                     TransformerFactory transformerFactory = TransformerFactory.newInstance();
                     Transformer transformer = transformerFactory.newTransformer();

                     DOMSource source = new DOMSource(newDocument);
                     StreamResult result = new StreamResult(file);

                     transformer.transform(source, result);

              } catch(Exception e) {
                     e.printStackTrace();
              }

       }

       public static void writeOutNodeList(NodeList list, String value){
              for(int i=0; i<list.getLength(); i++){
                     Node item = list.item(i);
                     if(!(item.getTextContent().trim().isEmpty())){
                            System.out.println( "<" + item.getNodeName() + "> " + item.getTextContent() + " <" + item.getNodeName() + ">");
                     }
                     
              }
       }

}

