package service;

import model.BaseDatos;
import model.Prestamo;
import model.Usuario;
import model.Libro;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoService {
    private BaseDatos db;
    private UsuarioService usuarioService;
    private LibroService libroService;

    public PrestamoService(BaseDatos db) {
        this.db = db;
        this.usuarioService = new UsuarioService(db);
        this.libroService = new LibroService(db);
    }

    public void agregarPrestamo(Prestamo p) throws SQLException {
        String sql = "INSERT INTO prestamo(idUsuario, idLibro, fechaPrestamo, fechaDevolucion) VALUES(?,?,?,?)";
        try (PreparedStatement ps = db.getConexion().prepareStatement(sql)) {
            ps.setInt(1, p.getUsuario().getIdUsuario());
            ps.setInt(2, p.getLibro().getIdLibro());
            ps.setString(3, p.getFechaPrestamo().toString());
            ps.setString(4, p.getFechaDevolucion() != null ? p.getFechaDevolucion().toString() : null);
            ps.executeUpdate();
        }

        // actualizar disponibilidad del libro
        p.getLibro().setDisponible(false);
        libroService.actualizarDisponibilidad(p.getLibro());
    }

    public List<Prestamo> listarPrestamos() throws SQLException {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamo";
        try (Statement stmt = db.getConexion().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario u = usuarioService.buscarUsuario(rs.getInt("idUsuario"));
                Libro l = libroService.buscarLibro(rs.getInt("idLibro"));
                Prestamo p = new Prestamo(
                        rs.getInt("idPrestamo"),
                        u,
                        l
                );
                String fechaDev = rs.getString("fechaDevolucion");
                if (fechaDev != null) {
                    p.setFechaDevolucion(LocalDate.parse(fechaDev));
                }
                lista.add(p);
            }
        }
        return lista;
    }
}