package factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    //nome do usuário
    private static final String username = "root";
    //senha do banco
    private static final String password = "lvrg_291119";
    //caminho do banco de dados, porsta
    private static final String database_url = "jdbc:mysql://localhost:3306/agenda";

    /*
     *Conexão com o banco de dados
     */

    public static Connection createConnectionToMySQL() throws Exception{
        //Faz com que a classe seja carregada pela JVM
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(database_url, username, password);

        return connection;
    }

    public static void main(String[] args) throws Exception {

        //Recuperar uma conexão com o banco de dados
        Connection con = createConnectionToMySQL();

        //Testar se a conexão é nula
        if(con != null){
            System.out.println("Conexao obtida com sucesso!");
            con.close();
        }
    }
}
