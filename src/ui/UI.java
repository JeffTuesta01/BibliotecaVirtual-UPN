package ui;

import model.BaseDatos;
import model.Usuario;
import model.Libro;
import model.Prestamo;
import service.UsuarioService;
import service.LibroService;
import service.PrestamoService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UI {
    private BaseDatos db;
    private UsuarioService usuarioService;
    private LibroService libroService;
    private PrestamoService prestamoService;
    private Scanner sc = new Scanner(System.in);

    public UI() {
        db = new BaseDatos();
        usuarioService = new UsuarioService(db);
        libroService = new LibroService(db);
        prestamoService = new PrestamoService(db);
    }

    public void iniciar() throws SQLException {
        db.conectar();
        db.crearTablas();  // asegurarse de que las tablas existan

        int opcion = 0;
        do {
            System.out.println("\n--- Biblioteca ---");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Agregar Libro");
            System.out.println("4. Listar Libros");
            System.out.println("5. Hacer Prestamo");
            System.out.println("6. Listar Prestamos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch(opcion) {
                case 1: agregarUsuario(); break;
                case 2: listarUsuarios(); break;
                case 3: agregarLibro(); break;
                case 4: listarLibros(); break;
                case 5: hacerPrestamo(); break;
                case 6: listarPrestamos(); break;
            }
        } while(opcion != 0);

        db.cerrar();
    }

    private void agregarUsuario() throws SQLException {
        System.out.print("Nombre: "); String nombre = sc.nextLine();
        System.out.print("Correo: "); String correo = sc.nextLine();
        System.out.print("Contraseña: "); String pass = sc.nextLine();
        usuarioService.agregarUsuario(new Usuario(0, nombre, correo, pass));
        System.out.println("Usuario agregado.");
    }

    private void listarUsuarios() throws SQLException {
        List<Usuario> lista = usuarioService.listarUsuarios();
        lista.forEach(System.out::println);
    }

    private void agregarLibro() throws SQLException {
        System.out.print("Título: "); String titulo = sc.nextLine();
        System.out.print("Autor: "); String autor = sc.nextLine();
        libroService.agregarLibro(new Libro(0, titulo, autor));
        System.out.println("Libro agregado.");
    }

    private void listarLibros() throws SQLException {
        List<Libro> lista = libroService.listarLibros();
        lista.forEach(System.out::println);
    }

    private void hacerPrestamo() throws SQLException {
        System.out.print("ID Usuario: "); int idU = Integer.parseInt(sc.nextLine());
        System.out.print("ID Libro: "); int idL = Integer.parseInt(sc.nextLine());

        Usuario u = usuarioService.buscarUsuario(idU);
        Libro l = libroService.buscarLibro(idL);

        if(u == null || l == null) {
            System.out.println("Usuario o Libro no válido.");
            return;
        }

        if(!l.isDisponible()) {
            System.out.println("Libro no disponible.");
            return;
        }

        Prestamo p = new Prestamo(0, u, l);
        prestamoService.agregarPrestamo(p);
        System.out.println("Préstamo realizado.");
    }

    private void listarPrestamos() throws SQLException {
        List<Prestamo> lista = prestamoService.listarPrestamos();
        lista.forEach(System.out::println);
    }

    public static void main(String[] args) throws SQLException {
        new UI().iniciar();
    }
}