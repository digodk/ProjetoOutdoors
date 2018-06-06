package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dados.Aluguel;
import dados.Auxiliares;

@SuppressWarnings("serial")
public class SelecionarAluguel extends JDialog {

  private static SelecionarAluguel frame;
  private static Aluguel aluguelEditado;
  private static JComboBox<Aluguel> cbxAluguel;
  private static JPanel contentPane;
  private static boolean telaCarregada;

  /**
   * Launch the application.
   */
  public static Aluguel selecionar() {
    if (!telaCarregada) {
      try {
        frame = new SelecionarAluguel();

      } catch (Exception e) {
        e.printStackTrace();
      }
      return aluguelEditado;
    }
    aluguelEditado = null;
    frame.setVisible(true);
    return aluguelEditado;
  }

  private static void editarDados(ActionEvent e) {
    Aluguel alu = cbxAluguel.getItemAt(cbxAluguel.getSelectedIndex());
    aluguelEditado = CadastroAlugueis.cadastrar(alu);
    Auxiliares.dispararEventoFecharJanela(frame);
  }

  public SelecionarAluguel() {

    // ---Configuração de listeners
    ActionListener acaoOK = SelecionarAluguel::editarDados;
    // Classe anônima para ação do botão cancelar
    ActionListener acaoCancelar = Auxiliares.getAcaoFecharJanela(this);
    // Classe anônima para ação de ocultar janela. Esse evento é disparado pelo botão cancelar ou
    // pelo botão de fechar janela.
    WindowListener listenerFecharJanela = Auxiliares.getListenerOcultarJanela(this);
    // ---Labels e controles
    JLabel lblSelecioneOOutdoor = new JLabel("Selecione o Outdoor");
    // Outdoor
    cbxAluguel = Aluguel.getComboBox();
    // Botões
    JButton btnEditar = new JButton("Editar");
    JButton btnCancelar = new JButton("Cancelar");

    // ---Listeners e validadores
    // Listeners para os botões
    btnCancelar.addActionListener(acaoCancelar);
    btnEditar.addActionListener(acaoOK);
    // Adiciona o listener para evento de fechar janela
    addWindowListener(listenerFecharJanela);

    setModalityType(ModalityType.APPLICATION_MODAL);
    setTitle("Editar Outdoor");
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setBounds(100, 100, 307, 173);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);

    GroupLayout gl_contentPane = new GroupLayout(contentPane);
    gl_contentPane
        .setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addGap(51)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblSelecioneOOutdoor)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                            .addComponent(cbxAluguel, Alignment.LEADING, 0,
                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(Alignment.LEADING,
                                gl_contentPane.createSequentialGroup().addComponent(btnEditar)
                                    .addGap(18).addComponent(btnCancelar))))
                    .addContainerGap(62, Short.MAX_VALUE)));
    gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_contentPane.createSequentialGroup().addComponent(lblSelecioneOOutdoor)
            .addGap(10)
            .addComponent(cbxAluguel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE)
            .addGap(18).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                .addComponent(btnEditar).addComponent(btnCancelar))
            .addContainerGap(25, Short.MAX_VALUE)));
    contentPane.setLayout(gl_contentPane);
    telaCarregada = true;
  }
}
