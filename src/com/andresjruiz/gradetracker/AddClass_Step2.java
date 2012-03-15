package com.andresjruiz.gradetracker;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddClass_Step2 extends ListActivity {
	
	private String ClassName;
	private String ProfName;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
        setContentView(R.layout.add_class_categories);
        
        //Get the information from the past screen
        Bundle info = this.getIntent().getExtras();
        
        ClassName = info.getString("ClassName");
        ProfName = info.getString("ProfName");
        
        //Set the title for this screen
        
        TextView title = ((TextView)findViewById(R.id.categoriesTitle));
        
        title.append(": " + ClassName);
        
        Button next = (Button) findViewById(R.id.next_button);
        
        next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
        
        Button back = (Button) findViewById(R.id.back_button);
        
        back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        
        
        //List Stuff
        String[] list = {"LOL", "ROLF", "W/E", "ROLF", "W/E", "ROLF", "W/E", "ROLF", "W/E", "ROLF", "W/E", "ROLF", "W/E", "ROLF", "W/E", "ROLF", "W/E", "ROLF", "W/E"};
        
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, list));

    }
}
