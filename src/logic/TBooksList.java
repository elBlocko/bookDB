package logic;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import database.TDatabase;
import java.sql.*;
import userInterface.*;

public class TBooksList extends ArrayList<TBook> {
	private static final long serialVersionUID = -98755586516L;
	List<TBook> FBooksList;

	public TBooksList(List<TBook> ABooksList) {
		this.FBooksList = ABooksList;
	}

	// METHODEN ------------------------------------------------------------------

	public void setContent(TAuthorList Authorlist, TLocationList LocationList, TGenreList Genrelist) {
		int tempId;
		int tempYear;
		int tempFKauthor;
		int tempFKlocation;
		int tempFKgenre;
		String tempName;
		String tempIsbn;

		TBook tempBook;

		try {
			Statement stmt = TDatabase.connection.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM [tblBooks];");
			while (rs.next()) {
				tempName = rs.getString("txtName"); // Werte holen
				tempId = rs.getInt("PKid");
				tempYear = rs.getInt("numYear");
				tempIsbn = rs.getString("txtIsbn");
				tempFKauthor = rs.getInt("FKauthor");
				tempFKlocation = rs.getInt("FKlocation");
				tempFKgenre = rs.getInt("FKgenre");

				TAuthor tempAuthor= new TAuthor(-1,null);
				for (TAuthor Author: Authorlist) {
					if (Author.getID() == tempFKauthor) {
						tempAuthor = Author;
						break;
					}
				}
				TLocation tempLocation = new TLocation(-1, null);
				for (TLocation Location  : LocationList) {
					if (Location.getID() == tempFKlocation) {
						tempLocation = Location;
						break;
					}
				}
				TGenre tempGenre = new TGenre(-1, null);
				for (TGenre Genre : Genrelist) {
					if (Genre.getID() == tempFKgenre) {
						tempGenre = Genre;
						break;
					}
				}
				

				tempBook = new TBook(tempId, tempName, tempAuthor, tempYear, tempIsbn, tempLocation, tempGenre); // Objekt
																													// erstellen
				this.add(tempBook); // objekt der Liste zufügen
			}
			rs.close();			

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim Laden der Daten in die Bücher Liste");
		}

	}

	public void delete(int ID) {
		String sql = "DELETE FROM [tblBooks] WHERE PKid = " + ID + ";";
		try {
			Statement stmt = TDatabase.connection.createStatement();
			// execute the delete statement
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim löschen der Daten in der Bücher Liste");
		}

	}

	
}
