package logic;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import database.TDatabase;
import java.sql.*;

public class TLocationList extends ArrayList<TLocation> {
	private static final long serialVersionUID = -9875136516L;
	List<TLocation> FLocationList;

	public TLocationList(List<TLocation> ALocationList) {
		this.FLocationList = ALocationList;
	}

	// METHODEN ------------------------------------------------------------------	
	
	public void setContent() {
		int tempId;
		String tempName;	
		TLocation tempLocation;
		
		try {			
			Statement stmt = TDatabase.connection.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM [tblLocation];");
            while (rs.next()) {
            	tempName = rs.getString("txtName");	// Werte holen
            	tempId = rs.getInt("PKid");
            	
            	tempLocation = new TLocation(tempId, tempName); // Objekt erstellen
            	this.add(tempLocation); // objekt der Liste zufügen           	
            }
            rs.close();
            // TDatabase.connection.close();
			
		} catch (SQLException e) {			
			JOptionPane.showMessageDialog(null,"Fehler beim Laden der Daten in die Location Liste");
		}

	}

}
