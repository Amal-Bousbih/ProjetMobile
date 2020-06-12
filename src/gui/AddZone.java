/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import Entities.Zone;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import services.services;

/**
 *
 * @author amalb
 */
public class AddZone extends BaseForm{
     //Form f;
       public AddZone(Resources res){
        /* f = new Form("Add Area", BoxLayout.y());
        f.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        //setTitle("Add a new task");
        //setLayout(BoxLayout.y());
         Image iv = null;
        try {
            //setTitle("Add a new Location");
            //setLayout(BoxLayout.y());
            iv = Image.createImage("/96.jpg").fill(1600, 900);
        } catch (IOException ex) {
            
        }*/
        
         super("Add Bike", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        Image img = res.getImage("88.jpg")/*.scaled(1500, 900)*/;
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        
         ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
         add(LayeredLayout.encloseIn(
                sl
        ));
        
        TextField tfName = new TextField("");
        tfName.setUIID("TextFieldBlack");
        addStringValue("Area Name", tfName);
        
        TextField tfStock= new TextField("");
        tfStock.setUIID("TextFieldBlack");
        addStringValue("Stock", tfStock);
        
        Button btnValider = new Button("Add Area");
        btnValider.setUIID("Button");
        addStringValue("",btnValider);
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfStock.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Zone z = new Zone();
                        z.setNom(tfName.getText());
                        z.setStcok(Integer.parseInt(tfStock.getText()));
                        if( services.getInstance().addZone(z))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
           
       });
        /*f.add(BorderLayout.NORTH, new Label(iv,""));
        center.addAll(tfName,tfStock,btnValider);
         f.addComponent(BorderLayout.CENTER, center);*/
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
       }    

     /*public Form getm() {
        return f;
     }*/
       
 private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
