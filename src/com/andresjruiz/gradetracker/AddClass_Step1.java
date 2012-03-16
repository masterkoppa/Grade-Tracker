package com.andresjruiz.gradetracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class AddClass_Step1 extends Activity {
	private String ClassName = "";
	private String ProfName = "";
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	//Remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
    	final Context This = this;
    	
        setContentView(R.layout.add_class);
        
        Button next = (Button) findViewById(R.id.next_button);
        
        next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
				//Validate Input
				ClassName = ((EditText) findViewById(R.id.class_name_input)).getText().toString();
				ProfName = ((EditText) findViewById(R.id.prof_name_input)).getText().toString();
				
				//If the string is empty
				if(ClassName.length() == 0){
					//Do an alert dialog to show the error message
					//For more info: http://developer.android.com/guide/topics/ui/dialogs.html#AlertDialog
					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(This);
					alertBuilder.setMessage("Class Name Can't Be Empty");
					alertBuilder.setTitle("Error in Input!");
					alertBuilder.setIcon(android.R.drawable.ic_dialog_alert);
					alertBuilder.create().show();
					return;
				}else if(ProfName.length() == 0){
					AlertDialog.Builder alertBuilder = new AlertDialog.Builder(This);
					alertBuilder.setTitle("Warning!");
					alertBuilder.setMessage("The Proferssors Name Shouldn't be empty, would you like to still continue?");
					alertBuilder.setIcon(android.R.drawable.ic_dialog_alert);
					alertBuilder.setCancelable(false);
					alertBuilder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// Do Nothing so I can continue to the next step
							goToNextActivity(AddClass_Step1.this.ClassName,  AddClass_Step1.this.ProfName);
						}
					});
					alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
							
						}
					});
					alertBuilder.create().show();
				} else{
					goToNextActivity(ClassName,  ProfName);
				
				}
			}
		});
        
        Button cancel = (Button) findViewById(R.id.cancel_button);
        
        cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});

    }
    
    private void goToNextActivity(String ClassName, String ProfName){
    	Intent nextStep = new Intent();
		nextStep.setClassName("com.andresjruiz.gradetracker", "com.andresjruiz.gradetracker.AddClass_Step2");
		nextStep.putExtra("ClassName", ClassName);
		nextStep.putExtra("ProfName", ProfName);
		
		startActivity(nextStep);
    }
}
