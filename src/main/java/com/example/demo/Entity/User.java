package com.example.demo.Entity;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;
    @Column(nullable  = false)
    private String username;
    @Column(nullable  = false)
    private String email;
    @Column(nullable  = false)
    private String password;
    private int blocked;
    @Column(nullable  = false)
    private String roles;
    @Column(nullable  = false)
    private String permissions;
    private int active;

    public User(String username, String email, String password, String roles, String permissions) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.blocked = 0;
        this.roles = roles;
        this.permissions = permissions;
        active=1;
    }

    public User() {
    }



    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public int getBlocked() {
        return blocked;
    }

    public String getRoles() {
        return roles;
    }
    public String getPermissions() {
        return permissions;
    }

    public List<String> getRoleList() {
        if(this.roles.length()>0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList() {
        if(this.roles.length()>0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }



    public int getActive() {
        return active;
    }
}
