package Agenda;

public class AjudaCriarAgendaSextaSemanal implements AjudaCriarAgenda{
	
	public AgendaDePagamento retornaAgenda() {
		AgendaSexta nova = new AgendaSexta(7,"Semanal sexta");
		return nova;
	}
	public void print() {
		System.out.print("Semanal sexta\n");
	}

}
