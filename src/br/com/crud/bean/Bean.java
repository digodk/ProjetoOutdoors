package br.com.crud.bean;

/**
 * @author Diogo Classe abstrata para todos os Beans de dados. Abstrai a propriedade ID e contém um
 *         método abstrato para gerar uma descrição da instância Esse método é usado para gerar as
 *         comboboxes
 */
public abstract class Bean {

  protected Integer iD = null;

  public Integer getID() {
    return iD;
  }

  /**
   * Método usado para configurar o iD do Bean. Usado quando retorna o objeto do BD. Associar um
   * valor inválido pode evitar gravação de dados ou escrever na linha errada do BD.
   * 
   * @param iD ID do Bean no Banco de dados.
   */
  public void setID(Integer iD) {
    this.iD = iD;
  }

  /**
   * Método abstrato que gera a descrição da instância da classe. Ele é particular de cada
   * implementação da classe abstrata. Os valores desse método são usados para gerar comboboxes
   * 
   * @return Um valor string que descreve o objeto
   */

  abstract public String getDescricao();
}
