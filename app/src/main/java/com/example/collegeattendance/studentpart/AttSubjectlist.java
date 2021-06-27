package com.example.collegeattendance.studentpart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.collegeattendance.R;
import com.example.collegeattendance.SubejctAdapter;
import com.example.collegeattendance.SubjectModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AttSubjectlist extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<SubjectModel> datalist;
    AttSubjectAdapter subejctAdapter;

    ArrayList<String> streamBatchYearValue;
    FirebaseFirestore firebaseFirestore ;

    String stream;
    String year;
    String semester;
    String section;
    String Rollno;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_att_subjectlist);
        recyclerView = findViewById(R.id.attbatchesRecylerView);

        datalist = new ArrayList<>();
        streamBatchYearValue = new ArrayList<>();
        subejctAdapter = new AttSubjectAdapter(this, streamBatchYearValue,datalist);

        firebaseFirestore = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        stream= intent.getStringExtra("stream");
        semester = intent.getStringExtra("semester");
        year = intent.getStringExtra("year");
        section= intent.getStringExtra("section");
        Rollno = intent.getStringExtra("rollno");


        Query query = firebaseFirestore.collection("subject").whereEqualTo("stream",stream).whereEqualTo("year",year).whereEqualTo("semester",semester);


        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:list)
                {
                    SubjectModel model = d.toObject(SubjectModel.class);
                    datalist.add(model);
                }
                subejctAdapter.notifyDataSetChanged();

            }
        });
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        streamBatchYearValue.add(stream);
        streamBatchYearValue.add(year);
        streamBatchYearValue.add(semester);
        streamBatchYearValue.add(section);
        streamBatchYearValue.add(Rollno);

        recyclerView.setAdapter(subejctAdapter);
    }

}