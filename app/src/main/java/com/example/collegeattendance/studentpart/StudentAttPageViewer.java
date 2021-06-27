package com.example.collegeattendance.studentpart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.collegeattendance.R;
import com.example.collegeattendance.SubjectModel;
import com.example.collegeattendance.WelcomeScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class StudentAttPageViewer extends AppCompatActivity {
    TextView totalPresent,totalAbsent,totalCounductedClass,percentage ,subjectName;
    int TOTAL_PRESENT ;
    int TOTAL_ABSENT;
    String stream;
    String year;
    String semester;
    String section;
    String rollno;
    String subject;


    FirebaseFirestore firebaseFirestore ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_att_page_viewer);
        totalPresent= findViewById(R.id.totalpresentcount);
        totalAbsent= findViewById(R.id.totalAbsentcount);
        totalCounductedClass =findViewById(R.id.totalconductedclassescount);
        subjectName = findViewById(R.id.subjectName);
        percentage= findViewById(R.id.attinPercentagecount);

        Intent intent = getIntent();
        stream= intent.getStringExtra("stream");
        semester = intent.getStringExtra("semester");
        year = intent.getStringExtra("year");
        section= intent.getStringExtra("section");
        subject = intent.getStringExtra("subject");
        rollno= intent.getStringExtra("rollno");
        subjectName.setText(subject);
        getAttCalC();

    }
    private void getAttCalC() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection("AttendanceRegister").whereEqualTo("RollNo",rollno).whereEqualTo("subject",subject).whereEqualTo("AttStatus","absent");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int count = 0;
                    for (DocumentSnapshot document : task.getResult()) {
                        count++;
                    }
                    String  TOTAL_PRESENT = String.valueOf(count);
                    totalAbsent.setText(TOTAL_PRESENT);

                }

                presenttotal();
            }

            private void presenttotal() {
                Query query2 = firebaseFirestore.collection("AttendanceRegister").whereEqualTo("RollNo",rollno).whereEqualTo("subject",subject).whereEqualTo("AttStatus","absent");

                query2.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            for (DocumentSnapshot document : task.getResult()) {
                                count++;
                            }
                            String  TOTAL_PRESENT = String.valueOf(count);
                            totalPresent.setText(TOTAL_PRESENT);
                        }


                    }
                });

            }
        });


        int total = TOTAL_ABSENT+TOTAL_PRESENT;
        String totalClasses = String.valueOf(total);
        totalCounductedClass.setText(totalClasses);

    }


    public void logout(View view) {
        Intent intent = new Intent(StudentAttPageViewer.this, WelcomeScreen.class);
        startActivity(intent);
    }
}