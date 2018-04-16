package hung.com.date;

import java.sql.*;
import java.text.SimpleDateFormat;

public class App43_Select_OdersTb_DateMilliSecond {

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
		selectRecords();
	}
	
	/**
	 * Template to select Records to a Table

	      String sql = "CREATE TABLE REGISTRATION " +
                   "(id INTEGER not NULL, " +
                   " first VARCHAR(255), " + 
                   " last VARCHAR(255), " + 
                   " age INTEGER, " + 
                   " PRIMARY KEY ( id ))"; 
	 */
	private static void selectRecords(){

		Connection conn = null;
		Statement stmt = null;
		String databaseName = "mydb";
		String tableName = "orders";
		try{
			String sqlOption = "?autoReconnect=true&useSSL=false";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName+sqlOption, USER, PASS);
			stmt = conn.createStatement();
			
			/**
				mysql> SELECT UNIX_TIMESTAMP();
				        -> 1447431666
				mysql> SELECT UNIX_TIMESTAMP('2015-11-13 10:20:19');
				        -> 1447431619
				mysql> SELECT UNIX_TIMESTAMP('2015-11-13 10:20:19.012');
				        -> 1447431619.012
			 */
			//UNIX_TIMESTAMP(OrderDate) = 1523863351.000 second  (chính xác đến millisecond)
			// seconds since '1970-01-01 00:00:00' UTC)
			String sql = "SELECT OrderId,CustomerId,UNIX_TIMESTAMP(OrderDate) AS 'date' FROM "+ tableName;
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				int orderId  = rs.getInt("OrderId");
				int customerId = rs.getInt("CustomerId");
				String OrderDate = rs.getString("date");  
				
				
				//Display values
				System.out.print("OrderId: " + orderId);
				System.out.print(", CustomerId: " + customerId);
				System.out.println(", OrderDate: " + OrderDate);
				

			}

			
			rs.close();
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
