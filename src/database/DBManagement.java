/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Pham Quoc Nghi ce140179
 */
public class DBManagement {
    private String URL = "jdbc:mysql://localhost:3306/reversi";    //database url
    private String USR = "root";                                    //user
    private String PWS = "";                                        //password
    private Connection conn = null;                                 //connection

    /**
     * Constructor DBManagement
     * @throws SQLException sql exception
     */
    public DBManagement() throws SQLException {
        DriverManager.registerDriver(new Driver());
        this.conn = (Connection) DriverManager.getConnection(URL, USR, PWS);
    }

    /**
     * Get connection
     * @return connection
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * Set connection
     * @param conn database connection
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    /**
     * Check if database is connected or not
     * @return is connected or not
     */
    public boolean isConnect() {
        return conn != null;
    }
    
    /**
     * Close connection with database
     * @throws SQLException sql exception
     */
    public void closeConnection() throws SQLException {
        this.conn.close();
    }
}
