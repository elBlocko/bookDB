package logic;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import database.TDatabase;
import java.sql.*;

public class TGenreList extends ArrayList<TGenre> {
	private static final long serialVersionUID = -98788886516L;
	List<TGenre> FGenreList;

	public TGenreList(List<TGenre> AGenreList) {
		this.FGenreList = AGenreList;
	}

	// METHODEN ------------------------------------------------------------------	
	
	public void setContent() {
		int tempId;
		String tempName;	
		TGenre tempGenre;
		
		try {			
			Statement stmt = TDatabase.connection.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM [tblLocation];");
            while (rs.next()) {
            	tempName = rs.getString("txtName");	// Werte holen
            	tempId = rs.getInt("PKid");
            	
            	tempGenre = new TGenre(tempId, tempName); // Objekt erstellen
            	this.add(tempGenre); // objekt der Liste zufügen           	
            }
            rs.close();
            // TDatabase.connection.close();
			
		} catch (SQLException e) {			
			JOptionPane.showMessageDialog(null,"Fehler beim Laden der Daten in die Genre Liste");
		}

	}

}
