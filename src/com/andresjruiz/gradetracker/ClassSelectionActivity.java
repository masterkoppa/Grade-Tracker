package com.andresjruiz.gradetracker;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;

public class ClassSelectionActivity extends ListActivity {
	
	private int rows = 15;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                
        String[] list = {"LOL", "ROLF", "W/E"};
        
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, list));
        
        
        
        for(int i = 0; i < rows; i++){
        	//addRow("LOL");
        }
        

        //Restore Preferences
        SharedPreferences settings = getSharedPreferences("Storage", 0);
    }
    
    @Override
    public void onStop(){
    	super.onStop();
    	
    	//Save Preferences        
        SharedPreferences settings = getSharedPreferences("Storage", 0);
        SharedPreferences.Editor editor = settings.edit();
        
        //Store all the info here
        
        
        //Commit the changes
        
        editor.commit();
    }

}
