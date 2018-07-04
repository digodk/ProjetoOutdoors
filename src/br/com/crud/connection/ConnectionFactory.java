package br.com.crud.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  // Constantes para acessar o banco de dados
  private static final String urlBD =
          "jdbc:mysql:/bits-please.c6g4ywjszepf.sa-east-1.rds.amazonaws.com:3306";
  private static final String user = "digodk";
  private static final String password = "dDK1,A0573551";

  // Método para retornar a conexão com o banco de dados
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

  public static void tester() {
    try {
      ConnectionFactory.connectJDBCToAWSEC2();
      System.out.println("Conexão com BD realizada com sucesso");
    } catch (Exception e) {
      System.out.println("Erro ao estabelecer a conexão");
      e.printStackTrace();
    }
  }

  public static void connectJDBCToAWSEC2() {

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
