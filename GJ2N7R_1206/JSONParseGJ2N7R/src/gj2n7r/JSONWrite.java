package gj2n7r;

import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONWrite {
    public static void main(String[] args) {
        
        JSONArray kurzusok = new JSONArray();
        kurzusok.add(createCourse("Valószínűségszámítás", "5", "A1-320", "Hétfő, 10-12", "Dr. Fegyverneki Sándor", "-"));
        kurzusok.add(createCourse("Valószínűségszámítás", "5", "A1-320", "Hétfő, 10-12", "Dr. Túri József", "-"));
        kurzusok.add(createCourse("Valószínűségszámítás", "5", "A1-312", "Szerda, 10-12", "Dr. Fegyverneki Sándor", "-"));
        kurzusok.add(createCourse("Mesterséges intelligencia", "5", "XXXII. előadó", "Kedd, 10-12", "Kunné Tamás Judit", "Fazekas Levente"));
        kurzusok.add(createCourse("Mesterséges intelligencia", "5", "III. előadó", "Csütörtök, 10-12", "Fazekas Levente", "-"));
        kurzusok.add(createCourse("Adatkezelés XML környezetben", "5", "XXXII. előadó", "Kedd, 12-14", "Bednarik László", "-"));
        kurzusok.add(createCourse("Adatkezelés XML környezetben", "5", "Inf-101", "Szerda, 12-14", "Bednarik László", "-"));
        kurzusok.add(createCourse("Algoritmusok és vizsgálatuk", "5", "A1-320", "Kedd, 14-16", "Házy Attila", "-"));
        kurzusok.add(createCourse("Algoritmusok és vizsgálatuk", "5", "A1-320", "Kedd, 16-18", "Házy Attila", "-"));
        kurzusok.add(createCourse("Vállalati információs rendszerek fejlesztése", "5", "Inf/101", "Szerda, 14-16", "Sasvári Péter", "-"));
        kurzusok.add(createCourse("Vállalati információs rendszerek fejlesztése", "5", "Inf/101", "Szerda, 18-20", "Sasvári Péter", "-"));
        kurzusok.add(createCourse("Webtechnológiák", "5", "A1-305", "Hétfő, 14-16", "Dr. Agárdi Anita", "-"));
        kurzusok.add(createCourse("Webtechnológiák", "5", "A1-305", "Hétfő, 16-18", "Dr. Agárdi Anita", "-"));

        JSONObject kurzusokElement = new JSONObject();
        kurzusokElement.put("kurzus", kurzusok);

        JSONObject rootElement = new JSONObject();
        rootElement.put("kurzusok", kurzusokElement);
        JSONObject object = new JSONObject();
        object.put("GJ2N7R_kurzusfelvetel", rootElement);

        saveFile(object, "kurzusfelvetelGJ2N7R1.json");
        writeOnConsole(object);
    }

    public static void writeOnConsole(JSONObject object){
        System.out.println("A kurzusok tartalma:\n");
		JSONObject rootElement = (JSONObject) object.get("GJ2N7R_kurzusfelvetel");
        JSONObject kurzusokElement = (JSONObject) rootElement.get("kurzusok");
		JSONArray kurzusok = (JSONArray) kurzusokElement.get("kurzus");
		for(int i=0; i<kurzusok.size(); i++) {
			JSONObject kurzus = (JSONObject) kurzusok.get(i);
            System.out.println("Kurzus neve: " + kurzus.get("kurzusnev"));
			System.out.println("Kredit: "+kurzus.get("kredit"));
			System.out.println("Időpont: "+kurzus.get("hely"));
			System.out.println("Helyszín: "+kurzus.get("idopont"));
			System.out.println("Oktató: "+kurzus.get("oktató") + "\n");
		}
    }

    public static void saveFile(JSONObject object, String filePath){
        try{
            FileWriter writer = new FileWriter(filePath);
            writer.write(object.toJSONString());
            writer.flush();
            writer.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static JSONObject createCourse(String name, String credits, String room, String time, String instructor, String tutor){
        JSONObject course = new JSONObject();
        
        course.put("kurzusnev", name);
        course.put("kredit", credits);
        course.put("hely", room);
        course.put("idopont", time);
        course.put("oktató", instructor);
        if(!tutor.equals("-")){
            course.put("óraadó", tutor);
        }

        return course;
    }
}
