package test;

import java.io.FileInputStream;

import database.DatabaseManager;
import database.DbException;
import qr.QRChecker;
import values.Usuario;

public class Test {

	public static void main(String[] args) throws Exception {
		//new QRChecker();
		//dbTest();
		usuarioTest();
		//findUsuarioTest();
		
	}
	
	public static void dbTest() throws DbException
	{
		DatabaseManager dbm=DatabaseManager.getInstance();
		dbm.createTables();
	}
	public static void usuarioTest() throws Exception
	
	{
		DatabaseManager dbm=DatabaseManager.getInstance();
		//dbm.createTables();
		
		Usuario usu=new Usuario();
		//
		FileInputStream fis=new FileInputStream("test_imgs/1.png");
		byte[] image=new byte[fis.available()];
		fis.read(image);
		usu.setId(1);
		usu.setNombre("Juan Rodriguez");
		usu.setFoto(image);
		usu.setCedula(8765556);
		usu.setPlaca("BHS-456");
		usu.setApartamento("Edificio 3 Casa 5");
		dbm.saveUsuario(usu);
		// 
		fis=new FileInputStream("test_imgs/2.png");
		image=new byte[fis.available()];
		fis.read(image);
		usu.setId(2);
		usu.setNombre("Cristian Arango");
		usu.setFoto(image);
		usu.setCedula(8765556);
		usu.setPlaca("FAS-321");
		usu.setApartamento("Edificio 1 Casa 12");
		dbm.saveUsuario(usu);
		//
		fis=new FileInputStream("test_imgs/3.png");
		image=new byte[fis.available()];
		fis.read(image);
		usu.setId(3);
		usu.setNombre("Juan Perchoya");
		usu.setFoto(image);
		usu.setCedula(8765556);
		usu.setPlaca("TRE-334");
		usu.setApartamento("Edificio 3 Casa 7");
		dbm.saveUsuario(usu);
		//
		fis=new FileInputStream("test_imgs/4.png");
		image=new byte[fis.available()];
		fis.read(image);
		usu.setId(4);
		usu.setNombre("Daniel Briceño");
		usu.setFoto(image);
		usu.setCedula(8765556);
		usu.setPlaca("BHS-897");
		usu.setApartamento("Edificio 1 Casa 7");
		dbm.saveUsuario(usu);
	}
	
	public static void findUsuarioTest() throws Exception
	{
		DatabaseManager dbm=DatabaseManager.getInstance();
		Usuario u=dbm.findUsuarioById(3);
		System.out.println(u.getNombre());
		System.out.println(u.getFoto());
	}
}