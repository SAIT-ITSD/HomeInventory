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
import models.Category;
import models.User;

/**
 *
 * @author 828200
 */
public class CategoryDB {
    public List<Category> getAll() throws Exception {
        List<Category> categorys = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM category";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int categoryId = rs.getInt(1);
                String categoryName = rs.getString(2);
                Category category = new Category(categoryId, categoryName);
                categorys.add(category);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return categorys;
    }

    public Category get(int categoryId) throws Exception {
        Category category = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM category WHERE category_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            if (rs.next()) {
                String categoryName = rs.getString(2);
                category = new Category(categoryId,categoryName);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return category;
    }
    public void update(int categoryId,String categoryName) throws Exception {
        Category category = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "UPDATE category SET category_name=? WHERE category_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, categoryName);
            ps.setInt(2, categoryId);
             ps.executeUpdate();
            
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
       
    }
   public void insert(int categoryId,String categoryName) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO category (category_id,category_name) VALUES (?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ps.setString(2, categoryName);
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
   
}
