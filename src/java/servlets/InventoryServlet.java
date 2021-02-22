
package servlets;

import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.HomeItem;

public class InventoryServlet extends HttpServlet {

   
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String logout=request.getParameter("logout");
        HttpSession session = request.getSession();
        String username=(String)session.getAttribute("username");
        String password=(String)session.getAttribute("password");
         HomeItem hi=new HomeItem();
         
         String path=getServletContext().getRealPath("/WEB-INF/homeitems.txt");
        if(logout==null)
        {
             
             if(username ==null)
             {
                 request.setAttribute("message","");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
             }
             else if(username.equals("admin"))
             {
                 request.setAttribute("total", hi.total(getServletContext().getRealPath("/WEB-INF/homeitems.txt")));
                   request.setAttribute("message","");
                       getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                .forward(request,response);
             }
             else
             {
                request.setAttribute("utotal", hi.total(username,getServletContext().getRealPath("/WEB-INF/homeitems.txt"))); 
                  String uname=(String)session.getAttribute("username");
          request.setAttribute("name",uname);
                  request.setAttribute("name",session.getAttribute("username"));
                 request.setAttribute("message","");
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
         String uname=(String)session.getAttribute("username");
          request.setAttribute("name",uname);
         ArrayList<String> Users = new ArrayList<String>();
         boolean isTrue=false;
         String name=request.getParameter("Item_name");
         String price=request.getParameter("price");
         String username=(String)session.getAttribute("username");
             HomeItem hi=new HomeItem();
         String path=getServletContext().getRealPath("/WEB-INF/homeitems.txt");
         request.setAttribute("utotal", hi.total(username,getServletContext().getRealPath("/WEB-INF/homeitems.txt"))); 
         String category=request.getParameter("category");
         String line=(String)session.getAttribute("username")+","+category+","+name+","+price;
        try{ 
            if((parseInt(price) < 0)||(parseInt(price) > 10000)||(parseInt(price)==0)||(name.equals("")))
            {
                
                request.setAttribute("message","invalid, please re-enter");
                getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                .forward(request,response);
                return;
            }
            else
            {
                hi.append(path, line);
                request.setAttribute("message","success");
                getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                .forward(request,response);
                return;
            }
           }
        catch(NumberFormatException e)
        {
            request.setAttribute("message","invalid, please re-enter");
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
            .forward(request,response);
            return;
        }

         
    }
}
