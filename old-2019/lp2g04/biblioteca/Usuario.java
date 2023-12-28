package lp2g04.biblioteca;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.io.*;
import java.util.Enumeration;

public class Usuario extends Pessoa implements Serializable{
	private String endereco;
	private int codigoUsuario;
	private ArrayList<Emprestimo> Historico; 
	private Hashtable<String,GregorianCalendar> livrosData;

	public Usuario(String n,GregorianCalendar dt,String e,int cod){
		super(n,dt);
		endereco=e;
		codigoUsuario=cod;
		Historico=new ArrayList();
		livrosData=new Hashtable<String,GregorianCalendar>();
	}
	public Usuario(){
		super();
	}

	public void addLivroHist(GregorianCalendar dloc,GregorianCalendar ddev, String cod){
		Historico.add(new Emprestimo(dloc,ddev,cod));
	}

	public  String getEndereco(){
		return endereco;
	}
	public int getCodigo(){
		return codigoUsuario;
	}
	public ArrayList getHistorico(){
		return Historico;
	}
	public Hashtable getLivrosData(){
		return livrosData;
	}

	public void adicionaLivroData(String cod,GregorianCalendar ddev){
		livrosData.put(cod,ddev);
	}
	public void removeLivroData(String cod)throws NullPointerException{
		livrosData.remove(cod);
	}
	public GregorianCalendar getDataDevolucao(String cod){
		return livrosData.get(cod);
	}
	public int numeroLivros(){
		return livrosData.size();
	}

	public String toString(){ 
		int i;
		GregorianCalendar dt;
		String s=super.toString();
		Enumeration e=livrosData.elements();
		Enumeration k=livrosData.keys();
		s+="Endereco: "+endereco+"\n"+"Codigo do Usuario: "+codigoUsuario+"\nPossui "+livrosData.size()+" livros"+"\n\n";
		s+="Livros em Maos:\n";
		while(e.hasMoreElements()){
			dt=(GregorianCalendar) e.nextElement();
			s+="Codigo Livro-> "+k.nextElement()+"     ";
			s+=String.format("Data de Devolucao-> %02d/%02d/%04d\n",dt.get(GregorianCalendar.DAY_OF_MONTH),dt.get(GregorianCalendar.MONTH),dt.get(GregorianCalendar.YEAR));
		} 
		s+="\nHistorico:\n";
		for(i=0;i<Historico.size();i++){
			s+=Historico.get(i)+"\n";
		}
		return s;
	}

}
	
