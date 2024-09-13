/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package category;

import DAO.AccountsDAO;
import DAO.CategoryDAO;
import DTO.Accounts;
import DTO.Category;
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
@WebServlet(name = "CategoryController", urlPatterns = {"/CategoryController"})
public class CategoryController extends HttpServlet {

    private static final String LIST_CATEGORY = "view/Private_pages/listCategory.jsp";
    private static final String LIST = "CategoryController?action=list";
    private static final String ADD = "CategoryController?action=add";
    private static final String ADD_CATEGORY = "view/Private_pages/addCategory.jsp";
    private static final String UPDATE_CATEGORY = "view/Private_pages/updateCategory.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String url = null;
        try {
            String action = request.getParameter("action");
            HttpSession session = request.getSession();

            int typeId;
            String categoryName;
            String memo;
            Category c;
            CategoryDAO cdao = new CategoryDAO();

            switch (action) {

                case "list":
                    List<Category> list = cdao.listAll();
                    request.setAttribute("dsCate", list);
                    url = LIST_CATEGORY;
                    break;

                case "add":
                    url = ADD_CATEGORY;
                    break;

                case "update":
                    try {
                        c = cdao.getObjectById(request.getParameter("typeId"));
                        request.setAttribute("categoryObj", c);
                        url = UPDATE_CATEGORY;
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
//
//                case "addAccount":
//                    url = ADD_JSP;
//                    break;
                case "delete":
                    String typeIdString = request.getParameter("typeId");

                    c = cdao.getObjectById(typeIdString);
                    cdao.deleteRec(c);
                    url = LIST;
                    break;
                default:
                //url = DASHBOARD;

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
