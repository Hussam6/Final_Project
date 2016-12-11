package com.example.jayeshvaghela.project_vaghela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class AddScoreAndCommentActivity extends AppCompatActivity {

    //Declaring widget references.
    EditText et_UserEnterStudentComment;
    SeekBar sb_UserSelectStudentScore;
    TextView tv_SeekBarValue;

    //Declaring variables.
    int score;
    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score_and_comment);

        //Initializing variables to widgets.
        et_UserEnterStudentComment = (EditText) findViewById(R.id.et_UserEnterStudentComment);
        sb_UserSelectStudentScore = (SeekBar) findViewById(R.id.sb_UserSelectStudentScore);
        tv_SeekBarValue = (TextView) findViewById(R.id.tv_SeekBarValue);

        db = new DBAdapter(this);

        //Seek bar listener is used to getting the score value from the user and also display the value in a textview.
        sb_UserSelectStudentScore.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                score = progress;
                tv_SeekBarValue.setText(String.valueOf(progress)+"/10");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                //remain empty
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                //remain empty
            }
        });
    }

    // The method adds the user entered student IDs, score, comment into the database when the user presses the submit button.
    public void OnClickSubmitUserEnteredDataIntoDatabase(View view) {

        //Opening the database.
        db.open();
        //Getting user input data from the previous activity.
        Intent intent = getIntent();
        String studentID = intent.getExtras().getString("output");
        String studentComment = et_UserEnterStudentComment.getText().toString();
        String studentScore = String.valueOf(score);
        db.insertStudentInfo(studentID, studentScore, studentComment);
        Toast.makeText(this, "done" , Toast.LENGTH_LONG).show();

        //Setting the comment edit text to empty string and seek bar to zero.
        et_UserEnterStudentComment.setText("");
        sb_UserSelectStudentScore.setProgress(0);
        db.close();
    }

    //This method takes the user back to the previous activity.
    public void OnClickGoToPreviousActivity(View view) {

        finish();
    }

    //This method takes the user back to the home screen.
    public void OnClickGoToHomeActivity(View view) {

        finish();
    }
}
