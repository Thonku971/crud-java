package userinterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.ModeloUsuario;
import entidades.Usuario;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.sql.SQLException;

public class Interface extends JFrame {
    private ModeloUsuario modelo;
    private Usuario usuario;
    private JTable userTable;
    private JTextField txtId, txtName, txtEmail, txtSenha;
    private JButton btnAdd, btnUpdate, btnDelete, btnRead;
    private JScrollPane scrollPane;

    public Interface() {
        // Inicializando os componentes da interface
        modelo = new ModeloUsuario();
        usuario = new Usuario();
        initComponents();
        readUsers(); // Carrega os usuários na tabela ao iniciar
    }

    private void initComponents() {
        // Inicializando os campos de texto
        txtId = new JTextField();
        txtId.setEnabled(false); // ID não pode ser editado diretamente
        txtName = new JTextField();
        txtEmail = new JTextField();
        txtSenha = new JTextField();

        // Inicializando a tabela com DefaultTableModel
        String[] columnNames = {"ID", "Name", "Email", "Senha"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Definindo o DefaultTableModel
        userTable = new JTable(model); // Inicializando a JTable com o DefaultTableModel

        // Adiciona a tabela a um JScrollPane
        scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(20, 200, 450, 150);
        add(scrollPane);

        // Configuração do layout e outros componentes
        setTitle("CRUD Application");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Labels e campos de texto
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(20, 20, 80, 25);
        add(lblName);

        txtName.setBounds(100, 20, 165, 25);
        add(txtName);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 60, 80, 25);
        add(lblEmail);

        txtEmail.setBounds(100, 60, 165, 25);
        add(txtEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(20, 100, 80, 25);
        add(lblSenha);

        txtSenha.setBounds(100, 100, 165, 25);
        add(txtSenha);

        // Botões de ação
        btnAdd = new JButton("Add");
        btnAdd.setBounds(20, 150, 80, 25);
        add(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(120, 150, 80, 25);
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(220, 150, 80, 25);
        add(btnDelete);

        btnRead = new JButton("Read");
        btnRead.setBounds(320, 150, 80, 25);
        add(btnRead);

        // Eventos
        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = userTable.getSelectedRow(); // Pega o índice da linha selecionada
                if (row != -1) { // Certifica-se de que há uma linha selecionada
                    DefaultTableModel model = (DefaultTableModel) userTable.getModel();
                    int userId = (int) model.getValueAt(row, 0);
                    String name = (String) model.getValueAt(row, 1);
                    String email = (String) model.getValueAt(row, 2);
                    String senha = (String) model.getValueAt(row, 3);

                    // Define os valores nos campos de texto
                    txtId.setText(String.valueOf(userId));
                    txtName.setText(name);
                    txtEmail.setText(email);
                    txtSenha.setText(senha);
                }
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });

        btnRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readUsers();
            }
        });
    }

    // Método para adicionar um usuário
    private void addUser() {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        usuario.setNome(name);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        try {
            modelo.criarUsuario(usuario);
            JOptionPane.showMessageDialog(this, "Usuário criado");
            readUsers(); // Atualiza a tabela
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao criar usuário: " + e.getMessage());
        }
    }

    // Método para atualizar um usuário
    private void updateUser() {
        int userId = Integer.parseInt(txtId.getText()); // Pegue o ID do campo de texto
        String name = txtName.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        usuario.setId(userId);
        usuario.setNome(name);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        try {
            modelo.atualizarUsuario(usuario);
            JOptionPane.showMessageDialog(this, "Usuário atualizado");
            readUsers(); // Atualiza a tabela
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    private void deleteUser() {
        int userId = Integer.parseInt(txtId.getText()); // Pegue o ID do campo de texto

        try {
            modelo.deletarUsuario(userId);
            JOptionPane.showMessageDialog(this, "Usuário deletado");
            readUsers(); // Atualiza a tabela
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao deletar usuário: " + e.getMessage());
        }
    }

    // Método para ler os usuários e atualizar a tabela
    private void readUsers() {
        try {
            DefaultTableModel model = (DefaultTableModel) userTable.getModel();
            model.setRowCount(0); // Limpa a tabela antes de atualizar

            for (Usuario user : modelo.listarUsuarios()) {
                model.addRow(new Object[]{user.getId(), user.getNome(), user.getEmail(), user.getSenha()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar usuários: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Interface frame = new Interface();
            frame.setVisible(true);
        });
    }
}
