package hung.com.all.CRUD.insert;

import java.sql.*;

public class App31_Insert_Statement {

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
			
			//============================================== statement (static SQL) ========================
			stmt = conn.createStatement();
			//cách 1: full columns
			String sql = "INSERT INTO Registration " +
					"VALUES (100, 'Zara', 'Ali', 18)";
			stmt.executeUpdate(sql);
			
			// cách 2: nhi�?u value 1 lúc
			sql = "INSERT INTO Registration (id,first,last,age) VALUES " +
							"(101, 'Mahnaz', 'Fatma', 25),"+
							"(102, 'Hung gay', 'nghiem mat', 25),"+
							"(103, 'hung tron', 'thong nhat', 30),"+
							"(104, 'hung con', 'manh yeu', 30),"+
							"(105, 'ky', 'dung manh', 25),"+
							"(106,'hungbeo','nguyen',18)";
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
