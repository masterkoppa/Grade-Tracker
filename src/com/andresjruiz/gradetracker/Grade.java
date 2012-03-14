package com.andresjruiz.gradetracker;

import java.io.Serializable;

public class Grade implements Serializable{
	
	private String Name;
	private double grade;
	//Optional
	private double maxGrade = -1;
	
	public Grade(String name, double grade){
		this.Name = name;
		this.grade = grade;
	}
	
	public Grade(String name, double grade, double maxGrade){
		this.Name = name;
		this.grade = grade;
		this.maxGrade = maxGrade;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		Name = name;
	}

	/**
	 * @return the grade
	 */
	public double getGrade() {
		if(maxGrade == -1)//Only return plain grade when maxGrade isn't set
			return grade;
		else
			return grade/maxGrade;
	}

	/**
	 * @param grade the grade to set
	 */
	protected void setGrade(double grade) {
		this.grade = grade;
	}

	/**
	 * @return the maxGrade
	 */
	public double getMaxGrade() {
		return maxGrade;
	}

	/**
	 * @param maxGrade the maxGrade to set
	 */
	protected void setMaxGrade(double maxGrade) {
		this.maxGrade = maxGrade;
	}
	
	

}
