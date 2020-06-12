/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Location;
import Entities.Velo;
import Entities.Zone;
import com.codename1.components.MultiButton;
import com.codename1.components.Switch;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import services.services;

/**
 *
 * @author amalb
 */
public class AddLocation  {
     Form q;
     public AddLocation(){
         TextModeLayout tl = new TextModeLayout(3, 2);
         q = new Form("Add Location", BoxLayout.y());
        q.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         //setTitle("Add a new Location");
        //setLayout(BoxLayout.y());
         Image iv = null;
        try {
            //setTitle("Add a new Location");
            //setLayout(BoxLayout.y());
            iv = Image.createImage("/33.jpg").scaled(1600, 800);
        } catch (IOException ex) {
            
        }
        
        services serviceTask=new services();
        ArrayList<Zone> list=serviceTask.AfficherZone();
        ComboBox<String> zone = new ComboBox<> ();
         
        
        for (Zone t : list){
        zone.addItem(t.getNom());
        }
        
        
         ComboBox<String> velo = new ComboBox<> ();
       
         zone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  ArrayList<Velo> list1 = serviceTask.getVelos(serviceTask.getidfromNomZone(zone.getSelectedItem()));/*AfficherVelo()*/;
                  
                  for (Velo t : list1){
                  velo.addItem(t.getNom());
        }
                
                
                
            }
           
       });
        PickerComponent tDatedebut = PickerComponent.createDate(new Date()).label("Start Date");
        PickerComponent tDatefin = PickerComponent.createDate(new Date()).label("End Date");
        
        Date d1 = tDatedebut.getPicker().getDate();
                     
        Date d2 = tDatefin.getPicker().getDate();
         /*Picker tDatedebut = new Picker ();
         tDatedebut.setType(Display.PICKER_TYPE_DATE);
         tDatedebut.setDate(new Date());*/
         /*Picker tDatefin = new Picker ();
         tDatefin.setType(Display.PICKER_TYPE_DATE);
         tDatefin.setDate(new Date());*/
       
        Button btnValider = new Button("Add Location");
        
       btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 if ((zone.getSelectedItem().length()==0)||(velo.getSelectedItem().length()==0)){
                     Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                 }
                /* else if(tDatedebut.after(tDatefin)) {
                     Dialog.show("Alert", "End date must be greater than a start date !!", new Command("OK"));
                 }*/
                else
                {
                    try {
                         SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");
                         
                        Location l = new Location();
                        l.setZone(serviceTask.getidfromzone(zone.getSelectedItem()));
                        l.setVelo(serviceTask.getidfromvelo(velo.getSelectedItem()));
                       Date datedebut = tDatedebut.getPicker().getDate();
                        Date datefin = tDatefin.getPicker().getDate();
                        l.setDateDebut(datedebut);
                        l.setDateFin(datefin);
                      
                        if( services.getInstance().addLocation(l))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
           
       });
        q.add(BorderLayout.NORTH, new Label(iv,""));
        center.add(zone);
        center.add(velo);
        center.add(tDatedebut);
        center.add(tDatefin);
        center.add(btnValider);
        
        //center.addAll(zone,velo,tDatedebut,tDatefin,btnValider);
         q.addComponent(BorderLayout.CENTER, center);
            }
     
     public Form getq() {
        return q;
    }

private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}



     
}
