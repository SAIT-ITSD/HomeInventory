
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
         
         
           List<User> users=udb.getAll();
        request.setAttribute("users",users);  
        request.setAttribute("categorys",categorys);
        if(user.getRole()==1)
        {
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);
           return;
        }
        else
        {
            String username=user.getFirstName()+" "+user.getLastName();
                    request.setAttribute("username",username);
                        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                    .forward(request,response);
                       request.setAttribute("username",null); 
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
         String inventoryMessage=null;
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
                String idLabel=null;
                int id=0;
                int category =0; 
                String name =null;
                float price=0;
                try
                 {
                    idLabel=(String)session.getAttribute("editId");
                    id=Integer.parseInt(idLabel);
                    category=Integer.parseInt(request.getParameter("categorys"));
                    name=request.getParameter("addName");
                    price=Float.valueOf(request.getParameter("addPrice"));
                 }
                 catch (Exception ex) 
                 {
                     String username=user.getFirstName()+" "+user.getLastName();
                    request.setAttribute("username",username);
                        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                    .forward(request,response);
                       request.setAttribute("username",null); 
                    return;
                }
                 Item item=new Item(category,name,price,email);
                
                 if(id==-1)
                 {
                     idb.insert(item);
                 }
                 else
                 {
                     idb.update(item, id);
                     
                 }
                 inventoryMessage="item succesfully added/edited.";
                 session.setAttribute("editId","-1");
            }
            else if(method.equals("delete"))
            {
                int itemId=Integer.parseInt(request.getParameter("deleteItem"));
                Item item=idb.get(itemId);
                if(item!=null)
                {
                    idb.delete(itemId);
                    inventoryMessage="item succesfully deleted.";
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
                inventoryMessage="press save to save changed to item.";
            }
               items=idb.getAll(email);
        request.setAttribute("items",items); 
        String username=user.getFirstName()+" "+user.getLastName();
                    request.setAttribute("username",username);
                    request.setAttribute("invMessage",inventoryMessage);
                        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                    .forward(request,response);
                        request.setAttribute("username",null);
                       request.setAttribute("username",null); 
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
