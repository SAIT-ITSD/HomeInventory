/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
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
             
             if(username ==null)
             {
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
             }
             else if(username.equals("admin"))
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
        
    }

   
}
