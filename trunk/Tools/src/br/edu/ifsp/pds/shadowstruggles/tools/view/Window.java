package br.edu.ifsp.pds.shadowstruggles.tools.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JCheckBox;
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;

public class Window {

	private JFrame frmTitle;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnEditButton;
	private JButton btnDeleteButton;
	private JTabbedPane tabbedPane;
	private JButton btnOpenButton;

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
		frmTitle.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTitle = new JFrame();
		frmTitle.setTitle("Shadow Struggles Editor");
		frmTitle.setBounds(100, 100, 751, 538);
		frmTitle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTitle.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setVisible(false);
		
		table = new JTable();
		table.setVisible(false);
		table.setBounds(150, 80, 550, 386);
		frmTitle.getContentPane().add(table);
		
		btnNewButton = new JButton("New");
		btnNewButton.setVisible(false);
		btnNewButton.setBounds(30, 80, 89, 23);
		frmTitle.getContentPane().add(btnNewButton);
		
		btnEditButton = new JButton("Edit");
		btnEditButton.setVisible(false);
		btnEditButton.setBounds(30, 110, 89, 23);
		frmTitle.getContentPane().add(btnEditButton);
		
		btnDeleteButton = new JButton("Delete");
		btnDeleteButton.setVisible(false);
		btnDeleteButton.setBounds(30, 140, 89, 23);
		frmTitle.getContentPane().add(btnDeleteButton);
		tabbedPane.setBounds(10, 48, 705, 440);
		frmTitle.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Fighters", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Effects", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Traps", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Card Actions", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Decks", null, panel_4, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Enemies", null, panel_5, null);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Rules", null, panel_6, null);
		
		
		Panel panel_7 = new Panel();
		panel_7.setBackground(SystemColor.control);
		tabbedPane.addTab("Battles", null, panel_7, null);
		
		Panel panel_8 = new Panel();
		panel_8.setBackground(SystemColor.control);
		tabbedPane.addTab("Events", null, panel_8, null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 735, 21);
		frmTitle.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setMnemonic('F');
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewFile = new JMenuItem("New File");
		mnNewMenu.add(mntmNewFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open File");
		mnNewMenu.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save File");
		mnNewMenu.add(mntmSave);
		
		JMenuItem mntmClose = new JMenuItem("Close File");
		mnNewMenu.add(mntmClose);
		
		JMenu mnResources = new JMenu("Resources");
		menuBar.add(mnResources);
		
		JMenuItem mntmEditResources = new JMenuItem("Edit Resources");
		mnResources.add(mntmEditResources);
		
		JMenuItem mntmClearResources = new JMenuItem("Clear Resources");
		mnResources.add(mntmClearResources);
		
		JMenu mnLanguages = new JMenu("Languages");
		menuBar.add(mnLanguages);
		
		JMenuItem mntmEditLanguages = new JMenuItem("Select Language");
		mntmEditLanguages.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new LanguageSelection().setVisible(true);
			}
		});
		mnLanguages.add(mntmEditLanguages);
		
		btnOpenButton = new JButton("Please select a file");
		
		btnOpenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				showElements();
				
			}
		});
		
		btnOpenButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnOpenButton.setBounds(172, 200, 361, 62);
		frmTitle.getContentPane().add(btnOpenButton);
	}
	private void showElements(){
		tabbedPane.setVisible(true);
		btnNewButton.setVisible(true);
		btnEditButton.setVisible(true);
		btnDeleteButton.setVisible(true);
		table.setVisible(true);
		btnOpenButton.setVisible(false);
	}
}
