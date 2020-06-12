/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author amalb
 */
public class User {
    int id;
    String Login;
    String pass;
    Boolean remeber;

    public User(int id, String Login, String pass, Boolean remeber) {
        this.id = id;
        this.Login = Login;
        this.pass = pass;
        this.remeber = remeber;
    }

    public User() {
        
    }

    public int getId() {
        return id;
    }
    
    public String getLogin() {
        return Login;
    }

    public String getPass() {
        return pass;
    }

    public Boolean getRemeber() {
        return remeber;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setLogin(String Login) {
        this.Login = Login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRemeber(Boolean remeber) {
        this.remeber = remeber;
    }
    
    
    
}
