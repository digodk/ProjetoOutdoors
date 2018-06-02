package dados;

import java.util.HashMap;
import java.util.Map;

public class Aluguel {

  private static Map<Integer, Aluguel> listaAlugueis = new HashMap<>();
  private static Integer proximoID = 0;
  private double valor = 0;
  private Integer iD = null;
  private Outdoor outdoor = null;

  public static boolean existeAluguel(Integer iD) {
    return listaAlugueis.containsKey(iD);
  }

  public static Aluguel cadastrar(double valor, Outdoor outdoor) {
    Integer iD = proximoID;
    Aluguel alu = new Aluguel(iD, valor, outdoor);
    if (!alu.erroDados()) {
      listaAlugueis.put(iD, alu);
      proximoID++;
    } else {
      alu = null;
    }
    return alu;
  }

  public static Aluguel getAluguel(Integer iD) {
    return listaAlugueis.get(iD);
  }

  private boolean erroDados() {
    boolean check = false;
    check |= iD.equals(null) || valor < 0 || outdoor.equals(null);
    return check;
  }

  private void gravarDados(Integer iD, double valor, Outdoor outdoor) {
    this.iD = iD;
    gravarDados(valor, outdoor);
  }

  public void gravarDados(double valor, Outdoor outdoor) {
    setValor(valor);
    setOutdoor(outdoor);
  }

  public static Map<Integer, Aluguel> getListaAlugueis() {
    return listaAlugueis;
  }

  public Integer getID() {
    return iD;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public Outdoor getOutdoor() {
    return outdoor;
  }

  public void setOutdoor(Outdoor outdoor) {
    this.outdoor = outdoor;
  }

  public Aluguel() {

  }

  private Aluguel(Integer iD, double valor, Outdoor outdoor) {
    gravarDados(iD, valor, outdoor);
  }

}
