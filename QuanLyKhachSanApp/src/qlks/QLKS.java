package qlks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QLKS {
    public static Connection layKetNoi(){
        Connection ketNoi = null;
        String uRL = "jdbc:sqlserver://;databaseName=QLKS";
        String userName = "sa";
        String password = "123";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ketNoi = DriverManager.getConnection(uRL, userName, password);
            System.out.println("Ket noi thanh cong roi nha!");
        }
        catch(ClassNotFoundException | SQLException ex){
            System.out.println("Thoi toang roi ong giao oi!");
        }
        return ketNoi;
    }
    public static Connection layKetNoiLT(){
        Connection ketNoi = null;
        String uRL = "jdbc:sqlserver://;databaseName=QLKS";
        String userName = "01";
        String password = "01";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ketNoi = DriverManager.getConnection(uRL, userName, password);
            System.out.println("Ket noi thanh cong roi nha!");
        }
        catch(ClassNotFoundException | SQLException ex){
            System.out.println("Thoi toang roi ong giao oi!");
        }
        return ketNoi;
    }
    public static void main(String[] args) {
        layKetNoi();
    }
    
}
