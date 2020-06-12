/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.User;
import Entities.Velo;
import Entities.Zone;
import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.FocusListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import services.services;

/**
 *
 * @author amalb
 */
public class Authentification extends BaseForm{
   
    public Boolean Loginexiste(ArrayList<User> list, String login)
    {
        for(User user: list)
        {
         if(login.toUpperCase().equals(user.getLogin().toUpperCase()))
         {
             return true ;
         }
        }
        return false; 
    }
    
    public Boolean passwordexiste(ArrayList<User> list, String login, String password)
    {
      for(User user: list)
        {
         if(login.toUpperCase().equals(user.getLogin().toUpperCase()) && (user.getPass().toUpperCase().equals(password.toUpperCase())))
         {
             return true ;
         }
        }
        return false;   
    }
    public Authentification(Resources res) {
        
        
       super(new BorderLayout());
         services s = new services();
         
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        add(BorderLayout.NORTH, new Label(res.getImage("velo.png").scaled(450, 350), "LogoLabel"));
        
        TextField login = new TextField("", "Username", 20, TextField.ANY);
        TextField pass = new TextField("", "Password", 20, TextField.PASSWORD);
         pass.setHint ("Password");
        login.setSingleLineTextArea(false);
        pass.setSingleLineTextArea(false);
         CheckBox cb2 = new CheckBox("Remeber me");
        Button btnValider = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        //signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
        
       
       
          btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                try
                {
                  
                    if(!s.CheckValidationLogin(login.getText()))
                    {
                         Dialog.show("Failed","Login does not exist",new Command("OK"));
                    }
                    else
                    {
                        if(!s.g(login.getText(),pass.getText())/*s.CheckValidationPassword(login.getText(),pass.getText())*/)
                        {
                            Dialog.show("Failed","Wrong password",new Command("OK"));
                            //System.out.println(s.g(login.getText(),pass.getText()));
                        }
                        else
                        {
                            if (cb2.isSelected()) 
                            {
                                 User u = new User();
                                 u.setId(s.getiduser(login.getText()));
                                 u.setLogin(login.getText());
                                 u.setPass(pass.getText());
                                 if(s.getRemember(login.getText()))
                                 {
                                     Dialog.show("","User is already Saved !\n Connexion Approved",new Command("OK"));
                                 }
                                 else
                                 {
                                    s.updateUser(u);
                                    Dialog.show("Success","User Has been Saved\n Connexion Approved",new Command("OK"));
                                    
                                 }
                            }
                            else
                            {
                                Dialog.show("Success","Connexion Approved",new Command("OK"));
                                
                                
                            }
                        }
                      
                    }
             
                }
                catch(Exception e)
                {
                    System.out.println(e.toString());
                }
           
               
            }
            
           
       });
     
    pass.addFocusListener(new FocusListener(){
        
             @Override
             public void focusGained(Component cmp) {
                  //Dialog.show("Success","aaaaaaaaaaaaaaaaa",new Command("OK"));
                  
                  if(!s.getPassword(login.getText()).equals(""))
                  {
                      pass.setText(s.getPassword(login.getText()));
                      pass.setEditable(false);
                  }
                  
             }

             @Override
             public void focusLost(Component cmp) {
                  //Dialog.show("Success","bbbbbbbbbbbbbbbb",new Command("OK"));
             }
    });
    
    login.addFocusListener(new FocusListener(){
        
             @Override
             public void focusGained(Component cmp) {
                  //Dialog.show("Success","aaaaaaaaaaaaaaaaa",new Command("OK"));
                  pass.setText("");
                  pass.setEditable(true);
                  cb2.setSelected(false);
             }

             @Override
             public void focusLost(Component cmp) {
                  //Dialog.show("Success","bbbbbbbbbbbbbbbb",new Command("OK"));
             }
    });
    
     
       Container content;
        content = BoxLayout.encloseYCenter(
                
                new FloatingHint(login),
                createLineSeparator(),
                new FloatingHint(pass),
                createLineSeparator(),
                cb2,
                btnValider
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        btnValider.requestFocus();       
    }
    
    
    
}
