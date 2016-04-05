package com.mycompany.devinaekawati.suitmediatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    private int resultCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        setTitle("Event");

        ArrayList<Event> events = new ArrayList<>();
        final EventAdapter adapter = new EventAdapter(this, events);

        // Inisialisasi event dengan data dummy
        Event event1 = new Event(R.drawable.events, "Android training", "5 April 2015");
        adapter.add(event1);

        Event event2 = new Event(R.drawable.events, "Unity training", "6 April 2015");
        adapter.add(event2);

        Event event3 = new Event(R.drawable.events, "Web development sharing session", "10 April 2015");
        adapter.add(event3);

        Event event4 = new Event(R.drawable.events, "Docker training", "11 April 2015");
        adapter.add(event4);

        Event event5 = new Event(R.drawable.events, "Project management training", "15 April 2015");
        adapter.add(event5);

        final ListView listView = (ListView) findViewById(R.id.eventsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = (Event) listView.getItemAtPosition(position);
                String eventName = event.getNama();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("EVENT-NAME", eventName);
                setResult(resultCode, resultIntent);
                finish();
            }
        });
    }
}
