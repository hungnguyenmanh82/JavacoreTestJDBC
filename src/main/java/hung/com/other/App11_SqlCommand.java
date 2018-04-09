package hung.com.other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.DatabaseMetaData;

/**
 * @author hungnm2
 * Example for all cases.
 * each functions can run independently.
 * This example get from Tutorialspoint.
 */
public class App11_SqlCommand {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	static final String DB_URL = "jdbc:mysql://localhost"; 
	static final String USER = "root"; //root
	static final String PASS = "123456789"; //123456789


	public static void main(String[] args) {
		String databaseName = "testCreateDB";
		
		//test1: create database
		createDatabase(databaseName);

		//test2: create table on the data base which have just created above
		String sqlTable = "CREATE TABLE REGISTRATION2 " +
				"(id INTEGER not NULL, " +
				" first VARCHAR(255), " + 
				" last VARCHAR(255), " + 
				" age INTEGER, " + 
				" PRIMARY KEY ( id ))";
		createTable(databaseName, sqlTable);
		
        //test3: show all tables in the database
		showTables(databaseName);

	}

	//mysql> show databases;

	private static String sqlCreateDatabase = "CREATE DATABASE IF NOT EXISTS "+ "databaseName" +";";
	private static String sqlDropDatabase = "DROP DATABASE IF EXISTS "+ "databaseName" +";";

	private static void executeRawSQL(String sql){
		Connection conn = null;
		Statement stmt = null;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER, PASS);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			System.out.println("run successfully sql command: "+ sql);
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

	}

	private static void createDatabase(String databaseName){
		String sql = "CREATE DATABASE IF NOT EXISTS "+ databaseName +";";
		executeRawSQL(sql);
	}

	private static void dropDatabase(String databaseName){
		String sql = "DROP DATABASE IF EXISTSDROP DATABASE IF EXISTS "+ databaseName +";";
		executeRawSQL(sql);
	}

	private static String sqlTable1 = "CREATE TABLE REGISTRATION " +
			"(id INTEGER not NULL, " +
			" first VARCHAR(255), " + 
			" last VARCHAR(255), " + 
			" age INTEGER, " + 
			" PRIMARY KEY ( id ))"; 

	private static String sqlTable2 = "CREATE TABLE city (" +
			"ID int(11) NOT NULL AUTO_INCREMENT, "+ 
			"Name char(35) NOT NULL DEFAULT '', " +
			"CountryCode char(3) NOT NULL DEFAULT '', "+
			"District char(20) NOT NULL DEFAULT '', "+
			"Population int(11) NOT NULL DEFAULT '0', "+
			"PRIMARY KEY (`ID`),"+
			"KEY `CountryCode` (`CountryCode`),"+
			"CONSTRAINT `city_ibfk_1` FOREIGN KEY (`CountryCode`) REFERENCES `country` (`Code`)"+
			") ENGINE=InnoDB AUTO_INCREMENT=4080 DEFAULT CHARSET=latin1;";

	private static String sqlTable3 = "CREATE TABLE `city` ("+
			"`ID` int(11) NOT NULL AUTO_INCREMENT,"+
			"`Name` char(35) NOT NULL DEFAULT '',"+
			"`CountryCode` char(3) NOT NULL DEFAULT '',"+
			"`District` char(20) NOT NULL DEFAULT '',"+
			"`Population` int(11) NOT NULL DEFAULT '0',"+
			"PRIMARY KEY (`ID`),"+
			"KEY `CountryCode` (`CountryCode`),"+
			"INDEX `PopulationIndex` (`Population`)"+
			") ENGINE=InnoDB AUTO_INCREMENT=4080 DEFAULT CHARSET=latin1;";

    /*
     * step1: connect database => need @databaseName
     * step2: create table
     * */
	private static void createTable(String databaseName, String sqlTable){
		Connection conn = null;
		Statement stmt = null;
		try{
			//step1: connect database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName, USER, PASS);
			stmt = conn.createStatement();

			//step2: create table
			stmt.executeUpdate(sqlTable);
			System.out.println(String.format("jdbc:mysql://localhost/%s >%s", databaseName, sqlTable));
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

	}

	//SQL> DROP TABLE Employees;
	private static void dropTable(String databaseName, String tableName){
		Connection conn = null;
		Statement stmt = null;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName, USER, PASS);
			stmt = conn.createStatement();

			//create table
			stmt.executeUpdate("DROP TABLE "+tableName);
			System.out.println("DROP TABLE "+tableName);
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
			}catch(SQLException se){
			}// do nothing
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try

	}

	//sql> CREATE INDEX index_name	ON table_name (column_name);
	//sql> CREATE INDEX index_name	on table_name (column1, column2);
	//sql> DROP INDEX index_name;

	private static String sqlIndex1 = "sql> CREATE INDEX index_name	ON table_name (column_name);";
	/*
	 * Thường index khi create table luôn
	 */
	private static void createIndex(String databaseName, String sqlIndex){
		Connection conn = null;
		Statement stmt = null;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName, USER, PASS);
			stmt = conn.createStatement();

			//create table
			stmt.executeUpdate(sqlIndex);
			System.out.println("Created table in given database...");
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
			}catch(SQLException se){
			}// do nothing
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
	}

	private static void showTables(String databaseName){
		Connection conn = null;
		Statement stmt = null;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName, USER, PASS);

			DatabaseMetaData md = (DatabaseMetaData) conn.getMetaData();
			//getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) 
			ResultSet rs = md.getTables(null, null, "%", null);
			System.out.println(">show tables:");
			while (rs.next()) {
				System.out.println(rs.getString(3));
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

	/**
	 * Template to insertRecords to a Table:

	      String sql = "CREATE TABLE REGISTRATION " +
                   "(id INTEGER not NULL, " +
                   " first VARCHAR(255), " + 
                   " last VARCHAR(255), " + 
                   " age INTEGER, " + 
                   " PRIMARY KEY ( id ))"; 
	 */

	private static void insertRecords(){
		Connection conn = null;
		Statement stmt = null;

		String databaseName = "databaseName";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName, USER, PASS);
			stmt = conn.createStatement();

			String sql = "INSERT INTO Registration " +
					"VALUES (100, 'Zara', 'Ali', 18)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Registration " +
					"VALUES (101, 'Mahnaz', 'Fatma', 25)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Registration " +
					"VALUES (102, 'Zaid', 'Khan', 30)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Registration " +
					"VALUES(103, 'Sumit', 'Mittal', 28)";
			stmt.executeUpdate(sql);
			System.out.println("Inserted records into the table...");

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
		String databaseName = "databaseName";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName, USER, PASS);
			stmt = conn.createStatement();

			String sql = "SELECT id, first, last, age FROM Registration";
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				int id  = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				//Display values
				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
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

	/**
	 * Template to update Records to a Table

	      String sql = "CREATE TABLE REGISTRATION " +
                   "(id INTEGER not NULL, " +
                   " first VARCHAR(255), " + 
                   " last VARCHAR(255), " + 
                   " age INTEGER, " + 
                   " PRIMARY KEY ( id ))"; 
	 */
	private static void updateRecords(){
		Connection conn = null;
		Statement stmt = null;
		String databaseName = "databaseName";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName, USER, PASS);
			stmt = conn.createStatement();

			String sql = "UPDATE Registration " +
					"SET age = 30 WHERE id in (100, 101)";
			stmt.executeUpdate(sql);

			// Now you can extract all the records
			// to see the updated records
			sql = "SELECT id, first, last, age FROM Registration";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				//Retrieve by column name
				int id  = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				//Display values
				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
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

	/**
	 * Template to update Records to a Table

	      String sql = "CREATE TABLE REGISTRATION " +
                   "(id INTEGER not NULL, " +
                   " first VARCHAR(255), " + 
                   " last VARCHAR(255), " + 
                   " age INTEGER, " + 
                   " PRIMARY KEY ( id ))"; 
	 */
	private static void deleteRecords(){
		Connection conn = null;
		Statement stmt = null;
		String databaseName = "databaseName";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName, USER, PASS);
			stmt = conn.createStatement();
			
			String sql = "DELETE FROM Registration " +
									"WHERE id = 101";
			stmt.executeUpdate(sql);

			// Now you can extract all the records
			// to see the remaining records
			sql = "SELECT id, first, last, age FROM Registration";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				//Retrieve by column name
				int id  = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				//Display values
				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
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
