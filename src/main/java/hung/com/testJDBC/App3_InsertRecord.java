package hung.com.testJDBC;

import java.sql.*;

public class App3_InsertRecord {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	// EMP là tên database => xem mục tạo Database 
	//localhost: là địa chỉ nội bộ
	static final String DB_URL = "jdbc:mysql://localhost/"; //Tomcat và Mysql on the same computer
	// jdbc:mysql://192.168.15.25:3306/yourdatabase
	//Make sure there is no firewall blocking the access to port 3306

	//  Database credentials
	static final String USER = "root"; //root
	static final String PASS = "123456789"; //123456789

	public static void main(String[] args) {
		insertRecords();
	}
	
	/**
	 * Template to insertRecords to a Table:

	      String sql = "CREATE TABLE REGISTRATION " +
                   "(id INTEGER not NULL, " +
                   " first VARCHAR(255), " + 
                   " last VARCHAR(255), " + 
                   " age INTEGER, " + 
                   " PRIMARY KEY ( id ))"; 
	 */

	private static void insertRecords(){
		Connection conn = null;
		Statement stmt = null;

		String databaseName = "testcreatedb";
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL+ databaseName, USER, PASS);
			stmt = conn.createStatement();

			String sql = "INSERT INTO Registration " +
					"VALUES (100, 'Zara', 'Ali', 18)";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO Registration " +
					"VALUES (101, 'Mahnaz', 'Fatma', 25)";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO Registration " +
					"VALUES (102, 'Zaid', 'Khan', 30)";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO Registration " +
					"VALUES(103, 'Sumit', 'Mittal', 28)";
			stmt.executeUpdate(sql);
			System.out.println("Inserted records into the table...");

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
		System.out.println("Goodbye!");
	}


}
