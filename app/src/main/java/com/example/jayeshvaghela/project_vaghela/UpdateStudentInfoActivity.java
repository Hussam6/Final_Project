package com.example.jayeshvaghela.project_vaghela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateStudentInfoActivity extends AppCompatActivity {

    //Declaring widget references.
    EditText et_UserUpdateStudentScore;
    EditText et_UserUpdateStudentComment;


    //Declaring variables.
    long userInput;
    String updatedScore;
    String updatedComment;
    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_info);

        //Initializing variables to widgets.
        et_UserUpdateStudentScore = (EditText) findViewById(R.id.et_UserUpdateStudentScore);
        et_UserUpdateStudentComment = (EditText) findViewById(R.id.et_UserUpdateStudentComment);


        db = new DBAdapter(this);
    }

    //When the update button is clicked the editted student score and comment gets changed in the database
    // and displayed in the listview.
    public void OnClickUpdatesStudentScoreAndComment(View view) {

        if (et_UserUpdateStudentScore.getText().length() <=0 && et_UserUpdateStudentComment.getText().length() <=0)
        {
            Toast.makeText(this, "Enter Score And Comment", Toast.LENGTH_SHORT).show();
        }
        else
        {
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


    }

    //This method takes the user back to the previous activity.
    public void OnClickGoToPreviousActivity(View view) {

        Intent intent = new Intent(this, ShowAllStudentInfoActivity.class);
        startActivity(intent);
        finish();
    }

    //This method takes the user back to the home screen.
    public void OnClickGoToHomeActivity(View view) {

        finish();
    }

    //This method cancels the update process.
    public void OnClickCancelUpdate(View view) {

        Intent intent = new Intent(this, ShowAllStudentInfoActivity.class);
        startActivity(intent);
        finish();
    }
}
