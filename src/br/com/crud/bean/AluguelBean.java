package br.com.crud.bean;

import br.com.crud.dao.OutdoorDao;

/*
 * Classe que representa o aluguel. Contém métodos estáticos para manipular todos os 
 * aluguéis e também métodos locais para manipular as instâncias.
*/
public class AluguelBean extends Bean  {

  private int numDias = 1;
  private double valor = 0;
  private String observacao = "";
  private int idOutdoor = 0;
  private OutdoorBean out = null;

  // Validação individual das variáveis
  public boolean validarNumDias() {
    return numDias > 0;
  }

  public boolean validarOutdoor() {
    return !(idOutdoor == 0);
  }

  public boolean validarValor() {
    return valor > 0;
  }

  // Método herdado da classe abstrata de dados. Fornece uma descrição do objeto
  // para ser colocada nas combobox
  // TODO Corrigir get do outdoor associado com o aluguel usando a classe DAO
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

  // Retorna o outdoor associado com esse aluguel
  public OutdoorBean getOutdoor(int idOutdoor) {
    return out;
  }

  public int getIDOutdoor() {
    return idOutdoor;
  }

  public double getValor() {
    return valor;
  }

  // Grava um conjunto de dados em uma instância
  public void gravarDados(int numDias, double valor, String observacao, int idOutdoor) {
    setValor(valor);
    setOutdoor(idOutdoor);
    setNumDias(numDias);
    setObservacao(observacao);
  }
  
  public boolean setNumDias(int numDias) {
    this.numDias = numDias;
    return validarNumDias();
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  public void setOutdoor(int idOutdoor) {
    this.idOutdoor = idOutdoor;
    out = OutdoorDao.inst().getBean(idOutdoor);
  }

  public boolean setValor(double valor) {
    this.valor = valor;
    return validarValor();
  }

  // Construtor usado para um novo aluguel
  public AluguelBean(int numDias, double valor, String observacao, int idOutdoor) {
    gravarDados(numDias, valor, observacao, idOutdoor);
  }

  // Construtor público genérico
  public AluguelBean() {
  }

  public static void tester() {

  }

}
