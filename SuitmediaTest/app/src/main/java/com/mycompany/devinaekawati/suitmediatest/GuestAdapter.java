package com.mycompany.devinaekawati.suitmediatest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Devina Ekawati on 4/5/2016.
 */
public class GuestAdapter extends ArrayAdapter<Guest> {

    public GuestAdapter(Context context, ArrayList<Guest> guests) {
        super(context, 0, guests);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Guest guest = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_guest, parent, false);
        }

        TextView namaEvent = (TextView) convertView.findViewById(R.id.guestTextView);

        namaEvent.setText(guest.getNama());
        namaEvent.setBackgroundResource(R.drawable.guest);

        return convertView;
    }
}
