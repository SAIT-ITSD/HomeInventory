/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.HomeItem;

/**
 *
 * @author 828200
 */
public class LoginServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String logout=request.getParameter("logout");
        HttpSession session = request.getSession();
        String username=(String)session.getAttribute("username");
        HomeItem hi=new HomeItem();
        
        if(logout==null)
        {
             
             if(session.getAttribute("username") ==null)
             {
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
             }
             else if(session.getAttribute("username").equals("admin"))
             {
                 request.setAttribute("total", hi.total(getServletContext().getRealPath("/WEB-INF/homeitems.txt")));
                 getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
             }
             else
             {
                 request.setAttribute("utotal", hi.total(username,getServletContext().getRealPath("/WEB-INF/homeitems.txt"))); 
                  String uname=(String)session.getAttribute("username");
                   request.setAttribute("name",uname);
                   
             getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                .forward(request,response);
             }
        }
        else
        {
           session.invalidate();
            request.setAttribute("message", "you have succesfully logged out.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
        }
          
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         request.setAttribute("message","");  
         HttpSession session = request.getSession();
         HomeItem hi=new HomeItem();
         ArrayList<String> Users = new ArrayList<String>();
         boolean isTrue=false;
         
         if(request.getParameter("username")!=null)
         { 
             session.setAttribute("username",request.getParameter("username"));
             session.setAttribute("password",request.getParameter("password"));
         }
         
        
         String username=(String)session.getAttribute("username");
         String password=(String)session.getAttribute("password");
         String path=getServletContext().getRealPath("/WEB-INF/users.txt");
        
         Scanner scannerUsers = new Scanner(new File(path));
         while(scannerUsers.hasNext())
         {
            String[] user=scannerUsers.next().split(",");
          
            if(username.equals(user[0])&&password.equals(user[1]))
            {
                
                isTrue=true;
                session.setAttribute("username",user[0]);
                session.setAttribute("password",user[1]);
            }
         }
         scannerUsers.close();
         if(isTrue==false)
         {
               session.setAttribute("username",null);
             request.setAttribute("message","invalid login!");
             getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
             return;
            
         }
         if(username.equals("admin"))
         {
             String[] key=hi.topUser(getServletContext().getRealPath("/WEB-INF/homeitems.txt")).split(",");
             request.setAttribute("productName", key[1]);
             request.setAttribute("userOfExpense",key[0]);
             request.setAttribute("total", hi.total(getServletContext().getRealPath("/WEB-INF/homeitems.txt")));
            request.setAttribute("name", username);
             getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
             return;
         }
         if(isTrue==true)
         {
           request.setAttribute("utotal", hi.total(username,getServletContext().getRealPath("/WEB-INF/homeitems.txt"))); 
           request.setAttribute("username",username);
             request.setAttribute("message","");
             getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
             .forward(request,response);
             return;
            
            
         }
        
    }

   
}
