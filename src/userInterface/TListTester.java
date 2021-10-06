package userInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

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

		/*
		 * TLocationList LocationList1; LocationList1 = new TLocationList(new
		 * ArrayList<TLocation>()); LocationList1.setContent();
		 */
		// Test output
		// System.out.println(AuthorList1.get(3).getName()); // output
		// ListObject[0].Name
//		int i = 0;
//		int PKid = 17;
//		for (TAuthor tempAuthor : AuthorList1) {
//			if (tempAuthor.getID() == PKid) {
//				tempAuthor.delete(PKid);			
//				AuthorList1.remove(i);				
//				return;
//			}
//			i++;
//		}
//		
//		TAuthor tempAuthor = new TAuthor(-1, "Roman");
//		int PKid = tempAuthor.save("Roman");
//		AuthorList1.add(tempAuthor);
//		System.out.println(PKid);
		
//		Scanner scan = null;
//		try {
//		    scan = new Scanner(new File(TConstants.CApiKeyPath));
//		} catch (FileNotFoundException e) {
//		    e.printStackTrace();
//		    JOptionPane.showMessageDialog(null, "Die Textdatei mit dem API Key konnte nicht gefunden werden.");
//		}
//		String key = scan.nextLine();		
//		System.out.println(key);
//		scan.close();
		// System.out.println(LocationList1.get(0).getName()); // output
		// ListObject[0].Name

		// close connection to database
		database1.disconnect();
	}

}
