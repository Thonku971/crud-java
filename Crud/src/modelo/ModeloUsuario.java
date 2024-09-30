package modelo;


import java.sql.SQLException;
import java.util.List;

import entidades.Usuario;
import servico.Servico;

public class ModeloUsuario {
    private Servico servico;

    // Construtor
    public ModeloUsuario() {
        this.servico = new Servico();
    }

    // Método para criar um novo usuário
    public void criarUsuario(Usuario usuario) throws SQLException {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        servico.criarUsuario(usuario);
    }

    // Método para listar todos os usuários
    public List<Usuario> listarUsuarios() throws SQLException {
        return servico.listarUsuarios();
    }

 
    // Método para atualizar um usuário
    public void atualizarUsuario(Usuario usuario) throws SQLException {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser vazio.");
        }
        servico.atualizarUsuario(usuario);
    }

    // Método para deletar um usuário
    public void deletarUsuario(int id) throws SQLException {
    	servico.deletarUsuario(id);
    }
}
