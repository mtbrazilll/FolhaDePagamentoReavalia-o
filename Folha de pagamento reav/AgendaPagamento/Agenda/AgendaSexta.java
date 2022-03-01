package Agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Stack;


public class AgendaSexta implements AgendaDePagamento,Cloneable{
	
	private int perido;
	private Stack<LocalDate> agenda = null;
	private String descricao;
	
	public  AgendaSexta(int periodo,String descricao) {
		LocalDate hoje = LocalDate.now();
		LocalDate base = LocalDate.of(2022, 02, 19);
		int dias = (int) base.until(hoje,ChronoUnit.DAYS);
		int resto = dias % 7;
		resto = 6 - resto;
		LocalDate proxSexta = hoje.plus(resto, ChronoUnit.DAYS);
		LocalDate diaDoPagamento = proxSexta.plus(periodo, ChronoUnit.DAYS);
		
		agenda = new Stack<LocalDate>();
		this.perido = periodo;
		this.descricao = descricao;
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
		LocalDate nova = antiga.plus(perido,ChronoUnit.DAYS);
		agenda.push(nova);

	}
	@Override
	public String toString()
	{
		return String.format("Dia do pagamento: %s", agenda.firstElement());
	}
	public int getPeriodo() {
		return perido;
	}
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
}
