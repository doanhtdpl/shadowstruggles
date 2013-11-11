package br.edu.ifsp.pds.shadowstruggles.tools.view;

import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import javax.swing.table.DefaultTableModel;

import net.lingala.zip4j.exception.ZipException;
import br.edu.ifsp.pds.shadowstruggles.tools.Controller;
import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Deck;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Effect;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Fighter;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Trap;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Enemy;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Shop;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Scene;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.ActionEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.BattleEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.DeckEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.EffectEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.ShopEditor;

import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.FighterEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.scenes.SceneEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.TrapEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.enemies.EnemyEditor;
import br.edu.ifsp.pds.shadowstruggles.tools.view.events.EventEditor;

import br.edu.ifsp.pds.shadowstruggles.tools.view.items.ItemChooser;

import javax.swing.JLabel;

import java.awt.Rectangle;
import javax.swing.JScrollPane;

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
	private JScrollPane scrollPane;
	

	public MainMenu(Controller controller) {
		this.controller = controller;
		controller.setViewer(this);
		initialize();
		frmTitle.setVisible(true);
		controller.setLanguage("en_us");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTitle = new JFrame();
		frmTitle.setTitle("Shadow Struggles Editor");
		frmTitle.setBounds(100, 100, 751, 538);
		frmTitle.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmTitle.getContentPane().setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try{
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
				if(selectedTab.equals("Items"))
					controller.updateTableToItems();
				if(selectedTab.equals("Shops"))
					controller.updateTableToShop();
				}catch(Exception e){}

			}
		});
		tabbedPane.setVisible(false);
		frmTitle.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int save = JOptionPane.showConfirmDialog(frmTitle, "Deseja salvar antes de sair?");
				if(save ==0){
					controller.saveZip();					
				}						
				if(save!=2)System.exit(0);
				
			}
		});

		
		
		

		btnNewButton = new JButton("New");
		btnNewButton.addMouseListener(new MouseAdapter() {
			

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String selectedTab = tabbedPane.getTitleAt(tabbedPane
						.getSelectedIndex());
				if (selectedTab.equals("Fighters"))
					new FighterEditor(controller, null);
				if (selectedTab.equals("Traps"))
					new TrapEditor();
				if (selectedTab.equals("Effects"))
					new EffectEditor();
				if (selectedTab.equals("Card Actions"))
					new ActionEditor();
				if (selectedTab.equals("Decks"))
					new DeckEditor(controller,null);
				if (selectedTab.equals("Enemies"))
					new EnemyEditor(controller, null);
				if (selectedTab.equals("Battles"))
					new BattleEditor(controller, null);
				if (selectedTab.equals("Events"))
					new EventEditor(controller, null);
				if (selectedTab.equals("Scenes"))
					new SceneEditor(controller, null);
				if(selectedTab.equals("Items"))
					new ItemChooser(controller, null);
				if(selectedTab.equals("Shops"))
					new ShopEditor(controller);
			}
		});

		btnNewButton.setVisible(false);
		
		
		
		
		
		table = new JTable();
		table.setVisible(false);
		table.setBounds(5, 5, 550, 386);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(148, 78, 555, 390);
		scrollPane.setVisible(false);
		
		
		frmTitle.getContentPane().add(scrollPane);
		
		btnNewButton.setBounds(30, 80, 89, 23);
		frmTitle.getContentPane().add(btnNewButton);
		btnEditButton = new JButton("Edit");
		btnEditButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getSelectedTableItem();
				String selectedTab = tabbedPane.getTitleAt(tabbedPane
						.getSelectedIndex());
				if (selectedTab.equals("Fighters"))
					new FighterEditor(controller,(Fighter)getSelectedObject());
				if (selectedTab.equals("Traps"))
					new TrapEditor();
				if (selectedTab.equals("Effects"))
					new EffectEditor();
				if (selectedTab.equals("Card Actions"))
					new ActionEditor();
				if (selectedTab.equals("Decks"))
					new DeckEditor(controller,(Deck)getSelectedObject());
				if (selectedTab.equals("Enemies"))
					new EnemyEditor(controller, (Enemy)getSelectedObject());
				if (selectedTab.equals("Battles"))
					new BattleEditor(controller, (BattlePlatform)getSelectedObject());
				if (selectedTab.equals("Events"))
					new EventEditor(controller,(Event)getSelectedObject());
				if (selectedTab.equals("Scenes"))
					new SceneEditor(controller, (Scene)getSelectedObject());
				if(selectedTab.equals("Items"))
					new ItemChooser(controller, (Item)getSelectedObject());
				if(selectedTab.equals("Shops"))
					new ShopEditor(controller);
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
					deleteSelecteditem();
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
		
		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("Items", null, panel_8, null);
		
		JPanel panel_9 = new JPanel();
		tabbedPane.addTab("Shops", null, panel_9, null);

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
		
		scrollPane.setVisible(true);
		table.setVisible(true);
		btnOpenButton.setVisible(false);
		lblOr.setVisible(false);
		btnCreateZIP.setVisible(false);
		controller.updateTableToFighter();
		
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
	
	public void deleteSelecteditem(){
		try {
			controller.deleteObject(getSelectedTableItem(), getSelectedClass());		
			updateTable();
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException
				| IOException e) {			
			e.printStackTrace();
		}
	}
	
	public void updateTable(){
		Class<?> c = getSelectedClass();
		if(c.equals(Fighter.class))controller.updateTableToFighter();
		if(c.equals(Trap.class))controller.updateTableToTraps();
		if(c.equals(Effect.class))controller.updateTableToEffects();
		if(c.equals(Deck.class))controller.updateTableToDecks();
		if(c.equals(Enemy.class))controller.updateTableToEnemies();
		if(c.equals(BattlePlatform.class))controller.updateTableToBattles();
		if(c.equals(Event.class))controller.updateTableToEvents();
		if(c.equals(Scene.class))controller.updateTableToScenes();
		if(c.equals(Item.class))controller.updateTableToItems();
		if(c.equals(Shop.class))controller.updateTableToShop();
	}
	
	public Integer getSelectedTableItem(){
		Object value = ((DefaultTableModel)table.getModel()).getValueAt(table.getSelectedRow(),0);
		System.out.println(value);
		return (Integer)value;
	}
	
	public Class getSelectedClass(){
		Class<?> c=Fighter.class;
		String selectedTab = tabbedPane.getTitleAt(tabbedPane
				.getSelectedIndex());
		if (selectedTab.equals("Fighters"))
			c=Fighter.class;
		if (selectedTab.equals("Traps"))
			c=Trap.class;
		if (selectedTab.equals("Effects"))
			c=Effect.class;			
		if (selectedTab.equals("Decks"))
			c=Deck.class;
		if (selectedTab.equals("Enemies"))
			c=Enemy.class;
		if (selectedTab.equals("Battles"))
			c=BattlePlatform.class;
		if (selectedTab.equals("Events"))
			c=Event.class;
		if (selectedTab.equals("Scenes"))
			c=Scene.class;
		if(selectedTab.equals("Items"))
			c=Item.class;
		if(selectedTab.equals("Shops"))
			c=Shop.class;
		return c;
	}
	
	public Object getSelectedObject(){
		Class<?> c=Fighter.class;
		String selectedTab = tabbedPane.getTitleAt(tabbedPane
				.getSelectedIndex());
		if (selectedTab.equals("Fighters"))
			c=Fighter.class;
		if (selectedTab.equals("Traps"))
			c=Trap.class;
		if (selectedTab.equals("Effects"))
			c=Effect.class;			
		if (selectedTab.equals("Decks"))
			c=Deck.class;
		if (selectedTab.equals("Enemies"))
			c=Enemy.class;
		if (selectedTab.equals("Battles"))
			c=BattlePlatform.class;
		if (selectedTab.equals("Events"))
			c=Event.class;
		if (selectedTab.equals("Scenes"))
			c=Scene.class;
		if(selectedTab.equals("Items"))
			c=Item.class;
		if(selectedTab.equals("Shops"))
			c=Shop.class;
		Object object = new Object();
		try {
			object = controller.getObject(getSelectedTableItem(), c);
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException
				| IOException e) {			
			e.printStackTrace();
		}
		return object;
	}
}
