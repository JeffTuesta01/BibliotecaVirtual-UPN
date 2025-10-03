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
        PreparedStatement ps = db.getConexion().prepareStatement(sql);
        ps.setString(1, u.getNombre());
        ps.setString(2, u.getCorreo());
        ps.setString(3, u.getContraseña());
        ps.executeUpdate();
        ps.close();
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        Statement stmt = db.getConexion().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");

        while (rs.next()) {
            Usuario u = new Usuario(
                rs.getInt("idUsuario"),
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("contraseña")
            );
            lista.add(u);
        }
        rs.close();
        stmt.close();
        return lista;
    }

    public Usuario buscarUsuario(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE idUsuario=?";
        PreparedStatement ps = db.getConexion().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Usuario u = null;
        if (rs.next()) {
            u = new Usuario(
                rs.getInt("idUsuario"),
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("contraseña")
            );
        }
        rs.close();
        ps.close();
        return u;
    }
}