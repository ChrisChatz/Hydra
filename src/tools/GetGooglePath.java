package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

import sun.applet.Main;

public class GetGooglePath {
	
	  private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }
	  
	  // 
	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      JSONObject json = new JSONObject(jsonText);
		      return json;
		    } finally {
		      is.close();
		    }
		  }
	  
	  // This method takes two arguments (source, destination) and returns the path from Google Maps API.
	  public static JSONObject jsonObject(String start, String finish) throws IOException, JSONException{

		JSONObject json = readJsonFromUrl("https://maps.googleapis.com/maps/api/directions/json?sensor=false&origin=%22+"+start+"+%22&destination=%22+"+finish);

		return json;
		  
		  
	  }
	  // Main method for test only
	  public static void main(String[] args) throws IOException, JSONException {
		
		  JSONObject json = jsonObject("Pikermi", "Faliro");
		  System.out.println(json.toString());
	}

}
