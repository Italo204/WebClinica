package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/clinica";
    private static final String user = "clinica";
    private static final String password = "12345";

    private static Connection con;

    public static Connection getConexao(){
        try{
            if(con == null){
                con = DriverManager.getConnection(url, user, password);
                return con;
            }else {
                return con;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR IN DATABASE: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void closeConnection() {
        try{
            if(con != null && !con.isClosed()) {
                con.close();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
