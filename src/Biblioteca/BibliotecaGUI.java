package Biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BibliotecaGUI {

    private JFrame frame;
    private ArrayList<String> usuarios = new ArrayList<>();
    private ArrayList<String> libros = new ArrayList<>();
    private ArrayList<String> prestamos = new ArrayList<>();

    public BibliotecaGUI() {
        frame = new JFrame("Biblioteca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(7, 1, 10, 10));

        JButton btnAgregarUsuario = new JButton("Agregar Usuario");
        JButton btnListarUsuarios = new JButton("Listar Usuarios");
        JButton btnAgregarLibro = new JButton("Agregar Libro");
        JButton btnListarLibros = new JButton("Listar Libros");
        JButton btnHacerPrestamo = new JButton("Hacer Prestamo");
        JButton btnListarPrestamos = new JButton("Listar Prestamos");
        JButton btnSalir = new JButton("Salir");

        frame.add(btnAgregarUsuario);
        frame.add(btnListarUsuarios);
        frame.add(btnAgregarLibro);
        frame.add(btnListarLibros);
        frame.add(btnHacerPrestamo);
        frame.add(btnListarPrestamos);
        frame.add(btnSalir);

        // Acciones de los botones
        btnAgregarUsuario.addActionListener(e -> agregarUsuario());
        btnListarUsuarios.addActionListener(e -> listarUsuarios());
        btnAgregarLibro.addActionListener(e -> agregarLibro());
        btnListarLibros.addActionListener(e -> listarLibros());
        btnHacerPrestamo.addActionListener(e -> hacerPrestamo());
        btnListarPrestamos.addActionListener(e -> listarPrestamos());
        btnSalir.addActionListener(e -> System.exit(0));

        frame.setLocationRelativeTo(null); // Centrar ventana
        frame.setVisible(true);
    }

    private void agregarUsuario() {
        String nombre = JOptionPane.showInputDialog(frame, "Ingrese el nombre del usuario:");
        if (nombre != null && !nombre.isEmpty()) {
            usuarios.add(nombre);
            JOptionPane.showMessageDialog(frame, "Usuario agregado correctamente.");
        }
    }

    private void listarUsuarios() {
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No hay usuarios registrados.");
        } else {
            JOptionPane.showMessageDialog(frame, String.join("\n", usuarios), "Usuarios", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void agregarLibro() {
        String titulo = JOptionPane.showInputDialog(frame, "Ingrese el título del libro:");
        if (titulo != null && !titulo.isEmpty()) {
            libros.add(titulo);
            JOptionPane.showMessageDialog(frame, "Libro agregado correctamente.");
        }
    }

    private void listarLibros() {
        if (libros.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No hay libros registrados.");
        } else {
            JOptionPane.showMessageDialog(frame, String.join("\n", libros), "Libros", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void hacerPrestamo() {
        if (usuarios.isEmpty() || libros.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Debe haber al menos un usuario y un libro para hacer un préstamo.");
            return;
        }
        String usuario = (String) JOptionPane.showInputDialog(frame, "Seleccione el usuario:", "Usuarios", JOptionPane.QUESTION_MESSAGE, null, usuarios.toArray(), usuarios.get(0));
        String libro = (String) JOptionPane.showInputDialog(frame, "Seleccione el libro:", "Libros", JOptionPane.QUESTION_MESSAGE, null, libros.toArray(), libros.get(0));
        if (usuario != null && libro != null) {
            prestamos.add(usuario + " prestó " + libro);
            JOptionPane.showMessageDialog(frame, "Préstamo registrado.");
        }
    }

    private void listarPrestamos() {
        if (prestamos.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No hay préstamos registrados.");
        } else {
            JOptionPane.showMessageDialog(frame, String.join("\n", prestamos), "Préstamos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BibliotecaGUI::new);
    }
}