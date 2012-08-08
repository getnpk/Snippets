package com.web.view;

import java.lang.reflect.*;


public class ReflectClass {

	
	Constructor[] constructors;
	Method[] methods;
	Field[] fields;
	
	String classname;
	String packagename;
	Class c;
	
	
	public void printClassDetails(){
		
		classname = c.getName();
	
		if(classname.indexOf(".") > 0)
			packagename = classname.substring(0,classname.lastIndexOf("."));
		else
			packagename = "default";
		
		System.out.println("ClassName: "+ classname);
		System.out.println("PackageName: "+ packagename);
		
		int mod = c.getModifiers();
		if (Modifier.isInterface(mod)){
			System.out.println("Type: Interface");
		}else{
			System.out.println("Type: Class");
		}
		Class s = c.getSuperclass();
		if (s!= null){
			System.out.println("SuperClass: " + s.getName());
		}
		
		System.out.println();
		
	}
	
	public String getClassName(){return classname;}
	
	public String getPackageName(){return packagename;}
	
	// Print field details 
	public void printFieldDetials(){
		
		System.out.println("----Fields---");
		
		fields = c.getDeclaredFields();
		for (Field f: fields){
			int m = f.getModifiers();
			System.out.printf("%s\t%s\t%s\n", Modifier.toString(m),f.getType().getName(), f.getName());
		}
		
		System.out.println();
	}
	
	//Print method Details
	public void printMethodDetails(){
	
		System.out.println("----Methods---");
		
		methods = c.getDeclaredMethods();
		for(Method m : methods){
			int mod = m.getModifiers();
			System.out.printf("%s\t%s\t%s", Modifier.toString(mod),m.getReturnType(), m.getName());
			
			Class[] cc = m.getParameterTypes();
			System.out.print(" (");
			for (Class s : cc){
				System.out.print(" "+s.getName());
				if (cc.length > 1)
					System.out.print(" , ");
			}
			
			System.out.print(" )\n");
		}
		
		System.out.println();
	}
	
	public void printConstructorDetails(){
		
		System.out.println("----Constructors---");
		
		constructors = c.getDeclaredConstructors();
		
		if (constructors != null){
			for (Constructor c: constructors){
				int mod = c.getModifiers();
				
				System.out.printf("%s\t%s\t", Modifier.toString(mod),c.getName());
				Class[] cc = c.getParameterTypes();
				
				System.out.print(" (");
				for (Class s : cc){
					System.out.print(" "+s.getName());
					if (cc.length > 1 )
						System.out.print(" , ");
				}
				
				System.out.print(" )\n");
			}
		}else{
			System.out.println("Default");
		}
		
		System.out.println();
	}
	
	
	public ReflectClass( Object object){
	
		c = null;
		
		try {
			
			String theclass = object.getClass().toString().split(" ")[1];
			//c = Class.forName("com.web.view.JDBCConnect");
			c = Class.forName(theclass);
			
						
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}// end constructor
	
	
	public void printFullInfo(){
		printClassDetails();
		printFieldDetials();
		printConstructorDetails();
		printMethodDetails();
	}
}
