package biblioteca;

public class Prestamo {
    private Usuario usuario;
    private Libro libro;
    private boolean activo;

    public Prestamo(Usuario usuario, Libro libro) {
        this.usuario = usuario;
        this.libro = libro;
        this.activo = false;
    }

    // Getters públicos
    public Usuario getUsuario() {
        return usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void solicitarPrestamo() {
        if(libro.getCopias() > 0) {
            activo = true;
            libro.setCopias(libro.getCopias() - 1);
            System.out.println(usuario.getNombre() + " ha solicitado el libro " + libro.getTitulo());
        } else {
            System.out.println("No hay copias disponibles de " + libro.getTitulo());
        }
    }

    public void devolverLibro() {
        if(activo) {
            activo = false;
            libro.setCopias(libro.getCopias() + 1);
            System.out.println(usuario.getNombre() + " ha devuelto el libro " + libro.getTitulo());
        }
    }
}