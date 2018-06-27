package br.com.crud.bean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;

import br.com.crud.view.Auxiliares;
import br.com.crud.view.DescritorComboBox;
import br.com.interfaces.Descritor;

public class UsuarioBean implements Descritor {
  public enum NiveisUsuarios {
    INDEFINIDO, FUNCIONARIO, GERENTE;
  }

  private static UsuarioBean usuarioAtivo = null;
  private static Map<String, UsuarioBean> listaUsuarios = new HashMap<>();
  static {
    new UsuarioBean("Diogo", "111", NiveisUsuarios.GERENTE);
    new UsuarioBean("Joao", "222", NiveisUsuarios.FUNCIONARIO);
  }
  private String nome = "";
  private String senha = "";
  
  private NiveisUsuarios nivel;

  public static boolean alguemLogado() {
    return !(usuarioAtivo==null);
  }

  public static UsuarioBean cadastrar(String nome, String senha, NiveisUsuarios nivel) {
    if(!existeUsuario(nome)) {
      return new UsuarioBean(nome, senha, nivel);
    } else {
      UsuarioBean usuario = getUsuario(nome);
      usuario.setNome(nome);
      usuario.setSenha(senha);
      usuario.setNivel(nivel);
      return usuario;
    }
  }
  
  public static UsuarioBean cadastrar(UsuarioBean usuario) {
    if(existeUsuario(usuario.getNome())) {
      listaUsuarios.put(usuario.getNome(), usuario);
    } else {
      usuario = cadastrar(usuario.getNome(), usuario.getSenha(), usuario.getNivel());
    }
    return usuario;
  }

  public static boolean existeUsuario(String nome) {
    return listaUsuarios.containsKey(nome.toLowerCase());
  }

  public static Map<String, UsuarioBean> getListaUsuarios() {
    return listaUsuarios;
  }
  
  public static UsuarioBean[] getArrayUsuarios() {
    if (listaUsuarios.size() == 0) {
      return new UsuarioBean[0];
    } else {
      return listaUsuarios.values().toArray(new UsuarioBean[listaUsuarios.size()]);
    }
  }
  
  public static JComboBox<UsuarioBean> getComboBox() {
    JComboBox<UsuarioBean> cbxOutdoor = new JComboBox<>();
    // Popula o combobox com os outdoors existentes
    cbxOutdoor.setModel(Auxiliares.listaComboBox(UsuarioBean.getArrayUsuarios()));
    // Define um renderer para determinar quais dados do Outdoor vão aparecer na combobox.
    // Para isso, foi criado uma classe DescritorComboBox que usa do método getDescricao de uma
    // subclasse da classe Dados, como a clase aluguel ou outdoor.
    cbxOutdoor.setRenderer(new DescritorComboBox());
    return cbxOutdoor;
  }

  public static int getNivelIndex(NiveisUsuarios nivel) {
    return Arrays.asList(NiveisUsuarios.values()).indexOf(nivel);
  }

  public static UsuarioBean getUsuario(String nome) {
    return listaUsuarios.get(nome.toLowerCase());
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

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public UsuarioBean() {

  }

  public UsuarioBean(String nome, String senha, NiveisUsuarios nivel) {
    if (!existeUsuario(nome)) {
      this.nome = nome;
      this.senha = senha;
      this.nivel = nivel;
      listaUsuarios.put(nome.toLowerCase(), this);
    }
  }
}
