package com.andresjruiz.gradetracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class StorageInterface implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8969617861310170810L;
	
	private ArrayList<Classes> Curriculum;
	
	public StorageInterface(){
		
		//Build test database
		
		this.Curriculum = new ArrayList<Classes>();
		
		HashMap<String, Integer> gradeWeights = new HashMap<String, Integer>();
		
		gradeWeights.put("Exams", 60);
		gradeWeights.put("Homework", 40);
		
		this.Curriculum.add(new Classes("Calc 1", 100, gradeWeights));
		this.Curriculum.add(new Classes("Calc 2", 20));
		this.Curriculum.add(new Classes("Calc 3", 50));
		this.Curriculum.add(new Classes("Discrete Mathematics", 15));
		this.Curriculum.add(new Classes("Cognitive Psychology", 95));
		this.Curriculum.add(new Classes("Personal Software Engineering", 86));
		this.Curriculum.add(new Classes("Software Eng of Software Subsystems", 78));
	}
	
	/**
	 * Returns a list with the names of all the classes in the database
	 * @return
	 */
	public String[] getClassNames(){
		String[] ClassNames = new String[Curriculum.size()];
		
		for(int i = 0; i < this.Curriculum.size(); i++){
			ClassNames[i] = Curriculum.get(i).getName();
		}
		
		return ClassNames;
	}
	
	public String[] getClassGradesPercent(){
		String[] ClassGrades = new String[Curriculum.size()];
		
		for(int i = 0; i < this.Curriculum.size(); i++){
			ClassGrades[i] = Integer.toString(this.Curriculum.get(i).getFinalGrade()) + "%";
		}
		
		return ClassGrades;
	}
	
	private void open(){
	}
	
	private void save(){
	}
	
	public void close(){
		
	}

	public String[] getClassCategories(String className) {
		String[] cat = new String[0];
		System.out.println(className);
		
		for(Classes i : Curriculum){
			if(i.getName().equals(className)){
				return i.getCategories();
			}
		}
		
		//return this.Curriculum.get(0).getCategories();
		
		return cat;//Return empty array if the class doesn't exist
	}

}
