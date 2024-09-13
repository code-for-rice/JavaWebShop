/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import DAO.AccountsDAO;
import DTO.Accounts;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dell
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private final String LOGIN = "/LoginServlet";
    private final String DASHBOARD = "view/Private_pages/dashboard.jsp";
    private final String LIST_JSP = "view/Private_pages/listAccount.jsp";
    private final String ERROR = "view/Other_pages/error.jsp";
    private final String ADD_JSP = "view/Private_pages/addNewAccount.jsp";
    private final String LIST = "MainController?action=list_acc";
    private final String UPDATE_JSP = "view/Private_pages/update.jsp";
    private final String HOME = "MainController?action=home";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            String account;
            String pass;
            String lastName;
            String firstName;
            String birthdayString;
            Date birthday;
            boolean gender;
            String phone;
            boolean isUse;
            int roleInSystem;
            Accounts a = null;
            AccountsDAO adao = new AccountsDAO();
            switch (action) {

                case "home":
                    url = DASHBOARD;
                    break;

                case "login":
                    account = request.getParameter("user");
                    pass = request.getParameter("pass");
                    a = null;

                    try {
                        a = new AccountsDAO().getObjectById(account, pass);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (a != null) {
                        session.setAttribute("u", a);
                        url = HOME;
                    } else {
                        session.setAttribute("errorMessage", "user or pass is wrong!! Please try again!!");
                        url = LOGIN;
                    }
                    break;
                case "logout":
                    //đăng xuất

                    session.invalidate();
                    url = LOGIN;
                    break;

                case "addAccountDoPost":
                    try {
                        account = request.getParameter("account");
                        pass = request.getParameter("pass");
                        lastName = request.getParameter("lastName");
                        firstName = request.getParameter("firstName");
                        birthdayString = request.getParameter("birthday");
                        birthday = Date.valueOf(birthdayString);
                        gender = Boolean.parseBoolean(request.getParameter("gender"));
                        phone = request.getParameter("phone");
                        isUse = request.getParameter("isUse") != null;
                        roleInSystem = Integer.parseInt(request.getParameter("roleInSystem"));

                        Accounts existingAccount = adao.getObjectById(account);
                        if (existingAccount != null) {
                            request.setAttribute("duplicateAcc", account + " is already in use!");
                            url = ADD_JSP;
                        } else {
                            Accounts newAccount = new Accounts(account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem);
                            adao.insertRec(newAccount);
                            if(session.getAttribute("u") != null)
                            response.sendRedirect(LIST);
                            else
                            request.getRequestDispatcher("view/Public_pages/login.jsp").forward(request, response);
                            return;
                        }

                    } catch (NumberFormatException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case "list_acc":
                    //show list
                    
                    List<Accounts> list = new AccountsDAO().listAll();
                    session.setAttribute("ds", list);
                    url = "ListServlet";
                    break;

                case "updateIsUse":

                    account = request.getParameter("account");
                    String isUse_raw = request.getParameter("isUse");
                    isUse = Boolean.valueOf(isUse_raw);
                    a = adao.getObjectById(account);
                    a.setIsUse(!isUse);
                    adao.updateRecIsUse(a);
                    url = LIST;
                    break;
                case "update":

                    account = request.getParameter("account");
                    a = adao.getObjectById(account);
                    request.setAttribute("accountObj", a);
                    url = UPDATE_JSP;
                    break;
                case "updateDoPost":

                    account = request.getParameter("account");
                    pass = request.getParameter("pass");
                    lastName = request.getParameter("lastName");
                    firstName = request.getParameter("firstName");
                    birthdayString = request.getParameter("birthday");
                    birthday = Date.valueOf(birthdayString);
                    gender = Boolean.parseBoolean(request.getParameter("gender"));
                    phone = request.getParameter("phone");
                    isUse = Boolean.parseBoolean(request.getParameter("isUse"));
                    roleInSystem = Integer.parseInt(request.getParameter("roleInSystem"));

                    a = new Accounts(account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem);
                    adao.updateRec(a);
                    session.setAttribute("updated", a);
                    
                    Accounts u = (Accounts)session.getAttribute("u");
                    
                    if (a.getAccount().equals(u.getAccount())) {
                        url = DASHBOARD;
                    } else {
                        url = LIST;
                    }
                    break;

                case "addAccount":
                    url = ADD_JSP;
                    break;
                case "delete":
                    account = request.getParameter("account");
                    a = adao.getObjectById(account);
                    adao.deleteRec(a);
                    url = LIST;
                    break;
                default:
                    url = DASHBOARD;

            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
