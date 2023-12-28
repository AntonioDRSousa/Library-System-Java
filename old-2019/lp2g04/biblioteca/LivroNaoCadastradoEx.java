package lp2g04.biblioteca;

public class LivroNaoCadastradoEx extends Exception{
	public LivroNaoCadastradoEx(){
		super("Erro. Livro nao cadastrado.");
	}
}
