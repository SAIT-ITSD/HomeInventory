/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.ForgotDB;
import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Forgot;
import models.User;
import services.AccountService;

/**
 *
 * @author 828200
 */
public class ForgotServlet extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dude=request.getParameter("dude");
        if(dude!=null)
        {
         getServletContext().getRequestDispatcher("/WEB-INF/confirmPassword.jsp")
                .forward(request,response);
         return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/forgotPassword.jsp")
                .forward(request,response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stage=(String)request.getParameter("stage");
        boolean passed=false;
        String email=request.getParameter("forgotEmail");
        String confirmation=request.getParameter("confirmation");
        String password=request.getParameter("gmailPassword");
        String path = getServletContext().getRealPath("/WEB-INF");
        String newPassword=request.getParameter("newPassword");
        UserDB udb=new UserDB();
        User user=null;
        try 
        {
            user=udb.get(email);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //we initialised stage,email and confirmation and initialised passed by fedault as false.
        if(stage.equals("get"))
        {
            if(user==null)
            {
                request.setAttribute("passwordMessage","there is no user with that email."); 
                getServletContext().getRequestDispatcher("/WEB-INF/forgotPassword.jsp")
                .forward(request,response);
                request.setAttribute("passwordMessage",null); 
                return;
                //simple return to page if email is incorrect
            }
            AccountService as=new AccountService();
            String url = request.getRequestURL().toString();
             url+="?dude="+"dude";
            as.forgot(email, password, path,url);
            request.setAttribute("passwordMessage","if you're email and password were entered correctly email has been sent with your passcode."); 
            getServletContext().getRequestDispatcher("/WEB-INF/forgotPassword.jsp")
                .forward(request,response);
            request.setAttribute("passwordMessage",null); 
            
            return;
        }
        //pass test for confirmation password
        ForgotDB fdb=new ForgotDB();
        Forgot forgot=null;
        try {
            forgot=fdb.get(confirmation);
        } catch (Exception ex) {
            Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(forgot!=null)
        {
            passed=true;
        }
        if(passed==true)
        {
          request.setAttribute("message","password succesfully changed."); 
            try 
            {
                String thisEmail=forgot.getForgotEmail();
                user=udb.get(thisEmail);
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            user.setPassword(newPassword);
            try {
                udb.update(user);
            } catch (Exception ex) {
                Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
          request.setAttribute("message",null);
            try 
            {
                fdb.delete(confirmation);
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(ForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          return;
        }
        request.setAttribute("passwordMessage","confirmation code is incorrect,try again.");  
         getServletContext().getRequestDispatcher("/WEB-INF/confirmPassword.jsp")
                .forward(request,response);
         request.setAttribute("passwordMessage",null); 
        //messahes have been configured correctly. 
    }


}
