package br.com.crud.bean;

import java.util.Arrays;

import br.com.crud.bean.OutdoorBean.TiposOutdoor;
import br.com.crud.view.Auxiliares;

/*
 * Classe que representa o outdoor. Contém métodos estáticos para manipular todos os 
 * aluguéis e também métodos locais para manipular as instâncias.
*/
public class OutdoorBean extends Bean {
  // Enum para os tipos de outdoor. Contém um valor autodescritivo para exibição
  // em menus.
  public enum TiposOutdoor {
    INDEFINIDO("Indefinido", 0), MADEIRA("Madeira", 1), METAL("Metal", 2), LED("LED", 3);

    private String nome;
    private int tipo;

    // Converte um número para um tipo de outdoor
    public static TiposOutdoor converterTipo(int tipo) {
      return Arrays.asList(TiposOutdoor.values())
              .stream()
              .filter(v -> v.getTipo() == tipo)
              .findFirst()
              .orElse(INDEFINIDO);
    }

    public String getNome() {
      return nome;
    }

    public int getTipo() {
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

  // Método herdado da classe abstrata de dados. Fornece uma descrição do objeto
  // para ser colocada nas combobox
  @Override
  public String getDescricao() {
    return tipo.getNome() + "-" + cidade + "-" + Auxiliares.safeLeftSubstring(endereco, 15);
  }

  // Settters e Getters
  public String getCidade() {
    return cidade;
  }

  public String getEndereco() {
    return endereco;
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
  
  public void setTipo(int tipo) {
    setTipo(TiposOutdoor.converterTipo(tipo));
  }

  // Construtor de uma instância
  public OutdoorBean(String endereco, String cidade, TiposOutdoor tipo) {
    gravarDados(endereco, cidade, tipo);
  }

  // Construtor genérico público
  public OutdoorBean() {

  }
}
