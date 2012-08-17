package com.getnpk.taskmanager;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Application;

public class TaskManagerApplication extends Application{

	private ArrayList<Task> currentTasks;

	@SuppressLint("ParserError")
	@Override
	public void onCreate() {
		super.onCreate();
		if (currentTasks == null){
			currentTasks = new ArrayList<Task>();
		}
	}

	public void setCurrentTasks(ArrayList<Task> currentTasks){
		this.currentTasks = currentTasks;
	}
	public ArrayList<Task> getCurrentTasks(){
		return currentTasks;
	}
	
	public void addTask( Task T ){
		assert (null != T);
		if (currentTasks == null)
			currentTasks = new ArrayList<Task>();
		currentTasks.add(T);
	}
	
}
