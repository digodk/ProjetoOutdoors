package dados;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Usuario {
  public enum NiveisUsuarios {
    INDEFINIDO, FUNCIONARIO, GERENTE
  }

  private static Usuario usuarioAtivo = null;
  private String nome = "";
  private String senha = "";
  private NiveisUsuarios nivel;
  private static Map<String, Usuario> listaUsuarios = new HashMap<>();
  
  static {
    new Usuario("Diogo", "111", NiveisUsuarios.GERENTE);
    new Usuario("Joao", "222", NiveisUsuarios.FUNCIONARIO);
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public NiveisUsuarios getNivel() {
    return nivel;
  }

  public void setNivel(NiveisUsuarios nivel) {
    this.nivel = nivel;
  }

  public static Map<String, Usuario> getListaUsuarios() {
    return listaUsuarios;
  }

  public Usuario getUsuarioAtivo() {
    return usuarioAtivo;
  }

  public void definirComoAtivo() {
    usuarioAtivo = this;
  }

  public boolean estaAtivo() {
    return this.equals(usuarioAtivo);
  }

  public static boolean existeUsuario(String nome) {
    return listaUsuarios.containsKey(nome.toLowerCase());
  }

  public static Usuario getUsuario(String nome) {
    return listaUsuarios.get(nome.toLowerCase());
  }

  public static boolean alguemLogado() {
    return !usuarioAtivo.equals(null);
  }
  
  public static Usuario cadastrar(String nome, String senha, NiveisUsuarios nivel) {
    if(!existeUsuario(nome)) {
      return new Usuario(nome, senha, nivel);
    } else {
      Usuario usuario = getUsuario(nome);
      usuario.setNome(nome);
      usuario.setSenha(senha);
      usuario.setNivel(nivel);
      return usuario;
    }
  }

  public static int getNivelIndex(NiveisUsuarios nivel) {
    return Arrays.asList(NiveisUsuarios.values()).indexOf(nivel);
  }

  public Usuario() {

  }

  public Usuario(String nome, String senha, NiveisUsuarios nivel) {
    if (!existeUsuario(nome)) {
      this.nome = nome;
      this.senha = senha;
      this.nivel = nivel;
      listaUsuarios.put(nome.toLowerCase(), this);
    }
  }
}
