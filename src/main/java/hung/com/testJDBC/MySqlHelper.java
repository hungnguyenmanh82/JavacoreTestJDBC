package hung.com.testJDBC;

import java.sql.*;

/*
 * Tạo template để truy cập dữ liệu DAO
 * */
public class MySqlHelper {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	private static final String DB_URL = "jdbc:mysql://localhost"; 
	private static final String USER = "root"; //root
	private static final String PASS = "123456789"; //123456789
	
	
	private Statement statement = null;

	MySqlHelper(String databaseName){
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseName,USER, PASS);
			statement = connection.createStatement();			
		}catch(Exception e){
			e.printStackTrace();
			try {
				if(statement != null){
					statement.close();	
				}
				if(connection != null){
					connection.close();	
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void closeConnection(){
		Connection connection = null;
		try {
			connection = statement.getConnection();
			statement.close();	
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if(statement != null){
					statement.close();	
				}
				if(connection != null){
					connection.close();	
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public boolean isConnectionClosed(){
		if(statement == null){
			return true;
		}
		return false;
	}

	public static void main(String[] args) {

	}//end main


}
