package hung.com.all.view;

import java.sql.*;

/**
 * Là 1 table tạm được tạo ra bởi các table gốc nhờ SELECT.
Alias table cũng là table tạm chỉ có tác dụng trong 1 truy vấn trên 1 connect (hết truy vấn là bị hủy).
View là table tạm, có thể phục vụ nhiều truy vấn 1 lúc đc ở nhiều socket connect khác nhau.
View tự động đc update mỗi lần có lệnh mới truy cập tới nó.
Tuy nhiên cả View và Alias table đều ko dùng đc INDEX  (khác biệt với Table) => nghĩa là việc tìm kiếm trên view là tuần tự.
Nếu muốn dùng index ta có thể tạo hẳn Table mới và copy dữ liệu từ SELECT sang để thực hiện truy vấn.
 Tuy nhiên table này lại ko đc cập nhật khi có dữ liệu mới.
View và Alias Table có tác dụng như Table nên dùng lệnh SELECT giống hệt table.

Nhưng Select trên View là duyet tuan tu
 *
 */
public class App7_CreateView {

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
		String viewName ="newView";
		createView(viewName);
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
	private static void createView(String viewName){

		Connection conn = null;
		Statement stmt = null;
		String databaseName = "testcreatedb";
		try{
			String sqlOption = "?autoReconnect=true&useSSL=false";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName+sqlOption, USER, PASS);
			stmt = conn.createStatement();

			String sql = "CREATE VIEW IF NOT EXISTS " + viewName + " AS SELECT id, first FROM Registration";
			int result = stmt.executeUpdate(sql);  //= 0 là ok
			
			System.out.println("result = " + result);
	
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
