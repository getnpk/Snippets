
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

import view.Hbase;



public class JdbcTest {
  private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";

  
  public static void main(String[] args) throws SQLException {
      try {
      Class.forName(driverName);
    } catch (ClassNotFoundException e) {
       
      e.printStackTrace();
      System.exit(1);
    }
    Connection con = DriverManager.getConnection("jdbc:hive://localhost:10000/default", "hadoop", "hadoop");
    Statement stmt = con.createStatement();
    String tableName = "nasdaq";
    
    
    ResultSet mes = stmt.executeQuery("show tables");
    while(mes.next()){
    	System.out.println(mes.getString(1));
    }
    
  
    // select * query
    String sql = "select * from " + tableName + " where monyear=\"mar2012\" and logdate=\"20120308\" limit 5";
    System.out.println("Running: " + sql);
    ResultSet res = stmt.executeQuery(sql);
    while (res.next()) {
    	System.out.println(res.getString("thedate"));
    	//System.out.println(String.valueOf(res.getString(1)) + "\t" + res.getString(2));
      
    }

    Hbase ob = new Hbase();
    try {
		System.out.println(ob.getvalue("one", "details", "name"));
	} catch (IOException e) {
		 
		e.printStackTrace();
	}

    /* regular hive query
    sql = "select count(1) from " + tableName;
    System.out.println("Running: " + sql);
    res = stmt.executeQuery(sql);
    while (res.next()) {
      System.out.println(res.getString(1));
    }
    */
  }
}