package vencimento;

import java.time.LocalDate;
import java.time.LocalTime;

public class Vencimento {
	
	LocalDate diaDoPagamento;
	String descricao;
	double valorTotal;
	double descontos;
	double valorLiquido;
	
	public LocalDate getDiaDoPagamento() {
		return diaDoPagamento;
	}
	public void setDiaDoPagamento(LocalDate diaDoPagamento) {
		this.diaDoPagamento = diaDoPagamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public double getDescontos() {
		return descontos;
	}
	public void setDescontos(double descontos) {
		this.descontos = descontos;
	}
	public double getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	
	

}
