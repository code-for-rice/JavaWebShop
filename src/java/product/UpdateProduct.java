/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import DAO.AccountsDAO;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import DTO.Accounts;
import DTO.Category;
import DTO.Products;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Dell
 */
@WebServlet(name = "UpdateProduct", urlPatterns = {"/UpdateProduct"})
@MultipartConfig
public class UpdateProduct extends HttpServlet {

    private static final String LIST_PRODUCT = "ProductController?action=list";
    private static final String UPDATE_JSP = "view/Private_pages/updateProduct.jsp";
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
        request.getRequestDispatcher(UPDATE_JSP).forward(request, response);
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
        try {
            response.setContentType("text/html;charset=UTF-8");

            String filename = null;
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(new File("D:/FPTU/Ky_4/PRJ301/Workshop1/web"));
            ServletFileUpload fileUpload = new ServletFileUpload(factory);

            List<FileItem> fileItems = fileUpload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    if (fileItem.getFieldName().equals("productImage")) {
                        File file = new File("D:/FPTU/Ky_4/PRJ301/Workshop1/web/images/sanPham");
                        try {
                            fileItem.write(file);
                        } catch (Exception ex) {
                            Logger.getLogger(UpdateProduct.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        filename = request.getServletPath() + "/images/sanPham/" + fileItem.getName();
                    }
                }
            }
            String productId = request.getParameter("productId");
            String productName = request.getParameter("productName");
            String brief = request.getParameter("brief");
//            String postedDateString = request.getParameter("postedDate");
//            Date postedDate = Date.valueOf(postedDateString);
            int price, discount;
            try {
                String categoryName = request.getParameter("categoryName");
                String priceString = request.getParameter("price");
                price = Integer.parseInt(priceString);
                String discountString = request.getParameter("discount");
                discount = Integer.parseInt(discountString);
                String account = request.getParameter("account");
                String unit = request.getParameter("unit");
                Accounts a = new AccountsDAO().getObjectById(account);
                Category c = new CategoryDAO().getCategoryByCategoryName(categoryName);
                ProductDAO pdao = new ProductDAO();
                Products p = pdao.getObjectById(productId);
                Products pnew = new Products(productId, productName, filename, brief, c, a, unit, price, discount);
                if (filename == null || filename.equals("")) {
                    pnew.setProductImage(p.getProductImage());
                }
                pdao.updateRec(pnew);
                request.getRequestDispatcher(LIST_PRODUCT).forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(UpdateProduct.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(UpdateProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(UpdateProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
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
