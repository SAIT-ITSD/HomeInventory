/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import dataaccess.UserDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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

public class AdminServlet extends HttpServlet {

   
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
        HttpSession session = request.getSession();
        String email=(String)session.getAttribute("email");
        UserDB udb=new UserDB();
         
         try {
           List<User> users=udb.getAll();
        request.setAttribute("users",users);  
        User user=udb.get(email);
        String username=user.getFirstName()+" "+user.getLastName();
                    request.setAttribute("username",username);
           CategoryDB cdb=new CategoryDB();
        List<Category> categorys=cdb.getAll();
        request.setAttribute("categorys",categorys);
        if(user.getRole()==1)
        {
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);request.setAttribute("username",null);
           return;
        }
        else if(user==null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);request.setAttribute("username",null);
           return;
        }
        else
        {
             ItemDB idb=new ItemDB();
             List<Item> items=idb.getAll(email);
         request.setAttribute("items",items);
                        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                    .forward(request,response);
                       request.setAttribute("username",null); 
           return;
        }
         } catch (Exception ex) {
             Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
       getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);request.setAttribute("username",null);
           return;
        
          
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {
        
            HttpSession session = request.getSession();
        String email=(String)session.getAttribute("email");
        String method=(String)request.getParameter("method");
        String message=null;
        UserDB udb=new UserDB();
         try {
        User user=udb.get(email);
        String username=user.getFirstName()+" "+user.getLastName();
                    request.setAttribute("username",username);
        List<User> users=udb.getAll();
        request.setAttribute("users",users);
        CategoryDB cdb=new CategoryDB();
        List<Category> categorys=cdb.getAll();
        request.setAttribute("categorys",categorys);
       
        if(user.getRole()==1&&method.equals("add"))
        {
           
            String newEmail=request.getParameter("newEmail");
            String newPassword=request.getParameter("newPassword");
            String newFirstName=request.getParameter("newFirstName");
            String newLastName=request.getParameter("newLastName");
            int newRole=Integer.parseInt(request.getParameter("newRole"));
            int active;
            String newActive=request.getParameter("newActive");
            if(newActive.equals("on"))
            {
                active=1;
            }
            else 
            {
                active=0;
            }
            User newUser=new User(newEmail,active,newFirstName,newLastName,newPassword,newRole);
            for(User us:users)
            {
                if(us.getEmail().equals(newEmail))
                {
                    try{ItemDB idb=new ItemDB();
                    List<Item> items=idb.getAll(us.getEmail());
                    for(Item thisItem:items)
                    {
                        idb.delete(thisItem.getItemID());
                    }
                    }
                    catch(Exception e)
                    {
                    }
                    
                    udb.delete(us);
                }
            }
             if(email.equals(newEmail))
            {
                newUser.setRole(1);
            }
            udb.insert(newUser);
            
             request.setAttribute("updatedEmail",null);
             request.setAttribute("updatedPassword",null);
             request.setAttribute("updatedFirstName",null);
             request.setAttribute("updatedLastName",null);
             users=udb.getAll();request.setAttribute("users",users);
             message="account succesfully added/edited.";
             request.setAttribute("adminMsg",message);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);request.setAttribute("username",null);
            request.setAttribute("adminMsg",null);
           return;
        }
        else if(user.getRole()==1&&method.equals("delete"))
        {
            String thisEmail=request.getParameter("thisEmail");
            User thisUser=udb.get(thisEmail);
            if(!email.equals(thisUser.getEmail()))
            {
                if(thisUser!=null)
                {
                      try{ItemDB idb=new ItemDB();
                        List<Item> items=idb.getAll(thisEmail);
                        for(Item thisItem:items)
                        {
                            idb.delete(thisItem.getItemID());
                            message="user succesfully added/edited.";
                        }
                        }
                        catch(Exception e)
                        {
                        }
                    udb.delete(thisUser);
                }
            }
            users=udb.getAll();
            request.setAttribute("users",users);
              message="account has been succesfully deleted.";
             request.setAttribute("adminMsg",message);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);request.setAttribute("username",null);
            request.setAttribute("adminMsg",null);
           return;
        }
        else if(user.getRole()==1&&method.equals("edit"))
        {
            String thisEmail=request.getParameter("thisEmail");
            User thisUser=udb.get(thisEmail);
            String newEmail=thisUser.getEmail();
            String newPassword=thisUser.getPassword();
            String newFirstName=thisUser.getFirstName();
            String newLastName=thisUser.getLastName();
            
            request.setAttribute("updatedEmail",newEmail);
            request.setAttribute("updatedPassword",newPassword);
             request.setAttribute("updatedFirstName",newFirstName);
            request.setAttribute("updatedLastName",newLastName);
           
          
               message="account can now be edited.";
             request.setAttribute("adminMsg",message);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);request.setAttribute("username",null);
            request.setAttribute("adminMsg",null);
           return;
        }
        else if(user.getRole()==1&&method.equals("editCategory"))
        {
            String getId=request.getParameter("thisCategoryId");
             String getName=request.getParameter("thisCategory");
             session.setAttribute("newCategoryId",getId);
             session.setAttribute("newCategoryName",getName);
             message="category can now be edited.";
        }
        else if(user.getRole()==1&&method.equals("addCategory"))
        {
              int getId=Integer.parseInt(request.getParameter("newCategoryId"));
              String getName=request.getParameter("newCategoryName");
              int tisID=-1;
              int highest=categorys.size()+1;
              for(int i=0;i<categorys.size();i++)
              {
                  if(categorys.get(i).getCategoryId()==getId)
                  {
                      tisID=i;
                     
                  } 
              }
              if(tisID!=-1)
              {
                  cdb.update(tisID+1, getName);
              }
              else
              {
                  cdb.insert((highest), getName);
                  
              }
              categorys=cdb.getAll();
        request.setAttribute("categorys",categorys);
         message="category has been added/edited.";
        }
        else if(user.getRole()==1&&method.equals("txtList"))
        {
           
               List<String> list=udb.getQntyCount();
           
                //this new arraylist is to fix a bug where if no number is present the quantity instead was the email.
                List<List<String>> userDoc = new ArrayList<>();

                for(int i = 0; i < list.size(); i++)
                {
                    List<String> cells = new ArrayList<>();
                    String line=list.get(i);
                    int index=line.indexOf(",");
                    int index2=line.indexOf(" ");
                    String name=line.substring(0,index2);
                    String lName=line.substring(index2,index);
                    String quantity=line.substring(index+1);
                    int intQty=0;
                    try
                    {
                        intQty=Integer.parseInt(quantity); 
                    }
                    catch (Exception ex) 
                    {
                     intQty=0;
                    }
                    quantity=String.valueOf(intQty);
                    cells.add(lName);
                    cells.add(name);
                    cells.add(quantity);
                    userDoc.add(cells); 
                }
           
            //now onto insert each line into an xlsx file.
           
            String path = getServletContext().getRealPath("/WEB-INF/customers.csv");
           try
           { 
               FileOutputStream fos=new FileOutputStream(path);
                PrintWriter pw=new PrintWriter(fos);
                for(int i=0;i<userDoc.size();i++)
                {
                    List<String> cells = new ArrayList<>();
                    cells=userDoc.get(i);
                    pw.println(cells.get(0)+","+cells.get(1)+","+cells.get(2));
                }
               pw.close();
               fos.close();
           }
           catch(Exception e)
           {
               e=e;
           }
           
               message="customer.csv succesfully created/edited.";
             request.setAttribute("adminMsg",message);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);request.setAttribute("username",null);
            request.setAttribute("adminMsg",null);
           return;
              
        }
          else if(user==null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);request.setAttribute("username",null);
           return;
        }
        else
        {
             ItemDB idb=new ItemDB();
         
             List<Item> items=idb.getAll(email);
         request.setAttribute("items",items);
                        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
                    .forward(request,response);request.setAttribute("username",null);
                       request.setAttribute("username",null); 
           return;
        }
         } catch (Exception ex) {
             Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
          request.setAttribute("adminMsg",message);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request,response);request.setAttribute("username",null);
            request.setAttribute("adminMsg",null);
           return;
        
        
    }

}
