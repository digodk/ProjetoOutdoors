package br.com.crud.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.crud.bean.UsuarioBean;

/**
 * @author Diogo DAO referente à classe de Usuários Segue o pattern singleton, a instância é
 *         acessada pelo método inst()
 */
public class UsuarioDao extends DAO<UsuarioBean> {

  private static UsuarioDao instancia = new UsuarioDao();
  private String campoNome = "nomeUsuario";

  public static UsuarioDao inst() {
    return instancia;
  }

  /**
   * Método adicional da classe usuários para retornar um usuário pelo nome
   * 
   * @param nome nome do usuário
   * @return um Objeto UsuarioBean se o nome está cadastro, senão null
   */
  public UsuarioBean getUsuario(String nome) {
    String query = "SELECT * FROM " + nomeTabela() + " WHERE " + campoNome + " = ?";
    try {
      PreparedStatement statement = con.prepareStatement(query);
      statement.setString(1, nome);
      ResultSet resultado = statement.executeQuery();
      if (!resultado.next()) {
        return null;
      }
      UsuarioBean bean = resultadoParaBean(resultado);
      return bean;
    } catch (Exception e) {
      System.out.println("Erro ao obter instância do usuario através do nome:");
      e.printStackTrace();
      return null;
    }
  }

  @Override
  protected UsuarioBean resultadoParaBean(ResultSet resultado) {
    UsuarioBean usu = new UsuarioBean();
    try {
      usu.setID(resultado.getInt(campoID()));
      usu.setNivel(resultado.getInt("nivelUsuario"));
      usu.setNome(resultado.getString("nomeUsuario"));
      usu.setSenha(resultado.getString("senhaUsuario"));
      return usu;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  protected PreparedStatement statementCadastro(String query, UsuarioBean bean) {
    try {
      PreparedStatement statement = con.prepareStatement(query);
      statement.setString(1, bean.getNome());
      statement.setString(2, bean.getSenha());
      statement.setInt(3, bean.getNivel().toInt());
      return statement;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  protected String nomeTabela() {
    return "Usuarios";
  }

  @Override
  protected String camposCadastro() {
    return "(nomeUsuario, senhaUsuario, nivelUsuario) VALUES (?, ?, ?)";
  }

  @Override
  protected String camposAlteracao() {
    return "nomeUsuario = ?, senhaUsuario = ?, nivelUsuario = ?";
  }

  @Override
  protected String campoID() {
    return "idUsuario";
  }

  private UsuarioDao() {
    super();
  }
}
