package com.imaginea.qctree;

import com.imaginea.qctree.hive.Hivejdbc;

public class BuildTree {

	public static void main (String[] args ){
		
		Hivejdbc obj = Hivejdbc.getObject();
		obj.buildQCube();
		
	}
}
