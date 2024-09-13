package product;

import DAO.AccountsDAO;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import DTO.Products;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Dell
 */
@WebServlet(name = "AddProduct", urlPatterns = {"/AddProduct"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddProduct extends HttpServlet {

    private static final String ADD_PRODUCT_JSP = "view/Private_pages/addProduct.jsp";
    private static final String LIST = "ProductController?action=list";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher(ADD_PRODUCT_JSP).forward(request, response);
    }

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "images" + File.separator + "sanPham";

    private String uploadImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //======================================================
        String nameImage = "";
        String b = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        String[] uploadStrings = b.split("\\\\build");
        String uploadPath = String.join("", uploadStrings);
        // Tạo thư mục nếu nó không tồn tại
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        // Lưu các file được upload vào thư mục images/sanPham
        int count = 1;
        for (Part part : request.getParts()) {
            if (part.getSubmittedFileName() != null) {
                nameImage = part.getSubmittedFileName();
                part.write(uploadPath + File.separator + part.getSubmittedFileName());
            }
        }
        return nameImage;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            String productId = request.getParameter("productId");
            String productName = request.getParameter("productName");
            String productImage = uploadImage(request, response);
            String brief = request.getParameter("brief");
            //String postedDate_raw = request.getParameter("postedDate");
            //Date postedDate = Date.valueOf(postedDate_raw);
            String categoryName = request.getParameter("categoryName");
            String account = request.getParameter("account");
            String unit = request.getParameter("unit");
            String price_raw = request.getParameter("price");
            int price = Integer.parseInt(price_raw);
            String discount_raw = request.getParameter("discount");
            int discount = Integer.parseInt(discount_raw);

            // Check if the product ID already exists
            Products existP = new ProductDAO().getObjectById(productId);
            if (existP != null) {
                request.setAttribute("duplicateProduct", "This product already exists");
                request.getRequestDispatcher(ADD_PRODUCT_JSP).forward(request, response);
                return;
            }

            // Create new product
            Products newProduct = new Products(
                    productId,
                    productName,
                    productImage,
                    brief,
                    new CategoryDAO().getCategoryByCategoryName(categoryName),
                    new AccountsDAO().getObjectById(account),
                    unit,
                    price,
                    discount
            );

            ProductDAO productDAO = new ProductDAO();
            productDAO.insertRec(newProduct);
            request.getRequestDispatcher(LIST).forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Database error: " + ex.getMessage());
            request.getRequestDispatcher(ADD_PRODUCT_JSP).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Error: " + ex.getMessage());
            request.getRequestDispatcher(ADD_PRODUCT_JSP).forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "AddProduct Servlet";
    }
}
