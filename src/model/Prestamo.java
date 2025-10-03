package model;

import java.time.LocalDate;

public class Prestamo {
    private int idPrestamo;
    private Usuario usuario;
    private Libro libro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo(int idPrestamo, Usuario usuario, Libro libro) {
        this.idPrestamo = idPrestamo;
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void devolver() {
        this.fechaDevolucion = LocalDate.now();
        libro.setDisponible(true);
    }

    @Override
    public String toString() {
        return "Prestamo: " + idPrestamo + " - " + libro.getTitulo() + " por " + usuario.getNombre() +
               " | Fecha préstamo: " + fechaPrestamo +
               " | Fecha devolución: " + (fechaDevolucion != null ? fechaDevolucion : "Pendiente");
    }
}