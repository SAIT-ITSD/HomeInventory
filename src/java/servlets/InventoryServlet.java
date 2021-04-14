
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
        String toDo=request.getParameter("toDo");
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
            String method=request.getParameter("method");
            if(method.equals("insert"))
            {
                String Cat=(String)request.getParameter("categorys");
                int addCategory;
                addCategory = Integer.parseInt(Cat);
                String addName=request.getParameter("addName");
                String addPrice=request.getParameter("addPrice");
                float price=Float.parseFloat(addPrice);
                Item item=new Item(addCategory,addName,price,email);
                String oldItemName=(String)session.getAttribute("oldItemName");
                if(oldItemName!=null)
                {
                     addName=request.getParameter("updatedName");
                     addPrice=request.getParameter("updatedPrice");
                     item.setItemName(addName);
                     item.setPrice(Float.parseFloat(addPrice));
                    idb.update(item, oldItemName);
                }
                else
                {
                    idb.insert(item);
                }
                
                session.setAttribute("oldItemName",null);
                   request.setAttribute("updatedName",null);
               request.setAttribute("updatedPrice",null);
            
            }
             if(method.equals("edit"))
            {
                int editCategory= Integer. parseInt(request.getParameter("editCategory"));
               String editName=request.getParameter("editItemName");
               String editPrice=request.getParameter("editPrice");
               float price=Float.parseFloat(editPrice);
               String oldItemName=request.getParameter("oldItemName");
               Item item=idb.get(email, editName);
              session.setAttribute("editItemName",editName);
               session.setAttribute("editPrice",editPrice);
               session.setAttribute("editCategory",editCategory);
               session.setAttribute("oldItemName",oldItemName);
               request.setAttribute("updatedName",editName);
               request.setAttribute("updatedPrice",editPrice);
            }
              if(method.equals("delete"))
            {

                int deleteCategory=Integer. parseInt(request.getParameter("deletecategory"));
              String deleteName=request.getParameter("deleteItemName");
             Item item=idb.get(email, deleteName);
              
               idb.delete(item);
            }
            
            
          
            
           
            
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
}
