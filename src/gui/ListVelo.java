/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.util.Date;
import Entities.Velo;
import Entities.Zone;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.contacts.Contact;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import services.services;

/**
 *
 * @author amalb
 */
public class ListVelo extends BaseForm {
    
    public ListVelo(Resources res){
        
         super("Area List", BoxLayout.yCenter());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        
        getTitleArea().setUIID("Container");
        //setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        
        
        
         services serviceTask=new services();
        ArrayList<Velo> list=serviceTask.AfficherVelo();
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
        
        
        
        for (Velo v : list) {
            MultiButton m = new MultiButton("List ENtity" + list);
        
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
            SimpleDateFormat Date = new SimpleDateFormat("dd-MM-yyyy");
           
            String dd= Date.format(v.getDebutservice());
            //String df= Date.format(t.getDate_fin());
             
            /*Label date_debut = new Label("\nDate de debut : " + dd  ); 
            Label date_fin = new Label("\nDate de fin : " + df  ); */
           /* Label nom=new  Label("nom : " + v.getNom()  ); 
            Label debutservice=new  Label("\nDebut service : " + dd); 
             Label type =new  Label("\nType : " + v.getType());
            Label Disponible =new  Label("\nDisponibilité : " + v.getDisponible()); 
            System.out.println(v.getZone());
             */
            
            
            
            
            m.setTextLine1(v.getNom());
            m.setTextLine2(dd);
            m.setTextLine3(v.getType());
         // m.setTextLine4(String.valueOf(v.getDisponible().toString()));
         Label Disponible =new  Label("\nDisponibilité : " + v.getDisponible()); 
            Label zone =new  Label("\nZone : " + v.getZone()); 
            Label prix =new  Label("\nPrix : " + v.getPrix());
            //m.setTextLine4(v.getZone());
            //m.setTextLine4(String.valueOf(v.getPrix().toString()));
            
           // m.setTextLine2(String.valueOf(t.getStcok()).toString());
          add(m);
          add(Disponible);
          add(zone);
          add(prix);
        }
        
       revalidate();
        
   });
    
});
            //this.add(new Slider());
            /*C1.addAll(nom,debutservice,type,Disponible,zone,prix);
            this.add(C1);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());*/
           
            
 
 
        }
    
   private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }  
     
    
}
