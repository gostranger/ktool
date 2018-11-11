package datalake;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import config.conf;

public class adlsGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private JLabel lblClientID;
	private JTextField txtClientId;
	private JLabel lblClientKey;
	private JTextField txtClientKey;
	private JLabel lblAccToken;
	private JTextField txtAccToken;
	private JLabel lblAccName;
	private JTextField txtAccName;
	private JButton btnLoad;
	private JList<String> dirList;
	private JTextArea txtContent;
	private JLabel lblDir;
	private JTextField txtDir;
	private JButton btndel;
	private JMenuBar mnu;
	private JFileChooser fc;
	private JScrollPane scrolllist;
	private JScrollPane tareascroll;

	public adlsGUI(conf cn) {
		// construct preComponents
		JMenu fileMenu = new JMenu("File");
		JMenuItem configItem = new JMenuItem("Config...");
		fileMenu.add(configItem);
		JMenuItem UploadFile = new JMenuItem("Upload File...");
		fileMenu.add(UploadFile);

		// construct components
		lblClientID = new JLabel("Client ID");
		txtClientId = new JTextField(5);
		lblClientKey = new JLabel("Client Key");
		txtClientKey = new JTextField(5);
		lblAccToken = new JLabel("Account Token");
		txtAccToken = new JTextField(5);
		lblAccName = new JLabel("Account Name");
		txtAccName = new JTextField(5);
		btnLoad = new JButton("Load");
		dirList = new JList<String>();
		scrolllist = new JScrollPane(dirList);
		scrolllist.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		txtContent = new JTextArea(5, 5);
		lblDir = new JLabel("Dir Name");
		txtDir = new JTextField(5);
		tareascroll = new JScrollPane(txtContent);
		tareascroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tareascroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		btndel = new JButton("Delete File");
		mnu = new JMenuBar();
		mnu.add(fileMenu);
		fc = new JFileChooser();

		// adjust size and set layout
		setPreferredSize(new Dimension(944, 553));
		setLayout(null);

		// add components
		add(lblClientID);
		add(txtClientId);
		add(lblClientKey);
		add(txtClientKey);
		add(lblAccToken);
		add(txtAccToken);
		add(lblAccName);
		add(txtAccName);
		add(btnLoad);
		// add (dirList);
		// add (txtContent);
		add(lblDir);
		add(txtDir);
		add(btndel);
		add(mnu);
		add(scrolllist);
		add(tareascroll);

		// set component bounds (only needed by Absolute Positioning)
		lblClientID.setBounds(10, 40, 100, 25);
		txtClientId.setBounds(110, 40, 170, 25);
		lblClientKey.setBounds(10, 65, 100, 25);
		txtClientKey.setBounds(110, 65, 170, 25);
		lblAccToken.setBounds(10, 90, 100, 25);
		txtAccToken.setBounds(110, 90, 170, 25);
		lblAccName.setBounds(10, 115, 100, 25);
		txtAccName.setBounds(110, 115, 170, 25);
		btnLoad.setBounds(10, 180, 270, 30);
		dirList.setBounds(10, 225, 270, 320);
		scrolllist.setBounds(10, 225, 270, 320);
		txtContent.setBounds(290, 40, 645, 475);
		tareascroll.setBounds(290, 40, 645, 475);
		lblDir.setBounds(10, 140, 65, 25);
		txtDir.setBounds(110, 140, 170, 25);
		btndel.setBounds(835, 520, 100, 25);
		mnu.setBounds(0, 0, 945, 30);
		// txtContent.setLineWrap(true);
		// JScrollPane scroll = new JScrollPane(txtContent);
		// scroll.setVerticalScrollBarPolicy(ScrollPaneConstxtAccNamets.VERTICAL_SCROLLBAR_ALWAYS);

		txtClientId.setText(cn.getAdlsConfig().getProperty("ClientID"));
		txtClientKey.setText(cn.getAdlsConfig().getProperty("Key"));
		txtAccToken.setText(cn.getAdlsConfig().getProperty("AuthEndPoint"));
		txtAccName.setText(cn.getAdlsConfig().getProperty("AccountName"));
		txtDir.setText(cn.getAdlsConfig().getProperty("Directory"));

		configItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		UploadFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				upFile();
			}
		});

		btnLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loader();
			}
		});

		btndel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(adlsGUI.this, "Are you Sure?", "Delete File",
						JOptionPane.OK_CANCEL_OPTION) == 0) {
					try {

						DeleteFile dl = new DeleteFile();
						if (dl.delete(txtAccToken.getText(), txtClientId.getText(), txtClientKey.getText(),
								txtAccName.getText(), txtDir.getText(),
								dirList.getModel().getElementAt(dirList.getAnchorSelectionIndex())) == true) {
							loader();
							JOptionPane.showMessageDialog(adlsGUI.this, "Deleted Successfully ");
						} else {
							JOptionPane.showMessageDialog(adlsGUI.this, "Error Deleteing the file: ");
						}

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(adlsGUI.this, "Error Deleteing the file: " + ex.getMessage());
					}
				}
			}
		});

		dirList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>) evt.getSource();
				if (evt.getClickCount() == 2) {
					// Double-click detected
					loadData(list);
				}
			}
		});

		JFrame frame = new JFrame("ADLS Explorer");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	private void openFile() {

	}

	private void upFile() {
		fc.showOpenDialog(this);
		txtContent.setText("Selected File : " + fc.getSelectedFile().getName());
		UploadFile up = new UploadFile();
		up.update(txtAccToken.getText(), txtClientId.getText(), txtClientKey.getText(), txtAccName.getText(),
				txtDir.getText(), fc.getSelectedFile().getName(), fc.getSelectedFile().toString());
		loader();
		JOptionPane.showMessageDialog(this, "Uploaded Successfully ");
	}

	protected void dispose() {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("deprecation")
	private void loadData(JList<?> list) {
		GetData gt = new GetData();
		txtContent.setText(gt.Data(txtAccToken.getText(), txtClientId.getText(), txtClientKey.getText(),
				txtAccName.getText(), txtDir.getText(), list.getSelectedValues()[0].toString()));
	}

	private void loader() {
		GetFiles gt = new GetFiles();
		ArrayList<String> al = gt.Get(txtAccToken.getText(), txtClientId.getText(), txtClientKey.getText(),
				txtAccName.getText(), txtDir.getText());
		String list[] = al.toArray(new String[al.size()]);
		dirList.setListData(list);
	}

}
