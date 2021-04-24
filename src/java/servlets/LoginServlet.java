/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import dataaccess.UserDB;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.HomeItem;
import models.Item;
import models.User;

/**
 *
 * @author 828200
 */
public class LoginServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String welcomeEmail=(String)request.getAttribute("release");
        String inactiveMessage="account succesfully inactivated.";
        User user=null;
       HttpSession session = request.getSession();
            String email=(String)session.getAttribute("email");
       try { 
           String log=(String)request.getParameter("log");
            
            UserDB  udb=new UserDB();
            user=udb.get(email);
            
        
        if(log.equals("out"))
        {
             session.invalidate();
        }
        if(log.equals("inactive")&&user.getRole()!=1)
        {
           user.setActive(0);
                udb.update(user);
             session.invalidate();
             request.setAttribute("message",inactiveMessage);
        }String username=user.getFirstName()+" "+user.getLastName();
                    request.setAttribute("username",username);
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);request.setAttribute("username",null);
           return;
     } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       if(user!=null)
       {
            String username=user.getFirstName()+" "+user.getLastName();
            request.setAttribute("username",username);
            request.setAttribute("username",username);
            CategoryDB cdb=new CategoryDB();
            List<Category> categorys=null;
                try {
                    categorys = cdb.getAll();
                } catch (Exception ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
        request.setAttribute("categorys",categorys);
        if(user.getRole()==1)
        {
            UserDB udb=new UserDB();
             List<User> users=null;
                try {
                    users = udb.getAll();
                } catch (Exception ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
        request.setAttribute("users",users);  
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);request.setAttribute("username",null);
           return;
        }
            
            else
            {
                ItemDB idb=new ItemDB();
             List<Item> items=null;
                try {
                    items = idb.getAll(email);
                } catch (Exception ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
         request.setAttribute("items",items);
                        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                    .forward(request,response);
                       request.setAttribute("username",null); 
           return;
            }
       }
     
       
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response); request.setAttribute("username",null);
        request.setAttribute("message",null);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setAttribute("message"," ");
        HttpSession session = request.getSession();
        String email=(String)request.getParameter("email");
        session.setAttribute("email",email);
        session.setAttribute("editId","-1");
        String path = getServletContext().getRealPath("/WEB-INF");
        if(email!=null)
        {
           UserDB udb=new UserDB();
                try {
                    List<User> users=udb.getAll();
                    request.setAttribute("users",users); 
                    User user=udb.get(email);
                     if(user!=null)
                {
                    String username=user.getFirstName()+" "+user.getLastName();
                             request.setAttribute("username",username);
                }
                    String password=request.getParameter("password");
                    CategoryDB cdb=new CategoryDB();
                    List<Category> categorys=cdb.getAll();
                            request.setAttribute("categorys",categorys);
                            
                    if(user!=null&&user.getPassword().equals(password)&&user.getActive()==1)
                    {
                        
                        if(user.getRole()==0)
                        {
                            String message="account is so far inactive.";
                            request.setAttribute("message",message);
                            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                                  .forward(request,response);request.setAttribute("username",null);
                             request.setAttribute("message",null);
                             return;
                        }
                        if(user.getRole()==1)
                        {
                              
                            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                                .forward(request,response);request.setAttribute("username",null);
                           return;
                        }
                        else
                        { 
                             ItemDB idb=new ItemDB();
                            
                            List<Item> items=idb.getAll(email);
                            request.setAttribute("items",items);
                            
                            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                                .forward(request,response);request.setAttribute("username",null);
                           return;
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                User user;
             try {
                 user = udb.get(email);
                 String username=user.getFirstName()+" "+user.getLastName();
                    request.setAttribute("username",username);
                 if(user.getActive()==0)
                    {
                    String message="you have set your account to inactive please contact the administrator to reactivate it.";
                    request.setAttribute("message",message);
                    }
             } 
             catch (Exception ex) 
             {
                 Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
             }
             
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);request.setAttribute("username",null);
           return;
        }
    }

   
}
