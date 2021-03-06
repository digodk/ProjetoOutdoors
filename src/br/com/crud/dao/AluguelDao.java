package br.com.crud.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.crud.bean.AluguelBean;

/**
 * @author Diogo DAO referente à classe de alugueis Segue o pattern singleton, a instância é
 *         acessada pelo método inst()
 */
public class AluguelDao extends DAO<AluguelBean> {

  private static AluguelDao instancia = new AluguelDao();

  /**
   * Usada para acessar a instância única da classe
   * 
   * @return a instância estática do DAO para acesso aos métodos
   */
  public static AluguelDao inst() {
    return instancia;
  }

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

  private AluguelDao() {
    super();
  }
}
