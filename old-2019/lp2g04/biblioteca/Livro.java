package lp2g04.biblioteca;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.io.*;

public class Livro implements Serializable{
	private String codigoLivro;
	private String tituloLivro;
	private String categoria;
	private int quantidade;
	private int emprestados;
	private ArrayList<EmprestadoPara> Historico;
	
	public Livro(String cod,String t,String cat,int qtd){
		codigoLivro=cod;
		tituloLivro=t;
		categoria=cat;
		quantidade=qtd;
		emprestados=0;
		Historico=new ArrayList();
	}
	public Livro(String t){
		tituloLivro=t;
	}
	public Livro(){
	}

	public void empresta()throws CopiaNaoDisponivelEx{
		if(quantidade>emprestados){
			emprestados++;
			System.out.println(emprestados);
		}
		else{
			throw new CopiaNaoDisponivelEx();
		}
	}
	public void devolve()throws NenhumaCopiaEmprestadaEx{
		if(emprestados>0){
			emprestados--;
		}
		else{
			throw new NenhumaCopiaEmprestadaEx();
		}
	}

	public void addUsuarioHist(GregorianCalendar dloc, GregorianCalendar ddev, int cod){
		Historico.add(new EmprestadoPara(dloc,ddev,cod));
	}

	public String getCodigo(){
		return codigoLivro;
	}
	public String getTitulo(){
		return tituloLivro;
	}
	public String getCategoria(){
		return categoria;
	}
	public int getQuantidade(){
		return quantidade;
	}
	public int getEmprestados(){
		return emprestados;
	}
	public ArrayList getHistorico(){
		return Historico;
	}

	public String toString(){ 
		int i;
		String s="Codigo do Livro:  "+codigoLivro+"\n"+"Titulo do Livro: "+tituloLivro+"\n";
		s+="Categoria: "+categoria+"\n"+"Quantidade: "+quantidade+"\n"+"Emprestados: "+emprestados+"\n\n";
		s+="Historico:\n";
		for(i=0;i<Historico.size();i++){
			s+=Historico.get(i)+"\n";
		}
		return s;
	}
}
