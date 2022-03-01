package Agenda;

public class AjudaCriarAgendaSemanal implements AjudaCriarAgenda {
	
	String descricao;
	String Semanal;
	int dia;
	
	public AjudaCriarAgendaSemanal() {
		
	}
	public AjudaCriarAgendaSemanal(int dia) {
		this.dia = dia;
		if(dia == 6) {
			this.descricao = "Semanal 1 sexta";
		}
		else if(dia == 2) {
			this.descricao = "Semanal 1 Segunda";
		}
		else if(dia == 3) {
			this.descricao = "Semanal 1 terça";
		}
		else if(dia == 4) {
			this.descricao = "Semanal 1 quarta";
		}
		else if(dia == 5) {
			this.descricao = "Semanal 1 quinta";
		}
	}
	public AgendaDePagamento retornaAgenda() {
		AgendaSemanal nova = new AgendaSemanal(this.descricao,this.dia+1);
		return nova;
	}

	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getSemanal() {
		return Semanal;
	}
	public void setSemanal(String semanal) {
		Semanal = semanal;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public void print() {
		System.out.println(descricao);
	}
	
	
}
