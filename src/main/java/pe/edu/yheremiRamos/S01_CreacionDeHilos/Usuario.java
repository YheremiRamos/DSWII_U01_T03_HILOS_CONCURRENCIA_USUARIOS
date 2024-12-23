package pe.edu.yheremiRamos.S01_CreacionDeHilos;

import java.util.List;

public class Usuario {
    private int id;
    private String username;
    private String password;
    private List<Rol> roles;

    public Usuario(int id, String username, String password, List<Rol> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Rol> getRoles() {
        return roles;
    }
}
