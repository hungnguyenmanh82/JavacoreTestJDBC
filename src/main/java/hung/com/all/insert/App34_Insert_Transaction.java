package hung.com.all.insert;

import java.sql.*;

/**
 * transaction JDBC là gộp nhi�?u lệnh vào làm 1 và gửi đi cung 1 lúc tới SQL server.
 * Cách thông thư�?ng là gửi 1 lệnh đi và ch�? response v�? rồi gửi lệnh tiếp theo.
 *
 */
public class App34_Insert_Transaction {

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
		//ko cần đẩy đủ column, chỉ cần đi�?n value đúng thứ tự column liệt kê là đc
		//tên column ko cần quote
		INSERT INTO TABLE_NAME (column1, column2, column3,...columnN)
		VALUES (value1, value2, value3,...valueN);

	 */

	private static void insertRecords(){
		Connection conn = null;
		Statement stmt = null;

		String databaseName = "testcreatedb";
		try{
			String sqlOption = "?autoReconnect=true&useSSL=false"; //ko dùng SSL socket để tăng performance lên
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName+sqlOption, USER, PASS);
			
			//============================================== statement (static SQL) ========================
			stmt = conn.createStatement();
			
			//bắt đầu 1 transaction.
			conn.setAutoCommit(false);//giá trị = false => nghĩa là ko commit lệnh lên server ngay
			
			//cách 1: full columns
			String sql = "INSERT INTO Registration " +
					"VALUES (400, 'Zara', 'Ali', 18)";
			stmt.executeUpdate(sql);  //chỗ này lưu lại mà ko gửi đi vì Autocomit = false
			
			// cách 2: nhi�?u value 1 lúc
			sql = "INSERT INTO Registration (id,first,last,age) VALUES " +
							"(401, 'Mahnaz', 'Fatma', 25),"+
							"(402, 'ok', 'conga', 25),"+
							"(403,'hungbeo','nguyen',18)";
			stmt.executeUpdate(sql);  //chỗ này lưu lại mà ko gửi đi vì Autocomit = false

			conn.commit();//gửi 1 transaction gồm nhi�?u command

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
			
			try {
				conn.rollback(); //lỗi ở 1 command nào đó => rollback lại toàn bộ
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
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
