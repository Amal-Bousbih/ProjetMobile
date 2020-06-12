/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Zone;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.contacts.Contact;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import services.services;

/**
 *
 * @author amalb
 */
public class ListZone extends BaseForm {
    /*Form hi = new Form("List Of Area", BoxLayout.y());
    Style s = UIManager.getInstance().getComponentStyle("TitleCommand");*/
   
    public ListZone(Resources res)
    {
        
         super("Area List", BoxLayout.yCenter());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        
        getTitleArea().setUIID("Container");
        //setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
       super.createLineSeparator();
       Container list1 = new Container (BoxLayout.y());
       list1.setScrollableY(true);
        services serviceTask=new services();
        ArrayList<Zone> list=serviceTask.AfficherZone();
        /*Resources theme = UIManager.initFirstTheme("/theme");
        this.setTitle("Liste des Zones");*/
        
        int fiveMM = Display.getInstance().convertToPixels(5);
 
       Toolbar.setGlobalToolbar(true);
       
       add(new InfiniteProgress());
       Display.getInstance().scheduleBackgroundTask(()-> {
    // this will take a while...
    Contact[] cnts = Display.getInstance().getAllContacts(true, true, true, true, false, false);
    Display.getInstance().callSerially(() -> {
        removeAll();
        for (Zone t : list) {
        //Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));
        MultiButton m = new MultiButton("List ENtity" + list);
            /*SimpleDateFormat Date = new SimpleDateFormat("dd-MM-yyyy");
           
            String dd= Date.format(t.getDate_debut());
            String df= Date.format(t.getDate_fin());*/
            
            /*Label date_debut = new Label("\nDate de debut : " + dd  ); 
            Label date_fin = new Label("\nDate de fin : " + df  ); */
            m.setTextLine1(t.getNom());
            m.setTextLine2(String.valueOf(t.getStcok()).toString());
            //Label nom=new  Label("nom : " + t.getNom()  ); 
            //Label stock=new  Label("\nStock : " + t.getStcok()  ); 
        
            //this.add(new Slider());
            add(m);
        }
        
       revalidate();
        
   });
    
});

super.getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                    line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        getContentPane().animateLayout(150);
    }
}, 4);
//  super.add(CENTER, list1);
//hi.show();
    
        }
    /* public Form getF() {
        return hi;
    }*/
   //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());      

 private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }  
    

   
}
