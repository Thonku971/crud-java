

package servico;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Usuario;
import modelo.Conexao;

public class Servico {

    // Método para inserir um novo usuário
    public void criarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO users (name, email, senha) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();
        }
    }

    // Método para buscar todos os usuários
    public List<Usuario> listarUsuarios() throws SQLException {
        String sql = "SELECT * FROM users";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("name"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }



    // Método para atualizar um usuário existente
    public void atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE users SET name = ?, email = ?, senha = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getId());
            stmt.executeUpdate();
        }
    }

    // Método para deletar um usuário por ID
    public void deletarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

