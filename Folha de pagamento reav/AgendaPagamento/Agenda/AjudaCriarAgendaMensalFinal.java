package Agenda;

public class AjudaCriarAgendaMensalFinal implements  AjudaCriarAgenda{
	
	public AgendaDePagamento retornaAgenda() {
		AgendaFinalDoMes nova = new AgendaFinalDoMes();
		return nova;
	}
	public void print() {
		System.out.print("Mensal ultimo dia util\n");
	}

}
