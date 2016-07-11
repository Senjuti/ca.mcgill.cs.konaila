package ca.mcgill.cs.konaila.presentation.formatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class FormattingServer {

	static final String SERVER_URL = "http://annieying.ca:8846/Formatter"; 
	static final String LOCAL_URL = "http://localhost:8846/Formatter";
	public static final String URL = LOCAL_URL;


	public static synchronized String format(String code, How how, String url) {
		String formattedCode = format(code, how, -1, url);	
//		formattedCode = doNotWrapSingleClosingBracketInItsOwnLine(formattedCode);
		return formattedCode;
	}
	
	public static synchronized String format(String code, How how, int lineLength, String url) {
		String formattedCode = formatFromServer(code, how, -1, url);	
//		formattedCode = doNotWrapSingleClosingBracketInItsOwnLine(formattedCode);
		return formattedCode;
	}
	
	private static synchronized String formatFromServer(String code, How how, int lineLength, String url) {
		
		System.out.print("Accessing Formatting Server: " + url + "...");
//		System.out.println(code);
		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("format", "text"));
		nameValuePairs.add(new BasicNameValuePair("code", code));
		nameValuePairs.add(new BasicNameValuePair("how", how.name()));
		nameValuePairs.add(new BasicNameValuePair("line-length", Integer.toString(lineLength)));		

		String text = "";
		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			HttpResponse response = client.execute(post);

			// Get the response
			BufferedReader rd = new BufferedReader
			  (new InputStreamReader(response.getEntity().getContent()));
			    
			String line = "";
			while ((line = rd.readLine()) != null) {
			  text += line + "\n";
			} 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("done");
		
		return text;
	}
	
	public static String doNotWrapSingleClosingBracketInItsOwnLine(String summary) {
		return (summary+"\n").replace("\n}\n","}\n").trim();
	}
	
}
