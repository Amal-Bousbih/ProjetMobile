/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author amalb
 */
public class Base extends Form {
    
     public Base() {
    }

    public Base(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public Base(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("36.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl
        ));
        
      //tb.addMaterialCommandToSideMenu(" Areas List ", FontImage.MATERIAL_ADD_LOCATION, e -> new ListZone(res).show());
      // tb.addMaterialCommandToSideMenu("Add New Area ", FontImage.MATERIAL_ADD_LOCATION, e -> new AddZone(res).show());
        //tb.addMaterialCommandToSideMenu("  Bikes List ", FontImage.MATERIAL_DIRECTIONS_BIKE, e -> new AddLocation(res).show());
        //tb.addMaterialCommandToSideMenu(" Add New Bike ", FontImage.MATERIAL_DIRECTIONS_BIKE, e -> new AddVelo(res).show());
        //tb.addMaterialCommandToSideMenu(" Rental List ", FontImage.MATERIAL_INFO, e -> new Vocale(res).show());
    }
}

 
