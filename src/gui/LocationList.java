/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Location;
import Entities.Velo;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.contacts.Contact;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import services.services;

/**
 *
 * @author amalb
 */
public class LocationList extends BaseForm{
    
    public LocationList(Resources res){
         super("Area List", BoxLayout.yCenter());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        
        getTitleArea().setUIID("Container");
        //setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
         services serviceTask=new services();
        ArrayList<Location> list=serviceTask.AfficherLocation();
        /*Resources theme = UIManager.initFirstTheme("/theme");
        this.setTitle("Liste des Location");*/
        int fiveMM = Display.getInstance().convertToPixels(5);
 
       Toolbar.setGlobalToolbar(true);
       
       add(new InfiniteProgress());
       Display.getInstance().scheduleBackgroundTask(()-> {
    // this will take a while...
    Contact[] cnts = Display.getInstance().getAllContacts(true, true, true, true, false, false);
    Display.getInstance().callSerially(() -> {
        removeAll();
        for (Location l : list) {
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        MultiButton m = new MultiButton("List ENtity" + list);
            SimpleDateFormat Date = new SimpleDateFormat("dd-MM-yyyy");
           
            String dd= Date.format(l.getDateDebut());
            String df= Date.format(l.getDateFin());
             
            /*Label date_debut = new Label("\nDate de debut : " + dd  ); 
            Label date_fin = new Label("\nDate de fin : " + df  ); */
            m.setTextLine1(l.getZone());
            m.setTextLine2(l.getVelo());
            m.setTextLine3(dd);
            m.setTextLine4(df);
           /* Label zone=new  Label("Zone : " + l.getZone() );
             Label velo =new  Label("\nVelo : " + l.getVelo());
            Label datedebut=new  Label("\nDate Debut : " + dd); 
            Label datefin=new  Label("\nDDate Fin : " + df); */
             
         add(m);
            //this.add(new Slider());
           /* C1.addAll(zone,velo,datedebut,datefin);
            this.add(C1);
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());*/
            
        }
        revalidate();
   });
    
});
    }
    
}
