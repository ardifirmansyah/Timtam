/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.*;

public class Handler implements ActionListener{

    private Aplikasi model;
    private View view;

    public Handler() {
        model = new Aplikasi();
        view = new View();
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.addActionListener(this);
        view.setDaftarTim(model.viewTim());
        view.setDaftarPeserta(model.viewPeserta());
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        try {
            if (source.equals(view.getBtnTambahPeserta())) {
                String username = view.getUsername();
                String nama = view.getNama();
                model.addPeserta(username, nama);
                view.reset();
            } else if (source.equals(view.getBtnTambahTim())) {
                String judul = view.getJudul();
                model.addTim(judul);
                view.reset();
            } else if (source.equals(view.getBtnCariPeserta())) {
                String username = view.getUsername();
                Peserta p = model.getPeserta(username);
                view.reset();
                if (p == null) {
                    throw new IllegalArgumentException("Peserta tidak ditemukan");
                }
                view.setDetilPeserta(p.toString());
            } else if (source.equals(view.getBtnCariTim())) {
                int id = view.getIdCari();
                Tim t = model.getTim(id);
                view.reset();
                if (t == null) {
                    throw new IllegalArgumentException("ID tim tidak ditemukan");
                }
                view.setDetilTim(t.toString());
            } else {
                String username = view.getUsernameDaftar();
                int id = view.getIdDaftar();
                if (source.equals(view.getBtnMasuk())) {
                    model.addAnggotaTim(username, id);
                } else {
                    model.removeAnggotaTim(username, id);
                }
                view.reset();
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan input");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
            view.reset();
        }
        view.setDaftarPeserta(model.viewPeserta());
        view.setDaftarTim(model.viewTim());
    }
}
