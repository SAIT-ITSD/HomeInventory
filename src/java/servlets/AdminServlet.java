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
import java.io.PrintWriter;
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

public class AdminServlet extends HttpServlet {

   
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
        HttpSession session = request.getSession();
        String email=(String)session.getAttribute("email");
        UserDB udb=new UserDB();
         
         try {
           List<User> users=udb.getAll();
        request.setAttribute("users",users);  
        User user=udb.get(email);
           CategoryDB cdb=new CategoryDB();
        List<Category> categorys=cdb.getAll();
        request.setAttribute("categorys",categorys);
        if(user.getRole()==1)
        {
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
           return;
        }
        else if(user==null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
           return;
        }
        else
        {
             ItemDB idb=new ItemDB();
             List<Item> items=idb.getAll(email);
         request.setAttribute("items",items);
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                .forward(request,response);
           return;
        }
         } catch (Exception ex) {
             Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
       getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
           return;
        
          
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            HttpSession session = request.getSession();
        String email=(String)session.getAttribute("email");
        String method=(String)request.getParameter("method");
        UserDB udb=new UserDB();
         try {
        User user=udb.get(email);
        List<User> users=udb.getAll();
        request.setAttribute("users",users);
        CategoryDB cdb=new CategoryDB();
        List<Category> categorys=cdb.getAll();
        request.setAttribute("categorys",categorys);
        if(user.getRole()==1&&method.equals("add"))
        {
           
            String newEmail=request.getParameter("newEmail");
            String newPassword=request.getParameter("newPassword");
            String newFirstName=request.getParameter("newFirstName");
            String newLastName=request.getParameter("newLastName");
            int active;
            String newActive=request.getParameter("newActive");
            if(newActive.equals("on"))
            {
                active=1;
            }
            else 
            {
                active=0;
            }
            User newUser=new User(newEmail,active,newFirstName,newLastName,newPassword,2);
            for(User us:users)
            {
                if(us.getEmail().equals(newEmail))
                {
                    udb.delete(us);
                }
            }
             if(email.equals(newEmail))
            {
                newUser.setRole(1);
            }
            udb.insert(newUser);
            
             request.setAttribute("updatedEmail",null);
             request.setAttribute("updatedPassword",null);
             request.setAttribute("updatedFirstName",null);
             request.setAttribute("updatedLastName",null);users=udb.getAll();
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
            
           return;
        }
        else if(user.getRole()==1&&method.equals("delete"))
        {
            String thisEmail=request.getParameter("thisEmail");
            User thisUser=udb.get(thisEmail);
            if(!email.equals(thisEmail))
            {
                udb.delete(thisUser);
            }
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
           return;
        }
        else if(user.getRole()==1&&method.equals("edit"))
        {
            String thisEmail=request.getParameter("thisEmail");
            User thisUser=udb.get(thisEmail);
            String newEmail=thisUser.getEmail();
            String newPassword=thisUser.getPassword();
            String newFirstName=thisUser.getFirstName();
            String newLastName=thisUser.getLastName();
            request.setAttribute("updatedEmail",newEmail);
            request.setAttribute("updatedPassword",newPassword);
             request.setAttribute("updatedFirstName",newFirstName);
            request.setAttribute("updatedLastName",newLastName);
           
          
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
           return;
        }
        else if(user.getRole()==1&&method.equals("editCategory"))
        {
            String getId=request.getParameter("thisCategoryId");
             String getName=request.getParameter("thisCategory");
             session.setAttribute("newCategoryId",getId);
             session.setAttribute("newCategoryName",getName);
          
        }
        else if(user.getRole()==1&&method.equals("addCategory"))
        {
              int getId=(int)request.getAttribute("newCategoryId");
              String getName=(String)request.getAttribute("newCategoryName");
              int tisID=-1;
              int highest=0;
              for(int i=0;i<categorys.size();i++)
              {
                  if(categorys.get(i).getCategoryId()==getId)
                  {
                      tisID=i;
                      if(highest<i)
                      {
                          highest=i;
                      }
                  }
              }
              if(tisID!=-1)
              {
                  cdb.update(tisID, getName);
              }
              else
              {
                  cdb.insert((highest+1), getName);
              }
        }
          else if(user==null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
           return;
        }
        else
        {
             ItemDB idb=new ItemDB();
         
             List<Item> items=idb.getAll(email);
         request.setAttribute("items",items);
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                .forward(request,response);
           return;
        }
         } catch (Exception ex) {
             Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
         getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
           return;
        
        
    }

}
