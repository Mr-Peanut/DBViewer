/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author guanshiqian
 */
public class Connect2TargetDB {
    private static ComboPooledDataSource ds =null;  
    //在静态代码块中创建数据库连接池  
    static{  
        try{  
            //通过代码创建C3P0数据库连接池  
           ds = new ComboPooledDataSource();  
           ds.setInitialPoolSize(10); 
           ds.setMinPoolSize(5); 
           ds.setMaxPoolSize(20);   
            try (Connection connection = JdbcUtils_C3P0.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("select DBINFOR_USER, DBINFOR_PASSWORD, DBINFOR_LINK from DBINFOR ");
               try (ResultSet resultSet = preparedStatement.executeQuery()) {
                   if(resultSet.next()){
                       ds.setPassword(resultSet.getString("DBINFOR_PASSWORD"));
                       ds.setUser(resultSet.getString("DBINFOR_USER"));
                       ds.setJdbcUrl(resultSet.getString("DBINFOR_LINK"));
                   }
               }
            }
            ds.setDriverClass("oracle.jdbc.driver.OracleDriver");
        }catch (Exception e) {  
            throw new ExceptionInInitializerError(e);  
        }  
    }  
   public static void setDBlink(Property dbProperty) throws PropertyVetoException{
           ds.setDriverClass(dbProperty.getDriverClass()); 
           ds.setJdbcUrl(dbProperty.getJdbcUrl()); 
           ds.setUser(dbProperty.getUser()); 
           ds.setPassword(dbProperty.getPassword());
   }
     
   //从数据源中获取数据库连接  
    public static Connection getConnection()throws SQLException{  
        //从数据源中获取数据库连接  
        Connection connection=ds.getConnection();
//        if(!connection.isClosed())
        return connection; 
  
    }  
     
    //释放链接  
    public static void release(Connection conn){  
        
        if(conn!=null){  
            try{  
                //将Connection连接对象还给数据库连接池  
                conn.close();  
            }catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
