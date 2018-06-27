package br.com.crud.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JComboBox;

import br.com.crud.view.Auxiliares;
import br.com.crud.view.DescritorComboBox;
import br.com.interfaces.Descritor;
/*
 * Classe que representa o aluguel. Contém métodos estáticos para manipular todos os 
 * aluguéis e também métodos locais para manipular as instâncias.
 * Mantém uma lista de todos os aluguéis cadastrados.
*/
public class AluguelBean implements Descritor {

  private static Map<Integer, AluguelBean> listaAlugueis = new HashMap<>();
  private static Integer proximoID = 0;
  private int numDias = 1;
  private double valor = 0;
  private Integer iD = null;
  private String observacao = "";
  private OutdoorBean outdoor = null;

  // Retorna todos os aluguéis associados a um determinado outdoor.
  public static List<AluguelBean> alugueisAssociados(OutdoorBean outdoor) {
    return AluguelBean.getListaAlugueis().values().stream().filter(v -> v.getOutdoor().equals(outdoor))
        .collect(Collectors.toList());
  }

  // Método para cadastrar um novo aluguel. Retorna o novo aluguel cadastrado.
  public static AluguelBean cadastrar(int numDias, double valor, String observacao, OutdoorBean outdoor) {
    Integer iD = proximoID;
    AluguelBean alu = new AluguelBean(iD, numDias, valor, observacao, outdoor);
    if (!alu.erroDados()) {
      listaAlugueis.put(iD, alu);
      proximoID++;
    } else {
      alu = null;
    }
    return alu;
  }
  
  // Método para cadastrar um aluguel. Se o objeto do aluguel que é passado já existe, então
  // o item da lista de aluguéis é atualizado. Caso contrário, o novo aluguel é adicionado.
  public static AluguelBean cadastrar(AluguelBean aluguel) {
    if(existeAluguel(aluguel.getID())) {
      listaAlugueis.put(aluguel.iD, aluguel);
    } else {
      aluguel = cadastrar(aluguel.getNumDias(), aluguel.getValor(), aluguel.getObservacao(), aluguel.getOutdoor());
    }
    return aluguel;
  }
  
  // Método auxiliar que passa o map de aluguéis para um array
  public static AluguelBean[] getArrayAluguel() {
    if (listaAlugueis.size() == 0) {
      return new AluguelBean[0];
    } else {
      return listaAlugueis.values().toArray(new AluguelBean[listaAlugueis.size()]);
    }
  }

  // Método auxiliar que checa se um aluguel existe (pelo iD)
  public static boolean existeAluguel(Integer iD) {
    return listaAlugueis.containsKey(iD);
  }

  // Método auxiliar que retorna um aluguel da lista
  public static AluguelBean getAluguel(Integer iD) {
    return listaAlugueis.get(iD);
  }
  
  // Método auxiliar que retorna uma combobox populada com a lista de aluguéis existentes.
  // Essa combobox usa como texto o descritor de um aluguel.
  public static JComboBox<AluguelBean> getComboBox() {
    JComboBox<AluguelBean> cbxOutdoor = new JComboBox<>();
    // Popula o combobox com os outdoors existentes
    cbxOutdoor.setModel(Auxiliares.listaComboBox(AluguelBean.getArrayAluguel()));
    // Define um renderer para determinar quais dados do Outdoor vão aparecer na combobox.
    // Para isso, foi criado uma classe DescritorComboBox que usa do método getDescricao de uma
    // subclasse da classe Dados, como a clase aluguel ou outdoor.
    cbxOutdoor.setRenderer(new DescritorComboBox());
    return cbxOutdoor;
  }

  // Retorna o map de aluguéis
  public static Map<Integer, AluguelBean> getListaAlugueis() {
    return listaAlugueis;
  }

  // Faz a conferência de todos os dados de uma instância
  private boolean erroDados() {
    return !validarID() || !validarNumDias() || !validarOutdoor() || !validarValor();
  }

  // Valida um iD de uma instância
  private boolean validarID() {
    return !(iD == null) || !existeAluguel(iD);
  }

  // Validação individual das variáveis
  private boolean validarNumDias() {
    return numDias > 0;
  }

  private boolean validarOutdoor() {
    return !(outdoor == null);
  }

  private boolean validarValor() {
    return valor > 0;
  }

  // Método herdado da classe abstrata de dados. Fornece uma descrição do objeto
  // para ser colocada nas combobox
  @Override
  public String getDescricao() {
    return numDias + "-" + String.format("R$ %.2f", valor) + ":" + outdoor.getDescricao();
  }

  // Getters e Setters
  public int getNumAlugueis() {
    return listaAlugueis.size();
  }

  public Integer getID() {
    return iD;
  }

  public int getNumDias() {
    return numDias;
  }

  public String getObservacao() {
    return observacao;
  }

  public OutdoorBean getOutdoor() {
    return outdoor;
  }

  public double getValor() {
    return valor;
  }

  // Grava um conjunto de dados em uma instância
  public void gravarDados(int numDias, double valor, String observacao, OutdoorBean outdoor) {
    setValor(valor);
    setOutdoor(outdoor);
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

  public boolean setOutdoor(OutdoorBean outdoor) {
    this.outdoor = outdoor;
    return validarOutdoor();
  }

  public boolean setValor(double valor) {
    this.valor = valor;
    return validarValor();
  }

  // Construtor usado internamente para um novo aluguel
  private AluguelBean(Integer iD, int numDias, double valor, String observacao, OutdoorBean outdoor) {
    this.iD = iD;
    gravarDados(numDias, valor, observacao, outdoor);
  }

  // Construtor público de um aluguel vazio
  public AluguelBean() {}

}
