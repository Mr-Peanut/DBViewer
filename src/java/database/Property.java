/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.beans.PropertyChangeSupport;

/**
 *
 * @author guanshiqian
 */
class Property {
    /*ds.setDriverClass("com.mysql.jdbc.Driver"); 
           ds.setJdbcUrl("jdbc:mysql://localhost:3306/test"); 
           ds.setUser("root"); 
           ds.setPassword("123456");*/
    private String driverClass;
    private String jdbcUrl;
    private String user;
    private String password;

    /**
     * @return the driverClass
     */
    public String getDriverClass() {
        return driverClass;
    }

    /**
     * @param driverClass the driverClass to set
     */
    public void setDriverClass(String driverClass) {
        java.lang.String oldDriverClass = this.driverClass;
        this.driverClass = driverClass;
        propertyChangeSupport.firePropertyChange(PROP_DRIVERCLASS, oldDriverClass, driverClass);
    }

    /**
     * @return the jdbcUrl
     */
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    /**
     * @param jdbcUrl the jdbcUrl to set
     */
    public void setJdbcUrl(String jdbcUrl) {
        java.lang.String oldJdbcUrl = this.jdbcUrl;
        this.jdbcUrl = jdbcUrl;
        propertyChangeSupport.firePropertyChange(PROP_JDBCURL, oldJdbcUrl, jdbcUrl);
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        java.lang.String oldUser = this.user;
        this.user = user;
        propertyChangeSupport.firePropertyChange(PROP_USER, oldUser, user);
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        java.lang.String oldPassword = this.password;
        this.password = password;
        propertyChangeSupport.firePropertyChange(PROP_PASSWORD, oldPassword, password);
    }
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    public static final String PROP_DRIVERCLASS = "driverClass";
    public static final String PROP_JDBCURL = "jdbcUrl";
    public static final String PROP_USER = "user";
    public static final String PROP_PASSWORD = "password";
    
}
