package br.com.crud.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.com.crud.bean.UsuarioBean;
import br.com.crud.bean.UsuarioBean.NiveisUsuarios;
import br.com.crud.dao.UsuarioDao;

/**
 * @author Diogo
 * Tela de cadastro e edição de usuários
 */
@SuppressWarnings("serial")
public class CadastroUsuarios extends JFrame {

  private static CadastroUsuarios frame;
  private static boolean telaCarregada = false;
  private static JPanel contentPane;
  private static JTextField txtNome;
  private static JLabel lblNewLabel;
  private static JPasswordField txtSenha;
  private static JButton btnGravar;
  private static JButton btnCancelar;
  private static JComboBox<NiveisUsuarios> cbxAcesso;
  private static UsuarioBean usuarioCadastrado, usuarioEmCadastro;
  private static String nome;
  private static String senha;
  private static NiveisUsuarios acesso;

  // ---Procedimentos de gravação do novo Usuário
  // Valida inputs
  private static boolean dadosOk() {

    if (!Validador.existeUsuario(nome)) {
      Auxiliares.mensagemErro("O nome digitado já está em uso.");
      ;
      txtNome.requestFocus();
      return false;
    }

    if (!Validador.nomeUsuario(nome)) {
      Auxiliares.mensagemErro("Você deve digitar o nome do usuário!");
      ;
      txtNome.requestFocus();
      return false;
    }

    if (!Validador.senhaUsuario(senha)) {
      Auxiliares.mensagemErro("Você deve digitar uma senha!");
      txtSenha.requestFocus();
      return false;
    }

    if (!Validador.nivelUsuario(acesso)) {
      Auxiliares.mensagemErro("Você deve selecionar um nível de acesso!");
      cbxAcesso.requestFocus();
      return false;
    }
    return true;
  }

  // Grava os inputs nas variáveis necessárias
  private static void lerInputs() {
    nome = txtNome.getText();
    senha = String.valueOf(txtSenha.getPassword());
    acesso = NiveisUsuarios.values()[cbxAcesso.getSelectedIndex()];
  }

  // Salva os dados depois de checar se os inputs estão conformes. Argumento de
  // ActionEvent para
  // poder ser usado como actionListener
  private static void salvarDados(ActionEvent e) {
    lerInputs();
    if (dadosOk()) {
      usuarioEmCadastro.setNome(nome);
      usuarioEmCadastro.setSenha(senha);
      usuarioEmCadastro.setNivel(acesso);
      UsuarioDao.inst().cadastrar(usuarioEmCadastro);
      usuarioCadastrado = usuarioEmCadastro;
      Auxiliares.dispararEventoFecharJanela(frame);
    }
  }

  // ---Métodos de cadastro. É possível cadastrar um novo usuário ou editar um
  // existente
  
  /**
   * Cadastrar um novo usuario
   * @return o objeto UsuarioBean se o cadastro foi bem sucedido, senão null
   */
  public static UsuarioBean cadastrar() {
    return cadastrar(new UsuarioBean());
  }

  /**
   * Editar um usuario existente. A tela é carregada com os valores do usuario
   * @param out o usuario a ser editado
   * @return o UsuarioBean editado
   */
  public static UsuarioBean cadastrar(UsuarioBean usuario) {
    usuarioEmCadastro = usuario;
    if (!telaCarregada) {
      frame = new CadastroUsuarios();
    }
    // Prenche as informações
    usuarioCadastrado = null;
    txtNome.setText(usuario.getNome());
    txtSenha.setText(usuario.getSenha());
    cbxAcesso.setSelectedItem(usuario.getNivel().toInt());
    frame.setVisible(true);
    return usuarioCadastrado;
  }

  // Cria e configura o frame
  private CadastroUsuarios() {
    // ---Configuração de listeners
    // Classe anônima para ação do botão cadastrar
    ActionListener acaoOK = CadastroUsuarios::salvarDados;
    // Classe anônima para ação do botão cancelar
    ActionListener acaoCancelar = Auxiliares.getAcaoFecharJanela(this);
    // Classe anônima para ação de ocultar janela. Esse evento é disparado pelo
    // botão cancelar ou
    // pelo botão de fechar janela.
    WindowListener listenerFecharJanela = Auxiliares.getListenerOcultarJanela(this);

    // ---Labels e controles
    JLabel lblNome = new JLabel("Nome");
    lblNewLabel = new JLabel("Senha");
    JLabel lblAcesso = new JLabel("Acesso");
    // Nome
    txtNome = new JTextField();
    txtNome.setColumns(10);
    // Senha
    txtSenha = new JPasswordField();
    // Seleção do nível de acesso
    cbxAcesso = new JComboBox<NiveisUsuarios>();
    cbxAcesso.setModel(Auxiliares.listaComboBox(NiveisUsuarios.values()));
    cbxAcesso.setSelectedIndex(0);
    // Botões
    btnGravar = new JButton("Gravar");
    btnCancelar = new JButton("Cancelar");

    // ---Listeners e validadores
    // Listeners para os botões
    btnCancelar.addActionListener(acaoCancelar);
    btnGravar.addActionListener(acaoOK);
    // Adiciona o listener para evento de fechar janela
    addWindowListener(listenerFecharJanela);

    // ---Painel e layout do form
    setTitle("Cadastro de Usuários");
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setBounds(100, 100, 269, 236);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    GroupLayout gl_contentPane = new GroupLayout(contentPane);
    gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                            .addGroup(gl_contentPane.createSequentialGroup()
                                    .addGroup(gl_contentPane
                                            .createParallelGroup(Alignment.LEADING, false)
                                            .addComponent(lblNome)
                                            .addComponent(txtNome))
                                    .addGap(18)
                                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                            .addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, 91,
                                                    GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNewLabel)))
                            .addComponent(lblAcesso)
                            .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(btnGravar)
                                    .addGap(18)
                                    .addComponent(btnCancelar))
                            .addComponent(cbxAcesso, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()));
    gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                            .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(lblNome)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(txtNome, GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(gl_contentPane.createSequentialGroup()
                                    .addComponent(lblNewLabel)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(txtSenha, GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(18)
                    .addComponent(lblAcesso)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(cbxAcesso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE)
                    .addGap(23)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                            .addComponent(btnGravar)
                            .addComponent(btnCancelar))
                    .addContainerGap(60, Short.MAX_VALUE)));
    contentPane.setLayout(gl_contentPane);
    telaCarregada = true;
  }

}
