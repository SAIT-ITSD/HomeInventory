
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

public class InventoryServlet extends HttpServlet {

   
    
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
        
         request.setAttribute("message","");  
         HttpSession session = request.getSession();
         
         ArrayList<String> Users = new ArrayList<String>();
         boolean isTrue=false;
         String price=request.getParameter("price");
        String username=request.getParameter("username");
           String password=request.getParameter("password");
         String path=getServletContext().getRealPath("/WEB-INF/users.txt");
         Scanner scannerUsers = new Scanner(new File(path));
         
         while(scannerUsers.hasNext())
         {
             Users.add(scannerUsers.next());
         }
         scannerUsers.close();
         if(session.getAttribute("username")==null)
         {
            for(String user:Users)
            {
                String[] theUser=user.split(",");

                if(username.equals(theUser[0])&& password.equals(theUser[1]))
                {
                    isTrue=true;
                   session.setAttribute("username", theUser[0]);
                   session.setAttribute("password", theUser[1]);
                }

            }
             if(session.getAttribute("username").equals("admin"))
            {
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                   .forward(request,response);
                return;
            }
         }
        
         else
         {
            isTrue=true;
         }
         if(isTrue==true)
         {
            String path2=getServletContext().getRealPath("/WEB-INF/homeitems.txt");
            Scanner scannerHomeItems = new Scanner(new File(path2));
            isTrue=false;
            int v_price=0;
             try {  
                     v_price=Integer.parseInt(price);
                     if(v_price <0 &&v_price!=0)
                     {
                         isTrue=true;
                     }
                     else
                     {
                         isTrue=false;
                     }
                   } 
             catch(NumberFormatException e){  
                     isTrue= false;  
                   }  
            if(isTrue==false)
            {
                request.setAttribute("message","Invalid.Please re-nter.");  
                  scannerHomeItems.close();
                 getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                .forward(request,response);
                 return;
            }
            scannerHomeItems.close();

            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
           .forward(request,response);
            return;
         }
        else
          { 
              request.setAttribute("message","Invalid Login.");
              getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
             .forward(request,response);
              return;
          }
         
    }


}
