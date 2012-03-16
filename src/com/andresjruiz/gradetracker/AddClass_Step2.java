package com.andresjruiz.gradetracker;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import android.app.Dialog;
import android.app.ListActivity;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AddClass_Step2 extends ListActivity {
	
	private String ClassName;
	private String ProfName;
	private MatrixCursor data;
	private SimpleCursorAdapter adapter;
	private Map<String, Integer> categories;
	private String[] listMappings;
	/**
     * <ul>
     * <li>The first element has to be some non-related view to put numbers in</li>
     * <li>The second element must be where the category name will be</li>
     * <li>The third element must be where the percentage will be shown</li>
     * </ul>
     */
    private int[] viewMapping;
	
	/** Called when the activity is first created. */
    @SuppressWarnings("unchecked")
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	gatherBundleInformation();
    	
    	//Remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
        setContentView(R.layout.add_class_categories);      
        
        setTitleText();
        
        //Defines the keys that will be associated with each object
        listMappings = new String[]{"_id", "Name", "Percent"};
        //Defines the resources linked to the keys
        viewMapping = new int[]{R.id.textView1, R.id.class_name, R.id.grade_display };
        data = new MatrixCursor(listMappings);
        try{
        	categories = ((Map<String, Integer>) getLastNonConfigurationInstance());
        }catch(ClassCastException e){
        	Log.e("ERROR_CAUGHT", e.getMessage());
        }
        
        if(categories == null){
	        data.addRow(new String[]{"0", "Exams", "50%"});
	        data.addRow(new String[]{"1", "Homework", "50%"});
	        
	        this.categories = new TreeMap<String, Integer>();
	        
	        categories.put("Exams", 50);
	        categories.put("Homework", 50);
        } else{
        	int i = 0;
    		for(String Cat : categories.keySet()){
    			int percent = categories.get(Cat);
    			data.addRow(new String[]{Integer.toString(i), Cat, percent + "%"});
    			i++;
    		}
        	
        }

        //ListAdapter adapter = new SimpleAdapter(this, listData, R.layout.main_list_item, listMappings, viewMapping);
        adapter = new SimpleCursorAdapter(this, R.layout.category_edit_item, data, listMappings, viewMapping);
        
        //Fill in the list data
        setListAdapter(adapter);
        
        //List Item Listener
        this.getListView().setOnItemClickListener(new OnItemClickListener(){

        	@Override
			public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) {
        		String catName = (String) ((TextView) view.findViewById(R.id.class_name)).getText();
        		String grade = (String) ((TextView) view.findViewById(R.id.grade_display)).getText();
        		//Remove the % sign at the end
        		grade = grade.substring(0, grade.length()-1);
        		
        		int weight = Integer.parseInt(grade);
        		
        		showGradeSelector(catName, weight, (int)id);
			}
        	
        });
        
        setButtonListeners();
    }
    
    @Override
    public Object onRetainNonConfigurationInstance(){
		return categories;
    	
    }

	private void gatherBundleInformation(){
    	//Get the information from the past screen
        Bundle info = this.getIntent().getExtras();
        
        ClassName = info.getString("ClassName");
        ProfName = info.getString("ProfName");
    }
    
    private void setTitleText(){
    	//Set the title for this screen
        
        TextView title = ((TextView)findViewById(R.id.categoriesTitle));
        title.append(": " + ClassName);
        
        if(ProfName.length() >= 1){
        	title.append(" By: " + ProfName);
        }
    }
    
    private void showGradeSelector(String Category, int startPercent, int catID){
    	final Dialog dialog = new Dialog(this);
    	
    	dialog.setContentView(R.layout.custom_seek_dialog);
    	
    	dialog.setTitle("Select Grade Weight in % of total grade");
    	
    	//Deal with the seek bar
    	SeekBar seek = (SeekBar)dialog.findViewById(R.id.gradeWeightBar);
    	
    	seek.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar seek, int progress, boolean fromUser) {
				TextView display = ((TextView)(dialog.findViewById(R.id.grade_weight_display)));
				display.setText(Integer.toString(progress) + "%");
			}

			@Override
			public void onStartTrackingTouch(SeekBar seek) {				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seek) {
			}
    		
    	});
    	
    	seek.setProgress(startPercent);
    	
    	//Handle the confirm button
    	Button confirmButton = (Button)(dialog.findViewById(R.id.confirm));
    	confirmButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				dialog.cancel();
			}
    		
    	});
    	
    	dialog.show();
    }
    
    /**
     * Handles all the logistics of getting the buttons working for this activity
     */
    private void setButtonListeners(){
    	//The next button
    	Button next = (Button) findViewById(R.id.next_button);
        
        next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
        
        //The back button
        Button back = (Button) findViewById(R.id.back_button);
        
        back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        
        //The add category button
        Button button = (Button) findViewById(R.id.addCategoryButton);
        
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addCategory();
			}
		});
    }
    
    /**
     * Adds a new category with the string by the ID string_new_category
     * This handles adding stuff to the internal representation as well as the matrix
     * cursor.
     * 
     * A nice little feature is that it will automatically re-balance everything to make sure
     * we always have 100% as a total.
     */
    private void addCategory(){
    	
    	if(categories.containsKey(getString(R.string.string_new_category))){
    		Toast.makeText(this, "Plase Edit New Category Before Adding Another", Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
    	
    	
    	int id = categories.size() + 1;
		
		if(id >= 1){
			int size = id - 1;
			Object[] c = categories.keySet().toArray();
			for(Object i : c){
				int oldValue = categories.get(i.toString());
				categories.remove(i);
				categories.put(i.toString(), oldValue-(getResources().getInteger(R.integer.default_category_value)/size));
			}
    	}
		
		//Check for total sum
		int sum = 0;
		for(Integer i : categories.values()){
			sum += i;
		}
		
		int value = 100-sum;//Account for any rounding errors
		
		categories.put(getString(R.string.string_new_category), value);
		
		data.close();
		
		data = new MatrixCursor(listMappings);
		
		int i = 0;
		for(String Cat : categories.keySet()){
			int percent = categories.get(Cat);
			data.addRow(new String[]{Integer.toString(i), Cat, percent + "%"});
			i++;
		}
		
    	adapter.changeCursor(data);
		adapter.notifyDataSetChanged();
    }
    
    private void deleteCategory(int id){
    	
    }
}
