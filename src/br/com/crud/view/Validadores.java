package br.com.crud.view;

import br.com.crud.bean.AluguelBean;
import br.com.crud.bean.OutdoorBean;
import br.com.crud.bean.UsuarioBean;
import br.com.crud.bean.UsuarioBean.NiveisUsuarios;
import br.com.crud.dao.AluguelDao;
import br.com.crud.dao.OutdoorDao;
import br.com.crud.dao.UsuarioDao;

public class Validadores {
  // Métodos que checam se existe no BD o ID informado. Usa o procedimento da
  // classe DAO
  public static boolean existeOutdoor(int idOutdoor) {
    return OutdoorDao.inst().existeID(idOutdoor);
  }

  public static boolean existeOutdoor(OutdoorBean outdoor) {
    return existeOutdoor(outdoor.getID());
  }

  public static boolean existeUsuario(int idUsuario) {
    return UsuarioDao.inst().existeID(idUsuario);
  }
  
  public static boolean existeUsuario(String nome) {
    UsuarioBean usu = UsuarioDao.inst().getUsuario(nome);
    return !(usu==null);
  }

  public static boolean existeUsuario(UsuarioBean usu) {
    return UsuarioDao.inst().existeID(usu.getID());
  }

  public static boolean existeAluguel(int idAluguel) {
    return AluguelDao.inst().existeID(idAluguel);
  }

  public static boolean existeAluguel(AluguelBean alu) {
    return AluguelDao.inst().existeID(alu.getID());
  }

  public static boolean numDias(int numDias) {
    return numDias > 0;
  }
  
  public static boolean valorAluguel(double valor) {
    return valor >=0;
  }
  
  public static boolean nomeUsuario(String nome) {
    return !("".equals(nome));
  }
  
  public static boolean senhaUsuario(String senha) {
    return !("".equals(senha));
  }
  
  public static boolean nivelUsuario(NiveisUsuarios acesso) {
    return !acesso.equals(NiveisUsuarios.INDEFINIDO);
  }
}
