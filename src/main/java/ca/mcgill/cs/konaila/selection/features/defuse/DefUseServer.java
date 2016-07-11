package ca.mcgill.cs.konaila.selection.features.defuse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class DefUseServer {

	public static final String SERVER_URL = "http://annieying.ca:8845/DefUse";
	public static final String LOCAL_URL = "http://localhost:8845/DefUse";
	public static final String URL = SERVER_URL;

	public static final String urlForServer(int server) {
		//return LOCAL_URL;
		return "http://runaway.lan:" + (8845 + server) + "/DefUse";
	}

	public static List<KonailaVariableDef> analyzeDefUse(String code,
			Strategy strategy) {
		return analyzeDefUse(code, strategy,
				ParsingAttribute.JavaCompilationUnit, URL);
	}

	public static List<KonailaVariableDef> analyzeDefUse(String code,
			Strategy strategy, ParsingAttribute attribute, String url) {

		System.out.println("Accessing DefUse Server: " + url);
		System.out.println(attribute);
		// System.out.println(code);

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("format", "json"));
		nameValuePairs.add(new BasicNameValuePair("code", code));
		nameValuePairs.add(new BasicNameValuePair("parsing-attribute",
				attribute.name()));
		nameValuePairs.add(new BasicNameValuePair("strategy", strategy.name()));

		List<KonailaVariableDef> defs;
		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = client.execute(post);

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String line = "";
			String json = "";
			while ((line = rd.readLine()) != null) {
				json += line + "\n";
			}

//			System.out.println(json);
			defs = KonailaVariableDef.fromJson(json);

			for (KonailaVariableDef def : defs) {
				Iterator<KonailaVariableUse> it = def.getUses().iterator();
				while (it.hasNext()) {
					KonailaVariableUse use = it.next();
					if (use.getCharEnd() == def.getCharEnd()
							&& use.getCharStart() == def.getCharStart()) {
						it.remove();
					}
				}
			}

			return defs;
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

		return Collections.EMPTY_LIST;
	}

}
