package hung.com.all.table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
public class App22_CreateTableFromOthers {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	static final String DB_URL = "jdbc:mysql://localhost"; //jdbc:mysql://localhost:3306/TableName?autoReconnect=true&useSSL=false
	static final String USER = "root"; //root
	static final String PASS = "123456789"; //123456789


	public static void main(String[] args) {
		
		createTable();
	}


    /*
     * step1: connect database => need @databaseName
     * step2: create table
     * */
	private static void createTable(){
		
		String databaseName = "testCreateDB";
		Connection conn = null;
		Statement stmt = null;
		try{
			//step1: connect database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName, USER, PASS);
			stmt = conn.createStatement();
			
			//
			String sql = "CREATE TABLE IF NOT EXISTS newTable AS SELECT id, first FROM registration  WHERE (id >102) AND (id < 403)";
			//step2: create table
			stmt.executeUpdate(sql);
			
			System.out.println(String.format("jdbc:mysql://localhost/%s >%s", databaseName, sql));
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
					conn.close();
			}catch(SQLException se){
			}// do nothing
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try

	}



}
