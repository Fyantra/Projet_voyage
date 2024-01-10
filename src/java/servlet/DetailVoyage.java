/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objet.Activite;
import objet.VoyageDetail;
import java.util.List;

/**
 *
 * @author ITU
 */
public class DetailVoyage extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            try {
                String montant1 = request.getParameter("montant1");
                String montant2 = request.getParameter("montant2");
                double montantt1 = Double.parseDouble(montant1);
                double montantt2 = Double.parseDouble(montant2);
                
//                System.out.println(description);
                
              VoyageDetail vd = new VoyageDetail();
                List<VoyageDetail> lvd = vd.selectVoyageBetween2PU(null, montantt1, montantt2);
             
               request.setAttribute("listeVoyage", lvd);
                request.setAttribute("formSubmitted", true);
              
                // request.setAttribute("controleAnomalie", ctrl);

                RequestDispatcher dispat = getServletContext().getRequestDispatcher("/filtreActivite.jsp");

                dispat.forward(request, response);

            } catch (Exception e) {
                request.setAttribute("exception", e);

                RequestDispatcher dispat = getServletContext().getRequestDispatcher("/exception.jsp");

                dispat.forward(request, response);
            }
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
