package br.com.crud.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    //Constantes para acessar o banco de dados
    private final String urlBD = "jdbc:mysql://localhost:3306/projetooutdoors";
    private final String user = "digodk";
    private final String password = "35512301";
    
    //Método para retornar a conexão com o banco de dados
    public Connection obterConexao() {
        
        //Variável para retornar a conex�o
        Connection conexao = null;
        
        //Realizar a conexão
        try {
            conexao = DriverManager.getConnection(urlBD, user, password);
            System.out.println("Conexão com BD realizada com sucesso");
        }catch(SQLException e) {
            System.out.println("Erro ao estabelecer a conexão");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return conexao;
    }
    
}
