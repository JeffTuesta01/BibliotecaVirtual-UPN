package service;

import model.BaseDatos;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private BaseDatos db;

    public UsuarioService(BaseDatos db) {
        this.db = db;
    }

    public void agregarUsuario(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuario(nombre, correo, contraseña) VALUES(?,?,?)";
        try (PreparedStatement ps = db.getConexion().prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getContraseña());
            ps.executeUpdate();
        }
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Statement stmt = db.getConexion().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contraseña")
                );
                lista.add(u);
            }
        }
        return lista;
    }

    public Usuario buscarUsuario(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE idUsuario=?";
        Usuario u = null;
        try (PreparedStatement ps = db.getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario(
                            rs.getInt("idUsuario"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("contraseña")
                    );
                }
            }
        }
        return u;
    }
}