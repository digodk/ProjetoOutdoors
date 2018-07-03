package br.com.crud.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.Arrays;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import br.com.crud.bean.OutdoorBean;
import br.com.crud.bean.OutdoorBean.TiposOutdoor;
import br.com.crud.dao.OutdoorDao;

@SuppressWarnings("serial")
public class CadastroOutdoor extends JDialog {

  private static boolean telaCarregada = false;
  private static CadastroOutdoor frame;
  private static OutdoorBean outdoorCadastrado, outdoorEmCadastro;
  private static JPanel contentPane;
  private static JTextField txtCidade;
  private static JTextArea txtEndereco;
  private static JComboBox<TiposOutdoor> cbxTipoOutdoor;
  private static String cidade, endereco;
  private static TiposOutdoor tipo;

  // ---Procedimentos de gravação do novo Outdoor
  // Checa inputs
  private static boolean dadosOK() {
    if ("".equals(cidade)) {
      txtCidade.requestFocus();
      Auxiliares.mensagemErro("Você deve informar uma cidade!");
      return false;
    }
    if ("".equals(endereco)) {
      txtEndereco.requestFocus();
      Auxiliares.mensagemErro("Você deve informar um endereço!");
      return false;
    }
    return true;
  }

  // Grava os inputs nas variáveis necessárias
  private static void lerInputs() {
    cidade = txtCidade.getText();
    endereco = txtEndereco.getText();
    tipo = TiposOutdoor.values()[cbxTipoOutdoor.getSelectedIndex()];
  }

  // Salva os dados depois de checar se os inputs estão conformes. Argumento de ActionEvent para
  // poder ser usado como actionListener
  private static void salvarDados(ActionEvent e) {
    lerInputs();
    if (dadosOK()) {
      outdoorEmCadastro.setCidade(cidade);
      outdoorEmCadastro.setEndereco(endereco);
      outdoorEmCadastro.setTipo(tipo);
      OutdoorDao.cadastrar(outdoorEmCadastro);
      outdoorCadastrado = outdoorEmCadastro;
      Auxiliares.dispararEventoFecharJanela(frame);
    }
  }

  public static OutdoorBean cadastrar() {
    return cadastrar(new OutdoorBean());
  }

  // ---Métodos de cadastro. É possível cadastrar um novo outdoor ou editar um existente
  public static OutdoorBean cadastrar(OutdoorBean out) {
    if (!telaCarregada) {
      try {
        frame = new CadastroOutdoor();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    outdoorCadastrado = null;
    txtCidade.setText(out.getCidade());
    txtEndereco.setText(out.getEndereco());
    int index = Arrays.asList(TiposOutdoor.values()).indexOf(out.getTipo());
    cbxTipoOutdoor.setSelectedIndex(index);
    
    frame.setVisible(true);

    return outdoorCadastrado;
  }

  // Cria e configura o frame
  private CadastroOutdoor() {
    // ---Configuração de listeners
    // Classe anônima para ação do botão cadastrar
    ActionListener acaoOK = CadastroOutdoor::salvarDados;
    // Classe anônima para ação do botão cancelar
    ActionListener acaoCancelar = Auxiliares.getAcaoFecharJanela(this);
    // Classe anônima para ação de ocultar janela. Esse evento é disparado pelo botão cancelar ou
    // pelo botão de fechar janela.
    WindowListener listenerFecharJanela = Auxiliares.getListenerOcultarJanela(this);

    // ---Labels e controles
    JLabel lblCidade = new JLabel("Cidade");
    JLabel lblEndereo = new JLabel("Endereço");
    JLabel lblTipoDeOutdoor = new JLabel("Tipo de Outdoor");

    // Tipo do Outdoor
    cbxTipoOutdoor = new JComboBox<TiposOutdoor>();
    cbxTipoOutdoor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    // Popula a combobox com os valores de tipos de outdoor
    cbxTipoOutdoor.setModel(Auxiliares.listaComboBox(TiposOutdoor.values()));
    // Cidade
    txtCidade = new JTextField();
    txtCidade.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    txtCidade.setColumns(10);
    // Endereço
    txtEndereco = new JTextArea();
    txtEndereco.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    // Botões
    JButton btnGravar = new JButton("Gravar");
    JButton btnCancelar = new JButton("Cancelar");

    // ---Listeners e validadores
    // Listeners para os botões
    btnGravar.addActionListener(acaoOK);
    btnCancelar.addActionListener(acaoCancelar);
    // Adiciona o listener para evento de fechar janela
    addWindowListener(listenerFecharJanela);

    // ---Painel e layout do form
    setModalityType(ModalityType.APPLICATION_MODAL);
    setTitle("Cadastro de Outdoors");
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setBounds(100, 100, 332, 323);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    GroupLayout gl_contentPane = new GroupLayout(contentPane);
    gl_contentPane
        .setHorizontalGroup(
            gl_contentPane
                .createParallelGroup(
                    Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addGap(26).addGroup(gl_contentPane
                    .createParallelGroup(Alignment.LEADING)
                    .addComponent(lblTipoDeOutdoor, GroupLayout.PREFERRED_SIZE, 103,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipoOutdoor, GroupLayout.PREFERRED_SIZE, 103,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCidade)
                    .addComponent(
                        txtCidade, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEndereo)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                        .addGroup(gl_contentPane.createSequentialGroup().addComponent(btnGravar)
                            .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                            .addComponent(btnCancelar))
                        .addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, 237,
                            GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(41, Short.MAX_VALUE)));
    gl_contentPane
        .setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
                gl_contentPane.createSequentialGroup().addComponent(lblTipoDeOutdoor).addGap(13)
                    .addComponent(cbxTipoOutdoor, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18).addComponent(lblCidade).addGap(7)
                    .addComponent(txtCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addGap(13).addComponent(lblEndereo).addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(txtEndereco, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addGap(18).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnCancelar).addComponent(btnGravar))
                    .addGap(19)));
    contentPane.setLayout(gl_contentPane);
    telaCarregada = true;
  }
}
