package com.mk.madpractical;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentHolder extends RecyclerView.ViewHolder {
    TextView name,rollno,sem;
    public StudentHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.Pr18name);
        rollno = itemView.findViewById(R.id.Pr18rollno);
        sem = itemView.findViewById(R.id.Pr18sem);
    }
}
