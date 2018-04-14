package hung.com.all.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Phần này thư�?ng làm trên SQL workbench. Nên ko cần thiết
// EMP là tên database ko cần quote => nếu có quote sẽ báo lỗi
 sql> CREATE DATABASE IF NOT EXISTS EMP;
 
 sql> show databases;
 
 //sau khi dùng lệnh này có thể dùng tablename without databaseName
mysql> use databaseName;

SQL> DROP DATABASE DATABASE_NAME;
 */


public class App12_DropDatabase {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	//jdbc:mysql://localhost:3306/TableName?autoReconnect=true&useSSL=false
	static final String DB_URL = "jdbc:mysql://localhost"; //chưa ch�?n DatabaseName
	static final String USER = "root"; //root
	static final String PASS = "123456789"; //123456789


	public static void main(String[] args) {
		String databaseName = "testCreateDB";
		
		//test1: create database
		createDatabase(databaseName);

	}

	//mysql> show databases;

	private static String sqlCreateDatabase = "CREATE DATABASE IF NOT EXISTS "+ "databaseName" +";";
	private static String sqlDropDatabase = "DROP DATABASE IF EXISTS "+ "databaseName" +";";
	
	private static void executeRawSQL(String sql){
		Connection conn = null;
		Statement stmt = null;
		try{

			String sqlOption = "?autoReconnect=true&useSSL=false";
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(DB_URL,USER, PASS);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			System.out.println("run successfully sql command: "+ sql);
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try

	}
	
	private static void createDatabase(String databaseName){
		String sql = "CREATE DATABASE IF NOT EXISTS "+ databaseName +";";
		executeRawSQL(sql);
	}

	private static void dropDatabase(String databaseName){
		String sql = "DROP DATABASE IF EXISTS "+ databaseName +";";
		executeRawSQL(sql);
	}
}
