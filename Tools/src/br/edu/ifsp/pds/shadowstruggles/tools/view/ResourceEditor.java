package br.edu.ifsp.pds.shadowstruggles.tools.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;



public class ResourceEditor extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private ResourceEditor frame;	
	private JList<String> list;
	private JTree tree;
	private SimpleTree simpleTree;
	private HashMap<String, File> fileMap;
	

	public ResourceEditor() {
		this.frame = this;
		this.fileMap = new HashMap<String, File>();		
		setTitle("Resource Edition");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 676, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(31, 34, 437, 23);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Images and Audio", null, panel, null);
		simpleTree = new SimpleTree(new File("./data/"));
		simpleTree.setBounds(31, 80, 283, 200);
		
		tree = new JTree();
		tree.setBounds(31, 80, 187, 100);
		tree.setVisible(false);		
		contentPane.add(tree);		
		contentPane.add(simpleTree);

		list = new JList<String>();
		list.setModel(new DefaultListModel<String>());
		list.setBounds(350, 80, 300, 200);
		contentPane.add(list);

		JButton btnAddFile = new JButton("Add File");
		btnAddFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Image Files", "jpg", "png");
				FileNameExtensionFilter audioFilter = new FileNameExtensionFilter(
						"Audio Files", "ogg");
				chooser.addChoosableFileFilter(filter);
				chooser.addChoosableFileFilter(audioFilter);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter(filter);
				chooser.setCurrentDirectory(Paths.get("").toFile());
				chooser.setMultiSelectionEnabled(true);
				int returnValue = chooser.showOpenDialog(frame);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File[] files = chooser.getSelectedFiles();
					for (File file : files) {
						String log = "Opening " + file.getName() + "\n";
						((DefaultListModel<String>) list.getModel())
								.addElement(file.getName());
						fileMap.put(file.getName(), file);
						list.setSelectedIndex(list.getModel().getSize() - 1);					
					}
				}

			}
		});
		btnAddFile.setBounds(357, 291, 89, 23);
		contentPane.add(btnAddFile);

		JButton btnFileToFolder = new JButton("File to Folder <<<");
		btnFileToFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (list.getSelectedValuesList().size() != 0) {					
					
					for (Object obj : list.getSelectedValuesList()) {

						try {
							Path orig = fileMap.get(obj.toString()).toPath();
							Path dest = new File(simpleTree.getSelectedTreePath().getLastPathComponent()+"/"
									+ obj.toString()).toPath();
							Files.copy(orig, dest);
							updateTree();
						} catch (IOException e) {
							Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
							e.printStackTrace();
						}

					}

				}

			}
		});
		btnFileToFolder.setBounds(165, 291, 149, 23);
		contentPane.add(btnFileToFolder);
		
		JButton btnNewFolder = new JButton("New Folder");
		btnNewFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(simpleTree.getSelectedTreePath()!=null){
					String folderName = JOptionPane.showInputDialog("Dentro da pasta selecionada, será criada uma pasta com o seguinte nome:");
					String folderPath= simpleTree.getSelectedTreePath().getLastPathComponent()+"/"+folderName;					
					new File(folderPath).mkdir();
					updateTree();					
				}
			}
		});
		btnNewFolder.setBounds(31, 291, 124, 23);
		contentPane.add(btnNewFolder);

	}
	
	private void updateTree(){
		contentPane.remove(simpleTree);
		contentPane.remove(tree);
		simpleTree = new SimpleTree(new File("./data/"));
		simpleTree.setBounds(31, 80, 220, 200);
		
		tree = new JTree();
		tree.setBounds(31, 80, 187, 100);
		tree.setVisible(false);		
		contentPane.add(tree);		
		contentPane.add(simpleTree);
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}	
}
