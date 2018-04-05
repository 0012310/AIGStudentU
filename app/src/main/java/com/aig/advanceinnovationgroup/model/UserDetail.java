package com.aig.advanceinnovationgroup.model;

import java.io.Serializable;

/**
 * Created by admin on 4/5/2018.
 */

public class UserDetail implements Serializable{
    int id;
    String userName;
    String password;
    String name;
    String role;

    public UserDetail(int id, String userName, String pass, String name, String role) {
        this.id = id;
        this.userName = userName;
        this.password = pass;
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
