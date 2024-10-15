package com.mk.madpractical;

import java.util.Objects;

public class Student {
    String key; // Firebase key
    String rollno;
    String name;
    String sem;

    public Student(String key, String rollno, String name, String sem) {
        this.key = key;
        this.rollno = rollno;
        this.name = name;
        this.sem = sem;
    }

    public Student() {
        // Default constructor required for calls to DataSnapshot.getValue(Student.class)
    }

    // Getter and setter for key
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(rollno, student.rollno) && Objects.equals(name, student.name) && Objects.equals(sem, student.sem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollno, name, sem);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }
}
