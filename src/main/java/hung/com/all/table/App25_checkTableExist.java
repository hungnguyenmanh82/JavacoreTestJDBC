package hung.com.all.table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * Phần này thư�?ng làm trên SQL workbench. Nên ko cần thiết
// EMP là tên database ko cần quote => nếu có quote sẽ báo lỗi
 sql> CREATE DATABASE IF NOT EXISTS EMP;

 sql> show databases;

 //sau khi dùng lệnh này có thể dùng tablename without databaseName
mysql> use databaseName;

SQL> DROP DATABASE DATABASE_NAME;
 */
public class App25_checkTableExist {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	static final String DB_URL = "jdbc:mysql://localhost"; //jdbc:mysql://localhost:3306/TableName?autoReconnect=true&useSSL=false
	static final String USER = "root"; //root
	static final String PASS = "123456789"; //123456789


	public static void main(String[] args) {
		String databaseName = "testCreateDB";

		//test3: show all tables in the database
		showTables(databaseName);

	}

	/**
	 * show các Table và View có trong database.
	 * Nên dùng MySQL workbench để show thì nhanh hơn nhiều và chính xác hơn
	 */
	private static void showTables(String databaseName){
		Connection conn = null;
		Statement stmt = null;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName, USER, PASS);

			DatabaseMetaData md = (DatabaseMetaData) conn.getMetaData();
			//getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) 
			String[] types = {"TABLE"};
			String tableName = "registration";
			ResultSet rs = md.getTables(null, null, "registration", types); // % = show tất cả table trong tất cả databases có trên server

			if (rs.next()) {
				System.out.println("Table exists"); 
			} else {
				System.out.println("Table does not exist"); 
			}


		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end try
	}
}
