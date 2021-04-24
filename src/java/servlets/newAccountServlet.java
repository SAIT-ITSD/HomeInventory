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
import models.Item;
import models.User;
import services.AccountService;

/**
 *
 * @author 828200
 */
public class newAccountServlet extends HttpServlet {

   
      
       @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String validate=request.getParameter("validate");
                if(validate!=null)
                {
                      getServletContext().getRequestDispatcher("/WEB-INF/welcome.jsp")
                .forward(request,response);
                }
                 HttpSession session = request.getSession();
        String email=(String)session.getAttribute("email");
        UserDB udb=new UserDB();
        ItemDB idb=new ItemDB();
        
             List<Item> items; 
             User user=null;
        try {
            items = idb.getAll(email); 
            request.setAttribute("items",items);
        user=udb.get(email);
        
        if(user==null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/newAccount.jsp")
                .forward(request,response);
        }
        String username=user.getFirstName()+" "+user.getLastName();
                    request.setAttribute("username",username);
           List<User> users=udb.getAll();
        request.setAttribute("users",users);  
       CategoryDB cdb=new CategoryDB();
        List<Category> categorys=cdb.getAll();
        request.setAttribute("categorys",categorys);
        } catch (Exception ex) {
            Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(user.getRole()==1)
        {
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
           return;
        }
                 getServletContext().getRequestDispatcher("/WEB-INF/newAccount.jsp")
                .forward(request,response);
            }
    

 
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String thisEmail=(String)session.getAttribute("email");
        
        String firstName=request.getParameter("RegisterFirstName");
        String lastName=request.getParameter("RegisterLastName");
        String email=request.getParameter("RegisterEmail");
        String password=request.getParameter("RegisterPassword");
        String path = getServletContext().getRealPath("/WEB-INF");
        
        int role=0;
        int active=1;
        User user;
        UserDB udb=new UserDB();
        User thisUser=null;
           try 
           {
                thisUser=udb.get(thisEmail);
                String username=thisUser.getFirstName()+" "+thisUser.getLastName();
                request.setAttribute("username",username);
           } 
           catch (Exception ex) 
           {
               Logger.getLogger(newAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
           if(firstName==null||lastName==null||email==null||password==null||firstName.equals("")||lastName.equals("")||email.equals("")||password.equals(""))
           {
               String message="text boxes cannot be empty.";
              request.setAttribute("dope", message);
              getServletContext().getRequestDispatcher("/WEB-INF/newAccount.jsp")
                .forward(request,response);
              request.setAttribute("dope", null);
              return;
           }
            if(firstName.equals(" ")||lastName.equals(" ")||email.equals(" ")||password.equals(" "))
           {
               String message="text boxes cannot be empty.";
              request.setAttribute("dope", message);
              getServletContext().getRequestDispatcher("/WEB-INF/newAccount.jsp")
                .forward(request,response);
              request.setAttribute("dope", null);
              return;
           }
           try 
           {
              user=udb.get(email);
              if(user==null)
              {
               user=new User(email,active,firstName,lastName, password,role);
                try {
                    udb.insert(user);
                     AccountService as=new AccountService();
                     String url = request.getRequestURL().toString();
                     url+="?validate=validate";
                    as.welcome(email, password, path,url);
                    CheatDB cdb=new CheatDB();
                    String random=as.fullRandom();
                    cdb.insert(random, email, password);
                } catch (Exception ex1) {
                    Logger.getLogger(newAccountServlet.class.getName()).log(Level.SEVERE, null, ex1);
                } 
                String message="account succesfully created! you will be given a confirmation email to activate you're account.If you're email entered exists.";
              request.setAttribute("dope", message);
                getServletContext().getRequestDispatcher("/WEB-INF/newAccount.jsp")
                .forward(request,response);
                request.setAttribute("dope", null);
                return;
              }
              
              String message="user already exists!";
              request.setAttribute("dope", message);
              getServletContext().getRequestDispatcher("/WEB-INF/newAccount.jsp")
                .forward(request,response);
              request.setAttribute("dope", null);
              return;
           } 
           catch (Exception ex) 
           {
              
           }
           
       
    }

   

}
