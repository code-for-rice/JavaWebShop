/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class DBContext {

    public static Connection getConnection() throws Exception {
        Connection cn = null;
        String IP = "localhost";
        String instanceName = "DESKTOP-8E67VD8\\MTUYEN";
        String port = "1433";
        String uid = "sa";
        String pwd = "12345";
        String db = "ProductIntro";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://" + IP + "\\" + instanceName + ":" + port
                + ";databasename=" + db + ";user=" + uid + ";password=" + pwd;
        Class.forName(driver);
        cn = DriverManager.getConnection(url);
        return cn;
    }

//    public static void main(String[] args) {
//        try {
//            Connection c = getConnection();
//            
//        } catch (Exception ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
