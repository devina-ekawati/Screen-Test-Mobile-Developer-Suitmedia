package com.mycompany.devinaekawati.suitmediatest;

/**
 * Created by Devina Ekawati on 4/5/2016.
 */
public class Guest {
    private int image;
    private String nama;
    private String birthDate;

    public Guest() {
        image = R.drawable.guest;
        nama = "";
    }

    public Guest(int image, String nama, String birthDate) {
        this.image = image;
        this.nama = nama;
        this.birthDate = birthDate;

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
