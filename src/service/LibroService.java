package service;

import model.BaseDatos;
import model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroService {
    private BaseDatos db;

    public LibroService(BaseDatos db) {
        this.db = db;
    }

    public void agregarLibro(Libro l) throws SQLException {
        String sql = "INSERT INTO libro(titulo, autor, disponible) VALUES(?,?,?)";
        PreparedStatement ps = db.getConexion().prepareStatement(sql);
        ps.setString(1, l.getTitulo());
        ps.setString(2, l.getAutor());
        ps.setInt(3, l.isDisponible() ? 1 : 0);
        ps.executeUpdate();
        ps.close();
    }

    public List<Libro> listarLibros() throws SQLException {
        List<Libro> lista = new ArrayList<>();
        Statement stmt = db.getConexion().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM libro");

        while (rs.next()) {
            Libro l = new Libro(
                rs.getInt("idLibro"),
                rs.getString("titulo"),
                rs.getString("autor")
            );
            l.setDisponible(rs.getInt("disponible") == 1);
            lista.add(l);
        }
        rs.close();
        stmt.close();
        return lista;
    }

    public Libro buscarLibro(int id) throws SQLException {
        String sql = "SELECT * FROM libro WHERE idLibro=?";
        PreparedStatement ps = db.getConexion().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Libro l = null;
        if (rs.next()) {
            l = new Libro(
                rs.getInt("idLibro"),
                rs.getString("titulo"),
                rs.getString("autor")
            );
            l.setDisponible(rs.getInt("disponible") == 1);
        }
        rs.close();
        ps.close();
        return l;
    }

    public void actualizarDisponibilidad(Libro l) throws SQLException {
        String sql = "UPDATE libro SET disponible=? WHERE idLibro=?";
        PreparedStatement ps = db.getConexion().prepareStatement(sql);
        ps.setInt(1, l.isDisponible() ? 1 : 0);
        ps.setInt(2, l.getIdLibro());
        ps.executeUpdate();
        ps.close();
    }
}