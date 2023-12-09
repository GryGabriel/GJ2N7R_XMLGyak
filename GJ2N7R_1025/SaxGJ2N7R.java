package hu.saxGJ2N7R;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxGJ2N7R{
	public static void main(String[] args) {
		try {
		File inputFile = new File("GJ2N7R_kurzusfelvetel.xml");
			
	        SAXParserFactory factory = SAXParserFactory.newInstance();
	        SAXParser saxParser = factory.newSAXParser();
	        NewHandler customHandler = new NewHandler();
	        saxParser.parse(inputFile, customHandler);
	    } catch (Exception e) {
	         e.printStackTrace();
	    }
	}   
}

class NewHandler extends DefaultHandler{
	static int indent = 0;
	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException{
		for(int i = 0; i < indent; i++){
			System.out.print("    ");
		}
		System.out.println("start");
		indent++;
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		indent--;
		
		for(int i = 0; i < indent; i++){
			System.out.print("    ");
		}
		System.out.print("end");
	}
	
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		String token = new String(ch, start, length);
		
		for(int i = 0; i < indent; i++){
			System.out.print("    ");
		}
		System.out.println(token);
		
	}
}
