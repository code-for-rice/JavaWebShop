/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBContext;

/**
 *
 * @author Dell
 */
public class CategoryDAO implements Accessible<Category> {

    private Connection conn;
    private static final String INSERT_CATEGORY = "INSERT INTO [dbo].[categories]\n"
            + "           ([categoryName]\n"
            + "           ,[memo])\n"
            + "     VALUES\n"
            + "           (?, ?)";
    private static final String UPDATE_CATEGORY = "UPDATE [dbo].[categories]\n"
            + "   SET [categoryName] = ?,\n"
            + "      [memo] = ? "
            + "WHERE typeId = ?";
    private static final String DELETE_CATEGORY = "DELETE FROM [dbo].[categories]\n"
            + "      WHERE typeId = ?";
    private static final String GET_CATEGORIES = "SELECT * FROM categories";
    private static final String GET_CATEGORIES_BY_TYPEID = GET_CATEGORIES + " WHERE typeID = ?";
    private static final String GET_CATEGORIES_BY_MAX_TYPEID = "SELECT * \n"
            + "FROM categories \n"
            + "WHERE typeId = (SELECT MAX(typeId) FROM categories)";
    private static final String GET_CATEGORIES_BY_CATEGORYNAME = GET_CATEGORIES + " WHERE categoryName = ?";

    public CategoryDAO() throws ClassNotFoundException, SQLException, Exception {
        this.conn = DBContext.getConnection();
    }

    public int getTypeIdOfLastCategory() {
        Category c = null;
        try {
            PreparedStatement st = this.conn.prepareStatement(GET_CATEGORIES_BY_MAX_TYPEID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                c = new Category();
                c.setTypeId(rs.getInt("typeId"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setMemo(rs.getString("memo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (c != null) ? c.getTypeId() : 0; // Return 0 if no categories found
    }
    

    

    public Category getCategoryByCategoryName(String categoryName) {
        Category c = null;
        try {
            PreparedStatement st = this.conn.prepareStatement(GET_CATEGORIES_BY_CATEGORYNAME);
            st.setString(1, categoryName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                c = new Category();
                c.setTypeId(rs.getInt("typeId"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setMemo(rs.getString("memo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;

    }

    @Override
    public int insertRec(Category obj) {
        int insert = 0;
        try {
            PreparedStatement st = this.conn.prepareStatement(INSERT_CATEGORY);
            st.setString(1, obj.getCategoryName());
            st.setString(2, obj.getMemo());
            insert = st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return insert;
    }

    @Override
    public int updateRec(Category obj) {
        int update = 0;
        try {
            PreparedStatement st = this.conn.prepareStatement(UPDATE_CATEGORY);
            st.setString(1, obj.getCategoryName());
            st.setString(2, obj.getMemo());
            st.setInt(3, obj.getTypeId());
            update = st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
    }

    @Override
    public int deleteRec(Category obj) {
        int re = 0;
        try {
            PreparedStatement st = this.conn.prepareStatement(DELETE_CATEGORY);
            st.setInt(1, obj.getTypeId());
            re = st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;

    }

    @Override
    public Category getObjectById(String typeIdString) {
        Category c = null;
        try {
            PreparedStatement st = this.conn.prepareStatement(GET_CATEGORIES_BY_TYPEID);
            int typeId = Integer.parseInt(typeIdString);
            st.setInt(1, typeId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                c = new Category();
                c.setTypeId(rs.getInt("typeId"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setMemo(rs.getString("memo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;

    }

    public Category getObjectById(int typeId) {
        Category c = null;
        try {
            PreparedStatement st = this.conn.prepareStatement(GET_CATEGORIES_BY_TYPEID);
            st.setInt(1, typeId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                c = new Category();
                c.setTypeId(rs.getInt("typeId"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setMemo(rs.getString("memo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;

    }
    
    @Override
    public List<Category> listAll() {
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement st = DBContext.getConnection().prepareStatement(GET_CATEGORIES);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setTypeId(rs.getInt("typeId"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setMemo(rs.getString("memo"));
                list.add(c);
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
