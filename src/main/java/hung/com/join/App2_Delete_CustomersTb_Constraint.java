package hung.com.join;

import java.sql.*;

public class App2_Delete_CustomersTb_Constraint {

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
		deleteRecord();
	}
	
	/**
	 * https://www.w3schools.com/sql/sql_dates.asp 
	 * 
	 * 
		MySQL 
		•	DATE - format YYYY-MM-DD
		•	DATETIME - format: YYYY-MM-DD HH:MI:SS
		•	TIMESTAMP - format: YYYY-MM-DD HH:MI:SS
		•	YEAR - format YYYY or YY
		SQL Server 
		•	DATE - format YYYY-MM-DD
		•	DATETIME - format: YYYY-MM-DD HH:MI:SS
		•	SMALLDATETIME - format: YYYY-MM-DD HH:MI:SS
		•	TIMESTAMP - format: a unique number

		SELECT * FROM Orders WHERE OrderDate='2008-11-11'

	 */

	private static void deleteRecord(){
		Connection conn = null;
		Statement stmt = null;

		String databaseName = "mydb";
		String tableName = "customers";
		try{
			String sqlOption = "?autoReconnect=true&useSSL=false"; //ko dùng SSL socket để tăng performance lên
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName+sqlOption, USER, PASS);
			
			//============================================== statement (static SQL) ========================
			stmt = conn.createStatement();
			
			//lưu ý CONSTRAINT FOREIGN key có thể làm insert fail chỗ này vì CustomerId đang đc dung ở Orders table
			String sql = "DELETE FROM "+ tableName+ " WHERE CustomerId = 9";
			stmt.executeUpdate(sql);


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
