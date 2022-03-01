package comandos;

import java.util.*;

import Agenda.AgendaDePagamento;
import Agenda.AjudaCriarAgenda;
import empregados.*;
import sindicato.Sindicato;



public class DepartamentoDeFuncionarios {
	
	private Map<String, CriaEmpregado> comandos = new HashMap<String,CriaEmpregado>();
	private ArrayList<Empregado> funcionarios = new ArrayList<Empregado>();
	private ArrayList<Empregado> funcionariosExcluidos = new ArrayList<Empregado>();
	private Stack<Empregado> historyD = new Stack<Empregado>();
	private Stack<Empregado> historyR = new Stack<Empregado>();
	private Stack<Empregado> clones = new Stack<Empregado>();
	
	private DepartamentoDeFinancas agenda;
	private DepartamentoSindical sindicato;
	
	public DepartamentoDeFuncionarios(DepartamentoDeFinancas agenda,DepartamentoSindical sindicato) {
		
		comandos.put("1", new CriaEmpregadoAssalariado());
		comandos.put("2", new CriaEmpregadoAssalariadoComissionado());
		comandos.put("3", new CriaEmpregadoHorista());
		this.agenda = agenda;
		this.sindicato = sindicato;
		
	}
	
	public boolean criaEmpregado() {
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("1-Assalariado \n2-Comissionado \n3-Horista \nDigite o tipo do funcionario :");
		String entrada = teclado.nextLine();
		CriaEmpregado comando = comandos.get(entrada);
		Empregado novo;
		if(comando != null) {
			novo = comando.criaEmpregado();
			if(existePorCpf(novo.getCpf())) {
				System.out.print("Já existe um empregado com esse cpf\n\n");
				return false;
			}
			int var = Integer.valueOf(entrada);
			var = var - 1;
			novo.setAgenda(agenda.getAjudaCriarAgenda(var).retornaAgenda());
			System.out.print("\nEsse funcionário pertecene ao sindicato?" + "\ndigite 1 para sim e para 2 não: ");
			entrada = teclado.nextLine();
			if(entrada.equals("1")) {
				Sindicato aux = sindicato.criaSindicatoN();
				if(aux != null) {
					novo.setSindicato(aux);
				}
			}
			funcionarios.add(novo);
			historyD.add(novo);
			historyR.clear();
			System.out.print("Empregado Adicionado\n\n");
			return true;
		}
		return false;
	}
	
	public boolean desfazCriaEmpregado() {
		if(!historyD.isEmpty()) {
			Empregado aux = historyD.pop();
			System.out.print(buscaPorId(aux.getId()));
			System.out.println();
			historyR.add(buscaPorId(aux.getId()));
			funcionarios.remove(buscaPorId(aux.getId()));
			System.out.print("Empregado foi removido\n\n");
			return true;
		}
		return false;
	}
	
	public boolean refazCriaEmpregado() {
		if(!historyR.isEmpty()) {
			Empregado aux = historyR.pop();
			System.out.print(aux);
			System.out.println();
			funcionarios.add(aux);
			System.out.print("Empregado foi adicionado\n\n");
			historyD.add(aux);
			return true;
		}
		return false;
	}
	
	public boolean excluiEmpregado() {
		Empregado empregado = buscaPorCpf();
		if(empregado == null) {
			return false;
		}
		System.out.print(empregado);
		funcionarios.remove(empregado);
		historyD.push(empregado);
		historyR.clear();
		System.out.print("Empregado removido\n\n");
		return true;
		
	}
	public boolean desfazExcluiEmpregado() {
		if(historyD.empty()) return false;
		Empregado empregado = historyD.pop();
		historyR.push(empregado);
		funcionarios.add(empregado);
		System.out.print(empregado);
		System.out.print("Empregado foi recuperado\n");
		return true;	
	}
	public boolean refazExcluiEmpregado() {
		if(historyR.empty()) return false;
		Empregado empregado = historyR.pop();
		historyD.push(empregado);
		funcionarios.remove(empregado);
		System.out.print(empregado);
		System.out.print("Empregado foi removido\n");
		return true;	
	}

	
	public boolean alteraEmpregado() {
		Scanner teclado = new Scanner(System.in);
		Empregado empregado = buscaPorCpf();
		if(empregado == null) {
			return false;
		}
		try {
			Empregado clone = (Empregado) empregado.clone();
			AgendaDePagamento cloneAgenda = (AgendaDePagamento) empregado.getAgenda().clone();
			cloneAgenda.configuraAgenda();
			clone.setAgenda(cloneAgenda);
			clones.push(clone);
			
			System.out.print("Empregado:\n\n");
			System.out.print(empregado);
			System.out.print("\n\nDigite novas informações ou deixe em branco para não fazer alterações\n");
			String var;
			System.out.print("Digite o nome: ");
			var = teclado.nextLine();
			if(!var.equals("")) {
				empregado.setNome(var);
			}
			System.out.print("Digite o endereço: ");
			var = teclado.nextLine();
			if(!var.equals("")) {
				empregado.setEndereco(var);
			}
			System.out.print("Digite o método de pagamento: ");
			var = teclado.nextLine();
			if(!var.equals("")) {
				empregado.setMetodoPagamento(var);
			}
			System.out.print("Esse funcionário pertecene ao sindicato? "
							+ "\nDigite 1 para sim e para não 2: ");
			String entrada = teclado.nextLine();
			if(entrada.equals("1")) {
				if(empregado.getSindicato() == null) {
					Sindicato novo = new Sindicato();
					empregado.setSindicato(novo);
				}
				System.out.print("Digite a taxa sindical: ");
				 entrada = teclado.nextLine();
				 empregado.getSindicato().setTaxaSindical(Double.valueOf(entrada));
			}
			else if(entrada.equals("2")) {
				empregado.saiDoSindicato();
			}
			System.out.print(empregado);
			historyD.push(empregado);
			historyR.clear();
			
			return true;
			
		}	catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return false;
		}

		
	}
	
	public boolean desfazAlteraEmpregado() {
		if(historyD.isEmpty()) return false;
		Empregado empregado = historyD.pop();
		historyR.push(buscaPorId(empregado.getId()));
		funcionarios.remove(buscaPorId(empregado.getId()));
		funcionarios.add(clones.pop());
		clones.push(empregado);
		return true;
	}
	
	public boolean refazAlteraEmpregado() {
		if(historyR.isEmpty()) return false;
		Empregado empregado = historyR.pop();
		historyD.push(buscaPorId(empregado.getId()));
		funcionarios.remove(buscaPorId(empregado.getId()));
		funcionarios.add(clones.pop());
		clones.push(empregado);
		return true;
	}

	
	public boolean mostraOsFuncionarios() {
		
		System.out.printf("funcionarios cadastrados:%d\n\n",funcionarios.size());
		for(Empregado aux: funcionarios) {
			System.out.println(aux);
			System.out.println();
		}
		System.out.println();
		System.out.println();
		
		return true;
	}
	
	public Empregado buscaPorCpf() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("Digite o cpf do funcionário: ");
		String cpf = teclado.next();
		System.out.print("\n");
		for(Empregado aux: funcionarios) {
			if(aux.getCpf().equals(cpf)) {
				return aux;
			}
		}
		System.out.print("Empregado não encontrado\n\n");
		return null;
	}
	
	public boolean existePorCpf(String cpf) {
		for(Empregado aux: funcionarios) {
			if(aux.getCpf().equals(cpf)) {
				return true;
			}
		}
		return false;
	}


	public void setAgenda(DepartamentoDeFinancas agenda) {
		this.agenda = agenda;
	}

	public void setSindicato(DepartamentoSindical sindicato) {
		this.sindicato = sindicato;
	}

	public ArrayList<Empregado> getFuncionarios() {
		return funcionarios;
	}
	
	public Empregado buscaPorId(UUID id) {

		for(Empregado aux: funcionarios) {
			if(aux.getId().equals(id)) {
				return aux;
			}
		}
		System.out.print("Empregado não encontrado\n\n");
		return null;
	}
	
	
	

}
