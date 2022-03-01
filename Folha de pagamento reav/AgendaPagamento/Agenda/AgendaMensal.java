package Agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Stack;

public class AgendaMensal implements AgendaDePagamento, Cloneable{
	
	private String descricao;
	private Stack<LocalDate> agenda = null;
	private final int dia;
	private int periodo = 30;
	
	public AgendaMensal(int dia){
		this.dia = dia;
		agenda = new Stack<LocalDate>();
		LocalDate aux = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue() ,dia);
		LocalDate diaDoPagamento = aux.plus(1,ChronoUnit.MONTHS) ;
		agenda.push(diaDoPagamento);	
	}
	public void atualiza() {
		
		LocalDate antiga = agenda.pop();
		LocalDate nova = antiga.plus(1,ChronoUnit.MONTHS);
		agenda.push(nova);
		
	}
	public LocalDate diaPagamento() {
		return agenda.firstElement();
	}
	public void configuraAgenda() {
		LocalDate var = LocalDate.of(agenda.peek().getYear(), agenda.peek().getMonthValue(), agenda.peek().getDayOfMonth());
		agenda.pop();
		agenda.push(var);
		
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
