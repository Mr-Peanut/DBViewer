/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author guanshiqian
 */
public class ObtainTittle {

    /**
     *
     * @param mkid
     * @return
     * @throws SQLException
     */
    public static Map<String,String> obtainTittle(String mkid) throws SQLException {
        Map<String,String> map=new HashMap();
        try (Connection connection = JdbcUtils_C3P0.getConnection()) {
            String sql="SELECT TITTLE, LABLE FROM TABLETITTLE WHERE MKID='"+mkid+"' ORDER BY XH";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {            
               
                while (resultSet.next()){
                    map.put(resultSet.getString("TITTLE"),resultSet.getString("LABLE"));
                }
                resultSet.close();
            }
            connection.close();
        }
        return map;
    }
}
