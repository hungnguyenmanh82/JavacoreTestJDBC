package hung.com.testperformance;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Test performance of insert data to database
 *
 */
public class App1_InsertRecord {
	private static final Logger log = LogManager.getLogger("console");
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	// EMP là tên database => xem mục tạo Database 
	//localhost: là địa chỉ nội bộ
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

		CREATE TABLE `login` (
		  `id` int(11) NOT NULL AUTO_INCREMENT,
		  `name` varchar(45) NOT NULL,
		  `email` varchar(45) NOT NULL,
		  `pass` varchar(45) NOT NULL,
		  `timeCreate` datetime DEFAULT CURRENT_TIMESTAMP,
		  PRIMARY KEY (`id`),
		  UNIQUE KEY `email_UNIQUE` (`email`),
		  UNIQUE KEY `name_UNIQUE` (`name`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8
		
	 */
	
	/**
	//ko cần đẩy đủ column, chỉ cần đúng thứ tự tên liệt kê là đc
	//tên column ko cần quote
	INSERT INTO TABLE_NAME (column1, column2, column3,...columnN)
	VALUES (value1, value2, value3,...valueN);
	
	//chèn nhiều phần tử một lúc performance sẽ tốt hơn
	INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)
	VALUES (1, 'Paul', 32, 'California', 20000.00 ), => chú ý kiểu int viết khác kiểu text
	(2, 'Paul1', 32, 'California1', 20000.00 ),
	 (3, 'Paul2', 32, 'California2', 20000.00 );

 */

	private static void insertRecords(){
		
		String name, email, pass;
		String sql;
		//
		Connection conn = null;
		Statement stmt = null;
		String databaseName = "login";
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL+ databaseName, USER, PASS);
			stmt = conn.createStatement();

			log.debug("start inserting 50k value");
			
			for(int i = 0; i < 5000; i ++){
				name = "abc" + String.valueOf(i);
				email = "hungbeo"+ String.valueOf(i);
				pass = "1234";
				sql = "INSERT INTO LOGIN (name,email,pass) " + "VALUES ('" + name + "','"+email +"','" + pass +"')";
				stmt.executeUpdate(sql);
			}
			
			log.debug("stop inserting 50k value");

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
