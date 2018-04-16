package hung.com.date;

import java.sql.*;

public class App1_Insert_CustomersTb {

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
		Statement stmt = null;

		String databaseName = "mydb";
		String tableName = "customers";
		try{
			String sqlOption = "?autoReconnect=true&useSSL=false"; //ko dùng SSL socket để tăng performance lên
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName+sqlOption, USER, PASS);
			
			//============================================== statement (static SQL) ========================
			stmt = conn.createStatement();
			
			
			String sql = "INSERT INTO "+ tableName+ " (CustomerName,ContactName,Country) VALUES " +
												"('Hung beo', 'Contact 1', 'VietName')," +
												"('Thao Hip', 'Contact 1', 'America')," +
												"('Truong Giang', 'Contact 2', 'Singapor')," +
												"('Cuon Cuon', 'Contact 3', 'America')," +
												"('Chay ve', 'Contact 4', 'Vietnam')," +
												"('Dong', 'Contact 5', 'America')," +
												"('Hao Pho', 'Contact 6', 'Thailand')," +
												"('co chi', 'Contact 7', 'Singapor')," +
												"('lam quan', 'Contact 8', 'Thailand')," +
												"('co gan', 'Contact 9', 'Thailand')," +
												"('lam giau', 'Contact 10', 'Philipine')," +
												"('ok', 'Contact 6', 'Thailand')," +
												"('Son Nit', 'Contact 7', 'PhiLipine');";
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
