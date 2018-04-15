package hung.com.join;

import java.sql.*;
import java.text.SimpleDateFormat;

public class App1_Insert_Date {

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

	private static void insertRecords(){
		Connection conn = null;
		PreparedStatement pstmt = null;

		String databaseName = "mydb";
		String tableName = "orders";
		try{
			String sqlOption = "?autoReconnect=true&useSSL=false"; //ko dùng SSL socket để tăng performance lên
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName+sqlOption, USER, PASS);

			//============================================== statement (static SQL) ========================
			

			//lưu ý CONSTRAINT FOREIGN key có thể làm insert fail chỗ này vì CustomerId ko ton tai
			String sql = "INSERT INTO "+ tableName+ " (CustomerId,OrderDate) VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 13);
//			pstmt.setDate(2, new java.sql.Date(new java.util.Date().getTime())); //Server: 2018-04-15 00:00:00
//			pstmt.setDate(2, new java.sql.Date(java.sql.Date.UTC(2018, 1, 22, 12, 30, 45))); // Server: 2018-04-15 00:00:00
//			pstmt.setDate(2,st); // don't use this way
			
			//============================
			String stDate = "2018-04-22 12:30:48.234";
			java.util.Date javaDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(stDate);
			System.out.println(javaDate.toString());
			//===========================
			String st =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(javaDate);
			System.out.println(st);
			
			pstmt.setString(2, stDate); //server: 2018-04-22 12:30:45
			int count = pstmt.executeUpdate(); // should use this way
			
			System.out.println("count = "+ count);

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
