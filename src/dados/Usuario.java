package dados;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;

public class Usuario extends Dados {
  public enum NiveisUsuarios {
    INDEFINIDO, FUNCIONARIO, GERENTE;
  }

  private static Usuario usuarioAtivo = null;
  private static Map<String, Usuario> listaUsuarios = new HashMap<>();
  static {
    new Usuario("Diogo", "111", NiveisUsuarios.GERENTE);
    new Usuario("Joao", "222", NiveisUsuarios.FUNCIONARIO);
  }
  private String nome = "";
  private String senha = "";
  
  private NiveisUsuarios nivel;

  public static boolean alguemLogado() {
    return !(usuarioAtivo==null);
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
  
  public static Usuario cadastrar(Usuario usuario) {
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

  public static Map<String, Usuario> getListaUsuarios() {
    return listaUsuarios;
  }
  
  public static Usuario[] getArrayUsuarios() {
    if (listaUsuarios.size() == 0) {
      return new Usuario[0];
    } else {
      return listaUsuarios.values().toArray(new Usuario[listaUsuarios.size()]);
    }
  }
  
  public static JComboBox<Usuario> getComboBox() {
    JComboBox<Usuario> cbxOutdoor = new JComboBox<>();
    // Popula o combobox com os outdoors existentes
    cbxOutdoor.setModel(Auxiliares.listaComboBox(Usuario.getArrayUsuarios()));
    // Define um renderer para determinar quais dados do Outdoor vão aparecer na combobox.
    // Para isso, foi criado uma classe DescritorComboBox que usa do método getDescricao de uma
    // subclasse da classe Dados, como a clase aluguel ou outdoor.
    cbxOutdoor.setRenderer(new DescritorComboBox());
    return cbxOutdoor;
  }

  public static int getNivelIndex(NiveisUsuarios nivel) {
    return Arrays.asList(NiveisUsuarios.values()).indexOf(nivel);
  }

  public static Usuario getUsuario(String nome) {
    return listaUsuarios.get(nome.toLowerCase());
  }
  
  public static Usuario getUsuarioAtivo() {
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
