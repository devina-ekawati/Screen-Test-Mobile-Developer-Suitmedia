package com.mycompany.devinaekawati.suitmediatest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button nextButton = (Button) findViewById(R.id.nextButton);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNama(v);
            }
        });
    }

    public void sendNama(View view) {
        EditText namaEditText = (EditText) findViewById(R.id.namaEditText);
        String nama = namaEditText.getText().toString();

        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.putExtra("NAMA", nama);
        startActivity(intent);
    }
}
