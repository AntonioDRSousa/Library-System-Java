package lp2g04.biblioteca;

import java.util.Hashtable;
import java.io.*;
import java.util.GregorianCalendar;
import java.util.Enumeration;
import java.util.*;
import java.util.ArrayList;

public class Biblioteca{
	private static int prazo;
	private static int maxLivros;
	private Hashtable<Integer,Usuario> cadastroUsuarios;
	private Hashtable<String,Livro> cadastroLivros;

	//construtores
	public Biblioteca(){
		cadastroUsuarios=new Hashtable<Integer,Usuario>();
		cadastroLivros=new Hashtable<String,Livro>();
	}
	public Biblioteca(String s1,String s2)throws IOException,ClassNotFoundException{
		cadastroUsuarios=new Hashtable<Integer,Usuario>();
		cadastroLivros=new Hashtable<String,Livro>();
		
		ObjectInputStream in=new ObjectInputStream(new FileInputStream(s1));
		cadastroUsuarios=(Hashtable)in.readObject( );
		in.close();
		in=new ObjectInputStream(new FileInputStream(s2));
		cadastroLivros=(Hashtable)in.readObject( );
		in.close();
	}
	
	//cadastros
	public void cadastraUsuario(Usuario obj){
		cadastroUsuarios.put(obj.getCodigo(),obj);
	}
	public void cadastraLivro(Livro obj){
		cadastroLivros.put(obj.getCodigo(),obj);
	}

	//operacoes com arquivos
	public void salvaArquivo(Hashtable h,String narq)throws IOException{
		ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(narq));
		out.writeObject(h);
		out.close();
	}
	public void leArquivo(String narq)throws IOException,FileNotFoundException,ClassNotFoundException{
		ObjectInputStream in=new ObjectInputStream(new FileInputStream(narq));
		Hashtable h=(Hashtable)in.readObject( );
		if(narq.equals("CadastroUsuarios.txt")){
			cadastroUsuarios=h;
		}
		else{
			cadastroLivros=h;
		}
		in.close();
	}
	
	//empresta e devolve
	public void emprestaLivro(Usuario us,Livro liv)throws CopiaNaoDisponivelEx,MaxLivrosEx{
		if(us.numeroLivros()>=maxLivros){
			throw new MaxLivrosEx();
		}
		liv.empresta();
		GregorianCalendar data1=new GregorianCalendar();
		GregorianCalendar data2=new GregorianCalendar();
		data2.add((GregorianCalendar.DAY_OF_MONTH),prazo);
		us.addLivroHist(data1,data2,liv.getCodigo());
		us.adicionaLivroData(liv.getCodigo(),data2);
		liv.addUsuarioHist(data1,data2,us.getCodigo());
	}
	public void devolveLivro(Usuario us,Livro liv)throws NenhumaCopiaEmprestadaEx,NullPointerException{
		liv.devolve();
		GregorianCalendar data=new GregorianCalendar();
		if((data.compareTo(us.getDataDevolucao(liv.getCodigo()))>0)){
			System.out.println("Devolveu livro em atraso. Paga multa.");
			System.out.println(us.getDataDevolucao(liv.getCodigo()));
			System.out.println(data);
		}
		us.removeLivroData(liv.getCodigo());
	}
	
	//imprimir
	public Comparator tituloC = new Comparator() {
		public int compare(Object p1,Object p2){
			String pf1,pf2;
			pf2=((Livro) p2).getTitulo();
			pf1=((Livro) p1).getTitulo();
			return (int) Math.round(pf2.compareTo(pf1));
		}
	};
	public Comparator nomeC = new Comparator() {
		public int compare(Object p1,Object p2){
			String pf1,pf2;
			pf2=((Usuario) p2).getNome();
			pf1=((Usuario) p1).getNome();
			return (int) Math.round(pf2.compareTo(pf1));
		}
	};

	public String imprimeLivros(){
		int i;
		String s="";
		ArrayList<Livro> lista=new ArrayList();
		Enumeration e=cadastroLivros.elements();
		while(e.hasMoreElements()){
			lista.add((Livro) e.nextElement());
		}
		Collections.sort(lista,tituloC.reversed());
		s+="~~~~~~~~\n";
		for(i=0;i<lista.size();i++){
			s+=(lista.get(i)).toString();
			s+="~~~~~~~~\n";
		}
		return s;
	}
	public String imprimeUsuarios(){
		int i;
		String s="";
		ArrayList<Usuario> lista=new ArrayList();
		Enumeration e=cadastroUsuarios.elements();
		while(e.hasMoreElements()){
			lista.add((Usuario) e.nextElement());
		}
		Collections.sort(lista,nomeC.reversed());
		s+="~~~~~~~~\n";
		for(i=0;i<lista.size();i++){
			s+=(lista.get(i)).toString();
			s+="~~~~~~~~\n";
		}
		return s;
	}

	//metodos get
	public Livro getLivro(String cod)throws LivroNaoCadastradoEx{
		if(cadastroLivros.containsKey(cod)){
			return cadastroLivros.get(cod); 
		}
		else{
			throw new LivroNaoCadastradoEx();
		}
	}
	public Usuario getUsuario(int cod)throws UsuarioNaoCadastradoEx{
		if(cadastroUsuarios.containsKey(cod)){
			return cadastroUsuarios.get(cod); 
		}
		else{
			throw new UsuarioNaoCadastradoEx();
		}
	}
	public Hashtable getCadastroUsuarios(){
		return cadastroUsuarios;
	}
	public Hashtable getCadastroLivros(){
		return cadastroLivros;
	}

	//setPrazo e setMaxLivros
	public void setPrazo(int n){
		prazo=n;
	}
	public void setMaxLivros(int n){
		maxLivros=n;
	}
}
	

	

	

	
