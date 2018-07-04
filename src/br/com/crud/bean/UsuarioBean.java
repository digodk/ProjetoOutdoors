package br.com.crud.bean;

import java.util.Arrays;

/**
 * @author 105004
 * Classe que representa um usuário. Usada para estruturar os dados.
 */
public class UsuarioBean extends Bean {
  public enum NiveisUsuarios {
    INDEFINIDO(0), FUNCIONARIO(1), GERENTE(2);

    private int nivel;

    
    /**
     * Converte um número inteiro em um nível de usuário. Números inválidos retornam INDEFINIDO
     * @param nivel valor inteiro que represent o nível do usuário
     * @return o NiveisUsuarios do usuário
     */
    public static NiveisUsuarios converterNivel(int nivel) {
      return Arrays.asList(NiveisUsuarios.values())
              .stream()
              .filter(v -> v.toInt() == nivel)
              .findFirst()
              .orElse(INDEFINIDO);
    }

    public int toInt() {
      return nivel;
    }

    NiveisUsuarios(int nivel) {
      this.nivel = nivel;
    }
  }

  private static UsuarioBean usuarioAtivo;

  private String nome = "";
  private String senha = "";
  private NiveisUsuarios nivel;

  public static boolean alguemLogado() {
    return !(usuarioAtivo == null);
  }

  public static UsuarioBean getUsuarioAtivo() {
    return usuarioAtivo;
  }

  public void definirComoAtivo() {
    usuarioAtivo = this;
  }

  public boolean estaAtivo() {
    return this.equals(usuarioAtivo);
  }

  @Override
  public String getDescricao() {
    return nome + "-" + nivel.toString();
  }

  public NiveisUsuarios getNivel() {
    return nivel;
  }

  public String getNome() {
    return nome;
  }

  public String getSenha() {
    return senha;
  }

  public void setNivel(NiveisUsuarios nivel) {
    this.nivel = nivel;
  }

  public void setNivel(int nivel) {
    setNivel(NiveisUsuarios.converterNivel(nivel));
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public UsuarioBean() {

  }

  public UsuarioBean(String nome, String senha, NiveisUsuarios nivel) {
    this.nome = nome;
    this.senha = senha;
    this.nivel = nivel;
  }

  public static void tester() {
    // Método de teste
  }
}
