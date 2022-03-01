package Agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Stack;


public class AgendaFinalDoMes implements AgendaDePagamento, Cloneable {
	
	private String descricao;
	private Stack<LocalDate> agenda = null;
	private int periodo = 30;
	
	public AgendaFinalDoMes() {
		
		LocalDate hoje = LocalDate.now().plus(1, ChronoUnit.MONTHS);
		agenda = new Stack<LocalDate>();
		int dia = hoje.lengthOfMonth();
		int ano = hoje.getYear();
		int mes = hoje.getMonthValue();
		
		LocalDate finalDoMes = LocalDate.of(ano, mes, dia);
		LocalDate finalDoMesCerto;
		
		if(finalDoMes.getDayOfWeek().getValue()==0) {
			finalDoMesCerto = finalDoMes.minus(2, ChronoUnit.DAYS);	
			agenda.push(finalDoMesCerto);
		}else if(finalDoMes.getDayOfWeek().getValue()==6){
			finalDoMesCerto = finalDoMes.minus(1, ChronoUnit.DAYS);
			agenda.push(finalDoMesCerto);
		}else {
			agenda.push(finalDoMes);
		}
		descricao = "Final do mês";
		
		;
		
		
	}
	public void atualiza() {
		
		
		LocalDate antiga = agenda.pop();
		LocalDate hoje = antiga.plus(7,ChronoUnit.DAYS);
		int dia = hoje.lengthOfMonth();
		int ano = hoje.getYear();
		int mes = hoje.getMonthValue();
		
		LocalDate finalDoMes = LocalDate.of(ano, mes, dia);
		LocalDate finalDoMesCerto;
		if(finalDoMes.getDayOfWeek().getValue()==0) {
			finalDoMesCerto = finalDoMes.minus(2, ChronoUnit.DAYS);	
		}else {
			finalDoMesCerto = finalDoMes.minus(1, ChronoUnit.DAYS);
		}
		descricao = "Final do mês";
		agenda.push(finalDoMesCerto);
		
	}
	
	public LocalDate diaPagamento() {
		return agenda.firstElement();
	}
	
	public void configuraAgenda() {
		Stack<LocalDate> novaAgenda = new Stack<LocalDate>();
		
		//novaAgenda.push(agenda.pop());
		novaAgenda.push(LocalDate.of(agenda.peek().getYear(), agenda.peek().getMonthValue(), agenda.peek().getDayOfMonth()));
		this.agenda = novaAgenda;		
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
