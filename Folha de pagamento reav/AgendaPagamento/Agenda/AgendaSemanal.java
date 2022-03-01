package Agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Stack;


public class AgendaSemanal implements AgendaDePagamento,Cloneable{
	
	private Stack<LocalDate> agenda = null;
	private String descricao;
	private int dia;
	private int periodo = 7;
	
	public  AgendaSemanal(String descricao,int dia) {
		
		LocalDate hoje = LocalDate.now();
		LocalDate base = LocalDate.of(2022, 02, 5).plus(dia,ChronoUnit.DAYS);
		int dias = (int) base.until(hoje,ChronoUnit.DAYS);
		int resto = dias % 7;
		resto = 6 - resto;
		LocalDate proxSexta = hoje.plus(resto, ChronoUnit.DAYS);
		LocalDate diaDoPagamento = proxSexta.plus(7, ChronoUnit.DAYS);
		
		agenda = new Stack<LocalDate>();
		this.descricao = descricao;
		this.dia = dia;
		agenda.push(diaDoPagamento);	
		
	}
	
	public void configuraAgenda() {
		Stack<LocalDate> novaAgenda = new Stack<LocalDate>();
		novaAgenda.push(agenda.pop());
		this.agenda = novaAgenda;		
	}

	
	public LocalDate diaPagamento() {
		return agenda.firstElement();
	}
	public void atualiza() {
		LocalDate antiga = agenda.pop();
		LocalDate nova = antiga.plus(7,ChronoUnit.DAYS);
		agenda.push(nova);

	}
	@Override
	public String toString()
	{
		return String.format("Dia do pagamento: %s", agenda.firstElement());
	}
	public int getPeriodo() {
		return periodo;
	}
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	

}
