package userInterface;

import java.util.ArrayList;

import database.TDatabase;
import logic.*;


public class TListTester {

	public static void main(String[] args) {
		// establish connection to database
		TDatabase database1 = TDatabase.getInstance();
		database1.connect();
		// create and fill List
		TAuthorList AuthorList1; // declare
		AuthorList1 = new TAuthorList(new ArrayList<TAuthor>()); // init
		AuthorList1.setContent(); // fill list
		
		// Test output
		System.out.println(AuthorList1.get(0).getName()); // output ListObject[0].Name
		// close connection to database
		database1.disconnect();
	}

}
