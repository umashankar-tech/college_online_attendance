package com.example.collegeattendance.studentpart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collegeattendance.PageToTeacherLogin;
import com.example.collegeattendance.R;
import com.example.collegeattendance.TeacherLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class StudentPartLogin extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText gmail ;
    EditText password1;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_part_login);

        firebaseAuth=FirebaseAuth.getInstance();
        gmail=(EditText) findViewById(R.id.studentloginMail);
        password1 = (EditText)findViewById(R.id.studentLoginPassword);
        signUp= (Button)findViewById(R.id.studentLoginButton);
    }

    public void goToStuAttSubjectList(View view) {
        String mail = gmail.getText().toString();
        String password = password1.getText().toString();

        if(mail.isEmpty()){
            gmail.setError("Enter your mailID");
            gmail.requestFocus();
        }
        else if (password.isEmpty()){
            password1.setError("Enter Your Password");
            password1.requestFocus();
        }
        else if(mail.isEmpty()&&password.isEmpty()){
            gmail.setError("Enter your mailID");
            gmail.requestFocus();
            password1.setError("Enter Your Password");
            password1.requestFocus();
            Toast.makeText(this, "Fields are being Empty", Toast.LENGTH_SHORT).show();
        }
        else if(!(mail.isEmpty()&&password.isEmpty())){
            firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        StyleableToast.makeText(StudentPartLogin.this, "Unable to Login \n Something Went Wrong",R.style.customFailureToast).show();
                    }
                    else{
                        Intent intent = new Intent(StudentPartLogin.this, StudentSubjectListToGetAttPer.class);
                        startActivity(intent);

                    }

                }
            });
        }

        else{

        }
    }



}