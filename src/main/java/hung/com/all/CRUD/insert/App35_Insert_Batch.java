package hung.com.all.CRUD.insert;

import java.sql.*;

/**
 * JDBC Batch khá giống với Transaction.
 * Batch đc hiểu là đóng gói các lệnh lại làm 1 và gửi tới SQL server. 1 batch tương đương với 1 lệnh.
 * Trong 1 transaction có thể có nhi�?u Batch.
 * 
 * Transaction khi fail nó sẽ dừng lại ko thực hiện nữa.
 * Batch thì 1 lệnh fail lệnh khác sẽ vẫn thực hiện tiếp.
 *
 */
public class App35_Insert_Batch {

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
		
		//chèn nhi�?u phần tử một lúc performance sẽ tốt hơn
		//tên colum ko can quote và ko phan biet chu hoa va chu thuong
		INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)
		VALUES (1, 'Paul', 32, 'California', 20000.00 ), => chú ý kiểu int viết khác kiểu text
		(2, 'Paul1', 32, 'California1', 20000.00 ),
		 (3, 'Paul2', 32, 'California2', 20000.00 );

	 */

	private static void insertRecords(){
		Connection conn = null;
		Statement stmt = null;

		String databaseName = "testcreatedb";
		try{
			String sqlOption = "?autoReconnect=true&useSSL=false"; //ko dùng SSL socket để tăng performance lên
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName+sqlOption, USER, PASS);
			
			// Create statement object
			stmt = conn.createStatement();

			// Set auto-commit to false
			conn.setAutoCommit(false);

			// Create SQL statement
			String SQL = "INSERT INTO Registration (id, first, last, age) " +
			             "VALUES(500,'Dung', 'Van', 30)";
			// Add above SQL statement in the batch.
			stmt.addBatch(SQL);

			// Create one more SQL statement
			SQL = "INSERT INTO Registration (id, first, last, age) " +
			             "VALUES(501,'Dung', 'Hoa', 35)";
			// Add above SQL statement in the batch.
			stmt.addBatch(SQL);

			// Create one more SQL statement
			SQL = "UPDATE Registration SET age = 35 " +
			             "WHERE id = 100";
			// Add above SQL statement in the batch.
			stmt.addBatch(SQL);

			// Create an int[] to hold returned values
			int[] counts = stmt.executeBatch();

			//Explicitly commit statements to apply changes
			conn.commit();
			
			for (int count: counts){
				System.out.println("Batch count = "+ count);	
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
