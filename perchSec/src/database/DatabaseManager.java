package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.zxing.Result;

import values.Usuario;

public class DatabaseManager {

    private int mOpenCounter;
    private Conector conector;
    private static DatabaseManager instance;
    

    private DatabaseManager()
    {
    	conector=new Conector();
    }
    
    public static synchronized DatabaseManager getInstance(){
    	if (instance==null) instance=new DatabaseManager();
    	return instance;
    }
    
    public void createTables() throws DbException
    {
    	
    	String sql = "CREATE TABLE USUARIOS " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NOMBRE           TEXT    NOT NULL, " + 
                " CEDULA            INT     NOT NULL, " + 
                " FOTO        BLOB     NOT NULL, " + 
                " PLACA         TEXT     NOT NULL, "+
                " APARTAMENTO	TEXT     NOT NULL)";
    	
    	runSQL(sql);   	
    	System.out.println("Se crearon las tablas");
   
    }
    
    
    
    public void runSQL(String sql) throws DbException {
    	
    	try{
    		Connection c=conector.conectar();
        	Statement stmt=c.createStatement();
        	System.out.println("RunSql:"+sql);
        	stmt.executeUpdate(sql);
        	stmt.close();
        	conector.desconectar();
        	
    	}catch(SQLException ex)
    	{
    		ex.printStackTrace();
    		throw new DbException();
    	}
    	
    }
    
    public void saveUsuario(Usuario user) throws DbException
    {

    	String sql="INSERT INTO USUARIOS (ID,NOMBRE,CEDULA,FOTO,PLACA,APARTAMENTO)"+
    	"VALUES (?,?,?,?,?,?)";
    	try{
    	Connection c=conector.conectar();
    	PreparedStatement pstmt=c.prepareStatement(sql);
    	pstmt.setInt(1, user.getId());
    	pstmt.setString(2, user.getNombre());
    	pstmt.setInt(3, user.getCedula());
    	pstmt.setBytes(4, user.getFoto());
    	pstmt.setString(5, user.getPlaca());
    	pstmt.setString(6, user.getApartamento());
    	pstmt.executeUpdate();
    	System.out.println("Se guardo el usuario");
    	}catch(SQLException ex)
    	{
    		ex.printStackTrace();
    		throw new DbException();
    	}  	
    	
    	
    }
    
    public Usuario findUsuarioById(int id) throws DbException
    {
    	String sql="SELECT * FROM USUARIOS WHERE ID=?";
    	Usuario usuario=new  Usuario();
    	try{
    	Connection c=conector.conectar();
    	PreparedStatement pstmt=c.prepareStatement(sql);
    	pstmt.setInt(1, id);
    	ResultSet rs=pstmt.executeQuery();
    	if(!rs.next()) throw new DbException("No se encontro el usuario id:"+id);
    	usuario.setId(rs.getInt("ID"));
    	usuario.setNombre(rs.getString("NOMBRE"));
    	usuario.setCedula(rs.getInt("CEDULA"));
    	usuario.setFoto(rs.getBytes("FOTO"));
    	usuario.setPlaca(rs.getString("PLACA"));
    	usuario.setApartamento(rs.getString("APARTAMENTO"));
    	
    	}catch(SQLException ex)
    	{
    		ex.printStackTrace();
    		throw new DbException();
    	}
    	return usuario;
    }
    
    
    
    
    
    
}