package Agenda;

public class AjudaCriarAjudaBiSemanal implements AjudaCriarAgenda  {

	String descricao;
	String Semanal;
	int dia;
	
	public AjudaCriarAjudaBiSemanal() {
		
	}
	public AjudaCriarAjudaBiSemanal(int dia) {
		this.dia = dia;
		if(dia == 6) {
			this.descricao = "Semanal 2 sexta";
		}
		else if(dia == 2) {
			this.descricao = "Semanal 2 Segunda";
		}
		else if(dia == 3) {
			this.descricao = "Semanal 2 terça";
		}
		else if(dia == 4) {
			this.descricao = "Semanal 2 quarta";
		}
		else if(dia == 5) {
			this.descricao = "Semanal 2 quinta";
		}
	}
	public AgendaDePagamento retornaAgenda() {
		AgendaBiSemanal nova = new AgendaBiSemanal(this.descricao,this.dia+1);
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
