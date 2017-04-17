/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

public class Tim {

    private int id;
    private String judul;
    private ArrayList<Peserta> anggota;

    public Tim(String judul) {
        this.judul = judul;
        anggota = new ArrayList();
    }

    public Tim(int id, String judul) {
        this.id = id;
        this.judul = judul;
        anggota = new ArrayList();
    }

    public String getJudul() {
        return judul;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addAnggota(Peserta p) {
        anggota.add(p);
    }

    public Peserta getAnggota(String username) {
        for (Peserta p : anggota) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }

    public void removeAnggota(String username) {
        Peserta p = getAnggota(username);
        anggota.remove(p);
    }

    @Override
    public String toString() {
        String s = "Tim " + id + " : \n"
                + "judul\t: " + judul + " \n"
                + "daftar anggota:\n";
        for (Peserta m : anggota) {
            s += m.getUsername() + "\n";
        }
        return s;
    }

}
