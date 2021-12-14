package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {

    public static Connection layKetNoi() {
        Connection ketNoi = null;
        String uRL = "jdbc:sqlserver://;databaseName=QLKS";
        String userName = "sa";
        String password = "123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ketNoi = DriverManager.getConnection(uRL, userName, password);
            System.out.println("Ket noi OK!");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("That bai!");
        }
        return ketNoi;
    }

    public static void main(String[] args) {
        layKetNoi();
    }
}
