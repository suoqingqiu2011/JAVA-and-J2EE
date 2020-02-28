package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionBaseDonnee {
	static Connection maConnection=null;
	Statement statement;
	String login ;
	String passwd;
	String tache;
	
	public ConnectionBaseDonnee(){		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Success to load jdbc driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("Failed to load jdbc driver");
		}
		
		try{		
			maConnection=DriverManager.getConnection("jdbc:mysql://localhost/framework?serverTimezone=UTC","root", "");
			if(!maConnection.isClosed()){
				System.out.println("Connected to database");
			}
							
		}catch(SQLException e){
			e.printStackTrace();
			
		}catch(Exception e){
			e.printStackTrace();	
		}
	}
	
	public void setUser(String login,String passwd){
		this.login=login;
		this.passwd=passwd;
	}
	
	public void setTaches(String tache){
		this.tache=tache;
	}
	
	public boolean VerifierUser() {
		try{
			statement=maConnection.createStatement();
			
			String SelectSqlStr  = "select * from user;";
			ResultSet selectResult1  = statement.executeQuery(SelectSqlStr);
			while(selectResult1.next()){
				if(!selectResult1.getString("user_name").equals(this.login.trim())){
					System.out.println("Username dosen't exist! ");
					return false;
				}else if(!selectResult1.getString("user_pwd").equals(this.passwd.trim())){
					System.out.println("Password is wrong! ");
					return false;	
				}else if(selectResult1.getString("user_name").equals(this.login.trim()) && selectResult1.getString("user_pwd").equals(this.passwd.trim())){
					System.out.println("Success to enter!");
				}		
			}
			
			selectResult1.close();
			maConnection.close();
		}catch(SQLException e){
			e.printStackTrace();
			
		}catch(Exception e){
			e.printStackTrace();	
		}
		return true;
	}
		
	public void InsertTaches() {
		try{
			statement=maConnection.createStatement();
			
			String InsertSqlStr="INSERT INTO tach(tach_name) VALUES('"+this.tache+"');";
			statement.execute(InsertSqlStr);
			
			String SelectSqlStr  = "select * from tach;";
			ResultSet selectResult2  = statement.executeQuery(SelectSqlStr);
			
			while(selectResult2.next()){
				System.out.println(selectResult2.getString("tach_id") + "\t"+ selectResult2.getString("tach_name"));
			}
			
			selectResult2.close();
			maConnection.close();
		}catch(SQLException e){
			e.printStackTrace();
			
		}catch(Exception e){
			e.printStackTrace();	
		}
	}
}
