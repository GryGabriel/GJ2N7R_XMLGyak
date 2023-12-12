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

public class DomQueryGJ2N7R {

    public static void main(String[] args){
        try{
        String filePath = "XML/XMLGJ2N7R.xml";

        File inputFile = new File(filePath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputFile);
        document.getDocumentElement().normalize();

        Element rootElement = document.getDocumentElement();
        //5 lekérdezés elvégzése az 'XMLGJ2N7R.xml' fájlon
        //1. Az első értékeléshez tartozó szövegek számának kiíratása
        ratingTextCounter(rootElement);
        
        //2. Az 1900 előtt született felhasználók felhasználónevének kiíratása
        bornBefore1900(rootElement);

        //3. Amelyik filmhez több mint egy kategória tartozik, annak a filmnek a neve kiíratásra kerül
        mostCategoryMovie(rootElement);

        //4. A legjobb értékeléssel rendelkező film kerül kiíratása
        topRankedMovie(rootElement);

        //5. A legtöbbszór kiosztott díj neve kerül kiíratásra
        topWonAward(rootElement);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //1. Az első értékeléshez tartozó szövegek számának kiíratása
    public static void ratingTextCounter(Element rootElement){
        //Értékelések lekérdezése egy listába
        NodeList ratings = rootElement.getElementsByTagName("Értékelés");
        //Első Értékelés és a hozzá tartozó gyerekelemek lekérdezése
        Node firstRating = ratings.item(0);
        NodeList childNodes = firstRating.getChildNodes();
        int textCounter = 0;
        for(int i=0; i<childNodes.getLength(); i++){
            Node child = childNodes.item(i);

            if(child.getNodeName().equals("Értékelés_szövege")){
                textCounter++;
            }
        }
        
        //Az eredmény kiíratása
        System.out.println("-----------------------------------------------------------");
        System.out.println("1. Az első értékeléshez tartozó szövegek számának kiíratása:");
        System.out.println("Az első 'Értékelés' elem " + textCounter + " darab szöveges értékelést tartalmaz");
        System.out.println("-----------------------------------------------------------");
    }

    //2. Az 1900 előtt született felhasználók felhasználónevének kiíratása
    public static void bornBefore1900(Element rootElement){
        //Felhasználók lekérdezése listába
        NodeList users = rootElement.getElementsByTagName("Felhasználó");

        System.out.println("-----------------------------------------------------------");
        System.out.println("2. Az 1900 előtt született felhasználók felhasználónevének kiíratása:");


        //Felhasználók születési dátumának megvizsgálása egyenként
        for(int i=0; i< users.getLength(); i++){
            Node user = users.item(i);

            //Felhasználó gyerekelemeinek lekérdezése
            NodeList userChildren = user.getChildNodes();

            //Feltételezve, hogy a felhasználó 1990 után született
            boolean correctAge = false;

            //Iterálás a felhasználó gyerekelemein
            for(int j=0; j<userChildren.getLength(); j++){
                Node child = userChildren.item(j);

                //Ha a gyerek egy element
                if(child.getNodeType() == Node.ELEMENT_NODE){
                    Element childElement = (Element) child;
                    
                    if(childElement.getNodeName().equals("Születési_dátum")){
                        String[] dateOfBirth = childElement.getTextContent().split("-");
                        if(Integer.parseInt(dateOfBirth[0]) < 1990){
                            correctAge = true;
                            j=0;
                        }
                    }else if (childElement.getNodeName().equals("Felhasználónév") && correctAge) {
                        System.out.println("A " + childElement.getTextContent() + " nevű felhasználó 1990 előtt született");
                        break;
                    }
                }
            }
        }
        System.out.println("-----------------------------------------------------------");
    }

    //3. Amelyik filmhez több mint egy kategória tartozik, annak a filmnek a neve kiíratásra kerül
    public static void mostCategoryMovie(Element rootElement){
        NodeList belong = rootElement.getElementsByTagName("Tartozik");

        System.out.println("-----------------------------------------------------------");
        System.out.println("3. Amelyik filmhez több mint egy kategória tartozik, annak a filmnek a neve kiíratásra kerül");

        //Kategória számláló tömb létrehozása
        int[] categoryCounter = new int[belong.getLength()];
        for (int i = 0; i < categoryCounter.length; i++) {
            categoryCounter[i] = 0;
        }

        //Iterálás a 'Tartozik' elemek között
        for(int i=0; i<belong.getLength(); i++){
            //Gyerekelemek azonosítása
            Node belongs = belong.item(i);

            NodeList belongChildren = belongs.getChildNodes();
            for(int j=0; j<belongChildren.getLength(); j++){
                Node belongChild = belongChildren.item(j);
                //Ha az elem egy gyerekelem
                if(belongChild.getNodeType() == Node.ELEMENT_NODE){
                    //Kategóriák számának lekérdezése
                    categoryCounter[i] = Integer.parseInt(belongChild.getTextContent());
                }
            }
        }

        //A legtöbb kategóriával rendelkező film keresése
        int max = categoryCounter[0];
        for (int i = 1; i < categoryCounter.length; i++) {
            if(categoryCounter[i] > max){
                max = categoryCounter[i];
            }
        }

        //Minden film kiíratása, mely a maximális értékű kategóriaszámmal rendelkezik
        for(int i=0; i<belong.getLength(); i++){
            //Ha rendelkezik a maximális értékű kategóriaszámmal
            if(categoryCounter[i] == max){
                Node belongs = belong.item(i);

                //A keresett film ID-je, azonosításra szorul
                int movieId = 0;

                //Gyerekelemek lekérdezése
                NamedNodeMap belongsAttributes = belongs.getAttributes();
                for(int j=0; j<belongsAttributes.getLength(); j++){
                    Node attribute =  belongsAttributes.item(j);
                    if(attribute.getNodeName().equals("film_id")){ //Ha megvan a film_id attribútum, értékének mentése a movieId-ba
                        movieId = Integer.parseInt(attribute.getNodeValue());
                    }
                }
                
                //Film megkeresése a filmId alapján
                NodeList filmek = rootElement.getElementsByTagName("Film");
                for(int j=0; j<filmek.getLength(); j++){
                    Node film = filmek.item(j);

                    //Film elem attribútumainak lekérdezése
                    NamedNodeMap filmAttributes = film.getAttributes();
                    for(int k=0; k<filmAttributes.getLength(); k++){
                        Node attribute = filmAttributes.item(k);
                        if(attribute.getNodeValue().equals(String.valueOf(movieId))){
                            //Meglett a keresett film; nevének kiíratása
                            NodeList filmChildren = film.getChildNodes();

                            for(int l=0; l<filmChildren.getLength(); l++){
                                Node filmChild = filmChildren.item(l);

                                //Cím kiíratása
                                if(filmChild.getNodeName().equals("Cím")){
                                    System.out.println("A legtöbb kategóriával rendelkező film címe: " + filmChild.getTextContent());
                                }
                            }
                        }
                    }
                }

            }
        }
        System.out.println("-----------------------------------------------------------");
    }

    //4. A legjobb értékeléssel rendelkező film neve kerül kiíratása
    public static void topRankedMovie(Element rootElement){
        //Értékelések lekérdezése
        NodeList rankings = rootElement.getElementsByTagName("Értékelés");

        //A maximális pontszám deklarálása
        double maxRanking = 0;

        //Értékelések iterációja
        for(int i=0; i<rankings.getLength(); i++){
            Node ranking = rankings.item(i);
            
            //Gyerekelemek lekérdezése
            NodeList rankingChildren = ranking.getChildNodes();
            for(int j=0; j<rankingChildren.getLength(); j++){
                Node rankingChild = rankingChildren.item(j);

                //Ha a gyerekelem 'Pontszám', és értéke nagyobb mint az eddig talált legnagyobb pontszám
                if(rankingChild.getNodeName().equals("Pontszám") && Double.parseDouble(rankingChild.getTextContent()) > maxRanking){
                    maxRanking = Double.parseDouble(rankingChild.getTextContent());
                }
            }
        }

        String movieId = "";

        //Értékelések újra iterációja a legnagyobb pontszámmal rendelkező film megtalálásához
        for(int i=0; i<rankings.getLength(); i++){
            Node ranking = rankings.item(i);
            
            //Gyerekelemek lekérdezése
            NodeList rankingChildren = ranking.getChildNodes();
            for(int j=0; j<rankingChildren.getLength(); j++){
                Node rankingChild = rankingChildren.item(j);

                //Ha a gyerekelem 'Pontszám', és értéke megegyezik a legnagyobb pontszámmal
                if(rankingChild.getNodeName().equals("Pontszám") && Double.parseDouble(rankingChild.getTextContent()) == maxRanking){
                    Node parent = rankingChild.getParentNode();

                    //Szülő attribútumok lekérdezése
                    NamedNodeMap parentAttributes = parent.getAttributes();
                    for(int k=0; k<parentAttributes.getLength(); k++){
                        if(parentAttributes.item(k).getNodeName().equals("film_id")){
                            movieId = parentAttributes.item(k).getNodeValue();
                        }
                    }
                }
            }
        }

        System.out.println("-----------------------------------------------------------");
        System.out.println("4. A legjobb értékeléssel rendelkező film neve kerül kiíratása");

        //Film elemek lekérdezése
        NodeList movies = rootElement.getElementsByTagName("Film");

        for(int i=0; i<movies.getLength(); i++){
            Node movie = movies.item(i);

            //Film attribútumok lekérdezése
            NamedNodeMap movieAttributes = movie.getAttributes(); 
            for(int j=0; j<movieAttributes.getLength(); j++){
                //Ha az attribútum 'film_id', és értéke megegyezik a movieId-vel
                if(movieAttributes.item(j).getNodeName().equals("film_id") && movieAttributes.item(j).getNodeValue().equals(movieId)){
                    //Film gyerekelemeinek lekérdezése
                    NodeList movieChildren = movie.getChildNodes();

                    for(int k=0; k<movieChildren.getLength(); k++){
                        Node child = movieChildren.item(k);
                        
                        if(child.getNodeName().equals("Cím")){
                            System.out.println("A legjobb értékeléssel rendelkező film a " +  child.getTextContent() +", értékelése: " + maxRanking);
                        }
                    }
                }
            }

        }

        System.out.println("-----------------------------------------------------------");
        
    }

    //5. A legtöbbszór kiosztott díj neve kerül kiíratásra
    public static void topWonAward(Element rootElement){
        //Díj elemek lekérdezése
        NodeList awards = rootElement.getElementsByTagName("Díj");

        int[] winCounter = new int[awards.getLength()];
        for(int i=0; i<winCounter.length; i++){
            winCounter[i] = 0;
        }

        //Iterálás a díjakon
        for(int i=0; i<awards.getLength(); i++){
            Node award = awards.item(i);

            int counter = 0;
            //Iterálás a díj gyerekelemein
            for(int j=0; j<award.getChildNodes().getLength(); j++){
                Node awardChild = award.getChildNodes().item(j);
                if(awardChild.getNodeName().equals("Nyertes")){
                    counter++;
                }
            }
            winCounter[i] = counter;
        }

        //Legtöbbször elnyert díjszám
        int maxWinnerCount = winCounter[0];
        for(int i=1; i<winCounter.length; i++){
            if(winCounter[i]>maxWinnerCount){
                maxWinnerCount = winCounter[i];
            }
        }

        //Újra iterálás a díjakon
        for(int i=0; i<awards.getLength(); i++){
            Node award = awards.item(i);

            //Ha a díj elnyerésének száma a maximálissal megegyezik
            if(winCounter[i] == maxWinnerCount){
                for(int j=0; j<award.getChildNodes().getLength(); j++){
                    //Gyerekelem lekérdezése
                    Node awardChild = award.getChildNodes().item(j);

                    if(awardChild.getNodeName().equals("Díj_típusa")){
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("5. A legtöbbszór kiosztott díj neve kerül kiíratásra");
                        System.out.println("A legtöbbször kiosztott díj neve: " + awardChild.getTextContent());
                        System.out.println("-----------------------------------------------------------");
                    }
                }
            }
        }

    }    

}
