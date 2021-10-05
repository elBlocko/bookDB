package logic;

public class TLocation {
	// interface	
			// PROPERTIES
			private int FID;
			private String FName;
		// implement
			// CONSTRUCTOR
			// @param to @property --> compile+
			public TLocation(int AID, String AName) {
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
}
