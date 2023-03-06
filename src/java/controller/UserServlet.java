/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.entity.Customer;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.entity.Product;
import model.entity.History;
import model.entity.cequre.Role;
import model.entity.cequre.User;
import model.session.ManufacturerFacade;
import model.session.ProductFacade;
import model.session.HistoryFacade;
import model.session.CustomerFacade;
import model.session.RoleFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "UserServlet", urlPatterns = {
    "/takeOnProduct",
    "/createHistory",
    "/updateHistory",
    "/listManufacturers"
    
    
})
public class UserServlet extends HttpServlet {

    @EJB private CustomerFacade readerFacade;
    @EJB private ProductFacade productFacade;
    @EJB private HistoryFacade historyFacade;
    @EJB private ManufacturerFacade manufacturerFacade;
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
        Role roleUser = roleFacade.findRoleByName("USER");
        if(!authUser.getRoles().contains(roleUser)){
            request.setAttribute("info", "У вас нет достаночного права. Авторизуйтесь!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            case "/takeOnProduct":
                //request.setAttribute("listReaders", readerFacade.findAll());
                request.setAttribute("listProducts", productFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/products/takeOnProduct.jsp").forward(request, response);
                break;
            
            case "/createHistory":
                String productId = request.getParameter("productId");
                //String readerId = request.getParameter("readerId");
                Product product = productFacade.find(Long.parseLong(productId));
                User user = (User) session.getAttribute("user");
                History history = new History();
                history.setProduct(product);
                history.setReader(user.getReader());
                history.setTakeOnBookDate(new GregorianCalendar().getTime());
                historyFacade.create(history);
                request.setAttribute("info", 
                        "Продукт \""+product.getName()
                                +"\"выдан покупателю "
                                +user.getReader().getFirstname()+" "+user.getReader().getLastname()
                );
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
//            case "/updateHistory":
//                String historyId = request.getParameter("historyId");
//                history = historyFacade.find(Long.parseLong(historyId));
//                history.setReturnProductDate(new GregorianCalendar().getTime());
//                historyFacade.edit(history);
//                request.getRequestDispatcher("/returnBook").forward(request, response);
//                break;
            
            case "/listManufacturers":
                request.setAttribute("listManufacturers", manufacturerFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/manufacturers/listManufacturers.jsp").forward(request, response);
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
