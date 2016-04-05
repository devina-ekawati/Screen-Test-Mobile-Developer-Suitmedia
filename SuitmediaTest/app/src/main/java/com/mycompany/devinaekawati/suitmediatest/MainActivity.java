package com.mycompany.devinaekawati.suitmediatest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private int eventActvityCode = 1;
    private Button eventButton;


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

        eventButton = (Button) findViewById(R.id.eventButton);

        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventActivity.class);
                startActivityForResult(intent, eventActvityCode);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == eventActvityCode) {
            if (data != null) {
                String eventName = data.getStringExtra("EVENT-NAME");
                eventButton.setText(eventName);
            }
        }
    }
}
