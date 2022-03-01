package Agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Stack;

public class AgendaBiSemanal implements AgendaDePagamento, Cloneable {
	
	private Stack<LocalDate> agenda = null;
	private String descricao;
	private int dia;
	private int periodo = 14;
	
	public  AgendaBiSemanal(String descricao,int dia) {
		
		LocalDate hoje = LocalDate.now();
		LocalDate base = LocalDate.of(2022, 02, 5).plus(dia,ChronoUnit.DAYS);
		int dias = (int) base.until(hoje,ChronoUnit.DAYS);
		int resto = dias % 7;
		resto = 6 - resto;
		LocalDate proxSexta = hoje.plus(resto, ChronoUnit.DAYS);
		LocalDate diaDoPagamento = proxSexta.plus(14, ChronoUnit.DAYS);
		
		agenda = new Stack<LocalDate>();
		this.descricao = descricao;
		this.dia = dia;
		agenda.push(diaDoPagamento);	
		
	}
	
	public void configuraAgenda() {
		LocalDate var = LocalDate.of(agenda.peek().getYear(), agenda.peek().getMonthValue(), agenda.peek().getDayOfMonth());
		agenda.pop();
		agenda.push(var);
		
	}
	
	public LocalDate diaPagamento() {
		return agenda.firstElement();
	}
	public void atualiza() {
		LocalDate antiga = agenda.pop();
		LocalDate nova = antiga.plus(14,ChronoUnit.DAYS);
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
