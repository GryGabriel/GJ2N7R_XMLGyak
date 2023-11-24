package xpathgj2n7r;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xpathgj2n7r {
    public static void main (String[] args){
        try {
            File inputXML = new File("studentGJ2N7R.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputXML);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            
            // 1) Válassza ki az összes student elemet, amely a class gyermeke!
                // XPathExpression expression = xPath.compile("/class/student");

                // NodeList nodeList = (NodeList) expression.evaluate(document, XPathConstants.NODESET);

                // for (int i = 0; i < nodeList.getLength(); i++) {
                //     System.out.println("\n");
                //     System.out.println("Student #" + (i + 1) + ":");
                //     System.out.println("ID: " + nodeList.item(i).getAttributes().getNamedItem("id").getNodeValue());
                //     System.out.println("Keresztnév: " + nodeList.item(i).getChildNodes().item(1).getTextContent());
                //     System.out.println("Vezetéknév: " + nodeList.item(i).getChildNodes().item(3).getTextContent());
                //     System.out.println("Becenév: " + nodeList.item(i).getChildNodes().item(5).getTextContent());
                //     System.out.println("Kor: " + nodeList.item(i).getChildNodes().item(7).getTextContent());
                //     System.out.println("\n");
                // }

            // 2) Válassza ki az összes student elemet, amely a class gyermeke!
                // XPathExpression xPathExpression = xPath.compile("/class/student[@id='2']");

                // Node node = (Node) xPathExpression.evaluate(document, XPathConstants.NODE);

                // if(node != null){
                //     System.out.println("\n");
                //     System.out.println("A '2' ID-vel rendelkező elem: ");
                //     System.out.println("Keresztnév: " + node.getChildNodes().item(1).getTextContent());
                //     System.out.println("Vezetéknév: " + node.getChildNodes().item(3).getTextContent());
                //     System.out.println("Becenév: " + node.getChildNodes().item(5).getTextContent());
                //     System.out.println("Kor: " + node.getChildNodes().item(7).getTextContent());
                //     System.out.println("\n");
                // } else {
                //     System.out.println("Nem található elem!");
                // }

            // 3) Válassza ki az összes student elemet, függetlenül attól, hogy hol vannak a dokumentumban!
                // XPathExpression xPathExpression = xPath.compile("//student");

                // NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);

                // for (int i = 0; i < nodeList.getLength(); i++) {
                //     System.out.println("\n");
                //     System.out.println("Student #" + (i + 1) + ":");
                //     System.out.println("ID: " + nodeList.item(i).getAttributes().getNamedItem("id").getNodeValue());
                //     System.out.println("Keresztnév: " + nodeList.item(i).getChildNodes().item(1).getTextContent());
                //     System.out.println("Vezetéknév: " + nodeList.item(i).getChildNodes().item(3).getTextContent());
                //     System.out.println("Becenév: " + nodeList.item(i).getChildNodes().item(5).getTextContent());
                //     System.out.println("Kor: " + nodeList.item(i).getChildNodes().item(7).getTextContent());
                //     System.out.println("\n");
                // }

            // 4) Válassza ki a második student elemet, amely a class root element gyermeke!
                // XPathExpression xPathExpression = xPath.compile("/class/student[2]");

                // Node node = (Node) xPathExpression.evaluate(document, XPathConstants.NODE);

                // if(node != null){
                //     System.out.println("\n");
                //     System.out.println("A 'class' gyökérelem második eleme: ");
                //     System.out.println("Keresztnév: " + node.getChildNodes().item(1).getTextContent());
                //     System.out.println("Vezetéknév: " + node.getChildNodes().item(3).getTextContent());
                //     System.out.println("Becenév: " + node.getChildNodes().item(5).getTextContent());
                //     System.out.println("Kor: " + node.getChildNodes().item(7).getTextContent());
                //     System.out.println("\n");
                // } else {
                //     System.out.println("Nem található elem!");
                // }


            // 5) Válassza ki az utolsó student elemet, amely a class root element gyermeke!
                // XPathExpression xPathExpression = xPath.compile("/class/student[last()]");
            
                // Node node = (Node) xPathExpression.evaluate(document, XPathConstants.NODE);

                // if(node != null){
                //     System.out.println("\n");
                //     System.out.println("A 'class' gyökérelem utolsó eleme: ");
                //     System.out.println("Keresztnév: " + node.getChildNodes().item(1).getTextContent());
                //     System.out.println("Vezetéknév: " + node.getChildNodes().item(3).getTextContent());
                //     System.out.println("Becenév: " + node.getChildNodes().item(5).getTextContent());
                //     System.out.println("Kor: " + node.getChildNodes().item(7).getTextContent());
                //     System.out.println("\n");
                // } else {
                //     System.out.println("Nem található elem!");
                // }


            // 6) Válassza ki az utolsó előtti student elemet, amely a class root elem gyermeke!
                // XPathExpression xPathExpression = xPath.compile("/class/student[last()-1]");

                            
                // Node node = (Node) xPathExpression.evaluate(document, XPathConstants.NODE);

                // if(node != null){
                //     System.out.println("\n");
                //     System.out.println("A 'class' gyökérelem utolsó eleme: ");
                //     System.out.println("Keresztnév: " + node.getChildNodes().item(1).getTextContent());
                //     System.out.println("Vezetéknév: " + node.getChildNodes().item(3).getTextContent());
                //     System.out.println("Becenév: " + node.getChildNodes().item(5).getTextContent());
                //     System.out.println("Kor: " + node.getChildNodes().item(7).getTextContent());
                //     System.out.println("\n");
                // } else {
                //     System.out.println("Nem található elem!");
                // }
                

            // 7) Válassza ki az első két student elemet, amelyek a class root elem gyermekei!
                // XPathExpression xPathExpression = xPath.compile("/class/student[position()<3]");

                // NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);

                // for (int i = 0; i < nodeList.getLength(); i++) {
                //     System.out.println("\n");
                //     System.out.println("Student #" + (i + 1) + ":");
                //     System.out.println("ID: " + nodeList.item(i).getAttributes().getNamedItem("id").getNodeValue());
                //     System.out.println("Keresztnév: " + nodeList.item(i).getChildNodes().item(1).getTextContent());
                //     System.out.println("Vezetéknév: " + nodeList.item(i).getChildNodes().item(3).getTextContent());
                //     System.out.println("Becenév: " + nodeList.item(i).getChildNodes().item(5).getTextContent());
                //     System.out.println("Kor: " + nodeList.item(i).getChildNodes().item(7).getTextContent());
                //     System.out.println("\n");
                // }

            // 8) Válassza ki a class root elem összes gyermek elemét!
                // XPathExpression xPathExpression = xPath.compile("/class/*");

                // NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);

                // System.out.println("A 'class' gyökérelem összes gyermek eleme: ");
                // for(int i=0; i<nodeList.getLength(); i++){
                //     System.out.println("A(z) " + (i+1) + ". gyerekelem: " + nodeList.item(i).getNodeName());
                // }

            // 9) Válassza ki az összes student elemet, amely rendelkezik legalább egy bármilyen attribútummal!
                // XPathExpression xPathExpression = xPath.compile("/class/student[@*]");

                // NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);

                // for (int i = 0; i < nodeList.getLength(); i++) {
                //     System.out.println("\n");
                //     System.out.println("Student #" + (i + 1) + ":");
                //     System.out.println("ID: " + nodeList.item(i).getAttributes().getNamedItem("id").getNodeValue());
                //     System.out.println("Keresztnév: " + nodeList.item(i).getChildNodes().item(1).getTextContent());
                //     System.out.println("Vezetéknév: " + nodeList.item(i).getChildNodes().item(3).getTextContent());
                //     System.out.println("Becenév: " + nodeList.item(i).getChildNodes().item(5).getTextContent());
                //     System.out.println("Kor: " + nodeList.item(i).getChildNodes().item(7).getTextContent());
                //     System.out.println("\n");
                // }

            // 10) Válassza ki a dokumentum összes elemét!
                // XPathExpression xPathExpression = xPath.compile("//*");

                // NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);

                // System.out.println("A dokumentum összes eleme: ");
                // for(int i=0; i<nodeList.getLength(); i++){
                //     System.out.println("A(z) " + (i+1) + ". gyerekelem: " + nodeList.item(i).getNodeName());
                // }

            // 11) Válassza ki class root elem összes student elemét, amelynél a kor>20!
                // XPathExpression xPathExpression = xPath.compile("/class/student[kor>20]");

                // NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);

                // for (int i = 0; i < nodeList.getLength(); i++) {
                //     System.out.println("\n");
                //     System.out.println("Student #" + (i + 1) + ":");
                //     System.out.println("ID: " + nodeList.item(i).getAttributes().getNamedItem("id").getNodeValue());
                //     System.out.println("Keresztnév: " + nodeList.item(i).getChildNodes().item(1).getTextContent());
                //     System.out.println("Vezetéknév: " + nodeList.item(i).getChildNodes().item(3).getTextContent());
                //     System.out.println("Becenév: " + nodeList.item(i).getChildNodes().item(5).getTextContent());
                //     System.out.println("Kor: " + nodeList.item(i).getChildNodes().item(7).getTextContent());
                //     System.out.println("\n");
                // }

            // 12) Válassza ki az összes student elem összes keresztnev vagy vezeteknev csomópontját!
                XPathExpression xPathExpression = xPath.compile("//student/keresztnev | //student/vezeteknev");

                NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);

                for(int i=0; i<nodeList.getLength(); i++){
                    System.out.println("A(z) " + (i+1) + ". vezetéknév/keresztnév: " + nodeList.item(i).getNodeName());
                }


        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
