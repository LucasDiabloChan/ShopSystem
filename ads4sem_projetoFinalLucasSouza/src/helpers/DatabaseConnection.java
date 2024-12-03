package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Workstation
 */
public class DatabaseConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "@nStr0ngP4sswd!";
    private static final String DATABASE = "agendalucas";
    private static final String URL = "jdbc:mysql://localhost:3306/".concat(DATABASE);
    
    static{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Driver não encontrado", ex);
        }
    }
    
    public static Connection obterConexao() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro de conexão com o banco de dados", e);
        }
    }    
}
