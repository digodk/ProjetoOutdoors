package br.com.crud.main;

import java.sql.Connection;

import br.com.crud.connection.Conexao;
import br.com.crud.connection.ConnectionFactory;
@SuppressWarnings("unused")
public class Tester {

  public static void main(String[] args) {
    ConnectionFactory.tester();
  }

}
