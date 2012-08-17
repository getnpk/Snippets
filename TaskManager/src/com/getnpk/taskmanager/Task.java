package com.getnpk.taskmanager;

public class Task {

	private String name;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(String name){
		return name;
	}
	
	public Task(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
