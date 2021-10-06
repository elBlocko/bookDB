package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

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
	 * API*/
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
	
	public void getJson(String searchquery) {
		String url = TConstants.CApiUrl + "?q=" + searchquery + "&key=" + getApiKey();
		
		
		
	}
	
} // eoc
