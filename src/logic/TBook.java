package logic;

public class TBook {
	// interface
	// PROPERTIES
	private int FID;
	private String FName;
	private TAuthor FAuthor;
	private int FYear;
	private String FIsbn;
	private TLocation FLocation;
	private TGenre FGenre;

	// implement
	// CONSTRUCTOR
	// @param to @property --> compile+
	public TBook(int AID, String AName, TAuthor AAuthor, int AYear, String AIsbn, TLocation ALocation, TGenre AGenre) {
		this.FID = AID;
		this.FName = AName;
		this.FAuthor = AAuthor;
		this.FYear = AYear;
		this.FIsbn = AIsbn;
		this.FLocation = ALocation;
		this.FGenre = AGenre;
	}

	// ***********************************************************
	// PROPERTY READ id WRITE id
	public int getID() {
		return FID;
	}

	public void setID(int ID) {
		this.FID = ID;
	}

	// PROPERTY r/w Year
	public int getYear() {
		return FYear;
	}

	public void setIYear(int Year) {
		this.FYear = Year;
	}

	// PROPERTY read Name write Name
	public String getName() {
		return FName;
	}

	public void setName(String Name) {
		this.FName = Name;
	}

	// PROPERTY r/w Isbn
	public String getIsbn() {
		return FIsbn;
	}

	public void setIsbn(String Isbn) {
		this.FIsbn = Isbn;
	}

	// PROPERTY r/w Author
	public TAuthor getAuthor() {
		return FAuthor;
	}

	public void setAuthor(TAuthor Author) {
		this.FAuthor = Author;
	}

	// PROPERTY r/w Location
	public TLocation getLocation() {
		return FLocation;
	}

	public void setLocation(TLocation Location) {
		this.FLocation = Location;
	}

	// PROPERTY r/w Genre
	public TGenre getGenre() {
		return FGenre;
	}

	public void setAuthor(TGenre Genre) {
		this.FGenre = Genre;
	}

}
