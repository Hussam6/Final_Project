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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //Declaring widget references.
    EditText et_UserEnterUserName;
    EditText et_UserEnterPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_UserEnterUserName = (EditText) findViewById(R.id.et_UserEnterUserName);
        et_UserEnterPassword = (EditText) findViewById(R.id.et_UserEnterPassword);
    }

    public void OnClickAccessUserToIntoApplication(View view) {

            Intent intent = new Intent(this, StudentIDActivity.class);
            startActivity(intent);
    }


    private String DownloadData(String u) {

        InputStream is = null;
        String result = "";
        try {
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            is = conn.getInputStream();
            result = processResult(is);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String processResult(InputStream is) throws Exception {

        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {

            Log.d("response ", line);
            sb = sb.append(line);
        }
        String res = sb.toString();
        return res;
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String u = params[0];
            return DownloadData(u);
        }

        @Override
        protected void onPostExecute(String s) {

        }

    }
}
