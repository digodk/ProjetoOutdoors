package br.com.crud.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;

import br.com.crud.view.Auxiliares;
import br.com.crud.view.DescritorComboBox;
import br.com.interfaces.Descritor;

/*
 * Classe que representa o outdoor. Contém métodos estáticos para manipular todos os 
 * aluguéis e também métodos locais para manipular as instâncias.
 * Mantém uma lista de todos os outdoors cadastrados.
*/
public class OutdoorBean implements Descritor {
  // Enum para os tipos de outdoor. Contém um valor autodescritivo para exibição em menus.
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
  // Map de outdoors cadastrados
  private static Map<Integer, OutdoorBean> listaOudoors = new HashMap<>();
  private static Integer proximoID = 0;
  // Cadastro inicial de dois outdoors para testes
  static {
    OutdoorBean.cadastrar("Rua 1 número 2", "Blumenau", TiposOutdoor.MADEIRA);
    OutdoorBean.cadastrar("Rua a0 número b", "Blumenau", TiposOutdoor.LED);
  }
  private Integer iD = null;
  private String endereco = "";
  private String cidade = "";
  
  private TiposOutdoor tipo = TiposOutdoor.INDEFINIDO;
  // Método para cadastrar um novo outdoor. Retorna o novo outdoor cadastrado.
  public static OutdoorBean cadastrar(String endereco, String cidade, TiposOutdoor tipo) {
    Integer iD = proximoID;
    OutdoorBean out = new OutdoorBean(iD, endereco, cidade, tipo);
    if (!out.dadosFaltando()) {
      listaOudoors.put(iD, out);
      proximoID++;
    } else {
      out = null;
    }
    return out;
  }
  // Método para cadastrar um outdoor. Se o objeto do outdoor que é passado já existe, então
  // o item da lista de outdoors é atualizado. Caso contrário, o novo outdoor é adicionado.
  public static OutdoorBean cadastrar(OutdoorBean outdoor) {
    if(existeOutdoor(outdoor.getID())) {
      listaOudoors.put(outdoor.iD, outdoor);
    } else {
      outdoor = cadastrar(outdoor.getEndereco(), outdoor.getCidade(), outdoor.tipo);
    }
    return outdoor;
  }

  // Método auxiliar que checa se um aluguel existe (pelo iD)
  public static boolean existeOutdoor(Integer iD) {
    return listaOudoors.containsKey(iD);
  }

  // Método auxiliar que passa o map de aluguéis para um array
  public static OutdoorBean[] getArrayOutdoor() {
    if (listaOudoors.size() == 0) {
      return new OutdoorBean[0];
    } else {
      return listaOudoors.values().toArray(new OutdoorBean[listaOudoors.size()]);
    }
  }

  // Retorna um List com os outdoors cadastrados
  public static List<OutdoorBean> getListaOutdoors() {
    return (List<OutdoorBean>) listaOudoors.values();
  }

  // Retorna um Outdoor pelo iD
  public static OutdoorBean getOutdoor(Integer iD) {
    return listaOudoors.get(iD);
  }
  
  // Método auxiliar que retorna uma combobox populada com a lista de aluguéis existentes.
  // Essa combobox usa como texto o descritor de um aluguel.
  public static JComboBox<OutdoorBean> getComboBox() {
    JComboBox<OutdoorBean> cbxOutdoor = new JComboBox<>();
    // Popula o combobox com os outdoors existentes
    cbxOutdoor.setModel(Auxiliares.listaComboBox(OutdoorBean.getArrayOutdoor()));
    // Define um renderer para determinar quais dados do Outdoor vão aparecer na combobox.
    // Para isso, foi criado uma classe DescritorComboBox que usa do método getDescricao de uma
    // subclasse da classe Dados, como a clase aluguel ou outdoor.
    cbxOutdoor.setRenderer(new DescritorComboBox());
    return cbxOutdoor;
  }

  // Confere os dados de uma instância
  private boolean dadosFaltando() {
    boolean check = false;
    check |= iD==null || endereco.equals("") || cidade.equals("")
        || tipo.equals(TiposOutdoor.INDEFINIDO);
    return check;
  }

  // Método privado que grava os dados de uma instância válida
  private void gravarDados(Integer iD, String endereco, String cidade, TiposOutdoor tipo) {
    this.iD = iD;
    gravarDados(endereco, cidade, tipo);
  }

  // Retorna uma lista com todos os aluguéis associados com uma instância
  public List<AluguelBean> getAlugueisAssociados() {
    return AluguelBean.alugueisAssociados(this);
  }
  
  // Método herdado da classe abstrata de dados. Fornece uma descrição do objeto
  // para ser colocada nas combobox
  @Override
  public String getDescricao() {
    if (!dadosFaltando()) {
      return tipo.getNome() + "-" + cidade + "-" + Auxiliares.safeLeftSubstring(endereco, 15);
    } else {
      return "----";
    }
  }

  // Settters e Getters
  public String getCidade() {
    return cidade;
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

  // Método que grava os dados setáveis de uma instância
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

  // Construtor interno de uma instância, com o iD configurado
  private OutdoorBean(Integer iD, String endereco, String cidade, TiposOutdoor tipo) {
    gravarDados(iD, endereco, cidade, tipo);
  }

  // Construtor genérico público
  public OutdoorBean() {

  }
}
