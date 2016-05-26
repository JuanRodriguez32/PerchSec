package database;
import java.sql.*;

public class Conector
{
	String URL="test.db";
	Connection connection;
	
	  
  public Connection conectar() throws DbException  
  {  
	  try {
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:"+URL);
		System.out.println("Opened database successfully");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new DbException();
	}
	  return connection;
	  
  }
  
  
  public void desconectar(){	  
	  try {
          connection.close();
      } catch (SQLException ex) {
          ex.printStackTrace();
      }
  }
}