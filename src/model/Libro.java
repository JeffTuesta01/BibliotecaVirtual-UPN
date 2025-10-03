package model;

public class Libro {
    private int idLibro;
    private String titulo;
    private String autor;
    private boolean disponible;

    public Libro(int idLibro, String titulo, String autor) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return idLibro + " - " + titulo + " (" + autor + ") - " + (disponible ? "Disponible" : "Prestado");
    }
}