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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class ProductControllerV2
 */
@WebServlet(name = "ProductControllerV2", urlPatterns = {"/ProductControllerV2"})
public class ProductControllerV2 extends HttpServlet {

    private static final String LIST_JSP = "view/Public_pages/listProduct.jsp";
    private static final Logger LOGGER = Logger.getLogger(ProductControllerV2.class.getName());
    private static final String LIST = "CategoryController?action=list";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        String productId;
        String productName;
        String filename = null;
        String brief;
        Date postedDate = null;
        String categoryName;
        String account;
        String unit;
        int price;
        int discount;

        Products pInfor;
        ProductDAO dao;
        try {
            dao = new ProductDAO();
            if (action == null || action.equals("")) {
                request.setAttribute("productObj", dao.listAll());
                request.getRequestDispatcher(LIST_JSP).forward(request, response);
                return;
            }
            switch (action) {
                case "AddOrEdit":
                    productId = request.getParameter("productId");
                    pInfor = dao.getObjectById(productId);
                    if (pInfor == null) {
                        request.setAttribute("productObj", new Products("", "", "", "", new CategoryDAO().getCategoryByCategoryName(""), new AccountsDAO().getObjectById(""), "", 0, 0));
                    } else {
                        request.setAttribute("productObj", pInfor);
                    }
                    request.setAttribute("ACTION", "SaveOrUpdate");
                    request.getRequestDispatcher("view/Private_pages/updateProduct.jsp").forward(request, response);
                    break;
                case "SaveOrUpdate":
                    productId = request.getParameter("productId");
                    productName = request.getParameter("productName");
                    filename = request.getParameter("productImage");
                    brief = request.getParameter("brief");
                    postedDate = Date.valueOf(LocalDate.now());
//                    try {
//                        String postedDateString = request.getParameter("postedDate");
//                        LOGGER.log(Level.INFO, "Posted Date: {0}", postedDateString);
//                        if (postedDateString == null || postedDateString.isEmpty()) {
//                            throw new IllegalArgumentException("Posted Date is required.");
//                        }
//                        postedDate = validateAndConvertDate(postedDateString);
//                    } catch (IllegalArgumentException e) {
//                        LOGGER.log(Level.WARNING, "Date parsing error: {0}", e.getMessage());
//                        request.setAttribute("errorMessage", e.getMessage());
//                        request.getRequestDispatcher("view/Private_pages/updateProduct.jsp").forward(request, response);
//                        return;
//                    }

                    categoryName = request.getParameter("categoryName");
                    account = request.getParameter("account");
                    unit = request.getParameter("unit");
                        price = Integer.parseInt(request.getParameter("price"));
                    discount = Integer.parseInt(request.getParameter("discount"));

                    pInfor = dao.getObjectById(productId);
                    if (pInfor == null) { // add product
                        Products pnew = new Products(productId, productName, brief, postedDate, new CategoryDAO().getCategoryByCategoryName(categoryName), new AccountsDAO().getObjectById(account), unit, price, discount);
                        dao.insertRec(pnew);
                    } else { // update product
                        filename = null;
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
                        productId = request.getParameter("productId");
                        productName = request.getParameter("productName");
                        brief = request.getParameter("brief");
                        postedDate = Date.valueOf(LocalDate.now());
                        try {
                            categoryName = request.getParameter("categoryName");
                            String priceString = request.getParameter("price");
                            price = Integer.parseInt(priceString);
                            String discountString = request.getParameter("discount");
                            discount = Integer.parseInt(discountString);
                            account = request.getParameter("account");
                            unit = request.getParameter("unit");
                            Accounts a = new AccountsDAO().getObjectById(account);
                            Category c = new CategoryDAO().getCategoryByCategoryName(categoryName);
                            ProductDAO pdao = new ProductDAO();
                            Products p = pdao.getObjectById(productId);
                            Products pnew = new Products(productId, productName, filename, brief, c, a, unit, price, discount);
                            if (filename == null || filename.equals("")) {
                                pnew.setProductImage(p.getProductImage());
                            }
                            pdao.updateRec(pnew);
                            request.getRequestDispatcher(LIST).forward(request, response);
                        } catch (SQLException ex) {
                            Logger.getLogger(UpdateProduct.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(UpdateProduct.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
        
                    break;
                case "delete":
                    productId = request.getParameter("productId");
                    ProductDAO pdao = new ProductDAO();
                    pInfor = pdao.getObjectById(productId);
                    pdao.deleteRec(pInfor);
                    request.setAttribute("productObj", dao.listAll());
                    request.getRequestDispatcher("ProductController?action=list").forward(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "SQL Exception", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "General Exception", ex);
        }
    }

//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        String action = request.getParameter("action");
//
//        String productId;
//        String productName;
//        String filename = null;
//        String brief;
//        Date postedDate = null;
//        String categoryName;
//        String account;
//        String unit;
//        int price;
//        int discount;
//
//        Products pInfor;
//        ProductDAO dao;
//        try {
//            dao = new ProductDAO();
//            if (action == null || action.equals("")) {
//                request.setAttribute("productObj", dao.listAll());
//                request.getRequestDispatcher(LIST_JSP).forward(request, response);
//                return;
//            }
//            switch (action) {
//                case "AddOrEdit":
//                    productId = request.getParameter("productId");
//                    pInfor = dao.getObjectById(productId);
//                    if (pInfor == null) {
//                        request.setAttribute("productObj", new Products("", "", "", "", null, new CategoryDAO().getCategoryByCategoryName(""), new AccountsDAO().getObjectById(""), "", 0, 0));
//                    } else {
//                        request.setAttribute("productObj", pInfor);
//                    }
//                    request.setAttribute("ACTION", "SaveOrUpdate");
//                    request.getRequestDispatcher("view/Private_pages/updateProduct.jsp").forward(request, response);
//                    break;
//                case "SaveOrUpdate":
////                    try {
////                        DiskFileItemFactory factory = new DiskFileItemFactory();
////                        ServletContext servletContext = this.getServletConfig().getServletContext();
////                        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
////                        factory.setRepository(repository);
////                        ServletFileUpload upload = new ServletFileUpload(factory);
////                        List<FileItem> items = upload.parseRequest(request);
////
////                        Iterator<FileItem> iter = items.iterator();
////                        HashMap<String, String> fields = new HashMap<>();
////                        while (iter.hasNext()) {
////                            FileItem item = iter.next();
////                            if (item.isFormField()) {
////                                fields.put(item.getFieldName(), item.getString());
////                                String name = item.getFieldName();
////                                String value = item.getString();
////                                System.out.println("name:" + name);
////                                System.out.println("value:" + value);
////                            } else {
////                                filename = item.getName();
////                                if (filename == null && filename.equals("")) {
////                                    break;
////                                } else {
////                                    Path path = Paths.get(filename);
////                                    String storePath = servletContext.getRealPath("/images/sanPham");
////                                    File uploadFile = new File(storePath + "/" + path.getFileName());
////                                    item.write(uploadFile);
////                                }
////                            }
////                        }
////
////                        productId = fields.get("productId");
////                        productName = fields.get("productName");
////                        brief = fields.get("brief");
////                        try {
////                            postedDate = validateAndConvertDate(fields.get("postedDate"));
////                        } catch (IllegalArgumentException e) {
////                            request.setAttribute("errorMessage", "Invalid date format for Posted Date. Please use yyyy-MM-dd.");
////                            request.getRequestDispatcher("view/Private_pages/updateProduct.jsp").forward(request, response);
////                            return;
////                        }
////                        categoryName = fields.get("categoryName");
////                        account = fields.get("account");
////                        unit = fields.get("unit");
////                        price = Integer.parseInt(fields.get("price"));
////                        discount = Integer.parseInt(fields.get("discount"));
//                        productId = request.getParameter("productId");
//                        productName = request.getParameter("productName");
//                        filename = request.getParameter("productImage");
//                        brief = request.getParameter("brief");
//                        
//                        try {
//                            postedDate = validateAndConvertDate(request.getParameter("postedDate"));
//                        } catch (IllegalArgumentException e) {
//                            request.setAttribute("errorMessage", "Invalid date format for Posted Date. Please use yyyy-MM-dd.");
//                            request.getRequestDispatcher("view/Private_pages/updateProduct.jsp").forward(request, response);
//                            return;
//                        }
//                        
//                        categoryName = request.getParameter("categoryName");
//                        account = request.getParameter("account");
//                        unit = request.getParameter("unit");
//                        price = Integer.parseInt(request.getParameter("price"));
//                        discount = Integer.parseInt(request.getParameter("discount"));
//                        
//                        pInfor = dao.getObjectById(productId);
//                        if (pInfor == null) { // add product
//                            //Products pnew = new Products(productId, productName, pInfor.getProductImage(), brief, postedDate, new CategoryDAO().getCategoryByCategoryName(categoryName), new AccountsDAO().getObjectById(account), unit, price, discount);
//                            Products pnew = new Products(productId, productName, brief, postedDate, new CategoryDAO().getCategoryByCategoryName(categoryName), new AccountsDAO().getObjectById(account), unit, price, discount);
//                            dao.insertRec(pnew);
//                        } else { // update product
//                            pInfor.setProductId(productId);
//                            pInfor.setProductName(productName);
//                            if (filename != null && !filename.equals("")) {
//                                pInfor.setProductImage("/images/sanPham/"+filename);
//                            } else {
//                                pInfor.setProductImage(pInfor.getProductImage());
//                            }
//                            pInfor.setBrief(brief);
//                            pInfor.setPostedDate(postedDate);
//                            pInfor.setTypeId(new CategoryDAO().getCategoryByCategoryName(categoryName));
//                            pInfor.setAccount(new AccountsDAO().getObjectById(account));
//                            pInfor.setUnit(unit);
//                            pInfor.setPrice(price);
//                            pInfor.setDiscount(discount);
//                            dao.updateRec(pInfor);
//                        }
////                    } catch (Exception e) {
////                        LOGGER.log(Level.SEVERE, "Error processing SaveOrUpdate action", e);
////                        request.setAttribute("errorMessage", "An error occurred while processing the request. Please try again.");
////                    }
//                    request.setAttribute("listProduct", dao.listAll());
//                    request.getRequestDispatcher(LIST_JSP).forward(request, response);
//                    break;
//                case "delete":
//                    productId = request.getParameter("productId");
//                    ProductDAO pdao = new ProductDAO();
//                    pInfor = pdao.getObjectById(productId);
//                    pdao.deleteRec(pInfor);
//                    request.setAttribute("productObj", dao.listAll());
//                request.getRequestDispatcher(LIST_JSP).forward(request, response);
//                    break;
//                default:
////                    request.setAttribute("listProduct", dao.listAll());
////                    request.getRequestDispatcher(LIST_JSP).forward(request, response);
//                    break;
//            }
//        } catch (SQLException ex) {
//            LOGGER.log(Level.SEVERE, "SQL Exception", ex);
//        } catch (Exception ex) {
//            LOGGER.log(Level.SEVERE, "General Exception", ex);
//        }
//    }
    private Date validateAndConvertDate(String dateString) throws IllegalArgumentException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            java.util.Date utilDate = sdf.parse(dateString);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
