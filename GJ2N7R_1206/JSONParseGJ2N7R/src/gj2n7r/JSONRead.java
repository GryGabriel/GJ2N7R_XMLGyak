package gj2n7r;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JsonObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONRead {
    public static void main(String[] args) {
     
        JSONParser parser = new JSONParser();

        try{

            JSONObject object = (JSONObject) parser.parse(new FileReader("GJ2N7R_kurzusfelvetel.json"));

            //Gyökérelem lekérdezése
            JSONObject rootElement = (JSONObject) object.get("GJ2N7R_kurzusfelvetel");

            //Hallgató kurzusainak lekérdezése:
            JSONObject kurzusokElement = (JSONObject) rootElement.get("kurzusok");
            JSONArray kurzusok = (JSONArray) kurzusokElement.get("kurzus");
            
            System.err.println("\nA hallgató kurzusai: ");
            for(int i=0; i<kurzusok.size(); i++){
                JSONObject kurzus = (JSONObject) kurzusok.get(i);

                System.out.println("\n");
                System.out.println("Neve: " + kurzus.get("kurzusnev"));
                System.out.println("Kreditszáma: " + kurzus.get("kredit"));
                System.out.println("Helye: " + kurzus.get("hely"));
                System.out.println("Időpontja: " + kurzus.get("idopont"));
                if(kurzus.get("oktató") != null){
                    System.out.println("Oktatója: " + kurzus.get("oktató"));
                }else
                if(kurzus.get("óraadó") != null){
                    System.out.println("Óraadója: " + kurzus.get("óraadó"));
                }else
                System.out.println("\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
