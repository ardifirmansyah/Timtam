/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private String server = "jdbc:mysql://localhost:3306/purapuradienkripsi";
    private String dbuser = "root";
    private String dbpasswd = "";
    private Statement statement = null;
    private Connection connection = null;

    public void connect() {
        try {
            connection = DriverManager.getConnection(server, dbuser, dbpasswd);
            statement = connection.createStatement();
        } catch (Exception e) {
            throw new IllegalArgumentException("Terjadi kesalahan saat koneksi database");
        }
    }

    public void savePeserta(Peserta m) {
        try {
            String query = "insert into peserta (username, nama) values "
                    + "('" + m.getUsername() + "', "
                    + "'" + m.getNama() + "')";
            statement.execute(query);
        } catch (Exception e) {
            System.out.println(e);
            throw new IllegalArgumentException("Terjadi kesalahan saat save peserta");
        }
    }

    public void saveTim(Tim t) {
        try {
            String query = "insert into tim(judul) values "
                    + "('" + t.getJudul() + "')";
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                t.setId(generatedId);
            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Terjadi kesalahan saat save tim");
        }
    }

    public void updateTim(Tim t, Peserta p) {
        try {
            String query;
            if (t != null) {
                query = "update peserta set id=" + t.getId()
                        + " where username='" + p.getUsername() + "'";
            } else {
                query = "update peserta set id=null"
                        + " where username='" + p.getUsername() + "'";
            }
            statement.executeUpdate(query);
        } catch (Exception e) {
            throw new IllegalArgumentException("Terjadi kesalahan saat update tim");
        }
    }

    public ArrayList<Peserta> loadPeserta() {
        try {
            ArrayList<Peserta> daftarPeserta = new ArrayList<>();
            String query = "select * from peserta";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Peserta p = new Peserta(rs.getString(1), rs.getString(2));
                daftarPeserta.add(p);
            }
            return daftarPeserta;
        } catch (Exception e) {
            throw new IllegalArgumentException("Terjadi kesalahan saat load peserta");
        }
    }

    public ArrayList<Tim> loadTim() {
        try {
            ArrayList<Tim> daftarTim = new ArrayList<>();
            String query = "select * from tim";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Tim t = new Tim(rs.getInt(1), rs.getString(2));
                Statement statement2 = connection.createStatement();
                String query2 = "select * from peserta where id=" + t.getId();
                ResultSet rs2= statement2.executeQuery(query2);
                while (rs2.next()) {
                    Peserta p = new Peserta(rs2.getString(1), rs2.getString(2));
                    t.addAnggota(p);
                }
                daftarTim.add(t);
            }
            return daftarTim;
        } catch (Exception e) {
            throw new IllegalArgumentException("Terjadi kesalahan saat load tim");
        }
    }

}
