/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author User
 */

import model.Catatan;
import model.CatatanDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class CatatanController {
   private CatatanDAO catatanDAO;

    public CatatanController() throws SQLException {
        // Koneksi SQLite
        Connection conn = DriverManager.getConnection("jdbc:sqlite:catatan.db");
        // Jika perlu, buat tabel jika belum ada
        try (var st = conn.createStatement()) {
            String createTable = """
                CREATE TABLE IF NOT EXISTS catatan (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    tanggal TEXT NOT NULL,
                    judul TEXT NOT NULL,
                    isi TEXT NOT NULL
                )
            """;
            st.execute(createTable);
        }
        catatanDAO = new CatatanDAO(conn);
    }

    public List<Catatan> getAllCatatan() throws SQLException {
        return catatanDAO.getAllCatatan();
    }

    public void addCatatan(String tanggal, String judul, String isi) throws SQLException {
        catatanDAO.addCatatan(new Catatan(0, tanggal, judul, isi));
    }

    public void updateCatatan(int id, String tanggal, String judul, String isi) throws SQLException {
        catatanDAO.updateCatatan(new Catatan(id, tanggal, judul, isi));
    }

    public void deleteCatatan(int id) throws SQLException {
        catatanDAO.deleteCatatan(id);
    }
}
