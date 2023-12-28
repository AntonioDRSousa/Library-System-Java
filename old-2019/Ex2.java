import java.util.Hashtable;
import java.io.*;
import java.util.GregorianCalendar;
import java.util.Enumeration;
import java.util.*;
import java.util.ArrayList;

import lp2g04.biblioteca.*;

public class Ex2{
	private static int maxLivros;
	private static int prazo;
	public static Biblioteca bib;
	private static int[] padrao={3,7};//padrao default da biblioteca, maximo 3 livros e prazo de 7 dias
	
	public static void main(String[] argc){
		BufferedReader inData=new BufferedReader(new InputStreamReader(System.in));
		String aux;
		int op;
		while(true){
			try{
				System.out.println("----------");
				System.out.println("Digite uma opcao:\n1.Inicializar Biblioteca\n2.Carregar Biblioteca Salva\n3.Customizar Biblioteca\n4.Sair");
				System.out.println("----------");
				aux=inData.readLine();
				op=Integer.parseInt(aux);
				
				if(op<1 || op>4){
					throw new IllegalArgumentException();
				}
				if(op==4){
					break;
				}
				switch(op){
					case 1:
						bib=new Biblioteca();
						manutencao();
						//configuracao
						while(true){
							try{
								System.out.println("----------");
								System.out.println("Digite uma opcao:\n1.Manter configuracao padrao(maximo 3 livros e prazo de 7 dias\n2.Customizar");
								System.out.println("----------");
								aux=inData.readLine();
								op=Integer.parseInt(aux);
								if(op<1 || op>2){
									throw new IllegalArgumentException();
								}
								switch(op){
									case 1:
										prazo=padrao[1];
										maxLivros=padrao[0];
										FileWriter writer = new FileWriter("configuracoes.txt");
										PrintWriter out = new PrintWriter(writer);
										out.println(prazo);
										out.println(maxLivros);
										writer.close();
										break;
									case 2:
										customizacao();
										break;
								}
								bib.setPrazo(prazo);
								bib.setMaxLivros(maxLivros);
								break;
							}
							catch(IOException e){
								System.out.println(e);
							}
							catch(NumberFormatException e){
								System.out.println(e);
							}
							catch(IllegalArgumentException e){
								System.out.println(e);
							}
						}
						inicializacao();
						opcoes();
						break;
					case 2:
						bib=new Biblioteca("CadastroUsuarios.txt","CadastroLivros.txt");
						
						//carrega configuracoes
						FileReader reader=new FileReader("configuracoes.txt");
						BufferedReader in=new BufferedReader(reader);
						aux=in.readLine();
						prazo=Integer.parseInt(aux);
						aux=in.readLine();
						maxLivros=Integer.parseInt(aux);
						reader.close();
						
						bib.setPrazo(prazo);
						bib.setMaxLivros(maxLivros);
						opcoes();
						break;
					case 3:
						customizacao();
						break;
				}
			}
			catch(IOException e){
				System.out.println(e);
			}
			catch(ClassNotFoundException e){
				System.out.println(e);
			}
			catch(NumberFormatException e){
				System.out.println(e);
			}
			catch(IllegalArgumentException e){
				System.out.println(e);
			}
		}	
	}
	public static void inicializacao(){
		bib.cadastraUsuario(new Usuario("Joao",new GregorianCalendar(1995,5,6),"Freguesia",123));
		bib.cadastraUsuario(new Usuario("Jose",new GregorianCalendar(1997,9,10),"Tijuca",321));
		bib.cadastraUsuario(new Usuario("Maria",new GregorianCalendar(1999,1,3),"Vila Isabel",333));
		bib.cadastraUsuario(new Usuario("Carlos",new GregorianCalendar(1998,8,8),"Grajau",432));
		bib.cadastraUsuario(new Usuario("Ana",new GregorianCalendar(1996,7,30),"Copacabana",111));
		bib.cadastraLivro(new Livro("456","Hamlet","Tragedia",1));
		bib.cadastraLivro(new Livro("654","Dom Quixote","Aventura",2));
		bib.cadastraLivro(new Livro("890","Senhor dos Aneis","Fantasia",3));
		bib.cadastraLivro(new Livro("098","Lusiadas","Poesia Epica",4));
		bib.cadastraLivro(new Livro("999","Vidas Secas","Romance",5));
		try{
			manutencao();
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
	public static void customizacao()throws IOException{
		BufferedReader inData=new BufferedReader(new InputStreamReader(System.in));
		String aux;
		int temp;

		//carregar configuracao
		try{
			FileReader reader=new FileReader("configuracoes.txt");
			BufferedReader in=new BufferedReader(reader);
			aux=in.readLine();
			prazo=Integer.parseInt(aux);
			aux=in.readLine();
			maxLivros=Integer.parseInt(aux);
			reader.close();
		}
		catch(IOException e){
			prazo=padrao[1];
			maxLivros=padrao[0];
		}
			
		System.out.println("Prazo = "+prazo);
		while(true){
			try{
				System.out.print("Digite o prazo de devolucao dos livros: ");
				aux=inData.readLine();
				temp=Integer.parseInt(aux);
				if(temp<1){
					throw new IllegalArgumentException();
				}
				prazo=temp;
				break;
			}
			catch(IOException e){
				System.out.println(e);
			}
			catch(NumberFormatException e){
				System.out.println(e);
			}
			catch(IllegalArgumentException e){
				System.out.println(e);
			}
		}
		System.out.println("Maximo de Livros = "+maxLivros);
		while(true){
			try{
				System.out.println("Digite o maximo de livros que pode ter ao mesmo tempo: ");
				aux=inData.readLine();
				temp=Integer.parseInt(aux);
				if(temp<1){
					throw new IllegalArgumentException();
				}
				maxLivros=temp;
				break;
			}
			catch(IOException e){
				System.out.println(e);
			}
			catch(NumberFormatException e){
				System.out.println(e);
			}
			catch(IllegalArgumentException e){
				System.out.println(e);
			}
		}
		FileWriter writer = new FileWriter("configuracoes.txt");
		PrintWriter out = new PrintWriter(writer);
		out.println(prazo);
		out.println(maxLivros);
		writer.close();
	}
	public static void opcoes(){
		BufferedReader inData=new BufferedReader(new InputStreamReader(System.in));
		String aux;
		int op;
		
		while(true){
			try{
				System.out.println("----------");
				System.out.println("Digite uma opcao:\n1.Cadastro\n2.Emprestimo\n3.Devolucao\n4.Relatorio\n5.Salvar nos usuarios e livros\n6.Voltar");
				System.out.println("----------");
				aux=inData.readLine();
				op=Integer.parseInt(aux);
				if(op<1 || op>6){
					throw new IllegalArgumentException();
				}
				else if(op==6){
					break;
				}
				switch(op){
					case 1:
						cadastro();
						break;
					case 2:
						emprestimo();
						break;
					case 3:
						devolucao();
						break;
					case 4:
						relatorio();
						break;
					case 5:
						manutencao();
						break;
				}
			}
			catch(IOException e){
				System.out.println(e);
			}
			catch(NumberFormatException e){
				System.out.println(e);
			}
			catch(IllegalArgumentException e){
				System.out.println(e);
			}
		}
	}
			
		
	public static void manutencao()throws IOException{
		bib.salvaArquivo(bib.getCadastroUsuarios(),"CadastroUsuarios.txt");
		bib.salvaArquivo(bib.getCadastroLivros(),"CadastroLivros.txt");
	}
	public static void cadastro(){
		BufferedReader inData=new BufferedReader(new InputStreamReader(System.in));
		String aux;
		String nome,endereco,titulo,categoria,codigoLivro;
		GregorianCalendar data;
		int codigoUsuario,dia,mes,ano,qtd,op;
		
		while(true){
			try{
				System.out.println("----------");
				System.out.println("Digite uma opcao:\n1.Cadastrar Usuario\n2.Cadastrar Livro\n3.Salvar em Arquivo\n4.Voltar");
				System.out.println("----------");
				aux=inData.readLine();
				op=Integer.parseInt(aux);
				if(op<1 || op>4){
					throw new IllegalArgumentException();
				}
				if(op==4){
					break;
				}
				switch(op){
					case 1://cadastrar usuario
						while(true){
							try{
								System.out.print("Nome: ");
								nome=inData.readLine();
								break;
							}
							catch(IOException e){
								System.out.println(e);
							}
						}
						while(true){
							try{
								System.out.println("Data de Nascimento");
								System.out.print("dia: ");
								aux=inData.readLine();
								dia=Integer.parseInt(aux);
								System.out.print("mes: ");
								aux=inData.readLine();
								mes=Integer.parseInt(aux);
								System.out.print("ano: ");
								aux=inData.readLine();
								ano=Integer.parseInt(aux);
								valiData(dia,mes,ano);
								data=new GregorianCalendar(ano,mes,dia);
								break;
							}
							catch(IOException e){
								System.out.println(e);
							}
							catch(NumberFormatException e){
								System.out.println(e);
							}
							catch(DataException e){
								System.out.println(e);
							}
						}
						while(true){
							try{
								System.out.print("Endereco: ");
								endereco=inData.readLine();
								break;
							}
							catch(IOException e){
								System.out.println(e);
							}
						}
						while(true){
							try{
								System.out.print("Codigo: ");
								aux=inData.readLine();
								codigoUsuario=Integer.parseInt(aux);
								break;
							}
							catch(IOException e){
								System.out.println(e);
							}
							catch(NumberFormatException e){
								System.out.println(e);
							}
						}
						bib.cadastraUsuario(new Usuario(nome,data,endereco,codigoUsuario));
						break;
						
					case 2://cadastrar livro
						while(true){
							try{
								System.out.print("Titulo: ");
								titulo=inData.readLine();
								break;
							}
							catch(IOException e){
								System.out.println(e);
							}
						}
						while(true){
							try{
								System.out.print("Categoria: ");
								categoria=inData.readLine();
								break;
							}
							catch(IOException e){
								System.out.println(e);
							}
						}
						while(true){
							try{
								System.out.print("Codigo: ");
								codigoLivro=inData.readLine();
								break;
							}
							catch(IOException e){
								System.out.println(e);
							}
						}
						while(true){
							try{
								System.out.print("Quantidade: ");
								aux=inData.readLine();
								qtd=Integer.parseInt(aux);
								break;
							}
							catch(IOException e){
								System.out.println(e);
							}
							catch(NumberFormatException e){
								System.out.println(e);
							}
						}
						bib.cadastraLivro(new Livro(codigoLivro,titulo,categoria,qtd));
						break;
				
				
					case 3://salvar em arquivo
						while(true){
							try{
								System.out.println("----------");
								System.out.println("Digite uma opcao:\n1.Salvar cadastro de usuarios\n2.Salvar Cadastro de livros\n3.Voltar");
								System.out.println("----------");
								aux=inData.readLine();
								op=Integer.parseInt(aux);
								if(op<1 || op>3){
									throw new IllegalArgumentException();
								}
								if(op==3){
									break;
								}
								switch(op){
									case 1://salvar usuarios
										System.out.println(bib.getCadastroUsuarios());
										bib.salvaArquivo(bib.getCadastroUsuarios(),"CadastroUsuarios.txt");
										break;
									case 2://salvar livros
										bib.salvaArquivo(bib.getCadastroLivros(),"CadastroLivros.txt");
										break;
								}
								break;
							}
							catch(IOException e){
								System.out.println(e);
							}
							catch(NumberFormatException e){
								System.out.println(e);
							}
							catch(IllegalArgumentException e){
								System.out.println(e);
							}
						}
					}
			}
			catch(IOException e){
				System.out.println(e);
			}
			catch(NumberFormatException e){
				System.out.println(e);
			}
			catch(IllegalArgumentException e){
				System.out.println(e);
			}
		}
	}
	public static void emprestimo(){
		BufferedReader inData=new BufferedReader(new InputStreamReader(System.in));
		String aux;
		int codigoUsuario;
		String codigoLivro;
		Livro liv;
		Usuario us;
		int op;
		
		while(true){
			try{
				System.out.println("----------");
				System.out.println("Digite uma opcao:\n1.Exibir Cadastro de Livros\n2.Fazer um emprestimo\n3.Sair");
				System.out.println("----------");
				aux=inData.readLine();
				op=Integer.parseInt(aux);
				if(op<1 || op>3){
					throw new IllegalArgumentException();
				}
				else if(op==3){
					break;
				}
				switch(op){
					case 1:
						String s=bib.imprimeLivros();
						System.out.println(s);
						break;
					case 2:
							try{
								System.out.print("Digite o codigo do usuario: ");
								aux=inData.readLine();
								codigoUsuario=Integer.parseInt(aux);
								us=bib.getUsuario(codigoUsuario);
								System.out.println(us);
								System.out.print("Digite o codigo do livro: ");
								codigoLivro=inData.readLine();
								liv=bib.getLivro(codigoLivro);
								System.out.println(liv);
								bib.emprestaLivro(us,liv);
								System.out.println(us.toString());
								System.out.println(liv.toString());
							}
							catch(IOException e){
								System.out.println(e);
							}
							catch(CopiaNaoDisponivelEx e){
								System.out.println(e);
							}
							catch(MaxLivrosEx e){
								System.out.println(e);
							}
							catch(LivroNaoCadastradoEx e){
								System.out.println(e);
							}
							catch(UsuarioNaoCadastradoEx e){
								System.out.println(e);
							}
						break;
				}
			}
			catch(IOException e){
				System.out.println(e);
			}
			catch(NumberFormatException e){
				System.out.println(e);
			}
			catch(IllegalArgumentException e){
				System.out.println(e);
			}
		}		
	}
	public static void devolucao(){
		BufferedReader inData=new BufferedReader(new InputStreamReader(System.in));
		String aux;
		int codigoUsuario;
		String codigoLivro;
		Livro liv;
		Usuario us;
		int op;

		System.out.println("DEVOLUCAO");
			try{
				System.out.println("Digite o codigo do usuario? ");
				aux=inData.readLine();
				codigoUsuario=Integer.parseInt(aux);
				us=bib.getUsuario(codigoUsuario);
				System.out.println("Digite o codigo do livro: ");
				codigoLivro=inData.readLine();
				liv=bib.getLivro(codigoLivro);
				bib.devolveLivro(us,liv);
			}
			catch(IOException e){
				System.out.println(e);
			}
			catch(UsuarioNaoCadastradoEx e){
				System.out.println(e);
			}
			catch(NenhumaCopiaEmprestadaEx e){
				System.out.println(e);
			}
			catch(LivroNaoCadastradoEx e){
				System.out.println(e);
			}
			catch(NullPointerException e){
				System.out.println("Erro. Usuario nao possui o livro");
			}
	}

		
	public static void relatorio(){
		BufferedReader inData=new BufferedReader(new InputStreamReader(System.in));
		String aux;
		String s;
		int cod;
		int op;
		
		while(true){
			try{
				System.out.println("----------");
				System.out.println("Digite uma opcao:\n1.Acervo de Livros\n2.Cadastro de Usuarios\n3.Detalhes de um usuario\n4.Detalhes de um livro\n5.Voltar");
				System.out.println("----------");
				aux=inData.readLine();
				op=Integer.parseInt(aux);
				if(op<1 || op>5){
					throw new IllegalArgumentException();
				}
				if(op==5){
					break;
				}
				switch(op){
					case 1:
						System.out.println(bib.imprimeLivros());
						break;
					case 2:
						System.out.println(bib.imprimeUsuarios());
						break;
					case 3:
						while(true){
							try{
								Usuario us;
								System.out.println("Digite o codigo do usuario: ");
								aux=inData.readLine();
								cod=Integer.parseInt(aux);
								us=bib.getUsuario(cod);
								System.out.println(us.toString());
								break;
							}
							catch(IOException e){
								System.out.println(e);
							}
							catch(NumberFormatException e){
								System.out.println(e);
							}
							catch(UsuarioNaoCadastradoEx e){
								System.out.println(e);
							}
						}
						break;
					case 4:
						while(true){
							try{
								Livro liv;
								System.out.println("Digite o codigo do livro: ");
								s=inData.readLine();
								liv=bib.getLivro(s);
								System.out.println(liv.toString());
								break;
							}
							catch(IOException e){
								System.out.println(e);
							}
							catch(NumberFormatException e){
								System.out.println(e);
							}
							catch(LivroNaoCadastradoEx e){
								System.out.println(e);
							}
						}
						break;
				}
			}
			catch(IOException e){
				System.out.println(e);
			}
			catch(NumberFormatException e){
				System.out.println(e);
			}
			catch(IllegalArgumentException e){
				System.out.println(e);
			}
		}
					
	}
	
	public static void valiData(int dia,int mes,int ano)throws DataException{
		if(ano>=0){
			if(mes>=1 || mes<=12){
				if(dia<1){
					throw new DataException();
				}
				if(mes==2){
					if(ano%4==0){
						if(dia>29){
							throw new DataException();
						}
					}
					else{
						if(dia>28){
							throw new DataException();
						}
					}
				}
				else if(mes==4 || mes==6 || mes==9 || mes==11){
					if(dia>30){
						throw new DataException();
					}
				}
				else{
					if(dia>31){
						throw new DataException();
					}
				}
			}
			else{
				throw new DataException();
			}
		}
		else{
			throw new DataException();
		}
	}

}
	
