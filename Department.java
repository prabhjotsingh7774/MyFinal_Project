package com.project;

import java.io.*;
import java.util.*;

public class Department {

	private String departmentName;
    private int departmentId;
    private int deanId;
    private Teacher dean;
    private List<Teacher> teachers;
    private List<Staff> staff;
    private String saveFile;

    public Department(String departmentName, int departmentId, int deanId,
                      List<Teacher> teachers, List<Staff> staff, String saveFile) {
        this.departmentName = departmentName;
        this.departmentId = departmentId;
        this.deanId = deanId;
        this.saveFile = saveFile;

        loadDept(saveFile);
        if ((this.teachers != null) && (this.staff != null)) {
            saveDept(saveFile);
        }
        for (Teacher t : teachers) {
            addTeacher(t);
        }
        for (Staff s : staff) {
            addStaff(s);
        }
    }

    public void addTeacher(Teacher teacher) {
        if (teachers == null) {
            teachers = new ArrayList<>();
        }
        if (!teacherExists(teacher.getId())) {
            teachers.add(teacher);
            saveDept(saveFile);
        }
        else {
            System.out.println(String.format(
                    "Teacher with ID %d is already in this department,"
                            + " not adding again.", teacher.getId()));
        }
    }

    public void addStaff(Staff staffMember) {
        if (staff == null) {
            staff = new ArrayList<>();
        }
        if (!staffMemberExists(staffMember.getId())) {
            staff.add(staffMember);
            saveDept(saveFile);
        }
        else {
            System.out.println(String.format(
                    "Staff member with ID %d is already in this department,"
                            + " not adding again.", staffMember.getId()));
        }
    }

    public void removeTeacher(Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teacher.getId() == teachers.get(i).getId()) {
                teachers.remove(teachers.get(i));
            }
        }
        saveDept(saveFile);
    }

    public void removeStaff(Staff staffMember) {
        if (staff == null) 
            return;
        for (int i = 0; i < staff.size(); i++) {
            if (staffMember.getId() == staff.get(i).getId()) {
                staff.remove(staff.get(i));
            }
        }
        saveDept(saveFile);
    }

    @SuppressWarnings("unchecked")
	public void loadDept(String name) {
        File tFile = new File(name + "_Teachers.ser");
        File sFile = new File(name + "_Staff.ser");

        try (FileInputStream fos = new FileInputStream(tFile);
                ObjectInputStream ois = new ObjectInputStream(fos)) {
                this.teachers = (List<Teacher>) ois.readObject();
            
        } catch (FileNotFoundException ex) {
            System.out.println("The file " + name + " does not exist!");
        } catch (IOException ex) {
            System.out.println("An error has occurred while loading the file" + name + ".");
        } catch (ClassNotFoundException ex) {
            System.out.println("You should never see this!");
        }
        
        try (FileInputStream fos = new FileInputStream(sFile);
                ObjectInputStream ois = new ObjectInputStream(fos)) {
                this.staff = (List<Staff>) ois.readObject();
            
        } catch (FileNotFoundException ex) {
            System.out.println("The file " + name + " does not exist!");
        } catch (IOException ex) {
            System.out.println("An error has occurred while loading the file" + name + ".");
        } catch (ClassNotFoundException ex) {
            System.out.println("You should never see this!");
        }

        
    }

    public void saveDept(String name) {
        File tFile = new File(name + "_Teachers.ser");
        File sFile = new File(name + "_Staff.ser");
        
        try (FileOutputStream fos = new FileOutputStream(tFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(teachers);
        } catch (IOException ex) {
            ex.getMessage();
        }
        
        try (FileOutputStream fos = new FileOutputStream(sFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(staff);
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    private boolean staffMemberExists(int id) {
        if (staff == null || staff.isEmpty())
            return false;
        for (Staff s : staff) {
            if (s.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private boolean teacherExists(int id) {
        if (teachers == null || teachers.isEmpty())
            return false;
        for (Teacher t : teachers) {
            if (t.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyDean() {
        if (teachers == null) 
            return false;
        for (Teacher t : teachers) {
            if (t.getId() == deanId) {
                dean = t;
                return true;
            }
        }
        System.out.println("No dean found!");
        return false;
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Department Information: \n");
        str.append("Name: ").append(departmentName).append("\n");
        str.append("Department ID: ").append(departmentId).append("\n");
        str.append("Dean ID: ").append(deanId).append("\n");
        str.append("Employee List: \n");
        for (Teacher t : teachers) {
            str.append(t).append("\n");
        }
        for (Staff s : staff) {
            str.append(s).append("\n");
        }
        return str.toString();
    }
    
}
