/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import DAO.ProductDAO;
import DTO.Products;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    private static final String LIST_JSP = "view/Public_pages/listProduct.jsp";
    private static final String UPDATE_JSP = "view/Private_pages/updateProduct.jsp";
    private static final String UPDATE = "/UpdateProduct";
    private static final String ADD_JSP = "view/Private_pages/addProduct.jsp";
    private static final String ADD_SERVLET = "/AddProduct";
    private static final String LIST = "ProductController?action=list";

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            String url = "";
            String action = request.getParameter("action");
            String productId = null;
            Products p = null;
            
            switch (action) {
                case "add":
                    url = ADD_SERVLET;
                    break;
                case "update":
                    productId = request.getParameter("productId");
                    {
                        try {
                            p = new ProductDAO().getObjectById(productId);
                            request.setAttribute("productObj", p);
                            url = UPDATE;
                        } catch (SQLException ex) {
                            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    break;
                case "delete":
                    productId = request.getParameter("productId");
                    ProductDAO pdao = new ProductDAO();
                    p = pdao.getObjectById(productId);
                    pdao.deleteRec(p);
                    url = LIST;
                    break;
                case "list":
                    try {
                        List<Products> list = new ProductDAO().listAll();
                        request.setAttribute("listProduct", list);
                        url = LIST_JSP;
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
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
