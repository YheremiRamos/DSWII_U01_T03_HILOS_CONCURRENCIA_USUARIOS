package pe.edu.yheremiRamos.S3_ConcurrenciaParalelismo;

import pe.edu.yheremiRamos.S01_CreacionDeHilos.Permiso;
import pe.edu.yheremiRamos.S01_CreacionDeHilos.Rol;
import pe.edu.yheremiRamos.S01_CreacionDeHilos.Usuario;
import pe.edu.yheremiRamos.S02_ManejoDeHilos.AutenticacionService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulacionInicioSesion implements Runnable {

    private Usuario usuario;
    private String username;
    private String password;
    private AutenticacionService autenticacionService;

    public SimulacionInicioSesion(Usuario usuario, String username, String password, AutenticacionService autenticacionService) {
        this.usuario = usuario;
        this.username = username;
        this.password = password;
        this.autenticacionService = autenticacionService;
    }

    @Override
    public void run() {
        System.out.println("Iniciando sesión para el usuario: " + usuario.getId() + " (" + usuario.getUsername() + ")");

        // Simulando el progreso de la autenticación
        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(200); // Simula la espera entre cada progreso
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Usuario " + usuario.getUsername() + " - Progreso: " + (i * 10) + "%");
        }

        // Realizar la autenticación
        boolean autenticado = autenticacionService.autenticarUsuario(username, password);
        if (autenticado) {
            System.out.println("Usuario " + usuario.getId() + " (" + usuario.getUsername() + ") autenticado correctamente.");
        } else {
            System.out.println("Error de autenticación para el usuario " + usuario.getId() + " (" + usuario.getUsername() + ")");
        }
    }

    public static void main(String[] args) {
        // Definición de permisos
        Permiso permiso1 = new Permiso("Leer datos");
        Permiso permiso2 = new Permiso("Escribir datos");
        Permiso permiso3 = new Permiso("Eliminar datos");

        // Definición de roles
        Rol rolAdmin = new Rol("Administrador", List.of(permiso1, permiso2, permiso3));
        Rol rolUsuario = new Rol("Usuario", List.of(permiso1));

        // Crear 100 usuarios
        List<Usuario> usuarios = new java.util.ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            String username = "user" + i;
            String password = "password" + i;
            Rol rol = (i % 2 == 0) ? rolUsuario : rolAdmin; // Alterna entre admin y usuario
            usuarios.add(new Usuario(i, username, password, List.of(rol)));
        }

        // Servicio de autenticación
        AutenticacionService autenticacionService = new AutenticacionService(usuarios);

        // Crear el pool de hilos con más hilos disponibles (por ejemplo, 100)
        ExecutorService executorService = Executors.newFixedThreadPool(50);

        // Ejecutar 100 tareas (simulando 100 inicios de sesión)
        for (Usuario usuario : usuarios) {
            Runnable tarea = new SimulacionInicioSesion(usuario, usuario.getUsername(), usuario.getPassword(), autenticacionService);
            executorService.execute(tarea);
        }

        // Cerrar el pool de hilos
        executorService.shutdown();
    }
}
