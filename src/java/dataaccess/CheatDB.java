/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.Cheat;

/**
 *
 * @author 828200
 */
public class CheatDB {
     public Cheat get(String cheatEmail) throws Exception {
        Cheat cheat=null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cheat where cheat_email=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cheatEmail);
             
            rs = ps.executeQuery();
            if (rs.next()) {
                String cheatId = rs.getString(1);
                String cheatPassword=rs.getString(3);
                
                cheat=new Cheat(cheatId,cheatEmail,cheatPassword);
            }
        }
        catch(Exception e){e=e;} finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return cheat;
    }
 public Cheat getById(String cheatId) throws Exception {
        Cheat cheat=null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cheat where cheat_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cheatId);
             
            rs = ps.executeQuery();
            if (rs.next()) 
            {
                String cheatEmail = rs.getString(2);
                String cheatPassword=rs.getString(3);
                
                cheat=new Cheat(cheatId,cheatEmail,cheatPassword);
            }
        }
        catch(Exception e){e=e;} finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return cheat;
    }

    public void insert(String cheatId,String cheatEmail,String cheatPassword) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO cheat (cheat_id,cheat_email,cheat_password) VALUES (?, ?,?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cheatId);
            ps.setString(2, cheatEmail);
            ps.setString(3, cheatPassword);
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


    public void delete(String cheatId) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM cheat WHERE cheat_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cheatId);
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
