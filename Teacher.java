package com.project;

public class Teacher extends Person implements PayRoll {

	private static final long serialVersionUID = 1L;
	private String specialty;
    private String degree;
    private boolean fullTime;
    private double hoursWorked;

    public Teacher(String name, int age, int hiredYear, String gender, int id, String specialty, String degree, boolean fullTime) {
        super(name, age, hiredYear, gender, id);
        this.specialty = specialty;
        this.degree = degree;
        this.fullTime = fullTime;
    }

    public Teacher(String name, int age, int hiredYear, String gender, int id, String specialty, String degree) {
        super(name, age, hiredYear, gender, id);
        this.specialty = specialty;
        this.degree = degree;
    }

    public Teacher(String name, int age, int hiredYear, String gender, int id, String specialty, String degree, boolean fullTime, double hoursWorked) {
        super(name, age, hiredYear, gender, id);
        this.specialty = specialty;
        this.degree = degree;
        this.fullTime = fullTime;
        this.hoursWorked = hoursWorked;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    @Override
    public double computePayRoll() {
        if (isFullTime()) {
            switch (degree) {
                case "PhD": return 54.4 * 112;
                case "Master": return 54.4 * 82;
                case "Bachelor": return 54.4 * 42;
            }
        } else {
            switch (degree) {
                case "PhD": return 1.52 * hoursWorked * 112;
                case "Master": return 1.52 * hoursWorked * 82;
                case "Bachelor": return 1.52 * hoursWorked * 42;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%d|%d|%s|%d|%s|%s", "Teacher", getName(), getAge(), getHiredYear(), getGender(),
                getId(), specialty, degree);
    }

    @Override
    public double determineBonus() {
        return (!degree.equals("Bachelor")) ? 150 * (5 + ((fullTime) ? 4 : 0)) : 250;
    }
    
}
