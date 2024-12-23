package pe.edu.yheremiRamos.S01_CreacionDeHilos;


import java.util.List;

public class Rol {
    private String nombreRol;
    private List<Permiso> permisos;

    public Rol(String nombreRol, List<Permiso> permisos) {
        this.nombreRol = nombreRol;
        this.permisos = permisos;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }
}

