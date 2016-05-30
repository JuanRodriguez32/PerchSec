package interfaz;



import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import Mundo.Mundo;
import database.DatabaseManager;
import qr.QRChecker;
import values.Usuario;

public class MainApp implements ActionListener {

	private JFrame frmPerchsec;
	public final static String CHECK = "Check";
	private Mundo mundo;
	private QRChecker qrChecker;
	private JTextArea txtrUserName;
	private JTextArea txtrUserId;
	private JTextArea txtrUserHouse;
	private JTextArea txtrCarId;
	private JTextArea txtrCarColor;
	private JLabel lblUserImg;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp window = new MainApp();
					window.frmPerchsec.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		mundo=new Mundo();
		
		frmPerchsec = new JFrame();
		frmPerchsec.setTitle("PerchSec");
		frmPerchsec.setBounds(100, 100, 757, 479);
		frmPerchsec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPerchsec.getContentPane().setLayout(null);
		
		txtrUserName = new JTextArea();
		txtrUserName.setText("User name");
		txtrUserName.setBounds(425, 79, 289, 22);
		frmPerchsec.getContentPane().add(txtrUserName);
		
		txtrUserId = new JTextArea();
		txtrUserId.setText("User ID");
		txtrUserId.setBounds(425, 125, 289, 22);
		frmPerchsec.getContentPane().add(txtrUserId);
		
		txtrUserHouse = new JTextArea();
		txtrUserHouse.setText("User house");
		txtrUserHouse.setBounds(425, 175, 289, 22);
		frmPerchsec.getContentPane().add(txtrUserHouse);
		
		txtrCarId = new JTextArea();
		txtrCarId.setText("Car ID");
		txtrCarId.setBounds(425, 223, 289, 22);
		frmPerchsec.getContentPane().add(txtrCarId);
		
		txtrCarColor = new JTextArea();
		txtrCarColor.setText("Car color / type");
		txtrCarColor.setBounds(425, 276, 289, 22);
		frmPerchsec.getContentPane().add(txtrCarColor);
		
	    lblUserImg = new JLabel("User Img");
		lblUserImg.setBounds(20, 84, 289, 223);
		frmPerchsec.getContentPane().add(lblUserImg);
		
		JButton btnCheckUser = new JButton("Check User");
		btnCheckUser.setBounds(32, 356, 89, 23);
		btnCheckUser.setActionCommand(CHECK);
		btnCheckUser.addActionListener(this);
		frmPerchsec.getContentPane().add(btnCheckUser);
		
		JMenuBar menuBar = new JMenuBar();
		frmPerchsec.setJMenuBar(menuBar);
		
		JMenu mnfile = new JMenu("File");
		menuBar.add(mnfile);
		
		JMenuItem file1 = new JMenuItem("Open");
		mnfile.add(file1);
		
		JMenuItem file2 = new JMenuItem("Save");
		mnfile.add(file2);
		
		JMenuItem file3 = new JMenuItem("Close");
		mnfile.add(file3);
		
		JMenu Edit = new JMenu("Edit");
		menuBar.add(Edit);
		
		JMenuItem edit1 = new JMenuItem("Edit menu 1");
		Edit.add(edit1);
		
		JMenuItem edit2 = new JMenuItem("Edit menu 2");
		Edit.add(edit2);
		
		JMenuItem edit3 = new JMenuItem("Edit Menu 3");
		Edit.add(edit3);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnAbout.add(mntmAbout);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mnHelp.add(mntmHelp);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmmd = e.getActionCommand();
		
		if(e.getActionCommand()== CHECK)
		{
			System.out.println("asda333333333");
			//qrChecker  = new QRChecker();
			System.out.println("asda");
			//qrChecker.show();
			mundo.buscarUsuario(3);
			actualizarLabels();
		}
		
	}

	private void actualizarLabels() {
		
	 Usuario usr = mundo.getUsuario();
	 txtrUserId.setText(""+usr.getId());
	 txtrUserName.setText(""+usr.getNombre());
	 txtrCarId.setText(""+usr.getPlaca());
	 txtrUserHouse.setText(""+usr.getApartamento());
	 BufferedImage bf=mundo.bytesToImage(usr.getFoto());
	 ImageIcon icon = new ImageIcon(bf);	 
	 lblUserImg.setIcon(icon);
	 
		
	 
	}
}