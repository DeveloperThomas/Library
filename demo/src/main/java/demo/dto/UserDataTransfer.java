package demo.dto;

import demo.model.Renting;
import demo.model.Role;
import demo.model.RoleName;

import java.util.HashSet;
import java.util.Set;

public class UserDataTransfer {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleName> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleName> roles) {
        this.roles = roles;
    }

    private Long id;
    private String username;
    private String password;
    private Set<RoleName> roles;


    public UserDataTransfer(Long id, String username, String password, Set<RoleName> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
