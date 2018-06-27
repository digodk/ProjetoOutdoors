package br.com.crud.view;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class Auxiliares {
  public static String safeLeftSubstring(String s, int numChars) {
    if (s.equals(null)) {
      return null;
    }
    int realnumChar = Math.min(numChars, s.length());
    return s.substring(0, realnumChar);
  }

  public static <E> ComboBoxModel<E> listaComboBox(E[] valores) {
    return new DefaultComboBoxModel<E>(valores);
  }

  // Método para informar o fechamento da janela
  public static void dispararEventoFecharJanela(Window source) {
    source.dispatchEvent(new WindowEvent(source, WindowEvent.WINDOW_CLOSING));
  }

  public static ActionListener getAcaoFecharJanela(Window source) {
    return new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        dispararEventoFecharJanela(source);
      }
    };

  }

  public static WindowAdapter getListenerOcultarJanela(Window source) {
    return new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent arg0) {
        source.setVisible(false);
      }
    };
  }
  
  public static void mensagemErro(String mensagem) {
    JOptionPane.showMessageDialog(null,mensagem);
  }
  
  public static void mensagemOK(String mensagem) {
    JOptionPane.showMessageDialog(null,mensagem);
  }
}
