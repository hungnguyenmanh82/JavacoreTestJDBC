package hung.com.all.CRUD.insert;

import java.sql.*;

public class App32_Insert_PrepareStatement {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	// EMP là tên database => xem mục tạo Database 
	//localhost: là địa chỉ nội bộ
	//jdbc:mysql://localhost:3306/TableName?autoReconnect=true&useSSL=false
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
		PreparedStatement pstmt = null;

		String databaseName = "testcreatedb";
		try{
			String sqlOption = "?autoReconnect=true&useSSL=false"; //ko dùng SSL socket để tăng performance lên
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName+sqlOption, USER, PASS);
			

			//================================================ PrepareStatement (dynamic SQL) ======================
			//code này sẽ chuyển thành Procedure trên SQL và cache trên SQL server 
			String SQL = "INSERT INTO Registration VALUES (?, ?, ?, ?)";//dynamic SQL statment
			 pstmt = conn.prepareStatement(SQL);//for dynamic SQL statement
			
			pstmt.setInt(1, 205);  //id = ? 1st
			pstmt.setString(2, "HungGay"); //first name = ? 2nd
			pstmt.setString(3, "Chu The"); //last name = ? 3rd
			pstmt.setInt(4, 15);   //age = ? 4th
			
			pstmt.executeUpdate();

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(pstmt!=null)
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
