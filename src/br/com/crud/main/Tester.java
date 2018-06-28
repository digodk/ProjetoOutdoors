package br.com.crud.main;

import java.sql.Connection;

import br.com.crud.connection.Conexao;
import br.com.crud.connection.ConnectionFactory;
@SuppressWarnings("unused")
public class Tester {

  public static void main(String[] args) {
    testarConexoesBD();
  }
  
  
  public static void testarConexoesBD() {
    try {
      Conexao conexaoBDOutdoors = ConnectionFactory.obterConexao();
      System.out.println("Conexão com BD realizada com sucesso");
    } catch(Exception e) {
      System.out.println("Erro ao estabelecer a conexão");
      e.printStackTrace();
    }
  }

}
