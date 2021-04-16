
package servlets;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import dataaccess.UserDB;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.InputMismatchException;
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

public class InventoryServlet extends HttpServlet {

   
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           HttpSession session = request.getSession();
        String email=(String)session.getAttribute("email");
        UserDB udb=new UserDB();
        ItemDB idb=new ItemDB();
         try {
             List<Item> items=idb.getAll(email);
         request.setAttribute("items",items);
        User user=udb.get(email);
          CategoryDB cdb=new CategoryDB();
        List<Category> categorys=cdb.getAll();
        request.setAttribute("categorys",categorys);
         if(user==null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
           return;
        }
        else
        {
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
        UserDB udb=new UserDB();
        ItemDB idb=new ItemDB();
        CategoryDB cdb=new CategoryDB();
         
        String method=request.getParameter("method");
         try {
        List<Item> items=idb.getAll(email);
        request.setAttribute("items",items);
        User user=udb.get(email);
        List<Category> categorys=cdb.getAll();
        request.setAttribute("categorys",categorys);
       
           if(user==null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
           return;
        }
        else
        {
            
            if(method.equals("insert"))
            {   
                String idLabel=(String)session.getAttribute("editId");
                int id=Integer.parseInt(idLabel);
                 int category=Integer.parseInt(request.getParameter("categorys"));
                 String name=request.getParameter("addName");
                 float price=Float.valueOf(request.getParameter("addPrice"));
                 Item item=new Item(category,name,price,email);
                
                 if(id==-1)
                 {
                     idb.insert(item);
                 }
                 else
                 {
                     idb.update(item, id);
                     
                 }
                 session.setAttribute("editId","-1");
            }
            else if(method.equals("delete"))
            {
                int itemId=Integer.parseInt(request.getParameter("deleteItem"));
                Item item=idb.get(itemId);
                if(item!=null)
                {
                    idb.delete(itemId);
                }
                
            }
            else if(method.equals("edit"))
            {
                int itemId=Integer.parseInt(request.getParameter("editItem"));
                Item item=idb.get(itemId);
                request.setAttribute("updatedName",item.getOwner());
                request.setAttribute("updatedPrice",item.getPrice());
                session.setAttribute("editId",Integer.toString(item.getItemID()));
                int tester=item.getItemID();
            }
            
            
          
            
           
        
           
               items=idb.getAll(email);
        request.setAttribute("items",items); 
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                .forward(request,response);
                   request.setAttribute("updatedName",null);
               request.setAttribute("updatedPrice",null);
           return;
        }
        
         } catch (Exception ex) {
             Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
       getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
           return;
    }
}
