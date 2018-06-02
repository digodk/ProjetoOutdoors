package telas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
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

import dados.Usuario;
import dados.Usuario.NiveisUsuarios;

@SuppressWarnings("serial")
public class TemplateCadastroUsuario extends JFrame {

  private static TemplateCadastroUsuario frame;
  private static boolean telaCarregada = false;
  private JPanel contentPane;
  private JTextField txtNome;
  private JLabel lblNewLabel;
  private JPasswordField txtSenha;
  private JButton btnGravar;
  private JButton btnCancelar;
  private JComboBox<NiveisUsuarios> cbxAcesso;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          TemplateCadastroUsuario frame = new TemplateCadastroUsuario(new Usuario());
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public static void novoUsuario() {
    if (!telaCarregada) {
      EventQueue.invokeLater(new Runnable() {
        public void run() {
          try {
            frame = new TemplateCadastroUsuario(new Usuario());
            frame.setVisible(true);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });
    }
  }

  // Checagem dos inputs nos campos do formulário
  private boolean dadosOk() {
    if (txtNome.getText().equals("")) {
      System.out.println("Você deve digitar um nome!");
      txtNome.requestFocusInWindow();
      return false;
    }
    if (String.valueOf(txtSenha.getPassword()).equals("")) {
      System.out.println("Você deve digitar uma senha!");
      txtSenha.requestFocus();
      return false;
    }
    if (cbxAcesso.getSelectedItem().toString().equals(NiveisUsuarios.INDEFINIDO.toString())) {
      System.out.println("Você deve selecionar um nível de acesso!");
      cbxAcesso.requestFocus();
      return false;
    }
    return true;
  }

  private void cadastrarUsuario() {
    new Usuario(txtNome.getText(), String.valueOf(txtSenha.getPassword()),
            NiveisUsuarios.values()[cbxAcesso.getSelectedIndex()]);
  }

  private void eventoFechar() {
    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }

  private void carregarTela() {
    // Evento para fechar a janela.
    WindowAdapter fecharJanela = new WindowAdapter() {

      @Override
      public void windowClosing(WindowEvent e) {
        txtNome.setText("");
        txtSenha.setText("");
        cbxAcesso.setSelectedIndex(0);
        TemplateCadastroUsuario.this.setVisible(false);
      }
    };

    ActionListener acaoOK = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (dadosOk()) {
          cadastrarUsuario();
          eventoFechar();
        }

      }
    };

    ActionListener acaoCancelar = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        eventoFechar();
      }
    };

    addWindowListener(fecharJanela);
    setTitle("Cadastro de Usuários");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 269, 215);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);

    JLabel lblNome = new JLabel("Nome");

    txtNome = new JTextField();
    txtNome.setColumns(10);

    lblNewLabel = new JLabel("Senha");

    txtSenha = new JPasswordField();

    JLabel lblAcesso = new JLabel("Acesso");

    cbxAcesso = new JComboBox<>();
    cbxAcesso.setModel(new DefaultComboBoxModel<>(NiveisUsuarios.values()));

    btnGravar = new JButton("Gravar");
    btnGravar.addActionListener(acaoOK);

    btnCancelar = new JButton("Cancelar");
    btnCancelar.addActionListener(acaoCancelar);

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
  }

  /**
   * Create the frame.
   */
  private TemplateCadastroUsuario(Usuario usuario) {
    // Cria a tela
    carregarTela();
    // Prenche as informações
    txtNome.setText(usuario.getNome());
    txtSenha.setText(usuario.getSenha());
    cbxAcesso.setSelectedItem(Usuario.getNivelIndex(usuario.getNivel()));
  }

}
