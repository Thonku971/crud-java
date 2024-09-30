package teste;

import java.sql.SQLException;
import java.util.List;

import entidades.Usuario;
import servico.Servico;

public class Teste {
    public static void main(String[] args) {
        Servico usuarioService = new Servico();

        try {
            // Criando um novo usuário
            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome("Carlos Andrade");
            novoUsuario.setEmail("carlos.andrade@example.com");
            novoUsuario.setSenha("teste");
            usuarioService.criarUsuario(novoUsuario);

            // Listando todos os usuários
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            for (Usuario u : usuarios) {
                System.out.println(u);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

