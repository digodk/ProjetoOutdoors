package br.com.crud.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import br.com.crud.bean.Bean;
import br.com.crud.connection.Conexao;
import br.com.crud.view.Auxiliares;
import br.com.crud.view.DescritorComboBox;

/**
 * @author Diogo
 * Classe para abstrair os métodos em comuns entre todos os DAOs
 * Necessida do parâmetro relativo à qual Bean a classe de DAO se refere
 * @param <T> A classe Bean para a qual esse DAO está sendo criado
 */
public abstract class DAO<T extends Bean> {
  // A variável de conexão com o BD é estática e compartilhada entre todas as classes
  protected static Conexao con;

  // A conexão com o BD é criada ao carregar a classe
  static {
    con = br.com.crud.connection.ConnectionFactory.obterConexao();
  }

  
  /**
   * Cadastra os dados de um bean em uma nova linha do Banco de Dados
   * @param bean Objeto a ser armazenado
   */
  public void cadastrar(T bean) {
    // SQL
    // nomeTabela e camposCadastro são abstratos e retornam as informações
    // relativas ao BD do bean ao qual a implementação se refere
    String query = "INSERT INTO " + nomeTabela() + " " + camposCadastro();

    // Tentar realizar o cadastro
    try {

      // Preparar o envio dos parâmetros
      PreparedStatement statement = statementCadastro(query, bean);

      // Executar o comando
      statement.execute();

      // Finalizar a conexão
      statement.close();

    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Falha ao cadastrar, erro: " + e.getMessage());
    }

  }

  /**
   * Altera os dados de uma linha do BD, de acordo com o ID da instância repassada
   * Se o objeto bean não tem uma ID associada, o processo resulta em erro
   * @param bean objeto contendo as informações a serem atualizadas
   */
  public void alterar(T bean) {

    // SQL
    String query =
            "UPDATE " + nomeTabela() + " SET " + camposAlteracao() + " WHERE " + campoID() + " = ?";

    // Tenta realizar o comando
    try {

      PreparedStatement statement = statementCadastro(query, bean);
      statement.setInt(4, bean.getID());

      statement.execute();
      statement.close();

    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Falha ao alterar os dados.");
    }

  }

  /**
   * Exclui uma linha de dados da tabela. Se o id informado não existe, resulta em erro
   * @param id o id da linha a ser excluída
   */
  public void excluir(int id) {

    // SQL
    String query = "DELETE FROM " + nomeTabela() + " WHERE " + campoID() + " = ?";

    // Tenta realizar a exclusão
    try {

      // Preparar a exclusão
      PreparedStatement statement = con.prepareStatement(query);
      statement.setInt(1, id);

      // Executar comando
      statement.execute();

      // Finalizar conexão com o banco de dados
      statement.close();

      // Mensagem
      JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Falha ao excluir");
    }

  }

  /**
   * Retorna uma lista de todos os objetos cadastrados em um BD
   * Caso não hajam dados salvos, retorna uma List vazia
   * @return um objeto List com todas as instâncias armazenadas do bean
   */
  public List<T> getLista() {
    List<T> lista = new ArrayList<>();
    // SQL
    String query = "SELECT * FROM " + nomeTabela();

    try {
      PreparedStatement statement = con.prepareStatement(query);
      ResultSet resultado = statement.executeQuery();
      while (resultado.next()) {
        T bean = resultadoParaBean(resultado);
        lista.add(bean);
      }
      statement.close();
      return lista;
    } catch (Exception e) {
      System.out.println("Erro ao solicitar a lista de objetos:");
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  // 
  /**
   * Método auxiliar que gera uma combobox populada com a lista de beans existentes.
   * Essa combobox usa como texto o descritor do Bean.
   * @return uma JComboBox com todos as instâncias salvas de um objeto
   */
  public JComboBox<Bean> getComboBox() {
    List<T> lista = getLista();
    JComboBox<Bean> cbxOutdoor = new JComboBox<>();
    // Popula o combobox com os outdoors existentes
    cbxOutdoor.setModel(Auxiliares.listaComboBox(lista.toArray(new Bean[lista.size()])));
    // Define um renderer para determinar quais dados do Outdoor vão aparecer na
    // combobox.
    // Para isso, foi criado uma classe DescritorComboBox que usa do método
    // getDescricao de uma
    // subclasse da classe Dados, como a clase aluguel ou outdoor.
    cbxOutdoor.setRenderer(new DescritorComboBox());
    return cbxOutdoor;
  }

  /**
   * @param idBean o id da linha no Banco de Dados
   * @return uma instância de um id específico da classe
   */
  public T getBean(int idBean) {
    String query = "SELECT * FROM " + nomeTabela() + " WHERE " + campoID() + " = ?";
    try {
      PreparedStatement statement = con.prepareStatement(query);
      statement.setInt(1, idBean);
      ResultSet resultado = statement.executeQuery();
      if (!resultado.next()) {
        return null;
      }
      T bean = resultadoParaBean(resultado);
      return bean;
    } catch (Exception e) {
      System.out.println("Erro ao obter instância do bean:");
      e.printStackTrace();
      return null;
    }
  }

  // Método para checar se existe um id do bean cadastrado
  public boolean existeID(int idBean) {
    String query = "SELECT * FROM " + nomeTabela() + " WHERE " + campoID() + " = ?";
    try {
      PreparedStatement statement = con.prepareStatement(query);
      statement.setInt(1, idBean);
      ResultSet resultado = statement.executeQuery();
      return resultado.next();
    } catch (Exception e) {
      System.out.println("Erro ao obter informação sobre entry do id:");
      e.printStackTrace();
      return false;
    }
  }

  // Método privado usado para converter um resultado de query em um objeto bean
  protected abstract T resultadoParaBean(ResultSet resultado);

  // Método para retornar o statement com as particularidades para cadastrar
  // todos os dados
  // do bean, exceto id
  protected abstract PreparedStatement statementCadastro(String query, T bean);

  // Métodos para retornar os parâmetros particulares de cada DAO
  protected abstract String nomeTabela();

  protected abstract String camposCadastro();

  protected abstract String camposAlteracao();

  protected abstract String campoID();

}
