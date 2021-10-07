package logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TJsonList extends ArrayList<TJson> {
	private static final long serialVersionUID = -987522286516L;
	List<TJson> FJsonList;

	public TJsonList(List<TJson> AJsonList) {
		this.FJsonList = AJsonList;
	}

	// METHODEN
	public void parseJson(String data) throws JSONException {
		TJson tempJson = null;
		int tempId = -1;
		String tempName = "";
		String tempAuthor = "";
		String tempYear = "";
		String tempIsbn = "";
		String tempGenre = "";
		
		
		JSONObject jObject = new JSONObject(data);
		JSONArray jsonArray = (JSONArray) jObject.get("items");
		Iterator<TJson> iterator = ((List<TJson>) jsonArray).iterator();
		while(iterator.hasNext()) {
		   
		}
		
	}

} // eoc
