/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Location;
import Entities.User;
import Entities.Velo;
import Entities.Zone;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.statics;
import utils.BCrypt;
/**
 *
 * @author amalb
 */
public class services {
    
       public ArrayList<Zone> tasks;
    
    public static services instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public services() {
         req = new ConnectionRequest();
    }

    public static services getInstance() {
        if (instance == null) {
            instance = new services();
        }
        return instance;
    }

    public boolean addTask(Zone z) {
        String url = statics.BASE_URL + "/tasks/" + z.getNom() + "/" + z.getStcok();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Zone> AfficherZone(){
          ArrayList<Zone> listZone = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/zone/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Zone z = new Zone();
                        z.setNom(obj.get("nom").toString());
                        z.setStcok((int) Float.parseFloat(obj.get("stock").toString()));   
                        z.setId((int) Float.parseFloat(obj.get("id").toString()));
                        //float id = Float.parseFloat(obj.get("id").toString()); 
                       // Map<String, Object> listRecupevent = null;
                      //  listRecupevent = (Map<String, Object>) obj.get("publication");
                        //Map<String, Object> listRecupeventuser = null;
                       /* listRecupeventuser = (Map<String, Object>) obj.get("user");
                        
                        String S=(String) listRecupeventuser.get("username");
                        task.setUsername(S);*/
                        
                        listZone.add(z);
                    }
                     
                    
                    System.out.println(tasks);
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listZone;
    }
    
    /**
     *
     * @param z
     * @return
     */
    public boolean addZone(Zone z) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/zones/new?nom=" +z.getNom()+  "&stock=" +z.getStcok());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
            }
        
        });
       NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
   }
    
        public ArrayList<Velo> AfficherVelo(){
          ArrayList<Velo> listVelo = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/velo/ListVelo");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Velo v = new Velo();
                        v.setNom(obj.get("nom").toString());
                        v.setType(obj.get("type").toString());
                        boolean disponible = Boolean.parseBoolean(obj.get("disponible").toString());
                        v.setDisponible(disponible);
                        //float zone = Float.parseFloat(obj.get("zone").toString()); 
                        Map<String, Object> listRecupZone = null;
                         
                       listRecupZone = (Map<String, Object>) obj.get("zone");
                       String S=(String) listRecupZone.get("nom");
                        System.out.println(listRecupZone.get("nom"));
                        Double prix = Double.parseDouble(obj.get("prix").toString()); 
                        v.setPrix(prix);

                       // Map<String, Object> listRecupevent = null;
                      //  listRecupevent = (Map<String, Object>) obj.get("publication");
                        //Map<String, Object> listRecupeventuser = null;
                       /* listRecupeventuser = (Map<String, Object>) obj.get("user");
                        
                        String S=(String) listRecupeventuser.get("username");
                        task.setUsername(S);*/
                        v.setZone(S);
                        listVelo.add(v);
                    }
                    
                    
                    System.out.println(tasks);
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listVelo;
    }
        
  /**
     *
     * @param v
     * @return
     */       
public boolean addVelo(Velo v) {
    ConnectionRequest con = new ConnectionRequest();
    
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/velos/AjoutVelo?nom="+v.getNom()+"&debutservice="+v.getDebutservice()+"&type="+v.getType()+"&disponible="+v.getDisponible()+"&prix="+v.getPrix()+"&zone="+v.getZone()+"");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
                
            }
        
        });
       NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
   
   }
            
            
             public ArrayList<Location> AfficherLocation(){
          ArrayList<Location> listLocation = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/location/AfficherLocation");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    
                    for (Map<String, Object> obj : list) {
                        
                        Location l = new Location();
                        
                        /*String zone = obj.get("zone").toString(); 
                        String velo = obj.get("velo").toString(); 
                        l.setZone(zone);
                        l.setVelo(velo);*/
                       
                        Map<String, Object> listRecupZone = null;
                         
                       listRecupZone = (Map<String, Object>) obj.get("zone");
                       
                        Map<String, Object> listRecupeventuser = null;
                        
                       listRecupeventuser = (Map<String, Object>) obj.get("velo");
                       
                        String S=(String) listRecupZone.get("nom");
                        
                            System.out.println(listRecupZone.get("nom"));
                    
                        String C=(String) listRecupeventuser.get("nom");
                        System.out.println(listRecupeventuser.get("nom"));
                        l.setZone(S);
                        l.setVelo(C);
                        
                        listLocation.add(l);
                    }
                    
                    
                    //System.out.println(tasks);
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listLocation;
    }
             
/**
 *
 * @param l
 * @return
 */       
public boolean addLocation(Location l) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/location/AddLocation?zone=" +l.getZone()+ "&velo=" +l.getVelo()+ "&Date Debut=" +l.getDateDebut()+ "&dDate Fin=" +l.getDateFin());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
             public void actionPerformed(NetworkEvent evt) {
                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
            }
        
        });
       NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
   }


        public ArrayList<User> AfficherUser(){
          ArrayList<User> listUser = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/user");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        User u = new User();
                        u.setLogin(obj.get("username").toString());
                        u.setPass(obj.get("password").toString());
                       
                        boolean remember = Boolean.parseBoolean(obj.get("remember").toString());
                        u.setRemeber(remember);
                        
                        //float zone = Float.parseFloat(obj.get("zone").toString()); 
                        /*Map<String, Object> listRecupZone = null;
                         
                       listRecupZone = (Map<String, Object>) obj.get("zone");
                       String S=(String) listRecupZone.get("nom");
                        System.out.println(listRecupZone.get("nom"));
                        Double prix = Double.parseDouble(obj.get("prix").toString()); 
                        v.setPrix(prix);*/

                       // Map<String, Object> listRecupevent = null;
                      //  listRecupevent = (Map<String, Object>) obj.get("publication");
                        //Map<String, Object> listRecupeventuser = null;
                       /* listRecupeventuser = (Map<String, Object>) obj.get("user");
                        
                        String S=(String) listRecupeventuser.get("username");
                        task.setUsername(S);*/
                        //v.setZone(S);
                        listUser.add(u);
                    }
                    
                    
                    System.out.println(tasks);
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUser;
    }
        
public void updateUser(User u) {
ConnectionRequest con = new ConnectionRequest();
con.setUrl("http://localhost/velotn1/web/app_dev.php/api/update/"+u.getId()+"?remember=1");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
            }
        
        });
       NetworkManager.getInstance().addToQueueAndWait(con);
        
   
   }


public int getiduser(String s) {
     User u = new User();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/getidfromuser/"+s);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        
                        u.setLogin(s);
                        u.setPass(obj.get("password").toString());
                        float i = Float.parseFloat(obj.get("id").toString());
                      
                        u.setId((int)i); 
                    }
              
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return u.getId();
   }


/*public int g(String s)
{
        User u = new User();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/getidfromuser/"+s);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        
                        u.setLogin(s);
                        u.setPass(obj.get("password").toString());
                        float i = Float.parseFloat(obj.get("id").toString());
                      
                        u.setId((int)i); 
                    }
              
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return u.getId();
}*/
 boolean exist = false ;
public boolean CheckValidationLogin(String s)
{
    
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/getidfromuser/"+s);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        
                        if (obj.get("username").toString().toUpperCase().equals(s.toUpperCase()))
                        {
                            exist = true ;
                        }
                       /* u.setLogin(s);
                        u.setPass(obj.get("password").toString());
                        float i = Float.parseFloat(obj.get("id").toString());
                      
                        u.setId((int)i); */
                    }
              
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return exist;
}


boolean password = false;
public boolean CheckValidationPassword(String s,String p)
{
     ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/getidfromuser/"+s);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        
                        if (obj.get("username").toString().toUpperCase().equals(s.toUpperCase()))
                        {
                           if(obj.get("password").toString().equals(p))
                           {
                               password =  true;
                           }
                        }
                     
                    }
              
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      return password;
    
}

 public static boolean checkPassword(String password_plaintext, String stored_hash)
    {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2y$"))
        {
           
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);
        return password_verified;
    }
 
 String passw = "";
public String getPassword(String s )
{
    passw = ""; 
    ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/getidfromuser/"+s);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                         boolean remember = Boolean.parseBoolean(obj.get("remember").toString());
                        if ((obj.get("username").toString().toUpperCase().equals(s.toUpperCase())) &&  (remember == true))
                        {
                            passw= obj.get("password").toString();
                           
                        }
                     
                    }
              
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return passw;
      
}
 

 boolean b = false;
 String db_pass="";
public boolean g(String s, String p)
{ 
    
    db_pass="";
    ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/getidfromuser/"+s);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                   byte[] data = con.getResponseData();
                   
                   Map<String,Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
                   List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");
                    for (Map<String, Object> obj : list) {
                         boolean remember = Boolean.parseBoolean(obj.get("remember").toString());
                        if ((obj.get("username").toString().toUpperCase().equals(s.toUpperCase())))
                        {
                          db_pass  = obj.get("password").toString();
                           
                        }
                    }
                   if(checkPassword(p, db_pass))
                   {
                      b = true; 
                   }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       return b;
    
}




boolean remember = false ;
public boolean getRemember(String s)
{
    
    ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/getidfromuser/"+s);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                         
                        if ((obj.get("username").toString().toUpperCase().equals(s.toUpperCase())))
                        {
                            remember = Boolean.parseBoolean(obj.get("remember").toString());
                           
                        }
                     
                    }
              
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return remember;
}
public String getidfromzone(String s)
{
    Zone z = new Zone();
      ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/getidfromzone/"+s);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        
                        float i = Float.parseFloat(obj.get("id").toString());
                        z.setId((int)i); 
                        
                        
                       
                        
                    }
              
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return String.valueOf(z.getId());
}
public String getidfromvelo(String s)
{
    Velo v = new Velo();
      ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/getidfromvelo/"+s);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        
                        float i = Float.parseFloat(obj.get("idz").toString());
                        v.setIdz((int)i); 
                        
                        
                       
                        
                    }
              
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return String.valueOf(v.getIdz());
}


public ArrayList<Velo> getVelos(int i){
      Velo v = new Velo();
      
          ArrayList<Velo> listVelo = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/getzoen/"+i);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                       
                        v.setNom(obj.get("nom").toString());
                        //v.setType(obj.get("type").toString());
                        //boolean disponible = Boolean.parseBoolean(obj.get("disponible").toString());
                        //v.setDisponible(disponible);
                        //float zone = Float.parseFloat(obj.get("zone").toString()); 
                      // Map<String, Object> listRecupZone = null;
                         
                       /*listRecupZone = (Map<String, Object>) obj.get("zone");
                       String S=(String) listRecupZone.get("nom");
                        System.out.println(listRecupZone.get("nom"));
                        Double prix = Double.parseDouble(obj.get("prix").toString()); 
                        v.setPrix(prix);*/

                       // Map<String, Object> listRecupevent = null;
                      //  listRecupevent = (Map<String, Object>) obj.get("publication");
                        //Map<String, Object> listRecupeventuser = null;
                       /* listRecupeventuser = (Map<String, Object>) obj.get("user");
                        
                        String S=(String) listRecupeventuser.get("username");
                        task.setUsername(S);*/
                        //v.setZone(S);
                        listVelo.add(v);
                    }
                    
                    
                    System.out.println(tasks);
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listVelo;
    }
int x = 0;
 public int getidfromNomZone(String s)
 {
     Zone Z = new Zone();
      ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/velotn1/web/app_dev.php/api/getidzone/"+s);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();     
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                 
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        
                       float i = Float.parseFloat(obj.get("id").toString());
                        Z.setId((int)i); 
                        
                        
                       //Z.setId(Integer.parseInt(obj.get("id").toString()));
                       Z.setNom(obj.get("nom").toString());
                        
                      // Z.setStcok(Integer.parseInt(obj.get("stcok").toString()));
                    }
                    
                    
                    System.out.println(tasks);
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Z.getId();
 }
}
