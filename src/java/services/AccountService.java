package services;

import dataaccess.ForgotDB;
import dataaccess.UserDB;
import dataaccess.WelcomeDB;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {
    
    public void forgot(String email, String password, String path,String toUrl) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful reminder email sent to {0}", email);
                
                String to = user.getEmail();
                String subject = "Forgot Password";
                String template = path + "/emailtemplates/forgot.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                String passcode=String.valueOf(random());
                for(int i=0;i<6;i++)
                {
                    passcode+=String.valueOf(random());
                }
                tags.put("passcode", passcode);
                tags.put("link", toUrl);
                GmailService.sendMail(to, subject, template, tags);
               ForgotDB fdb=new ForgotDB();
               fdb.insert(passcode,email);
            
        } catch (Exception e) {
        }
    
    }
     public void welcome(String email, String password, String path,String toPath) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful reminder email sent to {0}", email);
                
                String to = user.getEmail();
                String subject = "welcome";
                String template = path + "/emailtemplates/welcome.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                String passcode=String.valueOf(random());
                for(int i=0;i<6;i++)
                {
                    passcode+=String.valueOf(random());
                }
                tags.put("passcode", passcode);
                String newPath="";
                tags.put("link", toPath);
                GmailService.sendMail(to, subject, template, tags);
               WelcomeDB wdb=new WelcomeDB();
               wdb.insert(passcode,email);
            
        } 
        catch (Exception e) {
            e=e;
        }
    
    }
        public void cheatLink(String email, String password, String path,String passcode,String toPath) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful reminder email sent to {0}", email);
                
                String to = user.getEmail();
                String subject = "cheatLink";
                String template = path + "/emailtemplates/cheatLink.html";
                
               
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("passcode", toPath);
                
                GmailService.sendMail(to, subject, template, tags);
               
            
        } catch (Exception e) {
            e=e;
        }
    
    }
    public int random() 
    {
        int range = (9 - 0) + 1;     
        return (int)(Math.random() * range) + 0;
    }
     public String fullRandom() 
    {
        int range = (9 - 0) + 1;     
        String fullRandom = String.valueOf((int)(Math.random() * range) + 0);
        
        for(int i=0;i<6;i++)
        {
            fullRandom += String.valueOf((int)(Math.random() * range) + 0);
        }
        return fullRandom;
    }

}
