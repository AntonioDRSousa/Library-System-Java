package lp2g04.biblioteca;

import java.util.GregorianCalendar;
import java.io.*;

public class EmprestadoPara implements Serializable{
	private GregorianCalendar dataEmprestimo;
	private GregorianCalendar dataDevolucao;
	private int codigoUsuario;

	public EmprestadoPara(GregorianCalendar demp,GregorianCalendar ddev,int cod){
		dataEmprestimo=demp;
		dataDevolucao=ddev;
		codigoUsuario=cod;
	}
	public EmprestadoPara(){
	}

	public GregorianCalendar getDataEmprestimo(){
		return dataEmprestimo;
	}
	public GregorianCalendar getDataDevolucao(){
		return dataDevolucao;
	}
	public int getCodigoUsuario(){
		return codigoUsuario;
	}

	public String toString(){
		String s=String.format("Data de Emprestimo: %02d/%02d/%04d\n",dataEmprestimo.get(GregorianCalendar.DAY_OF_MONTH),dataEmprestimo.get(GregorianCalendar.MONTH),dataEmprestimo.get(GregorianCalendar.YEAR));
		s+=String.format("Data de Devolucao: %02d/%02d/%04d\n",dataDevolucao.get(GregorianCalendar.DAY_OF_MONTH),dataDevolucao.get(GregorianCalendar.MONTH),dataDevolucao.get(GregorianCalendar.YEAR));
		s+="Codigo Usuario: "+codigoUsuario+"\n";
		return s;
	} 
}
