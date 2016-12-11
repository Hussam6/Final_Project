package com.example.jayeshvaghela.project_vaghela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StudentIDActivity extends AppCompatActivity {

    //Declaring widget reference.
    EditText et_UserEnterStudentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_id);

        //Initializing variables to widgets.
        et_UserEnterStudentID = (EditText) findViewById(R.id.et_UserEnterStudentID);

    }

    //This is checks if the edit text has some value in it or is empty.
    //If it has value it takes the value and passes the value to the next activity.
    //If it is empty prompts with a toast message.
    public void OnClickGoToAddScoreAndCommentActivity(View view) {

        if(et_UserEnterStudentID.getText().toString().length() <=0) {
            Toast.makeText(this, "Please Enter Student ID", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String userInput = et_UserEnterStudentID.getText().toString();
            Intent intent = new Intent(this, AddScoreAndCommentActivity.class);
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra("output", userInput);
            startActivity(intent);

            et_UserEnterStudentID.setText("");

        }
    }

    //This method is called when the user presses the show all button.
    //Taking the user to listview activity and displaying all the data that is their in the database.
    public void OnClickGoToShowAllStudentActivityAndDisplaysAllStudentInfo(View view) {

        Intent intent = new Intent(this, ShowAllStudentInfoActivity.class);
        startActivity(intent);
    }

    //This method takes the user back to the login screen.
    public void OnClickLogoutOfApplication(View view) {
        finish();
    }
}
