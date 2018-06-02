package dados;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Outdoor {
  public enum TiposOutdoor {
    INDEFINIDO, MADEIRA, METAL, LED;
  }

  private static Map<Integer, Outdoor> listaOudoors = new HashMap<>();
  private static Integer proximoID = 0;
  private Integer iD = null;
  private String endereco = "";
  private String cidade = "";
  private TiposOutdoor tipo = TiposOutdoor.INDEFINIDO;

  static {

  }

  public static List<Aluguel> alugueisAssociados(Outdoor outdoor) {
    return Aluguel.getListaAlugueis()
            .values()
            .stream()
            .filter(v -> v.getOutdoor().equals(outdoor))
            .collect(Collectors.toList());
  }

  public static boolean existeOutdoor(Integer iD) {
    return listaOudoors.containsKey(iD);
  }

  public static Outdoor cadastrar(String endereco, String cidade, TiposOutdoor tipo) {
    Integer iD = proximoID;
    Outdoor out = new Outdoor(iD, endereco, cidade, tipo);
    if (!out.dadosFaltando()) {
      listaOudoors.put(iD, out);
      proximoID++;
    } else {
      out = null;
    }
    return out;
  }

  public static Outdoor getOutdoor(Integer iD) {
    return listaOudoors.get(iD);
  }

  private boolean dadosFaltando() {
    boolean check = false;
    check |= iD.equals(null) || endereco.equals("") || cidade.equals("")
            || tipo.equals(TiposOutdoor.INDEFINIDO);
    return check;
  }

  private void gravarDados(Integer iD, String endereco, String cidade, TiposOutdoor tipo) {
    this.iD = iD;
    gravarDados(endereco, cidade, tipo);
  }

  public void gravarDados(String endereco, String cidade, TiposOutdoor tipo) {
    setEndereco(endereco);
    setCidade(cidade);
    setTipo(tipo);
  }

  public Integer getID() {
    return iD;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public TiposOutdoor getTipo() {
    return tipo;
  }

  public void setTipo(TiposOutdoor tipo) {
    this.tipo = tipo;
  }

  public Outdoor() {

  }

  private Outdoor(Integer iD, String endereco, String cidade, TiposOutdoor tipo) {
    gravarDados(iD, endereco, cidade, tipo);
  }
}
