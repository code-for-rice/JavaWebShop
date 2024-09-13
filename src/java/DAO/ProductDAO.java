/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Products;
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
public class ProductDAO implements Accessible<Products> {

    private Connection conn;

    public ProductDAO() throws ClassNotFoundException, SQLException, Exception {
        this.conn = DBContext.getConnection();
    }

    private static final String LIST_ALL = "SELECT * FROM products";
    private static final String LIST_BY_PRODUCT_ID = "SELECT * FROM products where productId = ?";
    private static final String LIST_BY_CATEGORY_ID = "SELECT * FROM products WHERE typeId = ?";

    private static final String INSERT_PRODUCT = "INSERT INTO [dbo].[products]\n"
            + "           ([productId]\n"
            + "           ,[productName]\n"
            + "           ,[productImage]\n"
            + "           ,[brief]\n"
            + "           ,[typeId]\n"
            + "           ,[account]\n"
            + "           ,[unit]\n"
            + "           ,[price]\n"
            + "           ,[discount])\n"
            + "     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE [dbo].[products]\n"
            + "   SET [productName] = ?\n"
            + "      ,[productImage] = ?\n"
            + "      ,[brief] = ?\n"
            + "      ,[typeId] = ?\n"
            + "      ,[account] = ?\n"
            + "      ,[unit] = ?\n"
            + "      ,[price] = ?\n"
            + "      ,[discount] = ?\n"
            + " WHERE productId = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM [dbo].[products]\n"
            + "       WHERE productId = ?";

    @Override
    public int insertRec(Products obj) {
        try (PreparedStatement st = this.conn.prepareStatement(INSERT_PRODUCT)) {
            st.setString(1, obj.getProductId());
            st.setString(2, obj.getProductName());
            st.setString(3, obj.getProductImage());
            st.setString(4, obj.getBrief());
            st.setInt(5, obj.getTypeId().getTypeId());
            st.setString(6, obj.getAccount().getAccount());
            st.setString(7, obj.getUnit());
            st.setInt(8, obj.getPrice());
            st.setInt(9, obj.getDiscount());
            return st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateRec(Products obj) {
        int update = 0;
        try {
            PreparedStatement st = this.conn.prepareStatement(UPDATE_PRODUCT);
            st.setString(1, obj.getProductName());
            st.setString(2, obj.getProductImage());
            st.setString(3, obj.getBrief());
            st.setInt(4, obj.getTypeId().getTypeId());
            st.setString(5, obj.getAccount().getAccount());
            st.setString(6, obj.getUnit());
            st.setDouble(7, obj.getPrice());
            st.setInt(8, obj.getDiscount());
            st.setString(9, obj.getProductId());
            update = st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
    }

    @Override
    public int deleteRec(Products obj) {
        try (PreparedStatement st = this.conn.prepareStatement(DELETE_PRODUCT)) {
            st.setString(1, obj.getProductId());
            return st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public Products getObjectById(String productId) {
        try (PreparedStatement st = this.conn.prepareStatement(LIST_BY_PRODUCT_ID)) {
            st.setString(1, productId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Products p = new Products();
                p.setProductId(rs.getString("productId"));
                p.setProductName(rs.getString("productName"));
                p.setProductImage(rs.getString("productImage"));
                p.setBrief(rs.getString("brief"));
                p.setPostedDate(rs.getDate("postedDate"));
                try {
                    // Assuming getCategoryById and getAccountById methods exist to fetch Category and Account
                    p.setTypeId(new CategoryDAO().getObjectById(rs.getInt("typeId")));
                    p.setAccount(new AccountsDAO().getObjectById(rs.getString("account")));
                } catch (Exception ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                p.setUnit(rs.getString("unit"));
                p.setPrice(rs.getInt("price"));
                p.setDiscount(rs.getInt("discount"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Products> listByCategory(int categoryId) {
        List<Products> list = new ArrayList<>();
        try (PreparedStatement st = this.conn.prepareStatement(LIST_BY_CATEGORY_ID)) {
            st.setInt(1, categoryId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductId(rs.getString("productId"));
                p.setProductName(rs.getString("productName"));
                p.setProductImage(rs.getString("productImage"));
                p.setBrief(rs.getString("brief"));
                p.setPostedDate(rs.getDate("postedDate"));
                try {
                    p.setTypeId(new CategoryDAO().getObjectById(rs.getInt("typeId")));
                    p.setAccount(new AccountsDAO().getObjectById(rs.getString("account")));
                } catch (Exception ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                p.setUnit(rs.getString("unit"));
                p.setPrice(rs.getInt("price"));
                p.setDiscount(rs.getInt("discount"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Products> listAll() {
        List<Products> list = new ArrayList<>();
        try {
            String sql = LIST_ALL;
            PreparedStatement st = this.conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductId(rs.getString("productId"));
                p.setProductName(rs.getString("productName"));
                p.setProductImage(rs.getString("productImage"));
                p.setBrief(rs.getString("brief"));
                p.setPostedDate(rs.getDate("postedDate"));
                try {
                    p.setTypeId(new CategoryDAO().getObjectById(rs.getInt("typeId")));
                    p.setAccount(new AccountsDAO().getObjectById(rs.getString("account")));
                    p.setUnit(rs.getString("unit"));
                    p.setPrice(rs.getInt("price"));
                    p.setDiscount(rs.getInt("discount"));
                } catch (Exception ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
