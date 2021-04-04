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
    public List<Item> getAll() throws Exception {
        List<Item> items = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM item";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                int itemId = rs.getInt(1);
               int  category = rs.getInt(2);
                String itemName = rs.getString(3);
                float price = rs.getFloat(4);
                String owner = rs.getString(5);
                Item item = new Item(itemId, category,itemName,price,owner);
                items.add(item);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return items;
    }

    public Item get(int itemId) throws Exception {
        Item item = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM item WHERE item_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, itemId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int  category = rs.getInt(2);
                String itemName = rs.getString(3);
                float price = rs.getFloat(4);
                String owner = rs.getString(5);
                item = new Item(itemId, category,itemName,price,owner);
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
        String sql = "INSERT INTO item (item_id,category,item_name,price,owner) VALUES (?, ?, ?,?,?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getItemId());
            ps.setInt(2, item.getCategory());
            ps.setString(3, item.getItemName());
            ps.setFloat(4, item.getPrice());
            ps.setString(5, item.getOwner());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void update(Item item) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE item_id=? category=? item_name=? price=? owner=? WHERE item_id=?";
        
        try {
            ps = con.prepareStatement(sql);
             
            ps.setInt(1, item.getCategory());
            ps.setString(2, item.getItemName());
            ps.setFloat(3, item.getPrice());
            ps.setString(4, item.getOwner());
            ps.setInt(5, item.getItemId());
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
        String sql = "DELETE FROM item WHERE item_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getItemId());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
