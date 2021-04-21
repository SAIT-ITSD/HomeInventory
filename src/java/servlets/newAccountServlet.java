/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.CheatDB;
import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                
                 getServletContext().getRequestDispatcher("/WEB-INF/newAccount.jsp")
                .forward(request,response);
            }
    

 
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName=request.getParameter("RegisterFirstName");
        String lastName=request.getParameter("RegisterLastName");
        String email=request.getParameter("RegisterEmail");
        String password=request.getParameter("RegisterPassword");
        String path = getServletContext().getRealPath("/WEB-INF");
        
        int role=0;
        int active=1;
        User user;
        UserDB udb=new UserDB();
           try 
           {
              user=udb.get(email);
              if(user==null)
              {
               user=new User(email,active,firstName,lastName, password,role);
                try {
                    udb.insert(user);
                     AccountService as=new AccountService();
                    as.welcome(email, password, path);
                    CheatDB cdb=new CheatDB();
                    String random=as.fullRandom();
                    cdb.insert(random, email, password);
                } catch (Exception ex1) {
                    Logger.getLogger(newAccountServlet.class.getName()).log(Level.SEVERE, null, ex1);
                } 
                getServletContext().getRequestDispatcher("/WEB-INF/welcome.jsp")
                .forward(request,response);
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
