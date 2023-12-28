package lp2g04.biblioteca;

import java.util.GregorianCalendar;
import java.io.*;

public class Emprestimo implements Serializable{
	private GregorianCalendar dataEmprestimo;
	private GregorianCalendar dataDevolucao;
	private String codigoLivro;

	public Emprestimo(GregorianCalendar demp,GregorianCalendar ddev,String cod){
		dataEmprestimo=demp;
		dataDevolucao=ddev;
		codigoLivro=cod;
	}
	public Emprestimo(){
	}

	public GregorianCalendar getDataEmprestimo(){
		return dataEmprestimo;
	}
	public GregorianCalendar getDataDevolucao(){
		return dataDevolucao;
	}
	public String getCodigoLivro(){
		return codigoLivro;
	}

	public String toString(){
		String s=String.format("Data de Emprestimo: %02d/%02d/%04d\n",dataEmprestimo.get(GregorianCalendar.DAY_OF_MONTH),dataEmprestimo.get(GregorianCalendar.MONTH),dataEmprestimo.get(GregorianCalendar.YEAR));
		s+=String.format("Data de Devolucao: %02d/%02d/%04d\n",dataDevolucao.get(GregorianCalendar.DAY_OF_MONTH),dataDevolucao.get(GregorianCalendar.MONTH),dataDevolucao.get(GregorianCalendar.YEAR));
		s+="Codigo do Livro: "+codigoLivro+"\n";
		return s;
	}
}
