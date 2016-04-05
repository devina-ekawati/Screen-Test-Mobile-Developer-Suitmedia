package com.mycompany.devinaekawati.suitmediatest;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class GuestActivity extends AppCompatActivity {

    private int resultCode = 2;
    private GuestAdapter adapter;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        setTitle("Guest");

        new HttpAsyncTask().execute("http://dry-sierra-6832.herokuapp.com/api/people");

        ArrayList<Guest> guests = new ArrayList<>();
        adapter = new GuestAdapter(this, guests);

        gridView = (GridView) findViewById(R.id.guestGridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Guest guest = (Guest) gridView.getItemAtPosition(position);
                String guestName = guest.getNama();

                String guestBirthDate = guest.getBirthDate();
                String[] date = guestBirthDate.split("-");

                int birthDate = Integer.parseInt(date[2]);

                if ((birthDate % 2 ==0) && (birthDate % 3 == 0)) {
                    Toast.makeText(GuestActivity.this, "iOS", Toast.LENGTH_LONG).show();
                } else if (birthDate % 2 == 0) {
                    Toast.makeText(GuestActivity.this, "blackberry", Toast.LENGTH_LONG).show();
                } else if (birthDate % 3 == 0) {
                    Toast.makeText(GuestActivity.this, "android", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(GuestActivity.this, "feature phone", Toast.LENGTH_LONG).show();
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("GUEST-NAME", guestName);

                setResult(resultCode, resultIntent);
                finish();
            }
        });
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, Void> {

        String response = "";

        @Override
        protected Void doInBackground(String... location) {
            URL url = null;

            try {
                url = new URL(location[0]);
            } catch (MalformedURLException e) {
                Log.e("ERROR", "Can't connect to the url");
            }

            if (url != null) {
                try {
                    HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));

                    String inputline;

                    while ((inputline = in.readLine()) != null) {
                        response += inputline;
                    }

                    in.close();
                    urlconn.disconnect();
                } catch (IOException e) {
                    Log.e("ERROR", "Can't receive data");
                }
            } else {
                Log.e("ERROR", "Can't connect to the url");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d("response", response);

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String guestName = jsonObject.getString("name");
                    String guestBirthDate = jsonObject.getString("birthdate");

                    Guest guest = new Guest(R.drawable.guest, guestName, guestBirthDate);
                    adapter.add(guest);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            gridView.setAdapter(adapter);
        }

    }
}
