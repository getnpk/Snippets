package com.imaginea.qctree;

import com.imaginea.qctree.hive.Hivejdbc;

public class Launch {

	public static void main (String[] args ){
		Table table = Table.getTable();
		System.out.println(table.getDimensionHeaders());
		System.out.println(table.getMeasureHeaders());
		System.out.println(table.getHiveBaseTable());
		
		Hivejdbc obj = Hivejdbc.getObject();
		obj.buildQCube();
		
	}
}
