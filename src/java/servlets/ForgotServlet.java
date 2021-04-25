/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.CategoryDB;
import dataaccess.ForgotDB;
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
import models.Forgot;
import models.Item;
import models.User;
import services.AccountService;

/**
 *
 * @author 828200
 */
public class ForgotServlet extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dude=request.getParameter("dude");
       
        HttpSession session = request.getSession();
        String email=(String)session.getAttribute("email");
        UserDB udb=new UserDB();
        ItemDB idb=new ItemDB();
        
             List<Item> items; 
             User user=null; 
             if(dude!=null)
        {
            
          
         getServletContext().getRequestDispatcher("/WEB-INF/confirmPassword.jsp")
                .forward(request,response);request.setAttribute("username",null);
         return;
        }
               try {
                user=udb.get(email);
            } catch (Exception ex) {
                Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(user==null)
            {
                getServletContext().getRequestDispatcher("/WEB-INF/forgotPassword.jsp")
                .forward(request,response);request.setAttribute("username",null);
                request.setAttribute("message",null);
            }
String username=user.getFirstName()+" "+user.getLastName();
                    request.setAttribute("username",username);
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
            getServletContext().getRequestDispatcher("/WEB-INF/forgotPassword.jsp")
                .forward(request,response);request.setAttribute("username",null);
          request.setAttribute("message",null);
        }
        if(user.getRole()==1)
        {
            try {
                user=udb.get(email);
            } catch (Exception ex) {
                Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        username=user.getFirstName()+" "+user.getLastName();
                    request.setAttribute("username",username);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);request.setAttribute("username",null);
           return;
        }
        try {
            user=udb.get(email);
        } catch (Exception ex) {
            Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        getServletContext().getRequestDispatcher("/WEB-INF/forgotPassword.jsp")
                .forward(request,response);request.setAttribute("username",null);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stage=(String)request.getParameter("stage");
        boolean passed=false;HttpSession session = request.getSession();
        String email=(String)session.getAttribute("email");
        String confirmation=request.getParameter("confirmation");
        String path = getServletContext().getRealPath("/WEB-INF");
        String newPassword=request.getParameter("newPassword");
        UserDB udb=new UserDB();
        User user=null;
        try 
        {
            user=udb.get(email); String username=user.getFirstName()+" "+user.getLastName();
                    request.setAttribute("username",username);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       email=request.getParameter("forgotEmail");
        //we initialised stage,email and confirmation and initialised passed by fedault as false.
        if(stage.equals("get"))
        {
          
            AccountService as=new AccountService();
            String url = request.getRequestURL().toString();
             url+="?dude="+"dude";
            String message=as.forgot(email,path,url);
            request.setAttribute("passwordMessage",message); 
            
            getServletContext().getRequestDispatcher("/WEB-INF/forgotPassword.jsp")
                .forward(request,response);request.setAttribute("username",null);
            request.setAttribute("passwordMessage",null); 
            
            return;
        }
        //pass test for confirmation password
        ForgotDB fdb=new ForgotDB();
        Forgot forgot=null;
        try {
            forgot=fdb.get(confirmation);
        } catch (Exception ex) {
            Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(forgot!=null)
        {
            passed=true;
        }
        if(passed==true)
        {
          request.setAttribute("message","password succesfully changed."); 
            try 
            {
                String thisEmail=forgot.getForgotEmail();
                user=udb.get(thisEmail);
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            user.setPassword(newPassword);
            try {
                udb.update(user);
            } catch (Exception ex) {
                Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);request.setAttribute("username",null);
          request.setAttribute("message",null);
            try 
            {
                fdb.delete(confirmation);
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          return;
        }
        request.setAttribute("passwordMessage","confirmation code is incorrect,try again.");  
         getServletContext().getRequestDispatcher("/WEB-INF/confirmPassword.jsp")
                .forward(request,response);request.setAttribute("username",null);
         request.setAttribute("passwordMessage",null); 
        //messahes have been configured correctly. 
    }


}
