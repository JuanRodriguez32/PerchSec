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

	public  Usuario buscarUsuario(int id) throws DbException {
		// TODO Auto-generated method stub
		
			 usuario=dbm.findUsuarioById(id);
			 return usuario;
		
					
		
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
