package com.getnpk.taskmanager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class AddTaskActivity extends TaskManagerActivity {

    private EditText taskNameEditText;
	private Button addButton;
	private Button cancelButton;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setUpViews();
    }

	protected void addTask() {
		String taskName = taskNameEditText.getText().toString();
		Task task = new Task(taskName);
		getTaskManagerApplication().addTask(task);
		finish();
	}

	protected void cancel() {
		finish();
	}

	
    private void setUpViews() {
		taskNameEditText = (EditText)findViewById(R.id.task_name);
		addButton = (Button)findViewById(R.id.add_button);
		cancelButton = (Button)findViewById(R.id.cancel_button);
		
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				addTask();
			}
		});
		
		cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				cancel();
			}
		});
	}


    
}
