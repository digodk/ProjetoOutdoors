package dados;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;

public class Outdoor extends Dados {
  public enum TiposOutdoor {
    INDEFINIDO("Indefinido"), MADEIRA("Madeira"), METAL("Metal"), LED("LED");

    private String nome;

    public String getNome() {
      return nome;
    }

    TiposOutdoor(String nome) {
      this.nome = nome;
    }
  }

  private static Map<Integer, Outdoor> listaOudoors = new HashMap<>();
  private static Integer proximoID = 0;
  static {
    Outdoor.cadastrar("Rua 1 número 2", "Blumenau", TiposOutdoor.MADEIRA);
    Outdoor.cadastrar("Rua a0 número b", "Blumenau", TiposOutdoor.LED);
  }
  private Integer iD = null;
  private String endereco = "";
  private String cidade = "";

  private TiposOutdoor tipo = TiposOutdoor.INDEFINIDO;

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
  
  public static Outdoor cadastrar(Outdoor outdoor) {
    if(existeOutdoor(outdoor.getID())) {
      listaOudoors.put(outdoor.iD, outdoor);
    } else {
      outdoor = cadastrar(outdoor.getEndereco(), outdoor.getCidade(), outdoor.tipo);
    }
    return outdoor;
  }

  public static boolean existeOutdoor(Integer iD) {
    return listaOudoors.containsKey(iD);
  }

  public static Outdoor[] getArrayOutdoor() {
    if (listaOudoors.size() == 0) {
      return new Outdoor[0];
    } else {
      return listaOudoors.values().toArray(new Outdoor[listaOudoors.size()]);
    }
  }

  public static List<Outdoor> getListaOutdoors() {
    return (List<Outdoor>) listaOudoors.values();
  }

  public static Outdoor getOutdoor(Integer iD) {
    return listaOudoors.get(iD);
  }
  
  public static JComboBox<Outdoor> getComboBox() {
    JComboBox<Outdoor> cbxOutdoor = new JComboBox<>();
    // Popula o combobox com os outdoors existentes
    cbxOutdoor.setModel(Auxiliares.listaComboBox(Outdoor.getArrayOutdoor()));
    // Define um renderer para determinar quais dados do Outdoor vão aparecer na combobox.
    // Para isso, foi criado uma classe DescritorComboBox que usa do método getDescricao de uma
    // subclasse da classe Dados, como a clase aluguel ou outdoor.
    cbxOutdoor.setRenderer(new DescritorComboBox());
    return cbxOutdoor;
  }

  private boolean dadosFaltando() {
    boolean check = false;
    check |= iD==null || endereco.equals("") || cidade.equals("")
        || tipo.equals(TiposOutdoor.INDEFINIDO);
    return check;
  }

  private void gravarDados(Integer iD, String endereco, String cidade, TiposOutdoor tipo) {
    this.iD = iD;
    gravarDados(endereco, cidade, tipo);
  }

  public List<Aluguel> getAlugueisAssociados() {
    return Aluguel.alugueisAssociados(this);
  }

  public String getCidade() {
    return cidade;
  }

  @Override
  public String getDescricao() {
    if (!dadosFaltando()) {
      return tipo.getNome() + "-" + cidade + "-" + Auxiliares.safeLeftSubstring(endereco, 15);
    } else {
      return "----";
    }
  }

  public String getEndereco() {
    return endereco;
  }

  public Integer getID() {
    return iD;
  }

  public int getNumOutdoors() {
    return listaOudoors.size();
  }

  public TiposOutdoor getTipo() {
    return tipo;
  }

  public void gravarDados(String endereco, String cidade, TiposOutdoor tipo) {
    setEndereco(endereco);
    setCidade(cidade);
    setTipo(tipo);
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public void setTipo(TiposOutdoor tipo) {
    this.tipo = tipo;
  }

  private Outdoor(Integer iD, String endereco, String cidade, TiposOutdoor tipo) {
    gravarDados(iD, endereco, cidade, tipo);
  }

  public Outdoor() {

  }
}
