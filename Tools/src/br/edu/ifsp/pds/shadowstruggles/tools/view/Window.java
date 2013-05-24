package br.edu.ifsp.pds.shadowstruggles.tools.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window {

	private JFrame frmTitle;

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
		frmTitle.setBounds(100, 100, 450, 300);
		frmTitle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTitle.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 21);
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
		
		JMenu mnDatabase = new JMenu("Database");
		menuBar.add(mnDatabase);
		
		JMenuItem mntmEditDatabase = new JMenuItem("Edit Database");
		mntmEditDatabase.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new DatabaseEditor().setVisible(true);
			}
		});
		mnDatabase.add(mntmEditDatabase);
		
		JMenuItem mntmClearDatabase = new JMenuItem("Clear Database");
		mnDatabase.add(mntmClearDatabase);
		
		JMenu mnResources = new JMenu("Resources");
		menuBar.add(mnResources);
		
		JMenuItem mntmEditResources = new JMenuItem("Edit Resources");
		mnResources.add(mntmEditResources);
		
		JMenuItem mntmClearResources = new JMenuItem("Clear Resources");
		mnResources.add(mntmClearResources);
		
		JMenu mnLanguages = new JMenu("Languages");
		menuBar.add(mnLanguages);
		
		JMenuItem mntmEditLanguages = new JMenuItem("Edit Languages");
		mnLanguages.add(mntmEditLanguages);
		
		JMenuItem mntmClearLanguages = new JMenuItem("Clear Languages");
		mnLanguages.add(mntmClearLanguages);
	}
}
