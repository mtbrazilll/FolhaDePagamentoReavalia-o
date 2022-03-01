package agendaPagamento;

import java.util.Stack;
import java.time.LocalDate;

import empregados.Empregado;

//import java.time.LocalDate;


public class AgendaPagamento {
	
	
	private int periodo; // em dias
	public Stack<LocalDate> diaDoPagamento = null;
	private String descricao;
	
	public AgendaPagamento(){
		diaDoPagamento = new Stack<LocalDate>();
	}
	
	public AgendaPagamento(int periodo,String descricao){
		this.periodo = periodo;
		this.descricao = descricao;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public Stack<LocalDate> getDiaDoPagamento() {
		return diaDoPagamento;
	}

	public void setDiaDoPagamento(Stack<LocalDate> diaDoPagamento) {
		this.diaDoPagamento = diaDoPagamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

	
	
	
	
}
