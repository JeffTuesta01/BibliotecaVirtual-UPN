package biblioteca;

public class Libro {
    private String titulo;
    private String autor;
    private String categoria;
    private int copias;

    public Libro(String titulo, String autor, String categoria, int copias) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.copias = copias;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }
}