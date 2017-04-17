/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Peserta {

    private String username;
    private String nama;

    public Peserta(String username, String nama) {
        this.username = username;
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return "username\t: " + username + "\n"
                + "nama\t:" + nama + "\n";
    }

}
