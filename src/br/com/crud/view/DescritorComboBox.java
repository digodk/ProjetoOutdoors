package br.com.crud.view;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import br.com.crud.bean.Bean;

@SuppressWarnings("serial")
public class DescritorComboBox extends JLabel implements ListCellRenderer<Bean> {

  @Override
  public Component getListCellRendererComponent(JList<? extends Bean> list, Bean value, int index,
      boolean isSelected, boolean cellHasFocus) {

    if (isSelected) {
      setBackground(list.getSelectionBackground());
      setForeground(list.getSelectionForeground());
    } else {
      setBackground(list.getBackground());
      setForeground(list.getForeground());
    }

    setText(value.getDescricao());
    setFont(list.getFont());

    return this;
  }



}
