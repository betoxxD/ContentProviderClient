package com.example.clientecontentprovider;

public class Usuario {
    private long id;
    private String name;
    private String pass;
    private String email;
    private String tel;

    public Usuario() {}

    public Usuario(long id, String name, String pass, String email, String tel) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.tel = tel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
