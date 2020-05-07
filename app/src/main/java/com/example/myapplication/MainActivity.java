package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Button start;
    ProgressBar prBar;
    TextView info;
    int infoLength =100000;
    private final static String file = "file.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.btn_start);
        start.setEnabled(true);
        prBar  = findViewById(R.id.progressBar2);
        prBar.setVisibility(View.INVISIBLE);
        info = findViewById(R.id.info);
        info.setText("");
    }



    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prBar.setVisibility(View.VISIBLE);
            start.setEnabled(false);
            info.setText("Идет запись");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(file, 0));
                for(int i = 1 ; i <= infoLength; i++) {
                    outputStreamWriter.write((char) i % 100);
                }
                outputStreamWriter.close();

            } catch ( IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            prBar.setVisibility(View.INVISIBLE);
            start.setEnabled(true);
            info.setText("Запись завершена");
        }
    }

    MyTask td;
    public void writeToFile(View view){
        td = new MyTask();
        td.execute();
    }


}
