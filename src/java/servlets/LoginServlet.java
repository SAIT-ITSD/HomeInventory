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
        if(logout==null)
        {
             
             if(session.getAttribute("username") ==null)
             {
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
             }
             else if(session.getAttribute("username").equals("admin"))
             {
                 getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
             }
             else
             {
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
             
             getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
             return;
         }
         if(isTrue==true)
         {
            
           
             request.setAttribute("message","");
             getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
             .forward(request,response);
             return;
            
            
         }
        
    }

   
}
