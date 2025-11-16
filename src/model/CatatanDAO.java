/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */

import model.Catatan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatatanDAO {
     private Connection conn;

    public CatatanDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Catatan> getAllCatatan() throws SQLException {
        List<Catatan> list = new ArrayList<>();
        String sql = "SELECT * FROM catatan ORDER BY tanggal DESC";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Catatan c = new Catatan(
                    rs.getInt("id"),
                    rs.getString("tanggal"),
                    rs.getString("judul"),
                    rs.getString("isi")
                );
                list.add(c);
            }
        }
        return list;
    }

    public void addCatatan(Catatan catatan) throws SQLException {
        String sql = "INSERT INTO catatan (tanggal, judul, isi) VALUES (?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, catatan.getTanggal());
            pst.setString(2, catatan.getJudul());
            pst.setString(3, catatan.getIsi());
            pst.executeUpdate();
        }
    }

    public void updateCatatan(Catatan catatan) throws SQLException {
        String sql = "UPDATE catatan SET tanggal=?, judul=?, isi=? WHERE id=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, catatan.getTanggal());
            pst.setString(2, catatan.getJudul());
            pst.setString(3, catatan.getIsi());
            pst.setInt(4, catatan.getId());
            pst.executeUpdate();
        }
    }

    public void deleteCatatan(int id) throws SQLException {
        String sql = "DELETE FROM catatan WHERE id=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
    
}
