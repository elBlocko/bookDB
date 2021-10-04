package userInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*******************************************************************************************/
		/* Grid List *******************************************************************************/
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 774, 202);
		contentPane.add(scrollPane);
		
		grdMain = new JTable();
		scrollPane.setViewportView(grdMain);
		
		/* Grid Web ********************************************************************************/
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 312, 774, 90);
		contentPane.add(scrollPane_1);
		
		grdWeb = new JTable();
		scrollPane_1.setViewportView(grdWeb);
		
		/* Toolbar Search List **********************************************************************/
		

		JToolBar tbSearchBarList = new JToolBar();
		tbSearchBarList.setBounds(10, 11, 416, 33);
		contentPane.add(tbSearchBarList);
		
		txtListSearch = new JTextField();
		txtListSearch.setToolTipText("");
		tbSearchBarList.add(txtListSearch);
		txtListSearch.setColumns(10);
		
		JButton btnListSearch = new JButton("suchen");
		btnListSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tbSearchBarList.add(btnListSearch);
		
		/* Toolbar Tools (BUTTONS) *****************************************************************/
		
		
		JToolBar tbTools = new JToolBar();
		tbTools.setBounds(436, 11, 348, 33);
		contentPane.add(tbTools);
		
		JButton btnAdd = new JButton("hinzuf\u00FCgen");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tbTools.add(btnAdd);
		
		JButton btnEdit = new JButton("bearbeiten");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tbTools.add(btnEdit);
		
		JButton btnDelete = new JButton("l\u00F6schen");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tbTools.add(btnDelete);		
		
		/* Toolbar Web Search *************************************************************************/
		
		
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
			}
		});
		tbSearchBarWeb.add(btnWebSearch);
		
		
		
		/* Panel New Book ***************************************************************************/
		
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
		
		
		
	} // eomain

	// METHODEN *********************************************************************************
	/********************************************************************************************/
	
	
} // eoc
