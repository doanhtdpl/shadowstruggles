package br.edu.ifsp.pds.shadowstruggles.tools.view;

import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.lingala.zip4j.exception.ZipException;
import br.edu.ifsp.pds.shadowstruggles.tools.Controller;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.ActionEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.BattleEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.DeckEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.EffectEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.EnemyEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.EventEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.FighterEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.SceneEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.TrapEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.enemie.EnemyGUIFrame;

import javax.swing.JLabel;

import java.awt.Rectangle;

public class MainMenu {

	private JFrame frmTitle;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnEditButton;
	private JButton btnDeleteButton;
	private JTabbedPane tabbedPane;
	private JButton btnOpenButton;
	private Controller controller;
	private JMenuBar menuBar;
	private JLabel lblOr;
	private JButton btnCreateZIP;

	public MainMenu(Controller controller) {
		this.controller = controller;
		controller.setViewer(this);
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
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String selectedTab = tabbedPane.getTitleAt(tabbedPane
						.getSelectedIndex());
				if (selectedTab.equals("Fighters"))
					controller.updateTableToFighter();
				if (selectedTab.equals("Traps"))
					controller.updateTableToTraps();
				if (selectedTab.equals("Effects"))
					controller.updateTableToEffects();
				if (selectedTab.equals("Card Actions"))
					controller.updateTableToActions();
				if (selectedTab.equals("Decks"))
					controller.updateTableToDecks();
				if (selectedTab.equals("Enemies"))
					controller.updateTableToEnemies();
				if (selectedTab.equals("Rules"))
					controller.updateTableToRules();
				if (selectedTab.equals("Battles"))
					controller.updateTableToBattles();
				if (selectedTab.equals("Events"))
					controller.updateTableToEvents();
				if (selectedTab.equals("Scenes"))
					controller.updateTableToScenes();

			}
		});
		tabbedPane.setVisible(false);

		table = new JTable();
		table.setVisible(false);
		table.setBounds(150, 80, 550, 386);
		frmTitle.getContentPane().add(table);

		btnNewButton = new JButton("New");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String selectedTab = tabbedPane.getTitleAt(tabbedPane
						.getSelectedIndex());
				if (selectedTab.equals("Fighters"))
					new FighterEditor();
				if (selectedTab.equals("Traps"))
					new TrapEditor();
				if (selectedTab.equals("Effects"))
					new EffectEditor();
				if (selectedTab.equals("Card Actions"))
					new ActionEditor();
				if (selectedTab.equals("Decks"))
					new DeckEditor();
				if (selectedTab.equals("Enemies"))
					new EnemyGUIFrame().setVisible(true);
				if (selectedTab.equals("Battles"))
					new BattleEditor();
				if (selectedTab.equals("Events"))
					new EventEditor();
				if (selectedTab.equals("Scenes"))
					new SceneEditor();

			}
		});
		btnNewButton.setVisible(false);
		btnNewButton.setBounds(30, 80, 89, 23);
		frmTitle.getContentPane().add(btnNewButton);

		btnEditButton = new JButton("Edit");
		btnEditButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selectedTab = tabbedPane.getTitleAt(tabbedPane
						.getSelectedIndex());
				if (selectedTab.equals("Fighters"))
					new FighterEditor();
				if (selectedTab.equals("Traps"))
					new TrapEditor();
				if (selectedTab.equals("Effects"))
					new EffectEditor();
				if (selectedTab.equals("Card Actions"))
					new ActionEditor();
				if (selectedTab.equals("Decks"))
					new DeckEditor();
				if (selectedTab.equals("Enemies"))
					new EnemyEditor();
				if (selectedTab.equals("Battles"))
					new BattleEditor();
				if (selectedTab.equals("Events"))
					new EventEditor();
				if (selectedTab.equals("Scenes"))
					new SceneEditor();
				// TODO: (objeto selecionado da tabela como argumento);
			}
		});
		btnEditButton.setVisible(false);
		btnEditButton.setBounds(30, 110, 89, 23);
		frmTitle.getContentPane().add(btnEditButton);

		btnDeleteButton = new JButton("Delete");
		btnDeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int answer = JOptionPane.showConfirmDialog(frmTitle,
						"Do you really want to delete this element?");
				if (answer == 0) {
					String selectedTab = tabbedPane.getTitleAt(tabbedPane
							.getSelectedIndex());
					// TODO: decidir qual m�todo "delete" da controller chamar
				}
			}
		});
		btnDeleteButton.setVisible(false);
		btnDeleteButton.setBounds(30, 140, 89, 23);
		frmTitle.getContentPane().add(btnDeleteButton);
		tabbedPane.setBounds(10, 48, 705, 440);
		frmTitle.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnNewButton.setEnabled(false);
			}
		});
		tabbedPane.addTab("Fighters", null, panel, null);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnNewButton.setEnabled(true);
			}
		});
		tabbedPane.addTab("Effects", null, panel_1, null);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Traps", null, panel_2, null);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Decks", null, panel_3, null);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Enemies", null, panel_4, null);

		Panel panel_5 = new Panel();
		panel_5.setBackground(SystemColor.control);
		tabbedPane.addTab("Battles", null, panel_5, null);

		Panel panel_6 = new Panel();
		panel_6.setBackground(SystemColor.control);
		tabbedPane.addTab("Events", null, panel_6, null);

		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Scenes", null, panel_7, null);

		menuBar = new JMenuBar();
		menuBar.setVisible(false);
		menuBar.setBounds(0, 0, 735, 21);
		frmTitle.getContentPane().add(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setMnemonic('F');
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewFile = new JMenuItem("New File");
		mnNewMenu.add(mntmNewFile);

		JMenuItem mntmOpen = new JMenuItem("Open File");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openZipDialog();
			}
		});
		mnNewMenu.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save File");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveZip();
			}
		});
		mnNewMenu.add(mntmSave);

		JMenuItem mntmClose = new JMenuItem("Close File");
		mnNewMenu.add(mntmClose);

		JMenu mnResources = new JMenu("Resources");
		menuBar.add(mnResources);

		JMenuItem mntmEditResources = new JMenuItem("Edit Resources");
		mntmEditResources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ResourceEditor();
			}
		});
		mnResources.add(mntmEditResources);

		JMenuItem mntmClearResources = new JMenuItem("Clear Resources");
		mnResources.add(mntmClearResources);

		JMenu mnLanguages = new JMenu("Languages");
		menuBar.add(mnLanguages);

		JMenuItem mntmEditLanguages = new JMenuItem("Select Language");
		mntmEditLanguages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LanguageSelection(controller);
			}
		});

		mnLanguages.add(mntmEditLanguages);

		btnOpenButton = new JButton("Please select a file");

		btnOpenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				openZipDialog();

			}
		});

		btnOpenButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnOpenButton.setBounds(172, 200, 361, 62);
		frmTitle.getContentPane().add(btnOpenButton);

		lblOr = new JLabel("Or");
		lblOr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOr.setBounds(300, 300, 46, 14);
		frmTitle.getContentPane().add(lblOr);

		btnCreateZIP = new JButton("Create a ZIP");
		btnCreateZIP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.newZipClicked();
			}
		});
		btnCreateZIP.setBounds(new Rectangle(100, 400, 0, 0));
		btnCreateZIP.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCreateZIP.setBounds(200, 400, 305, 64);
		frmTitle.getContentPane().add(btnCreateZIP);

	}

	public void showElements() {
		tabbedPane.setVisible(true);
		menuBar.setVisible(true);
		btnNewButton.setVisible(true);
		btnEditButton.setVisible(true);
		btnDeleteButton.setVisible(true);
		table.setVisible(true);
		btnOpenButton.setVisible(false);
		lblOr.setVisible(false);
		btnCreateZIP.setVisible(false);
		controller.updateTableToDecks();
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	private void openZipDialog() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"ZIP Files", "zip");
		chooser.addChoosableFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(Paths.get(".").toFile());
		int returnValue = chooser.showOpenDialog(frmTitle);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			String log = "Opening " + file.getName() + "\n";
			try {
				controller.openZip(file.toString());
			} catch (IOException e) {
				Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)
						.severe(e.toString());
				e.printStackTrace();
			} catch (ZipException e) {
				Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)
						.severe(e.toString());
				e.printStackTrace();
			}
			menuBar.setVisible(true);
			showElements();
		} else {
			String log = "Open command cancelled by user\n";
		}
	}

	public JTable getTable() {
		return table;
	}
}
