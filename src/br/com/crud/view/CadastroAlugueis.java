package br.com.crud.view;

import java.awt.Color;
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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import br.com.crud.bean.AluguelBean;
import br.com.crud.bean.Bean;
import br.com.crud.bean.OutdoorBean;
import br.com.crud.dao.AluguelDao;
import br.com.crud.dao.OutdoorDao;

@SuppressWarnings("serial")
public class CadastroAlugueis extends JDialog {

  private static boolean telaCarregada = false;
  private static CadastroAlugueis frame;
  private static JPanel contentPane;
  private static JTextField txtValor;
  private static JSpinner txtDias;
  private static JComboBox<Bean> cbxOutdoor;
  private static JTextArea txtObservacoes;
  private static AluguelBean aluguelCadastrado, aluguelEmCadastro;
  private static int numDias;
  private static double valor;
  private static String observacao;
  private static OutdoorBean outdoor;

  // ---Procedimentos de gravação do novo aluguel
  // Valida inputs
  private static boolean inputsOK() {
    if (!Validador.numDias(numDias)) {
      Auxiliares.mensagemErro("Número inválido de dias");
      txtDias.requestFocus();
      return false;
    }
    if (!Validador.existeOutdoor(outdoor)) {
      Auxiliares.mensagemErro("Outdoor inválido, por favor selecione outro.");
      cbxOutdoor.requestFocus();
      return false;
    }
    if (!Validador.valorAluguel(valor)) {
      Auxiliares.mensagemErro("Valor inválido");
      txtValor.requestFocus();
      return false;
    }
    return true;
  }

  // Grava os inputs nas variáveis necessárias
  private static void lerInputs() {
    numDias = (int) (txtDias.getValue());
    valor = Double.valueOf(txtValor.getText().replace("R$", ""));
    outdoor = OutdoorDao.inst().getLista().get(cbxOutdoor.getSelectedIndex());
    observacao = txtObservacoes.getText();
  }

  // Salva os dados depois de checar se os inputs estão conformes. Argumento de
  // ActionEvent para
  // poder ser usado como actionListener
  private static void salvarDados(ActionEvent e) {
    lerInputs();
    if (inputsOK()) {
      aluguelEmCadastro.setNumDias(numDias);
      aluguelEmCadastro.setObservacao(observacao);
      aluguelEmCadastro.setOutdoor(outdoor.getID());
      aluguelEmCadastro.setValor(valor);
      AluguelDao.inst().cadastrar(aluguelEmCadastro);
      aluguelCadastrado = aluguelEmCadastro;
      Auxiliares.dispararEventoFecharJanela(frame);
    }
  }

  // ---Métodos de cadastro. É possível cadastrar um novo aluguel ou editar um
  // existente
  public static AluguelBean cadastrar() {
    return cadastrar(new AluguelBean());
  }

  public static AluguelBean cadastrar(AluguelBean alu) {
    if (!telaCarregada) {
      try {
        frame = new CadastroAlugueis();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    txtDias.setValue(alu.getNumDias());
    txtValor.setText(String.format("R$ %.2f", alu.getValor()));
    int index =
            alu.getOutdoor() == null ? 0 : OutdoorDao.inst().getLista().indexOf(alu.getOutdoor());
    cbxOutdoor.setSelectedIndex(index);
    aluguelEmCadastro = alu;
    aluguelCadastrado = null;
    frame.setVisible(true);
    return aluguelCadastrado;
  }

  // Cria e configura o frame
  private CadastroAlugueis() {

    // ---Configuração de listeners
    // Classe anônima para ação do botão cadastrar
    ActionListener acaoOk = CadastroAlugueis::salvarDados;
    // Classe anônima para ação do botão cancelar
    ActionListener acaoCancelar = Auxiliares.getAcaoFecharJanela(this);
    // Classe anônima para ação de ocultar janela. Esse evento é disparado pelo
    // botão cancelar ou
    // pelo botão de fechar janela.
    WindowListener listenerFecharJanela = Auxiliares.getListenerOcultarJanela(this);

    // ---Labels e controles
    JLabel lblOutdoor = new JLabel("Outdoor");
    JLabel lblValorDeAluguel = new JLabel("Valor de Aluguel");
    JLabel lblDias = new JLabel("Dias");
    JLabel lblObservaes = new JLabel("Observações");
    // Caixa de valor de aluguel
    txtValor = new JTextField();
    txtValor.setText("R$ 0,00");
    txtValor.setColumns(10);
    // Caixa de dias de duração do aluguel
    txtDias = new JSpinner();
    txtDias.setModel(new SpinnerNumberModel(1, 1, null, 1));
    // Botões
    JButton btnCadastrar = new JButton("Gravar");
    JButton btnCancelar = new JButton("Cancelar");
    // Observações
    txtObservacoes = new JTextArea();
    txtObservacoes.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
    // Combobox para seleção do Outdoor
    cbxOutdoor = OutdoorDao.inst().getComboBox();

    // ---Listeners e validadores
    // Listeners para os botões
    btnCancelar.addActionListener(acaoCancelar);
    btnCadastrar.addActionListener(acaoOk);
    // Adiciona o listener para evento de fechar janela
    addWindowListener(listenerFecharJanela);

    // ---Painel e layout do form
    setModalityType(ModalityType.APPLICATION_MODAL);
    setTitle("Cadastrar Aluguel");
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setBounds(100, 100, 332, 344);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    GroupLayout gl_contentPane = new GroupLayout(contentPane);
    gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                            .addComponent(cbxOutdoor, 0, 280, Short.MAX_VALUE)
                            .addComponent(lblOutdoor, GroupLayout.PREFERRED_SIZE, 85,
                                    GroupLayout.PREFERRED_SIZE)
                            .addGroup(gl_contentPane.createSequentialGroup()
                                    .addGroup(gl_contentPane
                                            .createParallelGroup(Alignment.LEADING, false)
                                            .addComponent(lblValorDeAluguel,
                                                    GroupLayout.DEFAULT_SIZE,
                                                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtValor, 0, 0, Short.MAX_VALUE))
                                    .addGap(18)
                                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                            .addComponent(txtDias, GroupLayout.PREFERRED_SIZE, 63,
                                                    GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblDias))
                                    .addGap(20))
                            .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 82,
                                            GroupLayout.PREFERRED_SIZE)
                                    .addGap(18)
                                    .addComponent(btnCancelar))
                            .addComponent(lblObservaes)
                            .addComponent(txtObservacoes, GroupLayout.DEFAULT_SIZE, 280,
                                    Short.MAX_VALUE))
                    .addContainerGap()));
    gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(lblOutdoor, GroupLayout.PREFERRED_SIZE, 22,
                            GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(cbxOutdoor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                            .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(lblValorDeAluguel)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(txtValor, GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(lblDias)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(txtDias, GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(lblObservaes)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(txtObservacoes)
                    .addGap(79)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                            .addComponent(btnCadastrar)
                            .addComponent(btnCancelar))
                    .addContainerGap()));
    contentPane.setLayout(gl_contentPane);
    telaCarregada = true;
  }
}
