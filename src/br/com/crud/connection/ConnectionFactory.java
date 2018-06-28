package br.com.crud.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    //Constantes para acessar o banco de dados
    private static final String urlBD = "jdbc:mysql://localhost:3306/projetooutdoors?useTimezone=true&serverTimezone=UTC";
    private static final String user = "digodk";
    private static final String password = "35512301";
    
    //Método para retornar a conexão com o banco de dados
    public static Connection obterConexao() {
        
        //Variável para retornar a conex�o
        Connection conexao = null;
        
        //Realizar a conexão
        try {
            conexao = DriverManager.getConnection(urlBD, user, password);
            
        }catch(SQLException e) {
           
            throw new RuntimeException(e);
        }
        return conexao;
    }
    
}
