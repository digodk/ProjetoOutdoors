package telas;

import dados.Usuario;

public class Main {

  public static void main(String[] args) {
    Login.logar();
    if (!Usuario.alguemLogado()) {
      System.exit(0);
    }
    TelaPrincipal.exibir(Usuario.getUsuarioAtivo());
  }

}
