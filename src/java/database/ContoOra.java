package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ContoOra {
	private final static String URL="jdbc:oracle:thin:@127.0.0.1:1521:guan";
	private final static String USER="ZZDATABASE";
	private final static String PASSWORD="12345";
	private static ContoOra contoOra;
	private Connection connection;
	private ContoOra() {		
	};
	private static ContoOra getContoOra()  {
		if(contoOra==null) {
			synchronized (ContoOra.class) {
				if(contoOra==null) {
					contoOra=new ContoOra();
									}				
			}
		}
		return contoOra;
	}
	private Connection initCon() throws ClassNotFoundException, SQLException {
		if(connection==null||connection.isClosed()) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection=DriverManager.getConnection(URL, USER, PASSWORD);
		}
		return connection;
	}
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return getContoOra().initCon();		
	}
}
