/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;



/**
 *
 * @author amalb
 */
public class Location {
    
    int ID;
    String  zone;
    String velo;
    Date dateDebut;
    Date dateFin;

    public Location() {
    }

    public Location(int ID, String zone, String velo, Date dateDebut, Date dateFin) {
        this.ID = ID;
        this.zone = zone;
        this.velo = velo;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getID() {
        return ID;
    }

    public String getZone() {
        return zone;
    }

    public String getVelo() {
        return velo;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setVelo(String velo) {
        this.velo = velo;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Location{" + "ID=" + ID + ", zone=" + zone + ", velo=" + velo + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }

   
    
    
}
