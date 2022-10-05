package com.project;

public class FullTimeTeacher extends Teacher {

    private static final long serialVersionUID = 1L;

	public FullTimeTeacher(String name, int age, int hiredYear, String gender, int id, String specialty, String degree) {
        super(name, age, hiredYear, gender, id, specialty, degree);
        super.setFullTime(true);
    }
    
}
