package br.com.crud.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.com.crud.bean.UsuarioBean;
import br.com.crud.bean.UsuarioBean.NiveisUsuarios;

@SuppressWarnings("serial")
public class TelaPrincipal extends JDialog {

  private static TelaPrincipal frame;
  private static JPanel contentPane;
  private static JPanel panelAdmin;
  private static boolean telaCarregada = false;

  public static void exibir(UsuarioBean usuario) {
    if (!telaCarregada) {
      try {
        frame = new TelaPrincipal();
        frame.setVisible(true);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    panelAdmin.setVisible(usuario.getNivel() == NiveisUsuarios.GERENTE);
    frame.setVisible(true);
  }

  public TelaPrincipal() {
    setTitle("Cadastro de Outdoors e Aluguéis");
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setBounds(100, 100, 349, 302);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);

    JButton btnCadastrarOutdoor = new JButton("Cadastrar Outdoor");
    btnCadastrarOutdoor.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        CadastroOutdoor.cadastrar();
      }
    });
    JButton btnCadastrarAluguel = new JButton("Cadastrar Aluguel");
    btnCadastrarAluguel.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        CadastroAlugueis.cadastrar();
      }
    });

    JButton btnCadastrarNovoUsurio = new JButton("Cadastrar Novo Usuário");
    btnCadastrarNovoUsurio.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        CadastroUsuarios.cadastrar();
      }
    });

    JButton btnEditarOutdoor = new JButton("Editar Outdoor");
    btnEditarOutdoor.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        SelecionarOutdoor.selecionar();
      }
    });

    JButton btnEditarAluguel = new JButton("Editar Aluguel");
    btnEditarAluguel.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        SelecionarAluguel.selecionar();
      }
    });

    JButton btnEditarUsuario = new JButton("Editar Usuários");
    btnEditarUsuario.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        SelecionarUsuario.selecionar();

      }
    });

    JButton btnTrocarUsurio = new JButton("Trocar Usuário");
    btnTrocarUsurio.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        Login.logar();
        panelAdmin.setVisible(UsuarioBean.getUsuarioAtivo().getNivel() == NiveisUsuarios.GERENTE);
      }
    });

    JButton btnSair = new JButton("Sair");
    btnSair.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);

      }
    });

    setModalityType(ModalityType.APPLICATION_MODAL);
    panelAdmin = new JPanel();
    panelAdmin.setVisible(false);
    GroupLayout gl_contentPane = new GroupLayout(contentPane);
    gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                            .addGroup(gl_contentPane.createSequentialGroup()
                                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                            .addComponent(panelAdmin, 0, 0, Short.MAX_VALUE)
                                            .addGroup(gl_contentPane.createSequentialGroup()
                                                    .addComponent(btnCadastrarOutdoor)
                                                    .addGap(18)
                                                    .addComponent(btnEditarOutdoor,
                                                            GroupLayout.PREFERRED_SIZE, 139,
                                                            GroupLayout.PREFERRED_SIZE))
                                            .addGroup(gl_contentPane.createSequentialGroup()
                                                    .addComponent(btnCadastrarAluguel,
                                                            GroupLayout.PREFERRED_SIZE, 139,
                                                            GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18)
                                                    .addComponent(btnEditarAluguel,
                                                            GroupLayout.PREFERRED_SIZE, 139,
                                                            GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(13, Short.MAX_VALUE))
                            .addGroup(Alignment.TRAILING,
                                    gl_contentPane.createSequentialGroup()
                                            .addComponent(btnTrocarUsurio)
                                            .addPreferredGap(ComponentPlacement.RELATED, 145,
                                                    Short.MAX_VALUE)
                                            .addComponent(btnSair)
                                            .addContainerGap()))));
    gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                            .addComponent(btnCadastrarOutdoor)
                            .addComponent(btnEditarOutdoor))
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                            .addComponent(btnCadastrarAluguel)
                            .addComponent(btnEditarAluguel))
                    .addGap(18)
                    .addComponent(panelAdmin, GroupLayout.PREFERRED_SIZE, 97,
                            GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                            .addComponent(btnSair)
                            .addComponent(btnTrocarUsurio))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    GroupLayout gl_panelAdmin = new GroupLayout(panelAdmin);
    gl_panelAdmin
            .setHorizontalGroup(gl_panelAdmin.createParallelGroup(Alignment.LEADING)
                    .addGroup(Alignment.TRAILING, gl_panelAdmin.createSequentialGroup()
                            .addContainerGap(69, Short.MAX_VALUE)
                            .addGroup(gl_panelAdmin.createParallelGroup(Alignment.LEADING)
                                    .addComponent(btnCadastrarNovoUsurio)
                                    .addComponent(btnEditarUsuario, GroupLayout.PREFERRED_SIZE, 167,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGap(60)));
    gl_panelAdmin.setVerticalGroup(gl_panelAdmin.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_panelAdmin.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnCadastrarNovoUsurio)
                    .addGap(18)
                    .addComponent(btnEditarUsuario)
                    .addContainerGap(29, Short.MAX_VALUE)));
    panelAdmin.setLayout(gl_panelAdmin);
    contentPane.setLayout(gl_contentPane);
  }
}
