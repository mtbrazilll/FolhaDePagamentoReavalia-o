package comandos;

import java.util.Stack;

import cartaoDePonto.CartaoDePonto;
import empregados.*;

public class DepartamentoDeControleDeTempo {
	
	private DepartamentoDeFuncionarios departamentoDeFuncionarios;
	private Stack<EmpregadoHorista> historyD = new Stack<EmpregadoHorista>();
	private Stack<EmpregadoHorista> historyR = new Stack<EmpregadoHorista>();
	private Stack<CartaoDePonto> historyPontoD = new Stack<CartaoDePonto>();
	private Stack<CartaoDePonto> historyPontoR = new Stack<CartaoDePonto>();
	
	public void setDepartamentoDeFuncionarios(DepartamentoDeFuncionarios departamentoDeFuncionarios) {
		this.departamentoDeFuncionarios = departamentoDeFuncionarios;
	}
	
	public boolean lancaCartaoDePonto() {
		Empregado aux = departamentoDeFuncionarios.buscaPorCpf();
		if(aux == null) return false;
		if(aux.getTipo() != 3) {
			System.out.print("O funcionário não é horista\n");
			return false;
		}	
		EmpregadoHorista empregado = (EmpregadoHorista) aux;
		System.out.println(empregado);
		CartaoDePonto ponto = new CartaoDePonto();
		ponto.cria();
		empregado.atualiza(ponto.getHorasTrabalhadas(),ponto.getHorasExtras());
		empregado.getCartaoDePonto().add(ponto);
		historyD.push(empregado);
		historyPontoD.push(ponto);
		historyPontoR.clear();
		historyR.clear();
		return true;
	}
	
	public boolean desfazLancarCartaoDePonto() {
		if(historyD.empty() || historyPontoD.empty()) return false;
		EmpregadoHorista empregado = historyD.pop();
		CartaoDePonto ponto = historyPontoD.pop();
		empregado.desatualiza(ponto.getHorasTrabalhadas(),ponto.getHorasExtras());
		System.out.println(empregado);
		System.out.println("Ponto removido\n");
		System.out.print(ponto);
		System.out.print("\n\n");
		historyR.push(empregado);
		historyPontoR.push(ponto);
		return true;
	}
	
	public boolean refazLancarCartaoDePonto() {
		if(historyR.empty() || historyPontoR.empty()) return false;
		EmpregadoHorista empregado = historyR.pop();
		CartaoDePonto ponto = historyPontoR.pop();
		empregado.atualiza(ponto.getHorasTrabalhadas(),ponto.getHorasExtras());
		System.out.println(empregado);
		System.out.print("\n");
		System.out.println("Ponto adicionado\n");
		System.out.print(ponto);
		System.out.print("\n\n");
		historyD.push(empregado);
		historyPontoD.push(ponto);
		return true;
	}
	
	public boolean mostraCartaoDePonto() {

		EmpregadoHorista empregado = (EmpregadoHorista) departamentoDeFuncionarios.buscaPorCpf();
		if(empregado == null) return false;
		if(empregado.getTipo() != 3) {
			System.out.print("O funcionário não é horista\n");
			return false;
		}
		System.out.print(empregado);
		System.out.print("\n\n");
		empregado.mostraCartaoDePonto();
		System.out.print("\n\n");
		return true;
		
	}


	
	


}
