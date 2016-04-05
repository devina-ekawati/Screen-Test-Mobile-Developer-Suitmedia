package com.mycompany.devinaekawati.suitmediatest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Devina Ekawati on 4/5/2016.
 */
public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Event event = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_event, parent, false);
        }

        ImageView imageEvent = (ImageView) convertView.findViewById(R.id.eventImageView);
        TextView namaEvent = (TextView) convertView.findViewById(R.id.namaEventTextView);
        TextView tanggalEvent = (TextView) convertView.findViewById(R.id.tanggalEventTextView);

        imageEvent.setImageResource(event.getImage());
        namaEvent.setText(event.getNama());
        tanggalEvent.setText(event.getTanggal());

        return convertView;
    }
}
