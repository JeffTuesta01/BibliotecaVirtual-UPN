package biblioteca;

public class Reserva {
    private Usuario usuario;
    private Libro libro;
    private boolean activa;

    public Reserva(Usuario usuario, Libro libro) {
        this.usuario = usuario;
        this.libro = libro;
        this.activa = false;
    }

    public void reservar() {
        activa = true;
        System.out.println(usuario.getNombre() + " ha reservado el libro " + libro.getTitulo());
    }

    public void cancelarReserva() {
        if(activa) {
            activa = false;
            System.out.println(usuario.getNombre() + " ha cancelado la reserva del libro " + libro.getTitulo());
        }
    }

    // Getters
    public Usuario getUsuario() {
        return usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public boolean isActiva() {
        return activa;
    }
}