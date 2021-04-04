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
        
        HttpSession session = request.getSession();
        String email=(String)session.getAttribute("email");
        String password=(String)session.getAttribute("password");
        if(email==null||password==null)
        {
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
           return;
        }
        else if(email.contains("admin"))
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

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            HttpSession session = request.getSession();
        String email=(String)session.getAttribute("email");
        String password=(String)session.getAttribute("password");
        if(email==null||password==null)
        {
           UserDB udb=new UserDB();
                try {
                    User user=udb.get(email);
                    password=request.getParameter("password");
                    if(user.getEmail()!=null&&user.getPassword().equals(password))
                    {
                        email=request.getParameter("email");
                        password=request.getParameter("password");
                        session.setAttribute("email",email);
                        session.setAttribute("password",password);
                        email=(String)session.getAttribute("email");
                        password=(String)session.getAttribute("password");
                        if(email.contains("admin"))
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
        else if(email.contains("admin"))
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

   
}
