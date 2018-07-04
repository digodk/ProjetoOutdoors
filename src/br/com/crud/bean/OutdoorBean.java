package br.com.crud.bean;

import java.util.Arrays;

import br.com.crud.view.Auxiliares;

/**
 * @author 105004 Classe que representa o outdoor. Contém métodos estáticos para
 *         manipular todos os aluguéis e também métodos locais para manipular as
 *         instâncias.
 */
public class OutdoorBean extends Bean {
  /**
   * @author 105004 Enum para os tipos de outdoor. Contém um valor
   *         autodescritivo para exibiçãoem menus.
   */
  public enum TiposOutdoor {
    INDEFINIDO("Indefinido", 0), MADEIRA("Madeira", 1), METAL("Metal", 2), LED("LED", 3);

    private String nome;
    private int tipo;

    //
    /**
     * Converte um número para um tipo de outdoor
     * 
     * @param tipo
     *          valor numérico que representa o tipo
     * @return valor TiposOutdoor associado ao número
     */
    public static TiposOutdoor converterTipo(int tipo) {
      return Arrays.asList(TiposOutdoor.values())
              .stream()
              .filter(v -> v.toInt() == tipo)
              .findFirst()
              .orElse(INDEFINIDO);
    }

    public String getNome() {
      return nome;
    }

    /**
     * @return número associado ao Tipo de Outdoor
     */
    public int toInt() {
      return tipo;
    }

    TiposOutdoor(String nome, int tipo) {
      this.nome = nome;
      this.tipo = tipo;
    }
  }

  private String endereco = "";
  private String cidade = "";

  private TiposOutdoor tipo = TiposOutdoor.INDEFINIDO;

  @Override
  public String getDescricao() {
    return tipo.getNome() + "-" + cidade + "-" + Auxiliares.safeLeftSubstring(endereco, 15);
  }

  public String getCidade() {
    return cidade;
  }

  public String getEndereco() {
    return endereco;
  }

  public TiposOutdoor getTipo() {
    return tipo;
  }

  /**
   * Grava vários valores em um método
   * 
   * @param endereco
   *          endereço do outdoor
   * @param cidade
   *          cidade do outdoor
   * @param tipo
   *          tipo de outdoors
   */
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

  public void setTipo(int tipo) {
    setTipo(TiposOutdoor.converterTipo(tipo));
  }

  /**
   * Construtor com configuração de valores
   * 
   * @param endereco
   *          endereço do outdoor
   * @param cidade
   *          cidade do outdoor
   * @param tipo
   *          tipo de outdoors
   */
  public OutdoorBean(String endereco, String cidade, TiposOutdoor tipo) {
    gravarDados(endereco, cidade, tipo);
  }

  /**
   * Construtor público
   */
  public OutdoorBean() {

  }
}
