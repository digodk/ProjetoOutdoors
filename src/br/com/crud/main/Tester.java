package br.com.crud.main;

import java.sql.Connection;

import br.com.crud.connection.ConnectionFactory;

public class Tester {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    testarConexoesBD();

  }
  
  public static void testarConexoesBD() {
    try {
      Connection conexaoBDOutdoors = ConnectionFactory.obterConexao();
      System.out.println("Conexão com BD realizada com sucesso");
    } catch(Exception e) {
      System.out.println("Erro ao estabelecer a conexão");
      e.printStackTrace();
    }
  }

}
