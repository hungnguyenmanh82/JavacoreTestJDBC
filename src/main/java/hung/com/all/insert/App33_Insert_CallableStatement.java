package hung.com.all.insert;

import java.sql.*;

public class App33_Insert_CallableStatement {

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
	/**
	 *  //SQL procedure
	 
		CREATE DEFINER=`root`@`localhost` 
		PROCEDURE `insertProcedure`(IN in_id int,IN in_first varchar(45),IN in_last varchar(45),IN in_age int)
		BEGIN
		 INSERT INTO registration (id,first,last,age) values (in_id ,in_first,in_last,in_age);
		END

	 */

	private static void insertRecords(){
		Connection conn = null;
		CallableStatement cstmt=null;

		String databaseName = "testcreatedb";
		try{
			String sqlOption = "?autoReconnect=true&useSSL=false"; //ko dùng SSL socket để tăng performance lên
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName+sqlOption, USER, PASS);
			

			//================================================ PrepareStatement (dynamic SQL) ======================
			//insertProcedure() là Procedure name lưu ở SQL server
			String SQL = "{call insertProcedure(?, ?, ?, ?)}";
			cstmt = conn.prepareCall(SQL);//for dynamic SQL statement
			
			cstmt.setInt(1, 306);  //id = ? 1st
			cstmt.setString(2, "ThaoHip"); //first name = ? 2nd
			cstmt.setString(3, "Nguyen"); //last name = ? 3rd
			cstmt.setInt(4, 33);   //age = ? 4th
			
			cstmt.executeUpdate();

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(cstmt!=null)
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
