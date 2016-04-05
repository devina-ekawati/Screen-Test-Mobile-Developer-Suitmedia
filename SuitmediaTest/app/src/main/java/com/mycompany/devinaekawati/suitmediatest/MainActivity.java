package com.mycompany.devinaekawati.suitmediatest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TextView namaTextView = (TextView) findViewById(R.id.namaTextView);
            String nama = extras.getString("NAMA");

            namaTextView.setText(nama);
        }


    }
}
