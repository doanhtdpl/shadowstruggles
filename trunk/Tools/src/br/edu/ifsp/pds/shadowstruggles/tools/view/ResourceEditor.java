package br.edu.ifsp.pds.shadowstruggles.tools.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.JList;

public class ResourceEditor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResourceEditor frame = new ResourceEditor();
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
	public ResourceEditor() {
		setTitle("Resource Edition");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(31, 34, 437, 23);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Images", null, panel, null);
		
		JTree tree = new JTree();
		tree.setBounds(31, 80, 187, 100);
		tree.setVisible(false);
		contentPane.add(tree);
		
		SimpleTree folderTree = new SimpleTree();
		folderTree.setBounds(31, 80, 220, 200);
		contentPane.add(folderTree);
		
		JList list = new JList();
		list.setBounds(264, 80, 182, 100);
		contentPane.add(list);
	}
}
