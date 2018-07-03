package br.com.crud.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.crud.bean.AluguelBean;

public class AluguelDao extends DAO<AluguelBean> {

  @Override
  protected AluguelBean resultadoParaBean(ResultSet resultado) {
    AluguelBean alu = new AluguelBean();
    try {
      alu.setID(resultado.getInt(campoID()));
      alu.setNumDias(resultado.getInt("nDiasAluguel"));
      alu.setObservacao(resultado.getString("obsAluguel"));
      alu.setOutdoor(resultado.getInt("idOutdoor"));
      alu.setValor(resultado.getDouble("valorAluguel"));
      return alu;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  protected PreparedStatement statementCadastro(String query, AluguelBean bean) {
    try {
      PreparedStatement statement = con.prepareStatement(query);
      statement.setInt(1, bean.getIDOutdoor());
      statement.setDouble(2, bean.getValor());
      statement.setString(3, bean.getObservacao());
      statement.setInt(4, bean.getNumDias());
      return statement;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  protected String nomeTabela() {
    return "Alugueis";
  }

  @Override
  protected String camposCadastro() {
    return "(idOutdoor, valorAluguel, obsAluguel, nDiasAluguel) VALUES (?, ?, ?, ?)";
  }

  @Override
  protected String camposAlteracao() {
    return "idOutdoor = ?, valorAluguel = ?, obsAluguel = ?, nDiasAluguel = ?";
  }

  @Override
  protected String campoID() {
    return "idAluguel";
  }
}