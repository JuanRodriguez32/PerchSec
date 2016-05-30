package Mundo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import database.DatabaseManager;
import database.DbException;
import values.Usuario;

public class Mundo {
	
	Usuario usuario;
	DatabaseManager dbm;
	
	public Mundo(){
		dbm=DatabaseManager.getInstance();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public  void buscarUsuario(int id) {
		// TODO Auto-generated method stub
		try {
			usuario=dbm.findUsuarioById(id);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public BufferedImage bytesToImage(byte[] imageInByte){
		InputStream in = new ByteArrayInputStream(imageInByte);
		try {
			BufferedImage bImageFromConvert = ImageIO.read(in);
			return bImageFromConvert;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return null;
	}
	
	

}
