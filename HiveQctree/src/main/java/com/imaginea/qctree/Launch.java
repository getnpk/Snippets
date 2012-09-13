package com.imaginea.qctree;

import com.imaginea.qctree.hadoop.QCTree;
import com.imaginea.qctree.hadoop.Query;
import com.imaginea.qctree.hive.Hivejdbc;

public class Launch {

	public static void main (String[] args ){
		Table table = Table.getTable();
		System.out.println(table.getDimensionHeaders());
		System.out.println(table.getMeasureHeaders());
		System.out.println(table.getHiveBaseTable());
		
		Hivejdbc obj = Hivejdbc.getObject();
		//obj.buildQCube();
		//System.out.println(obj.getTree());
		
		Query query = new Query();
		QCTree tree = obj.getTree();
		
		query.execute(tree, "*,Microsoft,*");
		System.out.println("-------------");
		query.execute(tree, "P2,Altavista,*");
		System.out.println("-------------");
		query.execute(tree, "*,*,p");
	}
}
