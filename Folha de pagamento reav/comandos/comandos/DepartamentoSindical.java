package comandos;

import java.util.*;


import empregados.*;
import sindicato.*;

public class DepartamentoSindical {
	
	private Stack<Empregado> historyD = new Stack<Empregado>();
	private Stack<Empregado> historyR = new Stack<Empregado>();
	private Stack<Double> historyServicosD = new Stack<Double>();
	private Stack<Double> historyServicosR = new Stack<Double>();
	private DepartamentoDeFuncionarios departamentoDeFuncionarios;
	
	private ArrayList<Sindicato> sindicato = null;
	
	public DepartamentoSindical() {
		sindicato = new ArrayList<Sindicato>();
	}
	
	public boolean lancaServicoAdicional() {
		Empregado empregado = departamentoDeFuncionarios.buscaPorCpf();
		if(empregado == null) return false;
		if(empregado.getSindicato() == null) {
			System.out.print(empregado);
			System.out.print("Esse funcionário não tem sindicato\n\n");
			return false;
		}
		System.out.print("Digite o valor do serviço adicional: ");
		Scanner teclado = new Scanner(System.in);
		String entrada = teclado.nextLine();
		double valor = Double.valueOf(entrada);
		if(valor>=0 && valor <= 200) {
			empregado.getSindicato().setAdditionalTaxes(valor);
			historyD.push(empregado);
			historyR.clear();
			historyServicosR.clear();
			historyServicosD.add(valor);
			System.out.println(empregado);
			System.out.print("Taxa de serviço lançada\n\n");
			return true;
		}else {
			System.out.print("Valor inválido\n\n");
			return false;

		}
	}
	
	public boolean desfazLancaSericoAdicional() {
		if(historyD.empty() || historyServicosD.empty()) return false;
		Empregado empregado = historyD.pop();
		double valor = historyServicosD.pop();
		historyR.push(empregado);
		historyServicosR.push(valor);
		empregado.getSindicato().getServicosAdicionais().remove(valor);
		System.out.println(empregado);
		System.out.printf("%.2f \n",valor);
		System.out.print("Taxa de serviço cancelada\n\n");
		return true;
		
	}
	
	public boolean refazLancaSericoAdicional() {
		if(historyR.empty() || historyServicosR.empty()) return false;
		Empregado empregado = historyR.pop();
		double valor = historyServicosR.pop();
		historyD.push(empregado);
		historyServicosD.push(valor);
		System.out.println(empregado);
		empregado.getSindicato().getServicosAdicionais().add(valor);
		System.out.printf("%.2f \n",valor);
		System.out.print("Taxa de serviço lançada\n\n");
		return true;
		
	}

	
	public boolean mostraOsSindicatos() {
		if(sindicato.size()==0) {
			System.out.print("Nenhum sindicato casdastrado\n\n");
			return true;
		}
		int i = 0;
		System.out.print("Sindicatos cadastrados:\n\n");
		for(Sindicato aux: sindicato) {
			System.out.print(i+" - "+aux);
			i++;
		}
		System.out.println();
		return true;
	}
	
	public boolean criaSindicato() {
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("Digite o nome do sindicato: " );
		String nome = teclado.nextLine();
		//System.out.println();
		System.out.print("Digite a taxa sindical: " );
		String valor = teclado.nextLine();
		System.out.println();
		double taxaSindical = Double.parseDouble(valor);
		if(taxaSindical>= 0 && taxaSindical <= 1000) {
			Sindicato novoSindicato = new Sindicato(taxaSindical,nome);
			sindicato.add(novoSindicato);
			System.out.print("Sindicato foi adicionado" );
			System.out.print("\n\n" );
			return true;
		}
		else {
			System.out.print("Taxa sindical é negativa ou maior que 200 reais\n" );
			System.out.print("Sindicato não Cadastrado\n\n" );
			return false;
		}
		
	}
	
	public Sindicato criaSindicatoN() {
		
		Scanner teclado = new Scanner(System.in);;
		//System.out.println();
		System.out.print("Digite a taxa sindical: " );
		String valor = teclado.nextLine();
		System.out.println();
		double taxaSindical = Double.parseDouble(valor);
		if(taxaSindical>= 0 && taxaSindical <= 1000) {
			Sindicato novoSindicato = new Sindicato(taxaSindical,"n/a");
			sindicato.add(novoSindicato);
			return novoSindicato;
		}
		else {
			System.out.print("Taxa sindical é negativa ou maior que 200 reais\n" );
			System.out.print("Sindicato não adicionado\n" );
			return null;
		}
		
	}

	
	
	public void excluiUltimoSindicatoAdicionado() {
		
		if(!sindicato.isEmpty()) {
			System.out.print(sindicato.get(sindicato.size()-1));
			System.out.print("Sindicato removido");
			sindicato.remove(sindicato.size()-1);
		}
		
	}
	
	public boolean sidincatoEstaVazio() {
		return sindicato.isEmpty();
	}
	
	public Sindicato retornarSindicato(int opc) {
		if(opc>=0 && opc<sindicato.size()) {
			try {
				Sindicato clone = (Sindicato) sindicato.get(opc).clone();
				return clone;
			}	catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	public void setDepartamentoDeFuncionarios(DepartamentoDeFuncionarios departamentoDeFuncionarios) {
		this.departamentoDeFuncionarios = departamentoDeFuncionarios;
	}
	
	
	
	

}
