package com.web.model;

import java.lang.reflect.Method;


@Details(author = "nitin",	date = "08/08/12",reviewers = { "nitin" })

public class User {

private String firstname;
private String username;
private String password;
private String lastname;

public void setFirstname(String value){
firstname = value;
}

public void setLastname(String value){
lastname = value;
}

public void setUsername(String value){
username = value;
}

public void setPassword(String value){
password = value;
}

public String getPassword(){
return password;
}

public String getUsername(){
return username;
}

@Details(author = "nitin", date = "today", reviewers = { "none" })
public String getFirstname(){
return firstname;
}

public String getLastname(){
return lastname;
}

public String toString(){
return String.format("%s %s %s %s", firstname, lastname, username, password);
}


public void showClassDetails(){

	Class<? extends User> c = this.getClass();
	try {
		Method m = c.getMethod("getFirstname");
		
		Details classDetails  = (Details) c.getAnnotation(Details.class);
		System.out.println("Class author " + classDetails.author());
		
		Details methodDetails = m.getAnnotation(Details.class);
		System.out.println("Method reviewer " + methodDetails.reviewers()[0]);
		
	} catch (SecurityException e) {
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		e.printStackTrace();
	}
	
	
}

}