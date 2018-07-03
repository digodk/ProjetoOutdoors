package br.com.crud.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.crud.bean.OutdoorBean;
import br.com.crud.bean.OutdoorBean.TiposOutdoor;

public class OutdoorDao extends DAO<OutdoorBean> {

  private static OutdoorDao instancia = new OutdoorDao();
  
  public static OutdoorDao inst() {
    return instancia;
  }
  
  @Override
  protected OutdoorBean resultadoParaBean(ResultSet resultado) {
    OutdoorBean out = new OutdoorBean();
    try {
      out.setID(resultado.getInt("idOutdoor"));
      out.setCidade(resultado.getString("cidadeOutdoor"));
      out.setEndereco(resultado.getString("enderecoOutdoor"));
      out.setTipo(TiposOutdoor.converterTipo(resultado.getInt("tipoOutdoor")));
      return out;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  protected PreparedStatement statementCadastro(String query, OutdoorBean bean) {
    try {
      PreparedStatement statement = con.prepareStatement(query);
      statement.setInt(1, bean.getTipo().toInt());
      statement.setString(2, bean.getEndereco());
      statement.setString(3, bean.getCidade());
      return statement;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  protected String nomeTabela() {
    return "Outdoors";
  }

  @Override
  protected String camposCadastro() {
    return "(tipoOutdoor, enderecoOutdoor, cidadeOutdoor) VALUES (?, ?, ?)";
  }

  @Override
  protected String camposAlteracao() {
    return "tipoOutdoor = ?, enderecoOutdoor = ?, cidadeOutdoor = ?";
  }

  @Override
  protected String campoID() {
    return "idOutdoor";
  }
  
  private OutdoorDao() {
    super();
  }

}
