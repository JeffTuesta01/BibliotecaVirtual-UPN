package biblioteca;

import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String email;
    private String password;

    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public void registrarse() {
        System.out.println(nombre + " se ha registrado correctamente.");
    }

    public boolean iniciarSesion(String email, String password) {
        if(this.email.equals(email) && this.password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public void buscarLibro(String titulo) {
        // Simulación de búsqueda (podemos conectar a catálogo real después)
        System.out.println(nombre + " buscó el libro: " + titulo);
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}