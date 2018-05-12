package hung.com.all.CRUD.insert;

import java.sql.*;

public class App37_Insert_getId {

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

	 */

	private static void insertRecords(){
		Connection conn = null;
		PreparedStatement pstmt = null;

		String databaseName = "mydb";
		String tableName = "customers";
		try{
			String sqlOption = "?autoReconnect=true&useSSL=false"; //ko dùng SSL socket để tăng performance lên
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName+sqlOption, USER, PASS);

			//============================================== get Id after Insert ========================

			String sql = "INSERT INTO "+ tableName+ " (CustomerName,ContactName,Country) VALUES " +
					"(?, ?, ?);";
			pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);//for dynamic SQL statement

			pstmt.setString(1, "Kien beo");  //id = ? 1st
			pstmt.setString(2, "Contact 22"); //first name = ? 2nd
			pstmt.setString(3, "HongKong"); //last name = ? 3rd

			//mỗi lần insert ko thành công ID sẽ tự động tăng lên 1
			int numberUpdate = pstmt.executeUpdate();
			System.out.println("numberUpdate = " + numberUpdate);
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()){
				System.out.println("id = " + rs.getLong(1));
			}
			
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
