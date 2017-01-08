package com.mycompany.devinaekawati.suitmediatest;

import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Devina Ekawati on 4/5/2016.
 */
public class Event {

    private int image;
    private String nama;
    private String tanggal;
    private ArrayList<String> tags;

    public Event() {
        image = R.drawable.events;
        nama = "";
        tanggal = "";
        tags = new ArrayList<>();
    }

    public Event(int image, String nama, String tanggal, ArrayList<String> tags) {
        this.image = image;
        this.nama = nama;
        this.tanggal = tanggal;
        this.tags = tags;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
