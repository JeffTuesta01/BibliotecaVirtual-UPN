package biblioteca;

public class Administrador extends Usuario {

    public Administrador(String nombre, String correo, String contraseña) {
        super(nombre, correo, contraseña);
    }

    public void gestionarCatalogo(Libro libro, String accion) {
        switch (accion.toLowerCase()) {
            case "agregar":
                System.out.println("Libro " + libro.getTitulo() + " agregado al catálogo.");
                break;
            case "editar":
                System.out.println("Libro " + libro.getTitulo() + " editado en el catálogo.");
                break;
            case "eliminar":
                System.out.println("Libro " + libro.getTitulo() + " eliminado del catálogo.");
                break;
            default:
                System.out.println("Acción no reconocida.");
        }
    }
}