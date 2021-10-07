package logic;

public class TJson {
	// interface
	// PROPERTIES
	private int FID;
	private String FName;
	private String FAuthor;
	private String FYear;
	private String FIsbn;
	private String FGenre;
	// CONSTRUCTOR
	// @param to @property
	public TJson(int AID, String AName, String AAuthor, String AYear, String AIsbn, String AGenre) {
		this.FID = AID;
		this.FName = AName;
		this.FAuthor = AAuthor;
		this.FYear = AYear;
		this.FIsbn = AIsbn;
		this.FGenre = AGenre;
	}	
	// ID
	public int getID() {
		return FID;
	}
	public void setID(int ID) {
		this.FID = ID;
	}	
	// NAME
	public String getName() {
		return FName;
	}	
	public void setName(String Name) {
		this.FName = Name;
	}
	// AUTHOR
	public String getAuthor() {
		return FAuthor;
	}
	public void setAuthor(String Author) {
		this.FAuthor = Author;
	}
	// YEAR
	public String getYear() {
		return FYear;
	}
	public void setYera(String Year) {
		this.FYear = Year;
	}
	// ISBN
	public String getIsbn() {
		return FIsbn;
	}
	public void setIsbn(String Isbn) {
		this.FIsbn = Isbn;
	}
	// GENRE
	public String getGenre() {
		return FGenre;
	}
	public void setGenre(String Genre) {
		this.FGenre = Genre;
	}
} // eoc
