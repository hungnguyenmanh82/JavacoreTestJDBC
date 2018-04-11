package hung.com.testperformance;

import java.sql.*;

public class App6_DeleteAllRecords {

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
		deleteRecords();
	}
	
	/**
	 * Template to update Records to a Table

	      String sql = "CREATE TABLE REGISTRATION " +
                   "(id INTEGER not NULL, " +
                   " first VARCHAR(255), " + 
                   " last VARCHAR(255), " + 
                   " age INTEGER, " + 
                   " PRIMARY KEY ( id ))"; 
	 */
	private static void deleteRecords(){
		Connection conn = null;
		Statement stmt = null;
		String databaseName = "login";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName, USER, PASS);
			stmt = conn.createStatement();
			
			String sql = "DELETE FROM login";
			int numberRowUpdate = stmt.executeUpdate(sql);
			
			System.out.println(sql);
			System.out.println("number of row was deleted: numberRowUpdate = "+ numberRowUpdate);	

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
