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
public class ObtainMKinfo {

    /**
     *
     * @param mkid
     * @return
     * @throws SQLException
     */
    public static Map<String,String> obtainMKinfo() throws SQLException {
        Map<String,String> map=new HashMap();
        try (Connection connection = JdbcUtils_C3P0.getConnection()) {
            String sql="SELECT MK_ID, MK_MC FROM MK ORDER BY MK_ID";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {          
               
                while (resultSet.next()){
                    map.put(resultSet.getString("MK_ID"),resultSet.getString("MK_MC"));
                    System.out.println(resultSet.getString("MK_ID"));
                }
                resultSet.close();
            }
            connection.close();
        }
        return map;
    }
}
