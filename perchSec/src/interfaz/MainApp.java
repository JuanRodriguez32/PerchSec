package interfaz;



import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
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
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Mundo.Mundo;
import database.DatabaseManager;
import database.DbException;
import qr.QRChecker;
import qr.QRgenerator;
import qr.QRChecker.QRListener;
import values.Usuario;

public class MainApp implements ActionListener,QRListener {

	private JFrame frmPerchsec;
	public final static String CHECK = "Check";
	public final static String TEST = "TEST";
	private Mundo mundo;
	private QRChecker qrChecker;
	private JTextArea txtrUserName;
	private JTextArea txtrUserId;
	private JTextArea txtrUserDoc;
	private JTextArea txtrUserHouse;
	private JTextArea txtrCarId;
	
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
		txtrUserName.setText("Nombre");
		txtrUserName.setBounds(425, 79, 289, 22);
		frmPerchsec.getContentPane().add(txtrUserName);
		
		txtrUserId = new JTextArea();
		txtrUserId.setText(" ID");
		txtrUserId.setBounds(425, 125, 289, 22);
		frmPerchsec.getContentPane().add(txtrUserId);
		
		txtrUserDoc = new JTextArea();
		txtrUserDoc.setText("Documento");
		txtrUserDoc.setBounds(425, 223, 289, 22);
		
		frmPerchsec.getContentPane().add(txtrUserDoc);
		
		
		txtrUserHouse = new JTextArea();
		txtrUserHouse.setText("Direccion");
		txtrUserHouse.setBounds(425, 175, 289, 22);
		frmPerchsec.getContentPane().add(txtrUserHouse);
		
		txtrCarId = new JTextArea();
		txtrCarId.setText("Placa");
		
		txtrCarId.setBounds(425, 276, 289, 22);
		frmPerchsec.getContentPane().add(txtrCarId);
		
		
	    lblUserImg = new JLabel("");
		lblUserImg.setBounds(20, 84, 289, 223);
		frmPerchsec.getContentPane().add(lblUserImg);
		
		JButton btnCheckUser = new JButton("Check User");
		btnCheckUser.setBounds(32, 356, 89, 23);
		btnCheckUser.setActionCommand(CHECK);
		btnCheckUser.addActionListener(this);
		frmPerchsec.getContentPane().add(btnCheckUser);
		
		JButton btnTest = new JButton("QR");
		btnTest.setBounds(102, 356, 89, 23);
		btnTest.setActionCommand(TEST);
		btnTest.addActionListener(this);
		frmPerchsec.getContentPane().add(btnTest);
		
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
			System.out.println("check");
			
			
			qrChecker  = new QRChecker();
			qrChecker.setQRListener(this);			
			qrChecker.show();
		
		}else if(e.getActionCommand().equals(TEST)){
			System.out.println("test");
			 String test1= JOptionPane.showInputDialog("Ingrese el codigo del usuario");
			 Usuario usu=buscarUsuario(test1.trim());
			 if (usu==null) return;
			 BufferedImage bf=QRgenerator.generate(test1.trim());
			 ImageIcon icon = new ImageIcon(bf);	 
			 lblUserImg.setIcon(icon);
		}
		
	}
	
	private BufferedImage resize(BufferedImage otherImage,int x1,int x2)
	{
		BufferedImage newImage = new BufferedImage(x1, x2, BufferedImage.TYPE_INT_RGB);

		Graphics g = newImage.createGraphics();
		g.drawImage(otherImage, 0, 0, x1, x2, null);
		g.dispose();
		return newImage;
	}
	
	
	private Usuario buscarUsuario(String i){
try {
	Usuario usu=mundo.buscarUsuario(Integer.parseInt(i));
	if(usu!=null){
		actualizarLabels();
		return usu;
		
	}else{
		//No se encontro
		JOptionPane.showMessageDialog(null, "No se encontro un usuario de ID="+i);
	}	
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	@Override
	//Es llamado cuando se lee un QR
	public void notice(String info) {
		System.out.println("QR userid:"+info);
		buscarUsuario(info);
		
	}

	private void actualizarLabels() {
		
	 Usuario usr = mundo.getUsuario();
	 txtrUserId.setText(""+usr.getId());
	 txtrUserDoc.setText(""+usr.getCedula());
	 txtrUserName.setText(""+usr.getNombre());
	 txtrCarId.setText(""+usr.getPlaca());
	 txtrUserHouse.setText(""+usr.getApartamento());
	 BufferedImage bf=mundo.bytesToImage(usr.getFoto());
	 BufferedImage bg2=resize(bf,95*2,164*2);
	 ImageIcon icon = new ImageIcon(bg2);	 
	 lblUserImg.setIcon(icon);
	 
		
	 
	}
}