/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsp;

import database.JdbcUtils_C3P0;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utillies.BaseSQLHttpServlet;

/**
 *
 * @author guanshiqian
 */
public class LinkServlet extends BaseSQLHttpServlet {

    @Override
    public String getSQL(HttpServletRequest request, HttpServletResponse response) {
        String result = null;
         try (Connection connection = JdbcUtils_C3P0.getConnection()) {
            String sql="SELECT MK_SQL FROM MK WHERE MK_ID='"+request.getParameter("MKID").trim()+"'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                
                if (resultSet.next()){
                  result=resultSet.getString("MK_SQL");
                }
                resultSet.close();
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(LinkServlet.class.getName()).log(Level.SEVERE, null, ex);
        }      
        
        
        return result;
    }
}
