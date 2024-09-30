package entidades;

public class Usuario {

    // Atributos
    private int id;
    private String nome;
    private String senha;
    private String email;

    // Construtor padrão
    public Usuario() {}

    // Construtor com parâmetros
    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    
	public void setSenha(String senha) {
		if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.senha = senha;		
	}

    
    // Método toString para representação em String
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
