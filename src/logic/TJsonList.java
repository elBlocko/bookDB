package logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import database.TDatabase;

public class TJsonList extends ArrayList<TJson> {
	private static final long serialVersionUID = -987522286516L;
	List<TJson> FJsonList;

	public TJsonList(List<TJson> AJsonList) {
		this.FJsonList = AJsonList;
	}

	// METHODEN
	public void parseJson(String searchquery) throws JSONException {
		TDatabase database1 = TDatabase.getInstance();
		String data = database1.getJson(searchquery);
		//System.out.println(data);
		JSONObject jObjectMain = new JSONObject(data);
		// JSONArray jsonArray = (JSONArray) jObject.get("items");
		JSONArray jsonArrayItems = jObjectMain.getJSONArray("items");
		for (int i = 0; i < jsonArrayItems.length(); i++) {
			// String tempId = jsonArrayItems.getJSONObject(i).getString("id");

			String tempName = jsonArrayItems.getJSONObject(i).getJSONObject("volumeInfo").getString("title");
			//int tempId = Integer.parseInt(jsonArrayItems.getJSONObject(i).getString("id"));
			String tempYear = jsonArrayItems.getJSONObject(i).getJSONObject("volumeInfo").getString("publishedDate");
			String tempGenre = jsonArrayItems.getJSONObject(i).getJSONObject("volumeInfo").getString("subtitle");
			String tempAuthor = jsonArrayItems.getJSONObject(i).getJSONObject("volumeInfo").getJSONArray("authors")
					.getString(0);
			String tempIdentifier = jsonArrayItems.getJSONObject(i).getJSONObject("volumeInfo")
					.getJSONArray("industryIdentifiers").get(0).toString();
			String tempIsbn = tempIdentifier.split("\"")[3];

			TJson tempJson = new TJson(-1, tempName, tempAuthor, tempYear, tempIsbn, tempGenre); // Objekt
			// erstellen
			this.add(tempJson);

		}
	}
} // eoc
