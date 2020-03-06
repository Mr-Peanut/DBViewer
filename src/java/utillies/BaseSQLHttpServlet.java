package utillies;

import database.Connect2TargetDB;
import database.JdbcUtils_C3P0;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public abstract class BaseSQLHttpServlet extends HttpServlet{
    private Connection connection;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
         String result = null;
         String mark="OK";
          response.setContentType("application/json");
          response.setCharacterEncoding("utf-8");
          String sql=getSQL(request,response);
        try {
//            connection=JdbcUtils_C3P0.getConnection();
             connection=Connect2TargetDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            result=SelectResult4Json.getJsonResult(resultSet);           
            //response.getWriter().write(result);
        } catch (SQLException ex) {
           
            mark=ex.getMessage();
        }finally{
             try {
                response.getWriter().write("{\""+"OTHER_SERVER_ERROR"+"\""+":"+"\""+mark+"\""+","+"\""+"RESULT"+"\""+":"+(result==null?"\""+"无"+"\"":result)+"}"); 
            if(connection!=null&&!connection.isClosed())           
                connection.close();
            } catch (SQLException ex) {
            
            }
        }
    }
    public abstract String getSQL(HttpServletRequest request,HttpServletResponse response);  
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
