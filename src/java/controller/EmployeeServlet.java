/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.entity.Manufacturer;
import model.entity.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.entity.cequre.Role;
import model.entity.cequre.User;
import model.session.ManufacturerFacade;
import model.session.ProductFacade;
import model.session.CustomerFacade;
import model.session.RoleFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "EmployeeServlet", urlPatterns = {
    "/newProduct",
    "/createProduct",
    "/newManufacturer",
    "/createManufacturer",
    "/listReaders",
})
public class EmployeeServlet extends HttpServlet {

    @EJB private ManufacturerFacade manufacturerFacade;
    @EJB private ProductFacade productFacade;
    @EJB private CustomerFacade readerFacade;
    @EJB private RoleFacade roleFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
           HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "У вас нет достаночного права. Авторизуйтесь!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        User authUser = (User) session.getAttribute("user");
        if(authUser==null){
            request.setAttribute("info", "У вас нет достаночного права. Авторизуйтесь!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        Role roleEmployee = roleFacade.findRoleByName("EMPLOYEE");
        if(!authUser.getRoles().contains(roleEmployee)){
            request.setAttribute("info", "У вас нет достаночного права. Авторизуйтесь!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            
            case "/newProduct":
                request.setAttribute("listManufacturers", manufacturerFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/products/createProduct.jsp").forward(request, response);
                break;
            case "/createProduct":
                String name = request.getParameter("name");
                String expirationDate = request.getParameter("expirationDate");
                String quantity = request.getParameter("quantity");
                String[] manufacturers = request.getParameterValues("manufacturers");
                List<Manufacturer> listProductmanufacturers = new ArrayList<>();
                for (int i = 0; i < manufacturers.length; i++) {
                    listProductmanufacturers.add(manufacturerFacade.find(new Long(manufacturers[i])));
                }
                Product product = new Product();
                product.setName(name);
                product.setPublishedYear(Integer.parseInt(expirationDate));
                product.setQuantity(Integer.parseInt(quantity));
                product.setCount(product.getQuantity());
                product.setmanufacturers(listProductmanufacturers);
                productFacade.create(product);
                for (int i = 0; i < listProductmanufacturers.size(); i++) {
                    Manufacturer a = listProductmanufacturers.get(i);
                    a.getBooks().add(product);
                    manufacturerFacade.edit(a);
                }
                request.setAttribute("listProducts", productFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/products/listProducts.jsp").forward(request, response);
                break;
            case "/newManufacturer":
                request.getRequestDispatcher("/WEB-INF/manufacturers/createManufacturer.jsp").forward(request, response);
                break;
            case "/createManufacturer":
                String namemanufacturer = request.getParameter("namemanufacturer");
                String brand = request.getParameter("brand");
                Manufacturer newManufacturer = new Manufacturer();
                newManufacturer.setFirstname(namemanufacturer);
                newManufacturer.setLastname(brand);
                manufacturerFacade.create(newManufacturer);
                request.getRequestDispatcher("/newProduct").forward(request, response);
                break;
             
            case "/listReaders":
                request.setAttribute("listReaders", readerFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/customers/listReaders.jsp").forward(request, response);
                break;    
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
