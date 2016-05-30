package test;

import java.io.FileInputStream;

import database.DatabaseManager;
import database.DbException;
import qr.QRChecker;
import values.Usuario;

public class Test {

	public static void main(String[] args) throws Exception {
		new QRChecker();
		//dbTest();
		//usuarioTest();
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
		FileInputStream fis=new FileInputStream("packman.jpg");
		byte[] image=new byte[fis.available()];
		fis.read(image);
		Usuario usu=new Usuario();
		usu.setId(3);
		usu.setNombre("tuGfe");
		usu.setFoto(image);
		usu.setCedula(456456);
		usu.setPlaca("LOK420");
		usu.setApartamento("Edificio 3 Casa 5");
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