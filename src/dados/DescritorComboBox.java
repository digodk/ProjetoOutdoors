package dados;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class DescritorComboBox extends JLabel implements ListCellRenderer<Dados> {

  @Override
  public Component getListCellRendererComponent(JList<? extends Dados> list, Dados value, int index,
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
