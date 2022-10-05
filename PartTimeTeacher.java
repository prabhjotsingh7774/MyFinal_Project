package com.project;

public class PartTimeTeacher extends Teacher {

    private static final long serialVersionUID = 1L;
	private int hoursWorked;
    
    public PartTimeTeacher(String name, int age, int hiredYear, String gender, int id, String specialty, String degree, double hoursWorked) {
        super(name, age, hiredYear, gender, id, specialty, degree, false, hoursWorked);
        super.setFullTime(false);
    }
    
}
