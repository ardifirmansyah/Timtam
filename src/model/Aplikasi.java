/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

public class Aplikasi {

    ArrayList<Peserta> daftarPeserta;
    ArrayList<Tim> daftarTim;
    Database db;

    public Aplikasi() {
        db = new Database();
        db.connect();
        daftarPeserta = db.loadPeserta();
        daftarTim = db.loadTim();
    }

    public void addPeserta(String username, String nama) {
        if (getPeserta(username) != null) {
            throw new IllegalArgumentException("Username telah dipakai");
        }
        Peserta m = new Peserta(username, nama);
        daftarPeserta.add(m);
        db.savePeserta(m);
    }

    public void addTim(String judul) {
        Tim u = new Tim(judul);
        daftarTim.add(u);
        db.saveTim(u);
    }

    public Peserta getPeserta(String username) {
        for (Peserta p : daftarPeserta) {
            if (p.getUsername().equals(username)) {
                System.out.println(p.toString());
                return p;
            }
        }
        return null;
    }

    public Tim getTim(int id) {
        for (Tim t : daftarTim) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public void addAnggotaTim(String username, int id) {
        Peserta p = getPeserta(username);
        if (p == null) {
            throw new IllegalArgumentException("Peserta tidak ditemukan");
        }
        for (Tim t : daftarTim) {
            if (t.getAnggota(username) != null) {
                throw new IllegalArgumentException("Peserta sudah menjadi anggota suatu tim");
            }
        }
        Tim t = getTim(id);
        if (t == null) {
            throw new IllegalArgumentException("ID tim tidak ditemukan");
        }
        t.addAnggota(p);
        db.updateTim(t, p);
    }

    public void removeAnggotaTim(String username, int id) {

    }

    public String viewPeserta() {
        String s = "";
        for (Peserta m : daftarPeserta) {
            s += m.getUsername() + "\n";
        }
        return s;
    }

    public String viewTim() {
        String s = "";
        for (Tim t : daftarTim) {
            s += t.getId() + "\n";
        }
        return s;
    }

}
