package comandos;

import java.util.*;

import ResultadoDeVenda.ResultadoDeVenda;
import empregados.*;

public class DepartamentoDeVendas {
	
	private Stack<EmpregadoAssalariadoComissionado> historyD = new Stack<EmpregadoAssalariadoComissionado>();
	private Stack<EmpregadoAssalariadoComissionado> historyR = new Stack<EmpregadoAssalariadoComissionado>();
	private Stack<ResultadoDeVenda> historyVendasD = new Stack<ResultadoDeVenda>();
	private Stack<ResultadoDeVenda> historyVendasR = new Stack<ResultadoDeVenda>();

	private DepartamentoDeFuncionarios empregados;
	
	public boolean lancarResultadoDeVenda() {
		
		Empregado aux = empregados.buscaPorCpf();
		if(aux == null) {
			return false;
		}
		if(aux.getTipo() != 2) {
			System.out.print(aux);
			System.out.print("Não é um empregado Comissionado\n\n");
			return false;
		}
		EmpregadoAssalariadoComissionado empregado = (EmpregadoAssalariadoComissionado) aux;
		System.out.print(empregado);
		ResultadoDeVenda venda = new ResultadoDeVenda();
		venda.cria();
		empregado.adicionarVenda(venda);
		System.out.print("A venda foi adicionada\n");
		System.out.println(venda);
		historyD.push(empregado);
		historyVendasD.push(venda);
		historyVendasR.clear();
		historyR.clear();
		//vendas.push(venda);
		empregado.atulizaVenda(venda.getValor());
		return true;
		
	}
	public boolean desfazLancarResultadoDeVenda() {
		if(historyD.isEmpty() || historyVendasD.isEmpty()) return false;
		EmpregadoAssalariadoComissionado empregado = historyD.pop();
		ResultadoDeVenda venda = historyVendasD.pop();
		historyR.push(empregado);
		historyVendasR.push(venda);
		empregado.atulizaVenda(-venda.getValor());
		System.out.println(venda);
		System.out.println(empregado);
		System.out.print("Lançamento desfeito\n\n");
		return true;
		
	}
	public boolean refazLancarResultadoDeVenda() {
		if(historyR.isEmpty() || historyVendasR.isEmpty()) return false;
		EmpregadoAssalariadoComissionado empregado = historyR.pop();
		ResultadoDeVenda venda = historyVendasR.pop();
		historyD.push(empregado);
		historyVendasD.push(venda);
		empregado.atulizaVenda(venda.getValor());
		System.out.println(venda);
		System.out.println(empregado);
		System.out.print("Lançamento recuperado\n\n");
		return true;
		
	}
	public boolean mostraResultadoDeVenda() {
		
		EmpregadoAssalariadoComissionado empregado = (EmpregadoAssalariadoComissionado)empregados.buscaPorCpf();
		if(empregado == null) {
			return false;
		}
		if(empregado.getTipo() != 2) {
			System.out.print(empregado);
			System.out.print("Não é um empregado Comissionado\n\n");
			return false;
		}
		empregado.mostraResultadoDeVenda();
		return false;
	}
	public void setEmpregados(DepartamentoDeFuncionarios empregados) {
		this.empregados = empregados;
	}
	
	

	

}
