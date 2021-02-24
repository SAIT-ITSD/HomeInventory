/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.HomeItem;

public class AdminServlet extends HttpServlet {

   
    
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String logout=request.getParameter("logout");
        HttpSession session = request.getSession();
        String username=(String)session.getAttribute("username");
        HomeItem hi=new HomeItem();
          request.setAttribute("name",username);
        if(logout==null)
        {
             
             if(session.getAttribute("username") ==null)
             {
                   request.setAttribute("message", "");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
             }
             if(session.getAttribute("username").equals("admin"))
             {
                  String[] key=hi.topUser(getServletContext().getRealPath("/WEB-INF/homeitems.txt")).split(",");
             request.setAttribute("productName", key[1]);
             request.setAttribute("userOfExpense",key[0]);
                 request.setAttribute("total", hi.total(getServletContext().getRealPath("/WEB-INF/homeitems.txt")));
                request.setAttribute("message", "");
                 getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
             }
             else
             {
                  request.setAttribute("utotal","\n "+"Total value in inventory: $"+hi.total(username,getServletContext().getRealPath("/WEB-INF/homeitems.txt")));
                   String uname=(String)session.getAttribute("username");
                    request.setAttribute("name",uname);
                  request.setAttribute("message", "");
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
