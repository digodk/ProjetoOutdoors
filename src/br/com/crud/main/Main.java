package br.com.crud.main;

import br.com.crud.bean.UsuarioBean;
import br.com.crud.view.Login;
import br.com.crud.view.TelaPrincipal;

/**
 * @author Diogo Roda o programa
 */
public class Main {

  public static void main(String[] args) {
    Login.logar();
    if (!UsuarioBean.alguemLogado()) {
      System.exit(0);
    }
    TelaPrincipal.exibir(UsuarioBean.getUsuarioAtivo());
  }

}
