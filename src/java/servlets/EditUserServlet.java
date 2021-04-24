/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.CategoryDB;
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
import models.User;

/**
 *
 * @author 828200
 */
public class EditUserServlet extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          HttpSession session = request.getSession();
        String email=(String)session.getAttribute("email");
        
        if(email==null)
        {
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
           return;
        }
        UserDB udb=new UserDB();
        User user=null;
        try {
            user = udb.get(email);
        } catch (Exception ex) {
            Logger.getLogger(EditUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
                List<User> users=udb.getAll();
        request.setAttribute("users",users);  
       
           CategoryDB cdb=new CategoryDB();
        List<Category> categorys=cdb.getAll();
        request.setAttribute("categorys",categorys);
        if(user.getRole()==1)
        {
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
           return;
        }
            user = udb.get(email);
            session.setAttribute("EditFirstName",user.getFirstName());
         session.setAttribute("EditLastName",user.getLastName());
          session.setAttribute("EditPassword",user.getPassword());
           session.setAttribute("EditEmail",user.getEmail());
        } catch (Exception ex) {
            Logger.getLogger(EditUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/edit.jsp")
                .forward(request,response);
           return;
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                HttpSession session = request.getSession();
        String email=(String)session.getAttribute("email");
        String EditFirstName=request.getParameter("EditFirstName");
        String EditLastName=request.getParameter("EditLastName");
        String EditPassword=request.getParameter("EditPassword");
        String EditEmail=request.getParameter("EditEmail");
        if(EditFirstName==null||EditFirstName.equals(" ")||EditLastName==null||EditLastName.equals(" ")||EditPassword==null||EditPassword.equals(" ")||EditEmail==null||EditEmail.equals(" "))
        {
           getServletContext().getRequestDispatcher("/WEB-INF/edit.jsp")
                .forward(request,response);
           return;
        }
        UserDB udb=new UserDB();
        User user;
        try {
            user = udb.get(email); 
            user.setFirstName(EditFirstName);
            user.setLastName(EditLastName);
            user.setPassword(EditPassword);
            user.setEmail(EditEmail);
            if(!user.getEmail().equals(email))
            {
                User oldUser=udb.get(email);
                udb.insert(user);
                udb.delete(oldUser);
                
            }
            else
            {
                udb.update(user);
            }
             
        } catch (Exception ex) {
            Logger.getLogger(EditUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
           return;
    }

  

}
