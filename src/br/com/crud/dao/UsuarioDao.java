package br.com.crud.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.crud.bean.UsuarioBean;

public class UsuarioDao extends DAO<UsuarioBean> {
  
  private static UsuarioDao instancia = new UsuarioDao();

  public static UsuarioDao inst() {
    return instancia;
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
      statement.setInt(3, bean.getNivel().getNivel());
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