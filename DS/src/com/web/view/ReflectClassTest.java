package com.web.view;

import com.web.model.DBFile;

public class ReflectClassTest {

	public static void main(String[] args){
	
		DBFile testobj = new DBFile();
		ReflectClass ob = new ReflectClass(testobj);
		ob.printFullInfo();

	}
}
