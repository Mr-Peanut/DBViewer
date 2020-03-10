/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsp;

import bean.Lable2Tittle;
import com.alibaba.fastjson.JSONArray;
import database.Connect2TargetDB;
import database.JdbcUtils_C3P0;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author guanshiqian
 */
public class CreateMKServlet extends HttpServlet {

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
      String mkid=request.getParameter("MKID");
      String mkmc=request.getParameter("MKMC");
      String sql=request.getParameter("SQL");
      String[] tittles;
      tittles = request.getParameterValues("TITTLES"); 
      JSONArray jSONArray=JSONArray.parseArray(Arrays.toString(tittles));
      System.out.println(jSONArray);
      List<Lable2Tittle> list=jSONArray.toJavaList(Lable2Tittle.class); 
     
      Connection connection;
        try {
            String sql2="INSERT INTO MK(MK_ID, MK_MC, MK_BZ, MK_SQL) VALUES(?,?,?,?) ";
            connection = JdbcUtils_C3P0.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, mkid);
            preparedStatement.setString(2, mkmc);
            preparedStatement.setString(3, "BZ");
            preparedStatement.setString(4, sql);
            preparedStatement.executeUpdate();
            Statement statement=connection.createStatement();
            int i=1;
             for(Lable2Tittle lable2Tittle:list){
          System.out.println(lable2Tittle.toString());
          statement.addBatch("INSERT INTO TABLETITTLE(MKID, TITTLE, LABLE, XH) VALUES('"+mkid+"','"+lable2Tittle.getLable()+"','"+lable2Tittle.getTittle()+"','"+String.valueOf(i)+"')");
          i++;
      }
             statement.executeBatch();
             connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CreateMKServlet.class.getName()).log(Level.SEVERE, null, ex);
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
