package logic;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import database.TDatabase;
import java.sql.*;

public class TAuthorList extends ArrayList<TAuthor> {
	private static final long serialVersionUID = -123456789012345678L;
	List<TAuthor> FArtistList;

	public TAuthorList(List<TAuthor> AArtistList) {
		this.FArtistList = AArtistList;
	}

	// METHODEN ------------------------------------------------------------------

	public void setContent() {
		int tempId;
		String tempName;
		TAuthor tempArtist;

		try {
			Statement stmt = TDatabase.connection.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM [tblAuthor];");
			while (rs.next()) {
				tempName = rs.getString("txtName"); // Werte holen
				tempId = rs.getInt("PKid");

				tempArtist = new TAuthor(tempId, tempName); // Objekt erstellen
				this.add(tempArtist); // objekt der Liste zuf�gen
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim Laden der Daten in die Autor Liste");
		}

	}

	public void delete(int ID) {
		String sql = "DELETE FROM [tblAuthor] WHERE PKid = " + ID + ";";
		try {
			Statement stmt = TDatabase.connection.createStatement();
			// execute the delete statement
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim l�schen der Daten in der Autor Liste");
		}

	}

}
