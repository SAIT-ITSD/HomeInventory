/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.UserDB;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.HomeItem;
import models.User;

/**
 *
 * @author 828200
 */
public class LoginServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
           return;
     
          
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            HttpSession session = request.getSession();
        String email=(String)request.getParameter("email");
        session.setAttribute("email",email);
        if(email!=null)
        {
           UserDB udb=new UserDB();
                try {
                    User user=udb.get(email);
                    String password=request.getParameter("password");
                    if(user!=null&&user.getPassword().equals(password))
                    {
                        
                        
                        if(user.getRole()==1)
                        {
                            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                                .forward(request,response);
                           return;
                        }
                        else
                        {
                            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                                .forward(request,response);
                           return;
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
           return;
        }
    }

   
}
