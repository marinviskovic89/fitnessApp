package com.example.fitnessapp;

public class User {
    private String id;
    private String ime;
    private String prezime;
    private double visina;
    private double težina;
    private String ciljevi;
    private String aktivnost;

    public User() {

    }
    public User(String id, String ime, String prezime, double visina, double težina, String ciljevi, String aktivnost) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.visina = visina;
        this.težina = težina;
        this.ciljevi = ciljevi;
        this.aktivnost = aktivnost;
    }

    public String getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public double getVisina() {
        return visina;
    }

    public double getTežina() {
        return težina;
    }

    public String getCiljevi() {
        return ciljevi;
    }

    public String getAktivnost() {
        return aktivnost;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setVisina(double visina) {
        this.visina = visina;
    }

    public void setTežina(double težina) {
        this.težina = težina;
    }

    public void setCiljevi(String ciljevi) {
        this.ciljevi = ciljevi;
    }

    public void setAktivnost(String aktivnost) {
        this.aktivnost = aktivnost;
    }
}
