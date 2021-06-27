package com.example.collegeattendance.studentpart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.collegeattendance.R;
import com.example.collegeattendance.StreamActivity;

public class StudentSubjectListToGetAttPer extends AppCompatActivity {
   EditText getRollNo;
   String RollNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_subject_list_to_get_att_per);
        getRollNo = findViewById(R.id.getRollno);

    }

    public void gotoViewAttBySubjects(View view) {
        RollNo = getRollNo.getText().toString();
        Intent intent = new Intent(StudentSubjectListToGetAttPer.this, ViewAttBySubjects.class);
        intent.putExtra("rollNo",RollNo);
        startActivity(intent);
    }

    public void gotoDownloadPdf1(View view) {
        Intent intent = new Intent(StudentSubjectListToGetAttPer.this,DownloadPdfFile.class);
        startActivity(intent);

    }

}