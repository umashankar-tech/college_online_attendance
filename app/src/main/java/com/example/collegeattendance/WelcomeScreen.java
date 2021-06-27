package com.example.collegeattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.collegeattendance.studentpart.StudentPartLogin;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
    }

    public void goAdminLogin(View view) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    public void gotoTeacherLogin(View view) {
        Intent intent = new Intent(this,TeacherLogin.class);
        startActivity(intent);
    }

    public void gotoStudentLogin(View view) {
        Intent intent = new Intent(this, StudentPartLogin.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
      return;
    }
}