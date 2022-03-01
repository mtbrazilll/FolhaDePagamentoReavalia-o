package Agenda;

public class AjudaCriarAgendaBisemanal implements AjudaCriarAgenda {

	public AgendaDePagamento retornaAgenda() {
		AgendaSexta nova = new AgendaSexta(14,"Bi-semanal sexta");
		return nova;
	}
	public void print() {
		System.out.print("Bi-semanal sexta\n");
	}
}
