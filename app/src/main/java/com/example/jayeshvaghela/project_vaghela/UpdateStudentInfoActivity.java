package com.example.jayeshvaghela.project_vaghela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateStudentInfoActivity extends AppCompatActivity {

    EditText et_UserUpdateStudentScore;
    EditText et_UserUpdateStudentComment;
    DBAdapter db;

    long userInput;
    String updatedScore;
    String updatedComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_info);

        et_UserUpdateStudentScore = (EditText) findViewById(R.id.et_UserUpdateStudentScore);
        et_UserUpdateStudentComment = (EditText) findViewById(R.id.et_UserUpdateStudentComment);

        db = new DBAdapter(this);
    }

    //When the update button is clicked the editted student score and comment gets changed in the database
    // and displayed in the listview.
    public void OnClickUpdatesStudentScoreAndComment(View view) {

        db.open();
        Intent intent = getIntent();
        userInput = intent.getExtras().getLong("output");
        updatedScore = et_UserUpdateStudentScore.getText().toString();
        updatedComment = et_UserUpdateStudentComment.getText().toString();
        db.updateContact(userInput, updatedScore, updatedComment);
        db.close();

        Toast.makeText(this, "Student Info Updated", Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent(this, ShowAllStudentInfoActivity.class);
        startActivity(intent2);
        finish();
    }

    public void OnClickGoToPreviousActivity(View view) {

        Intent intent = new Intent(this, ShowAllStudentInfoActivity.class);
        startActivity(intent);
        finish();
    }

    public void OnClickGoToHomeActivity(View view) {

        finish();
    }

    public void OnClickCancelUpdate(View view) {

        Intent intent = new Intent(this, ShowAllStudentInfoActivity.class);
        startActivity(intent);
        finish();
    }
}
