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
import models.Item;

/**
 *
 * @author 828200
 */
public class ItemDB {
    public List<Item> getAll(String owner) throws Exception {
        List<Item> items = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT category,item_name,price,owner FROM item where owner=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, owner);
            rs = ps.executeQuery();
            while (rs.next()) {
                
               int  category = rs.getInt(1);
                String itemName = rs.getString(2);
                float price = rs.getFloat(3);
                Item item = new Item(category,itemName,price,owner);
                items.add(item);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return items;
    }

    public Item get(String owner,String itemName) throws Exception {
        Item item = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT category,item_name,price,owner FROM item where owner=? and item_name=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, owner);
             ps.setString(2, itemName);
            rs = ps.executeQuery();
            if (rs.next()) {
                int  category = rs.getInt(1);
                float price = rs.getFloat(3);
                item = new Item(category,itemName,price,owner);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return item;
    }

    public void insert(Item item) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO item (category,item_name,price,owner) VALUES (?, ?, ?,?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getCategory());
            ps.setString(2, item.getItemName());
            ps.setFloat(3, item.getPrice());
            ps.setString(4, item.getOwner());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void update(Item item,String oldItemName) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE  category=? item_name=? price=? owner=? WHERE item_name=? and owner=?";
        
        try {
            ps = con.prepareStatement(sql);
             
            ps.setInt(1, item.getCategory());
            ps.setString(2, item.getItemName());
            ps.setFloat(3, item.getPrice());
            ps.setString(4, item.getOwner());
             ps.setString(5, oldItemName);
             ps.setString(6, item.getOwner());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void delete(Item item) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM item WHERE item_name=? and owner=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, item.getItemName());
            ps.setString(2, item.getOwner());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
