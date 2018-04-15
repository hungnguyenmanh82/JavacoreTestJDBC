package hung.com.all.functions;

import java.text.SimpleDateFormat;

public class App86_Date {
	public static void main(String[] args) throws Exception{
		//============================
		String stDate = "2018-04-22 12:30:45.333";  //333 millisecond
		java.util.Date javaDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(stDate);
		System.out.println(javaDate.toString());
		
		//===========================
		String st =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(javaDate);
		System.out.println(st);
		
		//
		System.out.println("1==============================");
		java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
		System.out.println("java millisecond = " +  javaDate.getTime());
		System.out.println("sql millisecond = " +  sqlDate.getTime());
		
		System.out.println("2==============================");
		System.out.println(sqlDate.toString());
		//
		System.out.println("3============================== SQL date");
		String sqlFormat =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date(sqlDate.getTime()));
		System.out.println(sqlFormat);
		String sqlFormat2 =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new java.util.Date(sqlDate.getTime()));
		System.out.println(sqlFormat2);
		
		//
		System.out.println("4============================== SQL Time");
		java.sql.Time time = new java.sql.Time(sqlDate.getTime());
		
		System.out.println(time.toString());
		//
		System.out.println("5============================== SQL TimeStemp");
		java.sql.Timestamp timestamp = new java.sql.Timestamp(sqlDate.getTime());
		System.out.println(timestamp.toString());
	}
	
}
