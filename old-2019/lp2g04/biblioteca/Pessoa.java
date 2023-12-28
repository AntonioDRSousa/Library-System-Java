package lp2g04.biblioteca;

import java.util.GregorianCalendar;
import java.io.*;

public class Pessoa implements Serializable{
	private String nome;
	private GregorianCalendar dataNasc;

	public Pessoa(String n,GregorianCalendar dt){
		nome=n;
		dataNasc=dt;
	}
	public Pessoa(){
	}

	public String getNome(){
		return nome;
	}
	public GregorianCalendar getData(){
		return dataNasc;
	}

	public String toString(){
		String s="Nome: "+nome+"\n";
		s+=String.format("Data: %02d/%02d/%04d\n",dataNasc.get(GregorianCalendar.DAY_OF_MONTH),dataNasc.get(GregorianCalendar.MONTH),dataNasc.get(GregorianCalendar.YEAR));
		return s;
	}
}
