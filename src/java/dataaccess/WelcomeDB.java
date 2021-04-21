/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.Welcome;

/**
 *
 * @author 828200
 */
public class WelcomeDB {
      public Welcome get(String welcomeId) throws Exception {
        Welcome welcome=null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM welcome where welcome_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, welcomeId);
             
            rs = ps.executeQuery();
            if (rs.next()) {
                String welcomeEmail = rs.getString(2);
                
                
                welcome=new Welcome(welcomeId,welcomeEmail);
            }
        }
        catch(Exception e)
        {
            e=e;
        }
        finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return welcome;
    }

    public void insert(String welcomeId,String welcomeEmail) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO welcome (welcome_id,welcome_email) VALUES (?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, welcomeId);
            ps.setString(2, welcomeEmail);
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


    public void delete(String welcomeId) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM welcome WHERE welcome_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, welcomeId);
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
