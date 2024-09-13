/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Accessible;
import DTO.Accounts;
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
public class AccountsDAO implements Accessible<Accounts> {

    private Connection conn;

    public AccountsDAO() throws ClassNotFoundException, SQLException, Exception {
        this.conn = new DBContext().getConnection();
    }

    @Override
    public int insertRec(Accounts obj) {
        int kq = 0;
        try {
            //--1. tạo mẫu sqlString (Tương ứng với lệnh muốn chạy trên SQL Server)
            String sqlString = "INSERT INTO accounts (account, pass, lastName, firstName, birthday, "
                    + "gender, phone, isUse, roleInSystem) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            //2. Tạo đối tượng prepared statement dựa vào đối tượng Connection
            PreparedStatement cmd = this.conn.prepareStatement(sqlString);
            //3. Truyền giá trị vào mẫu SQL String ở trên
            cmd.setString(1, obj.getAccount());
            cmd.setString(2, obj.getPass());
            cmd.setString(3, obj.getLastName());
            cmd.setString(4, obj.getFirstName());
            cmd.setDate(5, obj.getBirthday());
            cmd.setBoolean(6, obj.isGender());
            cmd.setString(7, obj.getPhone());
            cmd.setBoolean(8, obj.isIsUse());
            cmd.setInt(9, obj.getRoleInSystem());
            //4. Gọi thi hành lệnh
            kq = cmd.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    @Override
    public int updateRec(Accounts obj) {
        int re = 0;
        String sqlString = "UPDATE [dbo].[accounts]\n"
                + "   SET [pass] = ?\n"
                + "      ,[lastName] = ?\n"
                + "      ,[firstName] = ?\n"
                + "      ,[birthday] = ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[isUse] = ?\n"
                + "      ,[roleInSystem] = ?\n"
                + " WHERE account = ?";
        try {
            PreparedStatement st = this.conn.prepareStatement(sqlString);
            st.setString(1, obj.getPass());
            st.setString(2, obj.getLastName());
            st.setString(3, obj.getFirstName());
            st.setDate(4, obj.getBirthday());
            st.setBoolean(5, obj.isGender());
            st.setString(6, obj.getPhone());
            st.setBoolean(7, obj.isIsUse());
            st.setInt(8, obj.getRoleInSystem());
            st.setString(9, obj.getAccount());
            re = st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }

    @Override
    public int deleteRec(Accounts obj) {
        int re = 0;
        String sqlString = "DELETE FROM accounts WHERE account = ?";
        try {
            PreparedStatement st = this.conn.prepareStatement(sqlString);
            st.setString(1, obj.getAccount());
            re = st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }

    @Override
    public Accounts getObjectById(String id) {
        Accounts a = null;
        try {
//            String sql = "SELECT * FROM Account WHERE acc = '" + acc.trim()+"' AND pass = '" + pass.trim();
            String sql = "SELECT * FROM accounts WHERE account = ?";

            PreparedStatement st = this.conn.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = new Accounts();
                //---4.2. Lay thong tin tu ResultSet, va gan cho TaiKhoan object
                a.setAccount(rs.getString("account"));
                a.setPass(rs.getString("pass"));
                a.setLastName(rs.getString("lastName"));
                a.setFirstName(rs.getString("firstName"));
                a.setBirthday(rs.getDate("birthday"));
                a.setGender(rs.getBoolean("gender"));
                a.setPhone(rs.getString("phone"));
                a.setIsUse(rs.getBoolean("isUse"));
                a.setRoleInSystem(rs.getInt("roleInSystem"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    @Override
    public List<Accounts> listAll() {
        List<Accounts> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM accounts";
            PreparedStatement st = DBContext.getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Accounts a = new Accounts();
                a.setAccount(rs.getString("account"));
                a.setPass(rs.getString("pass"));
                a.setLastName(rs.getString("lastName"));
                a.setFirstName(rs.getString("firstName"));
                a.setBirthday(rs.getDate("birthday"));
                a.setGender(rs.getBoolean("gender"));
                a.setPhone(rs.getString("phone"));
                a.setIsUse(rs.getBoolean("isUse"));
                a.setRoleInSystem(rs.getInt("roleInSystem"));
                list.add(a);
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Accounts getObjectById(String acc, String pass) {
        Accounts a = null;
        try {
//            String sql = "SELECT * FROM Account WHERE acc = '" + acc.trim()+"' AND pass = '" + pass.trim();
            String sql = "SELECT * FROM accounts WHERE account = ? AND pass = ?";

            PreparedStatement st = this.conn.prepareStatement(sql);
            st.setString(1, acc);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = new Accounts();
                //---4.2. Lay thong tin tu ResultSet, va gan cho TaiKhoan object
                a.setAccount(rs.getString("account"));
                a.setPass(rs.getString("pass"));
                a.setLastName(rs.getString("lastName"));
                a.setFirstName(rs.getString("firstName"));
                a.setBirthday(rs.getDate("birthday"));
                a.setGender(rs.getBoolean("gender"));
                a.setPhone(rs.getString("phone"));
                a.setIsUse(rs.getBoolean("isUse"));
                a.setRoleInSystem(rs.getInt("roleInSystem"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public int updateRecIsUse(Accounts obj) {
        int re = 0;
        String sqlString = "UPDATE [dbo].[accounts]\n"
                + "   SET [isUse] = ?\n"
                + " WHERE account = ?";
        try {
            PreparedStatement st = this.conn.prepareStatement(sqlString);
            st.setBoolean(1, obj.isIsUse());
            st.setString(2, obj.getAccount());
            re = st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
}
