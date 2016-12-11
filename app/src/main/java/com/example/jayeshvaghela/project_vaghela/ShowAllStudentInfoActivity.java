package com.example.jayeshvaghela.project_vaghela;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ShowAllStudentInfoActivity extends AppCompatActivity {

    //Declaring widgets reference.
    ListView lv_DisplaysAllStudentInfo;

    //Declaring references to class.
    DBAdapter db;
    SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_student_info);

        lv_DisplaysAllStudentInfo = (ListView) findViewById(R.id.lv_DisplaysAllStudentInfo);
        db = new DBAdapter(this);
        db.open();

        //Calling the method to populate listview with data from database.
        DisplaysAllStudentInformation();

        lv_DisplaysAllStudentInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                long itemPosition = id;
                Intent intent = new Intent(ShowAllStudentInfoActivity.this, UpdateStudentInfoActivity.class);
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra("output", itemPosition);
                startActivity(intent);

                finish();
            }
        });
    }

    //This method displays all the student information that is stored in the database
    public void DisplaysAllStudentInformation()
    {
        Cursor cursor = db.getAllStudentInfo();
        String[] columns = new String[] {db.KEY_COL1, db.KEY_COL2, db.KEY_COL3};

        int[] to = new int[] {R.id.tv_StudentID, R.id.tv_StudentScore, R.id.tv_StudentComment};

        cursorAdapter = new SimpleCursorAdapter(this, R.layout.item_layout, cursor, columns, to, 0);

        lv_DisplaysAllStudentInfo.setAdapter(cursorAdapter);
    }

    //When the back button is clicked it takes the user back to the previous activity.
    public void OnClickGoToPreviousActivity(View view) {
        finish();
    }

    public void OnClickGoToHomeActivity(View view) {
        finish();
    }
}
