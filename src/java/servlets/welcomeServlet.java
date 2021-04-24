/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.CategoryDB;
import dataaccess.CheatDB;
import dataaccess.ItemDB;
import dataaccess.UserDB;
import dataaccess.WelcomeDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Cheat;
import models.Item;
import models.User;
import models.Welcome;
import services.AccountService;

/**
 *
 * @author 828200
 */
public class welcomeServlet extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cheat=null;
        try{cheat=request.getParameter("cheat").toString();}
        catch(Exception e){cheat=null;}
        
       
        HttpSession session = request.getSession();
        String email=(String)session.getAttribute("email");
        UserDB udb=new UserDB();
        ItemDB idb=new ItemDB();
        
             List<Item> items=null; 
             User user=null;
        try {
            items = idb.getAll(email); 
            request.setAttribute("items",items);
        user=udb.get(email);
           List<User> users=udb.getAll();
        request.setAttribute("users",users);  
       CategoryDB cdb=new CategoryDB();
        List<Category> categorys=cdb.getAll();
        request.setAttribute("categorys",categorys);
        } catch (Exception ex) {
            Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(user==null)
        {
            String message="ther is no user that has said cheat passcode.";
            request.setAttribute("message",message);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
            .forward(request,response);
            request.setAttribute("message",null);
            return;
        }
        if(user.getRole()==1)
        {
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
           return;
        }
        if(cheat!=null)
        {
         CheatDB cdb=new CheatDB();
         Cheat thisCheat;
          
                
            
            try 
            {
                thisCheat = cdb.getById(cheat);
                String cheatEmail=null;
                if(thisCheat!=null)
                {
                    cheatEmail=thisCheat.getCheatEmail();
                }
               else
                {
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                   .forward(request,response);
                    return;
                }
                user=udb.get(cheatEmail);
                //insert rest of code here.
                session.setAttribute("email",user.getEmail());
                email=user.getEmail();
                CategoryDB ctgdb=new CategoryDB();
                if(email!=null)
                {

                        try 
                        {
                            List<User> users=udb.getAll();
                            request.setAttribute("users",users); 
                            String username=user.getFirstName()+" "+user.getLastName();
                            request.setAttribute("username",username);
                            String password=request.getParameter("password");
                            
                            List<Category> categorys=ctgdb.getAll();
                            request.setAttribute("categorys",categorys);
                             try 
                            {

                                if(user.getActive()==0)
                                   {
                                        String message="you have set your account to inactive please contact the administrator to reactivate it.";
                                        request.setAttribute("message",message);
                                        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                                        .forward(request,response);
                                        request.setAttribute("message",null);
                                        return;
                                   }
                            } 
                            catch (Exception ex) 
                            {
                                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }        
                            if(user!=null&&user.getActive()==1)
                            {

                                     
                                    request.setAttribute("items",items);

                                    getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                                        .forward(request,response);
                                    String testing=(String)session.getAttribute("email");
                                   return;
                                
                            }
                        } 
                        catch (Exception ex) 
                        {
                            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }


                }
                //tip point
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(welcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/welcome.jsp")
                .forward(request,response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String passcode=request.getParameter("welcomePasscode");
        UserDB udb=new UserDB();
        WelcomeDB wdb=new WelcomeDB();
        try {
            Welcome welcome=wdb.get(passcode);
            if(welcome!=null)
            {
              User user=udb.get(welcome.getWelcomeEmail());
              user.setRole(3);
              udb.update(user);
              request.setAttribute("message","acount succesfully activated!");
              AccountService as=new AccountService();
              String path = getServletContext().getRealPath("/WEB-INF");
              
              CheatDB cdb=new CheatDB();
              
              Cheat cheat=cdb.get(user.getEmail());
             String url = request.getRequestURL().toString();
              url+="?cheat="+cheat.getCheatId();
              as.cheatLink(cheat.getCheatEmail(), cheat.getCheatPassword(), path, cheat.getCheatId(),url);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
            request.setAttribute("message",null);
            request.setAttribute("cheat", cheat.getCheatId());
            wdb.delete(passcode);
            return;
            }
            request.setAttribute("welcomeLog","this passcode is incorrect");
            getServletContext().getRequestDispatcher("/WEB-INF/welcome.jsp")
                .forward(request,response);
              request.setAttribute("welcomeLog",null);
              return;
        } catch (Exception ex) {
            Logger.getLogger(welcomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("welcomeLog","this passcode is incorrect");
        getServletContext().getRequestDispatcher("/WEB-INF/welcome.jsp")
                .forward(request,response);
        request.setAttribute("welcomeLog",null);
    }

   

}
