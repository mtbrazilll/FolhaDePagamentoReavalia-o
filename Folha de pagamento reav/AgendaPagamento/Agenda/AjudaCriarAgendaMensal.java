package Agenda;

public class AjudaCriarAgendaMensal implements AjudaCriarAgenda{
	
	int dia;
	String descricao;
	
	public AjudaCriarAgendaMensal() {
		
	}
	
	public AjudaCriarAgendaMensal(int dia) {
		this.dia = dia;
		descricao = "Mensal " + dia;
	}
	
	public AgendaDePagamento retornaAgenda() {
		AgendaMensal nova = new AgendaMensal(this.dia);
		return nova;
	}
	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void print() {
		System.out.println(descricao);
	}
	
	

}
