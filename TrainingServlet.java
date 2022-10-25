/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.jsu.mcis.lab6;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.jsu.mcis.lab6.dao.*;
import javax.servlet.ServletContext;


public class TrainingServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            
            out.println("<html>");
            
            out.println("<head>");
            
            out.println("<title>Servlet TrainingSessionServlet</title>");            
            
            out.println("</head>");
            
            out.println("<body>");
            
            out.println("<h1>Servlet TrainingSessionServlet at " + request.getContextPath() + "</h1>");
            
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
            
            TrainingDAO dao = daoFactory.getTrainingSessionDAO();
            
            String p_id = request.getParameter("id");            
            if (p_id == null || "".equals(p_id)) {
                // CODE GOES HERE
                System.err.println("Getting list of sessions...");
                out.println(dao.list());
            }
            else{
                // CODE GOES HERE
                System.err.println("Finding attendees registered to session " + p_id + "...");
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
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Training Session Servlet";
    }// </editor-fold>

}