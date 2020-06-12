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
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.Switch;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import services.services;

/**
 *
 * @author amalb
 */
public class AddVelo extends BaseForm {
    Form f;
     public AddVelo(Resources res){
        /* f = new Form("Add Velo", BoxLayout.y());
        f.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cente = new Container(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);*/
        super("Add Bike", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        Image img = res.getImage("66.jpg")/*.scaled(1500, 900)*/;
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
         add(LayeredLayout.encloseIn(
                sl
        ));
        
        /*Image iv = null;
        try {
            //setTitle("Add a new Location");
            //setLayout(BoxLayout.y());
            iv = Image.createImage("/23.png").fill(1600, 700);
        } catch (IOException ex) {
            
        }*/
        
         //Label l = new Label("Disponibilité"); 
        TextField tfvelo = new TextField("");
        tfvelo.setUIID("TextFieldBlack");
        addStringValue("Bike Name", tfvelo);
        
        
        
        PickerComponent tDate = PickerComponent.createDate(new Date());
         tDate.setUIID("PickerComponent");
        addStringValue("Service start date", tDate);
        
        
        TextField tType = new TextField("");
        tType.setUIID("TextFieldBlack");
        addStringValue("Type", tType);
        
       
        CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
         addStringValue("Availability", FlowLayout.encloseRightMiddle(cb1));
        
        ComboBox<String> combo = new ComboBox<> ();
        combo.setUIID("ComboBox");
        addStringValue("Area", combo);
        
        
        TextField tfprix= new TextField("");
        tfprix.setUIID("TextFieldBlack");
        addStringValue("Price", tfprix);
        
        
        Button btnValider = new Button("Add Zone");
        btnValider.setUIID("Button");
        addStringValue("",btnValider);
        
        /*Picker tDate = new Picker ();
        tDate.setType(Display.PICKER_TYPE_DATE);*/
        
       //Switch gswitch = new Switch();
        
        
        services serviceTask=new services();
        ArrayList<Zone> list=serviceTask.AfficherZone();
        
         
        
        for (Zone t : list){
        combo.addItem(/*String.valueOf(t.getId())+" "+*/t.getNom());
        }
        
       
        
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfvelo.getText().length()==0)||(tType.getText().length()==0)||(tfprix.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Velo v = new Velo();
                        v.setNom(tfvelo.getText());
                        v.setDebutservice(tDate.getPicker().getDate());
                        v.setType(tType.getText());
                        v.setDisponible(cb1.isFocusable());
                        v.setPrix(Double.parseDouble(tfprix.getText()));
                        v.setZone(serviceTask.getidfromzone(combo.getSelectedItem())/*combo.getSelectedItem().substring(0)*/);
                        //z.setStcok(Integer.parseInt(tfStock.getText()));
                        if( services.getInstance().addVelo(v))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
           
       });
       /* f.add(BorderLayout.NORTH, new Label(iv,""));
        center.add(tfvelo);
        center.add(tDate);
         center.add(tType);
        cente.add(l);
        cente.add(gswitch);
        center.add(cente);
       center.add(combo);
       center.add(tfprix);
       center.add(btnValider);*/
        //center.addAll(tfvelo,tDate,tType,gswitch,combo,tfprix,btnValider);
        
        //f.addComponent(BorderLayout.CENTER, center);
        
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        //getToolbar().addSideMenu();
            }
        public Form gets() {
        return f;
    }
        
         private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
