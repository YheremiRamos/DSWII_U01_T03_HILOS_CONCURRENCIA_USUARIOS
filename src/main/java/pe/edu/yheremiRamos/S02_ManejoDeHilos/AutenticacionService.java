package pe.edu.yheremiRamos.S02_ManejoDeHilos;

import pe.edu.yheremiRamos.S01_CreacionDeHilos.Usuario;

import java.util.List;

public class AutenticacionService {

    private List<Usuario> usuariosRegistrados;

    // Constructor para inicializar la lista de usuarios registrados
    public AutenticacionService(List<Usuario> usuariosRegistrados) {
        this.usuariosRegistrados = usuariosRegistrados;
    }

    // Método original modificado para ser más genérico
    public boolean autenticar(Usuario usuario, String username, String password) {
        System.out.println("Autenticando usuario: " + username);
        try {
            for (int i = 0; i <= 100; i += 10) {
                Thread.sleep(200);
                System.out.println("Usuario " + username + " - Progreso: " + i + "%");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
            System.out.println("Usuario " + username + " autenticado con éxito.");
            return true;
        } else {
            System.out.println("Autenticación fallida para el usuario: " + username);
            return false;
        }
    }

    // Nuevo método que autentica directamente por username y password
    public boolean autenticarUsuario(String username, String password) {
        // Buscar usuario en la lista por username
        Usuario usuario = usuariosRegistrados.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            System.out.println("Autenticación fallida: Usuario " + username + " no encontrado.");
            return false;
        }

        // Llama al método original para validar credenciales
        return autenticar(usuario, username, password);
    }
}
