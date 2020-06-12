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
public class Zone {
    
    int id;
    String nom;
    int stcok;

    public Zone() {
       
    }

    public Zone(int id, String nom, int stcok) {
        this.id = id;
        this.nom = nom;
        this.stcok = stcok;
    }

    public Zone(String nom, int stcok) {
        this.nom = nom;
        this.stcok = stcok;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getStcok() {
        return stcok;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setStcok(int stcok) {
        this.stcok = stcok;
    }

    @Override
    public String toString() {
        return "Zone{" + "id=" + id + ", nom=" + nom + ", stcok=" + stcok + '}';
    }

    
    
}
