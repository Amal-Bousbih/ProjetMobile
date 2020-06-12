/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author amalb
 */
public class HomeAdmin extends Form{
    
    Form current;
    public HomeAdmin() {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddVelo = new Button("Add Velo");
        Button btnListVelo= new Button("List Velo");
        Button btnAddZone = new Button("Add Zone");
        Button btnListZone= new Button("List Zone");
        
        Button btnListLocation= new Button("List Location");
        
        //btnAddVelo.addActionListener(e->new AddVelo(res).show());
        //btnListVelo.addActionListener(e-> new ListVelo(current).show());
        /*btnAddZone.addActionListener(e-> {
            AddZone lgWnd = new AddZone();
        Form loginForm = lgWnd.getm();
        loginForm.show();
        });
       /* btnListZone.addActionListener(e->{
            ListZone lgWnd = new ListZone();
        Form loginForm = lgWnd.getF();
        loginForm.show();
        });*/
         //btnListLocation.addActionListener(e-> new LocationList(current).show());
        
        addAll(btnAddVelo,btnListVelo,btnAddZone,btnListZone,btnListLocation);
        
        
    }
   
    
}
