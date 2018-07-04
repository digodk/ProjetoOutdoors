package br.com.crud.bean;

import br.com.crud.dao.OutdoorDao;

/**
 * 
 * @author 105004 * Classe que representa o aluguel. Contém métodos estáticos
 *         para manipular todos os aluguéis e também métodos locais para
 *         manipular as instâncias.
 */
public class AluguelBean extends Bean {

  private int numDias = 1;
  private double valor = 0;
  private String observacao = "";
  private int idOutdoor = 0;
  private OutdoorBean out = null;

  @Override
  public String getDescricao() {
    return numDias + "-" + String.format("R$ %.2f", valor) + ":" + out.getDescricao();
  }

  // Getters e Setters

  public int getNumDias() {
    return numDias;
  }

  public String getObservacao() {
    return observacao;
  }

  
  /**
   * @return objeto outdoor associado a esse aluguel
   */
  public OutdoorBean getOutdoor() {
    return out;
  }

  /**
   * @return o valor int do objeto outdoor associado a esse aluguel
   */
  public int getIDOutdoor() {
    return idOutdoor;
  }

  /**
   * @return o valor do aluguel (em R$)
   */
  public double getValor() {
    return valor;
  }

  /**
   * Usado para gravar vários dados de uma vez em uma instância
   * @param numDias número de dias de duração do aluguel
   * @param valor valor do aluguel (em R$)
   * @param observacao String de observação do aluguel
   * @param idOutdoor valor int com id do outdoor associado
   */
  public void gravarDados(int numDias, double valor, String observacao, int idOutdoor) {
    setValor(valor);
    setOutdoor(idOutdoor);
    setNumDias(numDias);
    setObservacao(observacao);
  }

  public void setNumDias(int numDias) {
    this.numDias = numDias;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  public void setOutdoor(int idOutdoor) {
    this.idOutdoor = idOutdoor;
    out = OutdoorDao.inst().getBean(idOutdoor);
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  /**
   * Construtor com a opção de colocar todos os parâmetros do aluguel
   * @param numDias número de dias de duração do aluguel
   * @param valor valor do aluguel (em R$)
   * @param observacao String de observação do aluguel
   * @param idOutdoor valor int com id do outdoor associado
   */
  public AluguelBean(int numDias, double valor, String observacao, int idOutdoor) {
    gravarDados(numDias, valor, observacao, idOutdoor);
  }

  
  /**
   * Construtor genérico
   */
  public AluguelBean() {
  }
}
