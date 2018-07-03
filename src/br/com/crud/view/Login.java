package br.com.crud.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.crud.bean.UsuarioBean;
import br.com.crud.dao.UsuarioDao;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class Login extends JDialog {

  private static final JPanel contentPanel = new JPanel();
  private static Login tela;
  private static JTextField txtNome;
  private static JPasswordField txtSenha;
  private static UsuarioBean usuarioLogado = null;
  private static boolean telaCriada = false;

  public static UsuarioBean logar() {
    usuarioLogado = null;
    try {
      if (!telaCriada) {
        tela = new Login();
        tela.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
      }
      limparCampos();
      tela.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return usuarioLogado;
  }
  
  private static void limparCampos() {
    txtNome.setText("");
    txtSenha.setText("");
  }

  /**
   * Create the dialog.
   */
  private Login() {
    // Classe anônima para ação do botão de gravar dados
    ActionListener acaoOk = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        String nome = txtNome.getText();
        String senha = String.valueOf(txtSenha.getPassword());
        if (nome.equals("") || senha.equals("")) {
          Auxiliares.mensagemErro("Usuário ou senha não preenchidos");
        } else {
          UsuarioBean usuario = UsuarioDao.inst().getUsuario(nome);
          if (!(usuario==null)) {
            
            if (senha.equals(usuario.getSenha())) {
              usuario.definirComoAtivo();
              Auxiliares.mensagemOK("Bem vindo!" + usuario.getNome());
              Auxiliares.dispararEventoFecharJanela(Login.this);
            }
          }
          Auxiliares.mensagemErro("Usuário ou senha incorretos");
          txtNome.setText("");
          txtSenha.setText("");
        }
      }
    };
    // Classe anônima para ação de cancelar
    ActionListener acaoCancelar = Auxiliares.getAcaoFecharJanela(this);
    // Classe anônima para ocultar a janela
    addWindowListener(Auxiliares.getListenerOcultarJanela(this));
    setModalityType(ModalityType.APPLICATION_MODAL);
    setTitle("Login");
    setBounds(100, 100, 184, 184);
    getContentPane().setLayout(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(new MigLayout("", "[grow,left]", "[][][][]"));
    {
      JLabel lblNewLabel = new JLabel("Nome");
      contentPanel.add(lblNewLabel, "cell 0 0,alignx leading");
    }
    {
      txtNome = new JTextField();
      contentPanel.add(txtNome, "cell 0 1,alignx left");
      txtNome.setColumns(10);
    }
    {
      JLabel lblNewLabel_1 = new JLabel("Senha");
      contentPanel.add(lblNewLabel_1, "cell 0 2,alignx leading");
    }
    {
      txtSenha = new JPasswordField();
      txtSenha.setHorizontalAlignment(SwingConstants.LEFT);
      txtSenha.setToolTipText("6 a 10 caracteres");
      contentPanel.add(txtSenha, "cell 0 3,growx");
    }
    {
      JPanel buttonPane = new JPanel();
      buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
      getContentPane().add(buttonPane, BorderLayout.SOUTH);
      {
        JButton okButton = new JButton("OK");
        okButton.addActionListener(acaoOk);
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
      }
      {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(acaoCancelar);
      }
      telaCriada = true;
    }
  }
}
