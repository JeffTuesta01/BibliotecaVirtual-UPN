package biblioteca;

import java.util.ArrayList;

public class Historial {
    private ArrayList<Prestamo> prestamos;

    public Historial() {
        prestamos = new ArrayList<>();
    }

    public void agregarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
        System.out.println("Prestamo agregado al historial de " + prestamo.getUsuario().getNombre());
    }

    public void mostrarHistorial() {
        System.out.println("Historial de préstamos:");
        for (Prestamo p : prestamos) {
            System.out.println("- " + p.getUsuario().getNombre() + " -> " + p.getLibro().getTitulo());
        }
    }
}