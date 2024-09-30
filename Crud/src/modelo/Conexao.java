package modelo;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "";
    private static final String USER = "root"; // Usuário do MySQL
    private static final String PASSWORD = ""; // Senha do MySQL (deixe em branco se for o padrão)

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
