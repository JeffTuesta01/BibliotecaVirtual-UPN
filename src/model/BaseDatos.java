package model;

import java.sql.*;

public class BaseDatos {
    private Connection conexion;

    public void conectar() throws SQLException {
        String url = "jdbc:sqlite:biblioteca.db";
        conexion = DriverManager.getConnection(url);
        System.out.println("Conexión exitosa a SQLite");
    }

    public void cerrar() throws SQLException {
        if (conexion != null) {
            conexion.close();
            System.out.println("Conexión cerrada");
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    // --- NUEVO: crear tablas ---
    public void crearTablas() throws SQLException {
        Statement stmt = conexion.createStatement();

        String tablaUsuarios = "CREATE TABLE IF NOT EXISTS usuario (" +
                "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "correo TEXT NOT NULL," +
                "contraseña TEXT NOT NULL" +
                ");";

        String tablaLibros = "CREATE TABLE IF NOT EXISTS libro (" +
                "idLibro INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "autor TEXT NOT NULL," +
                "disponible INTEGER NOT NULL" +
                ");";

        String tablaPrestamos = "CREATE TABLE IF NOT EXISTS prestamo (" +
                "idPrestamo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idUsuario INTEGER," +
                "idLibro INTEGER," +
                "fechaPrestamo TEXT," +
                "fechaDevolucion TEXT," +
                "FOREIGN KEY(idUsuario) REFERENCES usuario(idUsuario)," +
                "FOREIGN KEY(idLibro) REFERENCES libro(idLibro)" +
                ");";

        stmt.execute(tablaUsuarios);
        stmt.execute(tablaLibros);
        stmt.execute(tablaPrestamos);

        stmt.close();
        System.out.println("Tablas creadas correctamente");
    }
}