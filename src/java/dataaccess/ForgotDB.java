/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Forgot;
import models.Item;

/**
 *
 * @author 828200
 */
public class ForgotDB {
   
    public Forgot get(String forgotId) throws Exception {
        Forgot forgot=null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM forgot where forgot_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, forgotId);
             
            rs = ps.executeQuery();
            if (rs.next()) {
                String forgotID = rs.getString(1);
                String forgotEmail=rs.getString(2);
                
                
                forgot=new Forgot(forgotId,forgotEmail);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return forgot;
    }

    public void insert(String forgotId,String forgotEmail) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO forgot (forgot_id,forgot_email) VALUES (?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, forgotId);
            ps.setString(2, forgotEmail);
            ps.executeUpdate();
        } 
        catch(Exception e)
        {
            e=e;
        }
        finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }


    public void delete(String forgotId) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM forgot WHERE forgot_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, forgotId);
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
