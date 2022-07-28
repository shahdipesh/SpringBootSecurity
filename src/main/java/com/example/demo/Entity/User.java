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
    private int loginMethod; // 0 - email, 1 - google

    public User(String username, String email, String password, String roles, String permissions,int loginMethod) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.blocked = 0;
        this.roles = roles;
        this.permissions = permissions;
        active=1;
        this.loginMethod=loginMethod;
    }

    public User() {
    }


    //loginMethod 0 - email, 1 - google
    public int getLoginMethod() {
        return loginMethod;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setLoginMethod(int loginMethod) {
        this.loginMethod = loginMethod;
    }

    public int getActive() {
        return active;
    }
}
