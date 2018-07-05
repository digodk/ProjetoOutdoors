package br.com.crud.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Diogo
 * Classe efetivamente estática que retorna uma conexão para o banco de dados
 */
public class ConnectionFactory {

  // Constantes para acessar o banco de dados
  private static final String urlBD =
          "jdbc:mysql://bits-please.c6g4ywjszepf.sa-east-1.rds.amazonaws.com:3306/bitsplease";
  private static final String user = "digodk";
  private static final String password = "dDK1,A0573551";

  /**
   * Método para retornar a conexão com o banco de dados
   * @return Uma instância da conexão com o banco de dados se ela foi bem sucedida, caso contrário retorna null.
   */
  public static Conexao obterConexao() {

    // Variável para retornar a conexão
    Conexao conexao = null;

    // Realizar a conexão
    try {
      conexao = (Conexao) DriverManager.getConnection(urlBD, user, password);

    } catch (SQLException e) {

      throw new RuntimeException(e);
    }
    return conexao;
  }

  @SuppressWarnings("unused")
  private static void exemploConexao() {

    System.out.println("----MySQL JDBC Connection Testing -------");

    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("Where is your MySQL JDBC Driver?");
      e.printStackTrace();
      return;
    }

    System.out.println("MySQL JDBC Driver Registered!");
    Connection connection = null;

    try {
      connection = DriverManager.getConnection(
              "jdbc:mysql://" + "bits-please.c6g4ywjszepf.sa-east-1.rds.amazonaws.com" + ":"
                      + "3306" + "/" + "bitsplease",
              user, password);
    } catch (SQLException e) {
      System.out.println("Connection Failed!:\n" + e.getMessage());
    }

    if (connection != null) {
      System.out.println("SUCCESS!!!! You made it, take control     your database now!");
    } else {
      System.out.println("FAILURE! Failed to make connection!");
    }

  }

}
