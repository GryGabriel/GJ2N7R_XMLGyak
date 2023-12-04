package hu.domparse.gj2n7r;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Properties;
import javax.xml.transform.OutputKeys;

public class DomWriteGJ2N7R {

    public static void main(String[] args) throws ParserConfigurationException{

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
        Document document = dbBuilder.newDocument();

        //Gyökérelem létrehozása
        Element rootElement = document.createElement("Filmadatbázis");
        rootElement.setAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttribute("xs:noNamespaceSchemaLocation", "../xsd/XMLSchemaGJ2N7R.xsd");
        document.appendChild(rootElement);

        
        //Film komment hozzáadása
        Node text = document.createTextNode("\n");
        rootElement.appendChild(text);
        text = document.createComment("Film példányok");
        rootElement.appendChild(text);

        //Film Elemek létrehozása
        String[] movieIds = {"film_id"};
        String[] movieElementNames = {"Cím", "Kiadás_éve", "Műfaj"};
        String[][][] movies = {
            {{"Inception"}, {"The Shawshank Redemption"}, {"Avatar"}, {"The Dark Knight"}},
            {{"2010"}, {"1994"}, {"2009"}, {"2008"}},
            {{"Sci-fi"}, {"Dráma"}, {"Sci-fi"}, {"Akció"}}
        };
        createElement(document, rootElement, "Film", movieIds, movies, movieElementNames);

        //Értékelés komment hozzáadása
        text = document.createTextNode("\n");
        rootElement.appendChild(text);
        text = document.createComment("Értékelés példányok");
        rootElement.appendChild(text);

        //Értékelés Elemek létrehozása
        String[] rankIds = {"ertekeles_id", "film_id"};
        String[] rankElementNames = {"Pontszám", "Értékelések_száma", "Értékelés_szövege"};
        String[][][] ranks = {
            {{"4.8"}, {"4.9"}, {"4.5"}, {"4.7"}},
            {{"2000"}, {"1500"}, {"1200"}, {"1800"}},
            {
                {"Nagyon jó film, érdemes megnézni!", "Lenyűgöző képi világ!", "Remek színészi alakítások!"},
                {"Minden idők egyik legjobb filmje!", "Nagyon izgalmas történet!"},
                {"Varázslatos filmélmény!","Nagyszerű rendezés!","Lenyűgöző látványvilág!"}, 
                {"Nagyon élvezetes film!", "Izgalmas cselekmény!"}
            }
        };
        createElement(document, rootElement, "Értékelés", rankIds, ranks, rankElementNames);

        //Kategória komment hozzáadása
        text = document.createTextNode("\n");
        rootElement.appendChild(text);
        text = document.createComment("Kategória példányok");
        rootElement.appendChild(text);
        
        // Kategória Elemek létrehozása
        String[] categoryIds = {"kategoria_id"};
        String[] categoryElementNames = {"Kategória_név", "Leírás"};
        String[][][] categories = {
                {{"Akció"}, {"Drama"}, {"Sci-fi"}},
                {{"Izgalmas, pörgős jeleneteket tartalmazó filmek"}, {"Mély érzelmekre épülő filmek"}, {"Fantázia és tudományos elemeket tartalmazó filmek"}}
        };
        createElement(document, rootElement, "Kategória", categoryIds, categories, categoryElementNames);

        //Tartozik komment hozzáadása
        text = document.createTextNode("\n");
        rootElement.appendChild(text);
        text = document.createComment("Tartozik kapcsolótábla példányok");
        rootElement.appendChild(text);
        
        // Tartozik kapcsolótábla Elemek létrehozása
        String[] belongsIds = {"film_id", "kategoria_id", "tartozik_id"};
        String[] belongsElementNames = {"Kategóriák_száma"};
        String[][][] belongs = {
                {{"2"}, {"1"}, {"3"}, {"4"}}
        };
        createElement(document, rootElement, "Tartozik", belongsIds, belongs, belongsElementNames);

        //Felhasználó komment hozzáadása
        text = document.createTextNode("\n");
        rootElement.appendChild(text);
        text = document.createComment("Felhasználó példányok");
        rootElement.appendChild(text);
        
        // Felhasználó Elemek létrehozása
        String[] userIds = {"felhasznalo_id"};
        String[] userElementNames = {"Felhasználónév", "Születési_dátum", "Email-cím"};
        String[][][] users = {
                {{"user1"}, {"user2"}, {"user3"}, {"user4"}},
                {{"1985-05-15"}, {"1990-08-22"}, {"1988-03-10"}, {"1995-12-05"}},
                {{"user1@example.com"}, {"user2@example.com"}, {"user3@example.com"}, {"user4@example.com"}}
        };
        createElement(document, rootElement, "Felhasználó", userIds, users, userElementNames);

        //Megtekinti komment hozzáadása
        text = document.createTextNode("\n");
        rootElement.appendChild(text);
        text = document.createComment("Megtekinti kapcsolótábla példányok");
        rootElement.appendChild(text);

        // Megtekinti kapcsolótábla Elemek létrehozása
        Element megtekinti1 = document.createElement("Megtekinti");
        megtekinti1.setAttribute("felhasznalo_id", "1");
        megtekinti1.setAttribute("film_id", "1");
        rootElement.appendChild(megtekinti1);

        Element megtekinti2 = document.createElement("Megtekinti");
        megtekinti2.setAttribute("felhasznalo_id", "2");
        megtekinti2.setAttribute("film_id", "2");
        rootElement.appendChild(megtekinti2);

        Element megtekinti3 = document.createElement("Megtekinti");
        megtekinti3.setAttribute("felhasznalo_id", "3");
        megtekinti3.setAttribute("film_id", "3");
        rootElement.appendChild(megtekinti3);

        Element megtekinti4 = document.createElement("Megtekinti");
        megtekinti4.setAttribute("felhasznalo_id", "4");
        megtekinti4.setAttribute("film_id", "4");
        rootElement.appendChild(megtekinti4);

        //Színész komment hozzáadása
        text = document.createTextNode("\n");
        rootElement.appendChild(text);
        text = document.createComment("Színész példányok");
        rootElement.appendChild(text);
        
        //Színész Elemek létrehozása
        String[] actorIds = {"szinesz_id"};
        String[] actorElementNames = {"Név", "Születési_dátum", "Születési_hely"};
        String[][][] actors = {
                {{"Leonardo", "DiCaprio"}, {"Morgan", "Freeman"}, {"Sam", "Worthington"}},
                {{"1974-11-11"}, {"1937-06-01"}, {"1976-08-02"}},
                {{"Los Angeles, Kalifornia"}, {"Memphis, Tennessee"}, {"Godalming, Egyesült Királyság"}}
        };
        createElement(document, rootElement, "Színész", actorIds, actors, actorElementNames);

        //Szerepel komment hozzáadása
        text = document.createTextNode("\n");
        rootElement.appendChild(text);
        text = document.createComment("Szerepel példányok");
        rootElement.appendChild(text);
        
        // Szerepel Elemek létrehozása
        String[] actsIds = {"film_id", "szerepel_id", "szinesz_id"};
        String[] actsElementNames = {"Karakter_neve"};
        String[][][] acts = {
                {{"Dominic Cobb"}, {"Andy Dufresne"}, {"Jake Sully"}}
        };
        createElement(document, rootElement, "Szerepel", actsIds, acts, actsElementNames);

        //Élettárs komment hozzáadása
        text = document.createTextNode("\n");
        rootElement.appendChild(text);
        text = document.createComment("Élettárs példányok");
        rootElement.appendChild(text);
        
        // Élettárs Elemek létrehozása
        String[] partnerIds = {"elettars_id", "szinesz_id"};
        String[] partnerElementNames = {"Név", "Születési_dátum", "Születési_hely"};
        String[][][] partners = {
                {{"Camila", "Morrone"},{"Myrna", "Colley-Lee"},{"Lara", "Washington"}},
                {{"1997-06-16"},{"1941-03-15"},{"1976-08-02"}},
                {{"Buenos Aires, Argentina"},{"Milwaukee, Wisconsin"},{"Godalming, Egyesült Királyság"}}
        };
        createElement(document, rootElement, "Élettárs", partnerIds, partners, partnerElementNames);

        //Díj komment hozzáadása
        text = document.createTextNode("\n");
        rootElement.appendChild(text);
        text = document.createComment("Díj példányok");
        rootElement.appendChild(text);
        
        // Díj Elemek létrehozása
        String[] awardIds = {"dij_id"};
        String[] awardElementNames = {"Díj_típusa", "Feltételek", "Nyertes"};
        String[][][] awards = {
                {{"Oscar"}, {"Golden Globe"}, {"BAFTA"}},
                {{"Legjobb film"}, {"Legjobb színész"}, {"Legjobb rendező"}},
                {
                        {"Christopher Nolan", "Matthew McConaughey"},
                        {"Leonardo DiCaprio", "Tom Hanks", "Emma Stone"},
                        {"Alfonso Cuarón", "Greta Gerwig"}
                }
        };
        createElement(document, rootElement, "Díj", awardIds, awards, awardElementNames);

        //Elnyeri komment hozzáadása
        text = document.createTextNode("\n");
        rootElement.appendChild(text);
        text = document.createComment("Elnyeri kapcsolótábla példányok");
        rootElement.appendChild(text);

        // Elnyeri kapcsolótábla Elemek létrehozása
        Element elnyeri1 = document.createElement("Elnyeri");
        elnyeri1.setAttribute("szinesz_id", "1");
        elnyeri1.setAttribute("dij_id", "1");
        rootElement.appendChild(elnyeri1);

        Element elnyeri2 = document.createElement("Elnyeri");
        elnyeri2.setAttribute("szinesz_id", "2");
        elnyeri2.setAttribute("dij_id", "2");
        rootElement.appendChild(elnyeri2);

        Element elnyeri3 = document.createElement("Elnyeri");
        elnyeri3.setAttribute("szinesz_id", "3");
        elnyeri3.setAttribute("dij_id", "3");
        rootElement.appendChild(elnyeri3);

        //XML fájl mentése
        document.getDocumentElement().normalize();
        saveXMLDocument(document, "XML/XMLGJ2N7R1.xml");
    }

    public static void createElement(Document document, Element rootElement, String elementName, String[] ids, String[][][] elementValues, String[] elementNames){
        
        //Dimenziók kiszámítása
        int childElementCount = elementNames.length;
        int elementCount = elementValues[0].length;

        //Mennyi legyen ebből a bizonyos elemből
        for(int i=0; i<elementCount; i++){
            //Új element létrehozása
            Element newElement = document.createElement(elementName);
            //Attribútumok hozzáfűzése
            for(int j=0; j<ids.length; j++){
                newElement.setAttribute(ids[j], String.valueOf(i+1));
            }

            //Gyerekelemek létrehozása
            for(int j=0; j<childElementCount; j++){
                //Hányszor fordulnak elő a gyerekelemek
                for(int k=0; k< elementValues[j][i].length; k++){
                    Element newChildElement = document.createElement(elementNames[j]);
                    newChildElement.setTextContent(elementValues[j][i][k]);

                    newElement.appendChild(newChildElement);
                }               
            }

            rootElement.appendChild(newElement);
        }

    }

    public static void saveXMLDocument(Document document, String filePath) {
        try {
            //Transformer létrehozása
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            //Új 'Properties' objektum létrehozása a dokumentum struktúrálása érdekében
            Properties outputProperties = new Properties();
            outputProperties.setProperty(OutputKeys.INDENT, "yes"); //Indent property beállítása 'yes'-re
            outputProperties.setProperty("{http://xml.apache.org/xslt}indent-amount", "2"); //Szóköz beállítása 2-re
            transformer.setOutputProperties(outputProperties); //Maga a formátozás

            DOMSource source = new DOMSource(document);

            //Kiírás fájlba
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            //Kiírás a konzolra
            StreamResult console = new StreamResult(System.out);
            transformer.transform(source, console);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
