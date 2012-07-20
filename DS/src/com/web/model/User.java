package com.web.model;

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

public String getFirstname(){
return firstname;
}

public String getLastname(){
return lastname;
}

public String toString(){
return String.format("%s %s %s %s", firstname, lastname, username, password);
}

}