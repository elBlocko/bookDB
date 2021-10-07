package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

import org.json.JSONException;
import org.json.JSONObject;

import logic.*;

public class TDatabase {
	public static final TDatabase TDatabase = new TDatabase();
	// sqlite jdbc in build path hinzufügen
	public static Connection connection;
	// private static final String DB_PATH = System.getProperty("user.home") + "/" +
	// "testdb.db";

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.println("Fehler beim Laden des JDBC-Treibers");
			e.printStackTrace();
		}
	}

	// Klasse als SINGLETON -->
	// es wird programmweit nur eine Instanz dieses Objektes gebildet
	// --------------------------------------------------------------------------

	private TDatabase() {
	}

	public static TDatabase getInstance() {
		return TDatabase;
	}

	// --------------------------------------------------------------------------

	public void connect() {
		try {
			if (connection != null)
				return;
			System.out.println("Creating Connection to Database...");
			connection = DriverManager.getConnection("jdbc:sqlite:" + TConstants.CDatabasePath);
			if (!connection.isClosed())
				System.out.println("...Connection established");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					if (!connection.isClosed() && connection != null) {
						connection.close();
						if (connection.isClosed())
							System.out.println("Connection to Database closed");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void disconnect() {
		try {
			if (connection != null) {
				connection.close();
				if (connection.isClosed())
					System.out.println("Connection to Database closed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*************************************************************************************
	 * API
	 */
	private String getApiKey() {
		String key = "";
		Scanner scan = null;
		try {
			scan = new Scanner(new File(TConstants.CApiKeyPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Die Textdatei mit dem API Key konnte nicht gefunden werden.");
		}
		key = scan.nextLine();
		System.out.println(key);
		scan.close();
		return key;
	}

	public String getJson(String searchquery) {
		String url = TConstants.CApiUrl + "?q=" + searchquery + "&maxResults=2&key=" + getApiKey();
		String JsonString = "";
		try {
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			// add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = con.getResponseCode();

			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			JsonString = response.toString();
			return JsonString;
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, "Fehler bei der angegebenen Url, bitte auth key prüfen.");

			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fehler bei der Internetverbindung.");
			e.printStackTrace();
		}
		return JsonString;
	}

} // eoc
