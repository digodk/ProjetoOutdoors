package br.com.crud.bean;

public abstract class Bean {

  protected Integer iD = null;
  
  public Integer getID() {
    return iD;
  }
  
  public void setID(Integer iD) {
    this.iD = iD;
  }
  
  abstract public String getDescricao();
}
