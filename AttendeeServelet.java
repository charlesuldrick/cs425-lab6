/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.jsu.mcis.lab6;

import edu.jsu.mcis.lab6.dao.Attendee;
import edu.jsu.mcis.lab6.dao.AttendeeDAO;
import edu.jsu.mcis.lab6.dao.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chu19
 */
@WebServlet(name = "AttendeeServelet", urlPatterns = {"/AttendeeServelet"})
public class AttendeeServelet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AttendeeServelet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AttendeeServelet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        response.setContentType("application/json;charset=UTF-8");
        
        try(PrintWriter out = response.getWriter()){
            
            AttendeeDAO dao = daoFactory.getAttendeeDAO();
            
            String p_id = request.getParameter("id");
            if (p_id == null || "".equals(p_id)) {
                // CODE GOES HERE
                System.err.println("Attendee GET request...");
            }
            else{
                System.err.println("Getting attendee #" + p_id + "...");
                out.println(dao.find(Integer.parseInt(p_id)));
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        response.setContentType("application/json; charset=UTF-8");
        
        try ( PrintWriter out = response.getWriter()) {
            
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String displayname = request.getParameter("displayname");
            
            AttendeeDAO aDAO = daoFactory.getAttendeeDAO();
            
            Attendee aObj = new Attendee(null, firstname, lastname, displayname);
            out.println(aDAO.create(aObj));
             
        }
        catch (Exception e) {
            e.printStackTrace();
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
