package com.example.collegeattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class TeacherLogin extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText gmail ;
    EditText password1;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_login);

        firebaseAuth=FirebaseAuth.getInstance();
        gmail=(EditText) findViewById(R.id.teachemailId);
        password1 = (EditText)findViewById(R.id.teachloginpassword);
        signUp= (Button)findViewById(R.id.teachloginBtn);
    }

    public void goToHomePage(View view) {
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
                        StyleableToast.makeText(TeacherLogin.this, "Please Check LOGIN credetianls\nOR\nInternet Connection",R.style.customFailureToast).show();
                    }
                    else{
                        Intent intent = new Intent(TeacherLogin.this,PageToTeacherLogin.class);
                        startActivity(intent);

                    }

                }
            });
        }

        else{

        }
    }

}