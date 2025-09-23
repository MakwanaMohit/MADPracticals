package com.legpm.pr21;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentHolder> {
    public StudentAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    Context context;
    List<Student> students;

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student student = students.get(position);
        holder.rollno.setText("RollNO: " + student.rollno);
        holder.name.setText("Name: " + student.name);
        holder.sem.setText("Semester: " + student.sem);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void addStudent(Student student) {
        if (getStudentPositionByKey(student.key) == -1) {
            students.add(student);
            notifyItemInserted(students.size() - 1);
        }
    }


    public void updateStudent(Student updatedStudent) {
        int position = getStudentPositionByKey(updatedStudent.key);
        if (position != -1) {
            students.set(position, updatedStudent);
            notifyItemChanged(position);
        }
    }

    public void removeStudent(String key) {
        int position = getStudentPositionByKey(key);
        if (position != -1) {
            students.remove(position);
            notifyItemRemoved(position);
        }
    }

    private int getStudentPositionByKey(String key) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }
}
