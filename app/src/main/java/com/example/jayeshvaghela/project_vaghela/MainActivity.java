package com.example.jayeshvaghela.project_vaghela;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //Declaring widget references.
    EditText et_UserEnterUserName;
    EditText et_UserEnterPassword;
    String serverURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing variables to widgets.
        et_UserEnterUserName = (EditText) findViewById(R.id.et_UserEnterUserName);
        et_UserEnterPassword = (EditText) findViewById(R.id.et_UserEnterPassword);

    }

    //This method is invoked when the user presses the login button
    //Which checks if the credential are correct in the background.
    public void OnClickAccessUserToIntoApplication(View view) {

        String username = et_UserEnterUserName.getText().toString();
        String password = et_UserEnterPassword.getText().toString();

        //Checking if the edit texts are not empty. If it is, gives the user a Toast Message.
        if(et_UserEnterUserName.getText().toString().length() <=0 && et_UserEnterPassword.getText().length() <=0)
        {
            Toast.makeText(this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
        }
        else
        {

            serverURL = "http://mohameom.dev.fast.sheridanc.on.ca/users/verifyUserData.php?user="+username+"&password"+password+"=12345";
            new DownloadTask().execute();
        }

        //Setting edit text to empty string.
        et_UserEnterUserName.setText("");
        et_UserEnterPassword.setText("");

    }


    //This class does background tasks for the HTTP connection.
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {


            try {
                URL url = new URL(serverURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String value = reader.readLine();

                System.out.println(value);

            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            //Takes the user to next activity once the credentials are correct.
            Intent intent = new Intent(MainActivity.this, StudentIDActivity.class);
            startActivity(intent);
        }

    }
}
