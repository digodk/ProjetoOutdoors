package br.com.crud.view;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 * @author Diogo Funções auxiliares para as telas e comportamento do programa
 */
public class Auxiliares {
  public static String safeLeftSubstring(String s, int numChars) {
    if (s.equals(null)) {
      return null;
    }
    int realnumChar = Math.min(numChars, s.length());
    return s.substring(0, realnumChar);
  }

  /**
   * Função auxiliar para gerar um modelo de combo box com base em um array de valores
   * 
   * @param valores array de valores de uma classe E
   * @param <E> o tipo de dados a ser inserido na combo box
   * @return Um ComboBoxModel da classe E com base no array
   */
  public static <E> ComboBoxModel<E> listaComboBox(E[] valores) {
    return new DefaultComboBoxModel<E>(valores);
  }

  /**
   * Método para informar o fechamento da janela
   * 
   * @param source a janela a ser fechada
   */
  public static void dispararEventoFecharJanela(Window source) {
    source.dispatchEvent(new WindowEvent(source, WindowEvent.WINDOW_CLOSING));
  }

  /**
   * Retorna um listener para botões que fecham janelas
   * 
   * @param source a janela a ser fechada pelo botão
   * @return um ActionListener que dispara a ação de fechar a janela
   */
  public static ActionListener getAcaoFecharJanela(Window source) {
    return new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        dispararEventoFecharJanela(source);
      }
    };

  }

  /**
   * Método para obter um listener que oculta a janela quando necessário
   * 
   * @param source a janela que vai ser fechada
   * @return um WindowAdapter que recebe a ação de windowClosing do source
   */
  public static WindowAdapter getListenerOcultarJanela(Window source) {
    return new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent arg0) {
        source.setVisible(false);
      }
    };
  }

  public static void mensagemErro(String mensagem) {
    JOptionPane.showMessageDialog(null, mensagem);
  }

  public static void mensagemOK(String mensagem) {
    JOptionPane.showMessageDialog(null, mensagem);
  }
}
