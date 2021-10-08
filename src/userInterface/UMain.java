package userInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

import logic.*; // importiert alle Klassen aus dem Package
import database.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ListSelectionModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UMain extends JFrame {

	private JPanel contentPane;
	private JTable grdMain;
	private JTextField txtListSearch;
	private JTextField txtAuthor;
	private JTextField txtYear;
	private JTextField txtGenre;
	private JTextField txtIsbn;
	private JTextField txtWebSearch;
	private JTable grdWeb;
	private JTextField txtName;
	private JTextField txtLocation;

	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnSave;

	JButton btnListSearch;
	JButton btnRefresh;

	public TAuthorList Authorlist1;
	public TLocationList Locationlist1;
	public TGenreList Genrelist1;
	public TBooksList Bookslist1;
	public TJsonList JsonList1;
	
	
	private int rowIndexGrdMain;
	private int rowIndexGrdWeb;

	// INIT GRID HEADERS
	Object[] columns = { "ID", "Buchtitel", "Autor", "Genre", "Erscheinungsjahr", "Isbn", "Regalplatz" };
	DefaultTableModel modelList = new DefaultTableModel();
	DefaultTableModel modelWeb = new DefaultTableModel();

	Object[] rowList = new Object[7];
	Object[] rowWeb = new Object[7];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UMain frame = new UMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public UMain() {
		setTitle("B\u00FCcherverwaltung v 1.0");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				// JOptionPane.showMessageDialog(null,"open window");
				connectDatabase();
				createLists();
				setListContent();
				setGridContent();

			}

			@Override
			public void windowClosed(WindowEvent e) {
				try {
					TDatabase.connection.close();

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*******************************************************************************************/
		/*
		 * Grid List
		 *******************************************************************************/

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 774, 202);
		contentPane.add(scrollPane);
		grdMain = new JTable();
		grdMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowIndexGrdMain = grdMain.getSelectedRow();
				// JOptionPane.showMessageDialog(null, rowIndexGrdMain);
			}
		});
		grdMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// my Method
		setGrdMainHeader();
		setColumnIdInvisible();
		scrollPane.setViewportView(grdMain);

		/*
		 * Grid Web
		 ********************************************************************************/

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 312, 774, 90);
		contentPane.add(scrollPane_1);
		grdWeb = new JTable();
		grdWeb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowIndexGrdWeb = grdWeb.getSelectedRow();
			}
		});
		grdWeb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setGrdWebHeader();
		scrollPane_1.setViewportView(grdWeb);

		/*
		 * Toolbar Search List
		 **********************************************************************/

		JToolBar tbSearchBarList = new JToolBar();
		tbSearchBarList.setBounds(10, 11, 416, 33);
		contentPane.add(tbSearchBarList);

		txtListSearch = new JTextField();
		txtListSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchGrdMain();
				}
			}
		});
		txtListSearch.setToolTipText("");
		tbSearchBarList.add(txtListSearch);
		txtListSearch.setColumns(10);

		btnListSearch = new JButton("suchen");
		btnListSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchGrdMain();
			}
		});
		tbSearchBarList.add(btnListSearch);

		btnRefresh = new JButton("aktualisieren");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setGridContent();
			}
		});
		tbSearchBarList.add(btnRefresh);

		/*
		 * Toolbar Tools (BUTTONS)
		 *****************************************************************/

		JToolBar tbTools = new JToolBar();
		tbTools.setBounds(436, 11, 348, 33);
		contentPane.add(tbTools);

		btnAdd = new JButton("hinzuf\u00FCgen");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewBook();
			}
		});
		tbTools.add(btnAdd);

		btnEdit = new JButton("bearbeiten");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editSelectedBook();
			}
		});
		tbTools.add(btnEdit);

		btnDelete = new JButton("l\u00F6schen");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSelectedBook();
			}
		});
		tbTools.add(btnDelete);

		btnSave = new JButton("speichern");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateChanges();
			}
		});
		btnSave.setEnabled(false);
		tbTools.add(btnSave);

		/*
		 * Toolbar Web Search
		 *************************************************************************/

		JToolBar tbSearchBarWeb = new JToolBar();
		tbSearchBarWeb.setBounds(10, 268, 416, 33);
		contentPane.add(tbSearchBarWeb);

		txtWebSearch = new JTextField();
		txtWebSearch.setToolTipText("");
		txtWebSearch.setColumns(10);
		tbSearchBarWeb.add(txtWebSearch);

		JButton btnWebSearch = new JButton("suchen");
		btnWebSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getWebResult();
				} catch (JSONException | UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tbSearchBarWeb.add(btnWebSearch);
		
		JButton btnAdd_1 = new JButton("hinzuf\u00FCgen");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addWebBook();
			}
		});
		tbSearchBarWeb.add(btnAdd_1);

		/*
		 * Panel New Book
		 ***************************************************************************/

		JPanel pnlNewBook = new JPanel();
		pnlNewBook.setBounds(10, 430, 774, 96);
		contentPane.add(pnlNewBook);
		pnlNewBook.setLayout(null);

		// Toolbar Name

		JToolBar tbNameNew = new JToolBar();
		tbNameNew.setBounds(10, 11, 211, 33);
		pnlNewBook.add(tbNameNew);

		JLabel lblName = new JLabel("Titel: ");
		tbNameNew.add(lblName);

		txtName = new JTextField();
		txtName.setColumns(10);
		tbNameNew.add(txtName);

		// Toolbar Author

		JToolBar tbAuthorNew = new JToolBar();
		tbAuthorNew.setBounds(231, 11, 211, 33);
		pnlNewBook.add(tbAuthorNew);

		JLabel lblAutor = new JLabel("Autor: ");
		tbAuthorNew.add(lblAutor);

		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		tbAuthorNew.add(txtAuthor);

		// Toolbar Year

		JToolBar tbYearNew = new JToolBar();
		tbYearNew.setBounds(452, 11, 211, 33);
		pnlNewBook.add(tbYearNew);

		JLabel lblYear = new JLabel("Erscheinungsjahr: ");
		tbYearNew.add(lblYear);

		txtYear = new JTextField();
		txtYear.setColumns(10);
		tbYearNew.add(txtYear);

		// Toolbar Genre

		JToolBar tbGenreNew = new JToolBar();
		tbGenreNew.setBounds(10, 52, 211, 33);
		pnlNewBook.add(tbGenreNew);

		JLabel lblGenre = new JLabel("Stichwort: : ");
		tbGenreNew.add(lblGenre);

		txtGenre = new JTextField();
		txtGenre.setColumns(10);
		tbGenreNew.add(txtGenre);

		// Toolbar ISBN

		JToolBar tbIsbnNew = new JToolBar();
		tbIsbnNew.setBounds(231, 55, 211, 33);
		pnlNewBook.add(tbIsbnNew);

		JLabel lblIsbn = new JLabel("ISBN: ");
		tbIsbnNew.add(lblIsbn);

		txtIsbn = new JTextField();
		txtIsbn.setColumns(10);
		tbIsbnNew.add(txtIsbn);

		// Toolbar Location

		JToolBar tbLocationNew = new JToolBar();
		tbLocationNew.setBounds(452, 55, 211, 33);
		pnlNewBook.add(tbLocationNew);

		JLabel lblLocation = new JLabel("Regalplatz: ");
		tbLocationNew.add(lblLocation);

		txtLocation = new JTextField();
		txtLocation.setColumns(10);
		tbLocationNew.add(txtLocation);

	} // eomain

	// METHODEN
	// *********************************************************************************
	/********************************************************************************************/

	void setGrdMainHeader() {

		modelList.setColumnIdentifiers(columns);
		grdMain.setModel(modelList);

	}

	void setGrdWebHeader() {
		modelWeb.setColumnIdentifiers(columns);
		grdWeb.setModel(modelWeb);

	}

	void connectDatabase() {
		// establish connection to database
		TDatabase database1 = TDatabase.getInstance();
		database1.connect();
	}

	void createLists() {
		Authorlist1 = new TAuthorList(new ArrayList<TAuthor>()); // init
		Locationlist1 = new TLocationList(new ArrayList<TLocation>()); // init
		Genrelist1 = new TGenreList(new ArrayList<TGenre>()); // init
		Bookslist1 = new TBooksList(new ArrayList<TBook>()); // init
		JsonList1 = new TJsonList(new ArrayList<TJson>()); // init

	}

	void setListContent() {
		Authorlist1.setContent(); // fill list
		Locationlist1.setContent(); // fill list
		Genrelist1.setContent(); // fill list
		Bookslist1.setContent(Authorlist1, Locationlist1, Genrelist1); // fill list
	}

	void setGridContent() {
		modelList.setRowCount(0);
		for (int i = 0; i < Bookslist1.size(); i++) {

			rowList[0] = Bookslist1.get(i).getID();
			rowList[1] = Bookslist1.get(i).getName();
			rowList[2] = Bookslist1.get(i).getAuthor().getName();
			rowList[3] = Bookslist1.get(i).getGenre().getName();
			rowList[4] = Bookslist1.get(i).getYear();
			rowList[5] = Bookslist1.get(i).getIsbn();
			rowList[6] = Bookslist1.get(i).getLocation().getName();

			modelList.addRow(rowList);
		}
	}

	void setColumnIdInvisible() {
		grdMain.getColumn("ID").setMinWidth(0);
		grdMain.getColumn("ID").setMaxWidth(0);
	}

	void deleteSelectedBook() {

		int PKid = Bookslist1.get(rowIndexGrdMain).getID();
		Bookslist1.delete(PKid);
		for (TBook Book : Bookslist1) {
			if (Book.getID() == PKid) {
				Bookslist1.remove(Book);
				break;
			}
		}
		setGridContent();
	}

	void addNewBook() {
		try {
			if (txtLocation.getText().equals("") || txtAuthor.getText().equals("") || txtGenre.getText().equals("")
					|| txtName.getText().equals("") || txtIsbn.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen");
				return;
			}

			// TODO : doppelte Einträge in der Datenbank verhindern
			TLocation tempLocation = new TLocation(-1, txtLocation.getText());
			int FKlocation = tempLocation.save(txtLocation.getText());
			Locationlist1.add(tempLocation);

			TAuthor tempAuthor = new TAuthor(-1, txtAuthor.getText());
			int FKauthor = tempAuthor.save(txtAuthor.getText());
			Authorlist1.add(tempAuthor);

			TGenre tempGenre = new TGenre(-1, txtGenre.getText());
			int FKgenre = tempGenre.save(txtGenre.getText());
			Genrelist1.add(tempGenre);

			String tempName = txtName.getText();
			String tempIsbn = txtIsbn.getText();

			int tempYear = Integer.parseInt(txtYear.getText());
			TBook tempBook = new TBook(-1, tempName, tempAuthor, tempYear, tempIsbn, tempLocation, tempGenre);
			tempBook.save(tempName, FKauthor, tempYear, tempIsbn, FKlocation, FKgenre);
			Bookslist1.add(tempBook);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Fehler beim erstellen eines Neuen Buches," + "die Jahreszahl darf nur 4 Ziffern haben");
		}

		setGridContent();
		clearTextFields();
	}

	void clearTextFields() {

		txtName.setText("");
		txtIsbn.setText("");
		txtGenre.setText("");
		txtAuthor.setText("");
		txtYear.setText("");
		txtLocation.setText("");
	}

	void editSelectedBook() {
		btnSave.setEnabled(true);
		try {
			txtName.setText(Bookslist1.get(rowIndexGrdMain).getName());
			txtIsbn.setText(Bookslist1.get(rowIndexGrdMain).getIsbn());
			txtGenre.setText(Bookslist1.get(rowIndexGrdMain).getGenre().getName());
			txtAuthor.setText(Bookslist1.get(rowIndexGrdMain).getAuthor().getName());
			txtYear.setText(String.valueOf(Bookslist1.get(rowIndexGrdMain).getYear()));
			txtLocation.setText(Bookslist1.get(rowIndexGrdMain).getLocation().getName());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fehler beim Übertragen des Buches in die Textfelder");
		}

	}

	void updateChanges() {
		int PKid = Bookslist1.get(rowIndexGrdMain).getID();
		TLocation tempLocation = Bookslist1.get(rowIndexGrdMain).getLocation();
		int FKlocation = Bookslist1.get(rowIndexGrdMain).getLocation().getID();
		TAuthor tempAuthor = Bookslist1.get(rowIndexGrdMain).getAuthor();
		int FKauthor = Bookslist1.get(rowIndexGrdMain).getAuthor().getID();
		TGenre tempGenre = Bookslist1.get(rowIndexGrdMain).getGenre();
		int FKgenre = Bookslist1.get(rowIndexGrdMain).getGenre().getID();
		
		
		
		try {
			if (txtLocation.getText().equals("") || txtAuthor.getText().equals("") || txtGenre.getText().equals("")
					|| txtName.getText().equals("") || txtIsbn.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen");
				return;
			}
			
			if (!Bookslist1.get(rowIndexGrdMain).getLocation().getName().equals(txtLocation.getText())) {
				tempLocation = new TLocation(-1, txtLocation.getText());
				FKlocation = tempLocation.save(txtLocation.getText());
				Locationlist1.add(tempLocation);
			} 
			
			
			if (!Bookslist1.get(rowIndexGrdMain).getAuthor().getName().equals(txtAuthor.getText())) {
			tempAuthor = new TAuthor(-1, txtAuthor.getText());
			FKauthor = tempAuthor.save(txtAuthor.getText());
			Authorlist1.add(tempAuthor);
			}
			
			if (!Bookslist1.get(rowIndexGrdMain).getGenre().getName().equals(txtGenre.getText())) {
			tempGenre = new TGenre(-1, txtGenre.getText());
			FKgenre = tempGenre.save(txtGenre.getText());
			Genrelist1.add(tempGenre);
			}
			
			String tempName = txtName.getText();
			String tempIsbn = txtIsbn.getText();

			int tempYear = Integer.parseInt(txtYear.getText());

			TBook tempBook = new TBook(-1, tempName, tempAuthor, tempYear, tempIsbn, tempLocation, tempGenre);
			tempBook.update(PKid, tempName, FKauthor, tempYear, tempIsbn, FKlocation, FKgenre);
			Bookslist1.removeAll(Bookslist1);
			Locationlist1.removeAll(Locationlist1);
			Genrelist1.removeAll(Genrelist1);
			Authorlist1.removeAll(Authorlist1);
			setListContent();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Fehler beim erstellen eines Neuen Buches," + "die Jahreszahl darf nur 4 Ziffern haben");
		}

		setGridContent();
		clearTextFields();
		btnSave.setEnabled(false);
	}

	void searchGrdMain() {
		int i = 0;
		boolean match = false;
		if (txtListSearch.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Bitte etwas in das Suchfeld eingeben.");
			return;
		}
		for (TBook tempBook : Bookslist1) {
			if (txtListSearch.getText().equals(tempBook.getIsbn())) {
				modelList.setRowCount(0);
				rowList[0] = Bookslist1.get(i).getID();
				rowList[1] = Bookslist1.get(i).getName();
				rowList[2] = Bookslist1.get(i).getAuthor().getName();
				rowList[3] = Bookslist1.get(i).getGenre().getName();
				rowList[4] = Bookslist1.get(i).getYear();
				rowList[5] = Bookslist1.get(i).getIsbn();
				rowList[6] = Bookslist1.get(i).getLocation().getName();

				modelList.addRow(rowList);
				match = true;
			}
			i++;
		}
		if (match == false) {
			JOptionPane.showMessageDialog(null,
					"Keine Einträge gefunden.\nHinweis: Es kann nur nach ISBN-Nummer gesucht werden!");

		}
	}
	
	private void getWebResult() throws JSONException, UnsupportedEncodingException {
		JsonList1.removeAll(JsonList1);
		if (txtWebSearch.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Bitte etwas in das Suchfeld eingeben.");
			return;
		}
		
		JsonList1.parseJson(txtWebSearch.getText());
		modelWeb.setRowCount(0);
		for (int i = 0; i < JsonList1.size(); i++) {

			rowWeb[0] = JsonList1.get(i).getID();
			rowWeb[1] = JsonList1.get(i).getName();
			rowWeb[2] = JsonList1.get(i).getAuthor();
			rowWeb[3] = JsonList1.get(i).getGenre();
			rowWeb[4] = JsonList1.get(i).getYear();
			rowWeb[5] = JsonList1.get(i).getIsbn();
			rowWeb[6] = "noch kein Platz zugeordnet";

			modelWeb.addRow(rowWeb);
		}
	}	
	
	private void addWebBook() {
		String tempName = JsonList1.get(rowIndexGrdWeb).getName();
		String Year = JsonList1.get(rowIndexGrdWeb).getYear();
		if (Year.length() > 4) {
		Year = Year.split("-")[1]; }
		int tempYear = Integer.parseInt(Year);
		String tempIsbn = JsonList1.get(rowIndexGrdWeb).getIsbn();
		
		TLocation tempLocation = new TLocation(-1, "noch kein Platz zugeordnet");
		int FKlocation = tempLocation.save("noch kein Platz zugeordnet");
		Locationlist1.add(tempLocation);

		TAuthor tempAuthor = new TAuthor(-1, JsonList1.get(rowIndexGrdWeb).getAuthor());
		int FKauthor = tempAuthor.save(JsonList1.get(rowIndexGrdWeb).getAuthor());
		Authorlist1.add(tempAuthor);

		TGenre tempGenre = new TGenre(-1, JsonList1.get(rowIndexGrdWeb).getGenre());
		int FKgenre = tempGenre.save(JsonList1.get(rowIndexGrdWeb).getGenre());
		Genrelist1.add(tempGenre);
		
		TBook tempBook = new TBook(-1, tempName, tempAuthor, tempYear, tempIsbn, tempLocation, tempGenre);
		tempBook.save(tempName, FKauthor, tempYear, tempIsbn, FKlocation, FKgenre);
		Bookslist1.add(tempBook);
		setGridContent();
	}
}
// eoc
