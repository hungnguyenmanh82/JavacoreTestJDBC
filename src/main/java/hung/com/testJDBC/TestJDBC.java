package hung.com.testJDBC;

import java.sql.*;

public class TestJDBC {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	// EMP là tên database => xem mục tạo Database 
	//localhost: là địa chỉ nội bộ
	static final String DB_URL = "jdbc:mysql://localhost/EMP"; //Tomcat và Mysql on the same computer
	// jdbc:mysql://192.168.15.25:3306/yourdatabase
	//Make sure there is no firewall blocking the access to port 3306

	//  Database credentials
	static final String USER = "root"; //root
	static final String PASS = "123456789"; //123456789

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver (MySql, Oracle, MS sql….)
			Class.forName("com.mysql.jdbc.Driver"); //JDBC kết nối với MySQL connector J api

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			//dùng function này là cách ổn nhất (con nhiều cách khác)
			conn = DriverManager.getConnection(DB_URL,USER,PASS); //tạo 1 socket kết nối server

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement(); //for static SQL 
			String sql;
			sql = "SELECT id, first, last, age FROM Employees";  //static SQL statement

			//rs là cursor trỏ vào row của data trả về
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				int id  = rs.getInt("id");  // “id” là column name
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				//Display values
				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
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
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
	}//end main


}
