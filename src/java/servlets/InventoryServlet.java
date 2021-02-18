
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

public class InventoryServlet extends HttpServlet {

   
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String logout=request.getParameter("logout");
        HttpSession session = request.getSession();
         String username=request.getParameter("username");
         String password=request.getParameter("password");
        if(logout==null)
        {
             
             if(session.getAttribute("username") ==null)
             {
                 request.setAttribute("message","");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
             }
             else
             {
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
         
         ArrayList<String> Users = new ArrayList<String>();
         boolean isTrue=false;
         String name=request.getParameter("Item_name");
         String price=request.getParameter("price");
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
            
            try{ 
                if((parseInt(price) < 0)||(parseInt(price) > 10000)||(parseInt(price)==0))
                {
                    request.setAttribute("message","invalid, please re-enter");
                    getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                    .forward(request,response);
                    return;
                }
                else
                {
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
}
