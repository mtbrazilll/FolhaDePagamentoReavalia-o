package Agenda;

import java.time.LocalDate;


public interface AgendaDePagamento {

	
	public void configuraAgenda();
	public LocalDate diaPagamento();
	public void atualiza();
	public int getPeriodo();
	public Object clone() throws CloneNotSupportedException ;

	

}
