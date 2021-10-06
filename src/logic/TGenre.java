package logic;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import database.TDatabase;

public class TGenre {
	// interface
	// PROPERTIES
	private int FID;
	private String FName;

	// implement
	// CONSTRUCTOR
	// @param to @property --> compile+
	public TGenre(int AID, String AName) {
		this.FID = AID;
		this.FName = AName;
	}

	// ***********************************************************
	// PROPERTY READ id WRITE id
	public int getID() {
		return FID;
	}

	public void setID(int ID) {
		this.FID = ID;
	}

	// PROPERTIE read Name write Name
	public String getName() {
		return FName;
	}

	public void setName(String Name) {
		this.FName = Name;
	}

	/***************************************************************
	 * METHODEN
	 */
	public void delete(int ID) {
		String sql = "DELETE FROM [tblGenre] WHERE PKid = " + ID + ";";
		try {
			Statement stmt = TDatabase.connection.createStatement();
			// execute the delete statement
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim löschen der Daten in der Genre Tabelle");
		}
	}

	public int save(String Name) {
		String sql = "insert into tblGenre (txtName) values ('" + Name + "');";
		int PKid = -1;
		try {
			Statement stmt = TDatabase.connection.createStatement();
			// execute the insert statement
			stmt.executeUpdate(sql);
			PKid = stmt.getGeneratedKeys().getInt(1);
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim speichern der Daten in der Genre Tabelle");
		}
		return PKid;
	}
}
