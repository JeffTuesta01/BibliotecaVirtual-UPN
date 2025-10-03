package biblioteca;

public class Main {
    public static void main(String[] args) {

        // 1️⃣ Crear usuarios
        Usuario estudiante1 = new Usuario("Juan Perez", "juan@mail.com", "1234");
        Usuario docente1 = new Usuario("Maria Lopez", "maria@mail.com", "abcd");
        Administrador admin = new Administrador("Admin", "admin@mail.com", "admin");

        // 2️⃣ Registro (simulado)
        estudiante1.registrarse();
        docente1.registrarse();

        // 3️⃣ Login
        if(estudiante1.iniciarSesion("juan@mail.com", "1234")) {
            System.out.println(estudiante1.getNombre() + " inició sesión correctamente.");
        }

        if(docente1.iniciarSesion("maria@mail.com", "abcd")) {
            System.out.println(docente1.getNombre() + " inició sesión correctamente.");
        }

        // 4️⃣ Crear libros
        Libro libro1 = new Libro("Java Básico", "Autor A", "Programación", 5);
        Libro libro2 = new Libro("POO Avanzado", "Autor B", "Programación", 3);

        // 5️⃣ Administrador gestiona catálogo
        admin.gestionarCatalogo(libro1, "agregar");
        admin.gestionarCatalogo(libro2, "agregar");

        // 6️⃣ Buscar libros
        estudiante1.buscarLibro("Java Básico");
        docente1.buscarLibro("POO Avanzado");

        // 7️⃣ Préstamos
        Prestamo prestamo1 = new Prestamo(estudiante1, libro1);
        prestamo1.solicitarPrestamo();

        Prestamo prestamo2 = new Prestamo(docente1, libro2);
        prestamo2.solicitarPrestamo();

        // 8️⃣ Devoluciones
        prestamo1.devolverLibro();
        prestamo2.devolverLibro();

        // 9️⃣ Reservas
        Reserva reserva1 = new Reserva(estudiante1, libro2);
        reserva1.reservar();

        // 🔟 Historial
        Historial historial = new Historial();
        historial.agregarPrestamo(prestamo1);
        historial.agregarPrestamo(prestamo2);
        historial.mostrarHistorial();
    }
}