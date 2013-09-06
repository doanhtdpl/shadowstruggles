package br.edu.ifsp.pds.shadowstruggles.tools.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class DatabaseEditor extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public DatabaseEditor() {
		setTitle("Database Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 262);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Cards", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Events", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Battles", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Rules", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Card Effects", null, panel_4, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Decks", null, panel_5, null);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Enemies", null, panel_6, null);
	}
}
