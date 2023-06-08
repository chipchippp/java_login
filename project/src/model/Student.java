package model;

import javafx.scene.control.Button;

public final class Student {
    private String user;
    private String email;

    private String pass;

    public Student(String user, String email, String pass) {
        this.user = user;
        this.email = email;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}