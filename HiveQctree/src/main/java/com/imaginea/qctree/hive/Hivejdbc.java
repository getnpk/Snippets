package com.imaginea.qctree.hive;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imaginea.qctree.Cell;
import com.imaginea.qctree.QCCube;
import com.imaginea.qctree.Row;
import com.imaginea.qctree.Table;
import com.imaginea.qctree.Class;
import com.imaginea.qctree.hadoop.QCTree;


public class Hivejdbc {

	private static final Log LOG = LogFactory.getLog(Hivejdbc.class);
	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
	private static Hivejdbc hivejdbc;
	
	
	private String wareHouseURL = "jdbc:hive://localhost:10000/default";
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultset;
	
	private Table baseTable = Table.getTable();
	private String basetablename = baseTable.getHiveBaseTable();
	private int noOfDims = baseTable.getDimensionHeaders().size();
	private int noOfMeas = baseTable.getMeasureHeaders().size();
	
	private String[] dims = new String[noOfDims];
	private double[] meas = new double[noOfMeas];
	
	private Row row;
	
	public static Hivejdbc getObject(){
		
		if (hivejdbc == null)
			return new Hivejdbc();
		else
			return hivejdbc; 
	}
	
	private Hivejdbc(){
		try {
		      java.lang.Class.forName(driverName);
		    } catch (ClassNotFoundException e) {
		      e.printStackTrace();
		      System.exit(1);
		    }
		
		try {
			connection = DriverManager.getConnection(wareHouseURL, "hadoop", "hadoop");
			statement = connection.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void buildQCube(){
		
		try {
			resultset = statement.executeQuery("select * from " + basetablename);
			
			while(resultset.next()){
				try{
					for (int i = 0 ; i < noOfDims ; i++){
						dims[i] = resultset.getString(i+1);
					}
					for (int i = noOfDims +1 ; i< noOfMeas; i++){
						meas[i] = resultset.getDouble(i+1);
					}
				}catch(Exception e){
					LOG.info("Invalid record!");
				}
				
				row = new Row(dims, meas);
				
				if (LOG.isInfoEnabled()) {
				       LOG.info("Adding row : " + row);
				}
				baseTable.addRow(row);
			}
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	
		QCCube cube = QCCube.construct();
		cube.printClasses();
		QCTree tree = QCTree.build(cube);
		System.out.println(tree);
		
		try {
			String fullPath = System.getProperty("user.dir") + "/qcube_lattice.csv";
			LOG.info(fullPath);
			resultset = statement.executeQuery("load data local inpath '" + fullPath +"' into table qcube_lattice");
			if(resultset != null){
				System.out.println("loaded data");
				new File(fullPath).delete();
				System.out.println("file deleted.");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				resultset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/*
	 * Need to store aggregate values as well,
	 * does not work with just the lattice structure.
	 * Direction 1
	 * */
	public void buildTree(){
		Set<Class> classes = new TreeSet<Class>();
		Cell cell;
		try {
			resultset = statement.executeQuery("select * from qcube_lattice");
			while(resultset.next()){
				Class theclass = new Class();
				//System.out.printf("%d %s %s %d", resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getInt(4));
				
				theclass.setClassID(resultset.getInt(1));
				cell = new Cell(resultset.getString(2).toString().split(" ")); 
				theclass.setUpperBound(cell);
				cell = new Cell(resultset.getString(3).toString().split(" "));
				theclass.setLowerBound(cell);
				
				int chdId = resultset.getInt(4); 
				if (chdId == -1)
					theclass.setChild(null);
				else{
					//theclass.setChild(new Class(chdId));
					for (Class c: classes)
						if (c.getClassID() == chdId) theclass.setChild(c);
				}
					
				classes.add(theclass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (Class c : classes){
			System.out.println(c);
		}
		
	}
}
