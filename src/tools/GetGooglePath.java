package tools;
import java.net.*;
import java.io.*;

public class GetGooglePath {
	
	private static String getUrlContents(String theUrl)
	  {
	    StringBuilder content = new StringBuilder();
	    try
	    {

	      URL url = new URL(theUrl);
	      URLConnection urlConnection = url.openConnection();
	      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	      String line;
	      while ((line = bufferedReader.readLine()) != null)
	      {
	        content.append(line + "\n");
	      }
	      bufferedReader.close();
	    }
	    catch(Exception e)
	    {
	      e.printStackTrace();
	    }
	    return content.toString();
	  }

	public static String getlink(String question) throws IOException
	{
		String start = question.split(",")[0];
		String finish = question.split(",")[1];
		String output  = getUrlContents("https://maps.googleapis.com/maps/api/directions/json?sensor=false&origin=%22+"+start+"+%22&destination=%22+"+finish);
		output = "something??";
		return output;
	}
	
}
