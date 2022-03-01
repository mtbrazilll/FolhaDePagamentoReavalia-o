package comandos;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.*;

import sindicato.Sindicato;
import empregados.*;
import ResultadoDeVenda.ResultadoDeVenda;
import cartaoDePonto.CartaoDePonto;
import Agenda.*;


public class FolhaDePagamento {
	
public ArrayList<Sindicato> sindicato = null;
//public ArrayList<Sindicato> agenda = null;
public ArrayList<Empregado> funcionarios = null;
public ArrayList<Empregado> funcionariosExcluidos = null;
public ArrayList<AgendaDePagamento> agendaDePagamento = null;
//public ArrayList<ResultadoDeVenda> vendas = new ArrayList();
public Stack<ResultadoDeVenda> vendas = null;
public Stack<Empregado> history = null;
public Stack<Empregado> clones = null;
public ArrayList <AjudaCriarAgenda> agenda = null;


	
	
	public FolhaDePagamento() {
		sindicato = new ArrayList<Sindicato>();
		//agenda = new ArrayList<AgendaPa>();
		funcionarios = new ArrayList<Empregado>();
		funcionariosExcluidos = new ArrayList<Empregado>();
		agendaDePagamento = new ArrayList<AgendaDePagamento>();
		vendas = new Stack<ResultadoDeVenda>();
		history = new Stack<Empregado>();
		clones = new Stack<Empregado>();
		agenda = new ArrayList<AjudaCriarAgenda>();
		
		agenda.add(new AjudaCriarAgendaMensalFinal());
		agenda.add(new AjudaCriarAgendaSextaSemanal());
		agenda.add(new AjudaCriarAgendaBisemanal());		
		
	}
	
	public void criaFuncionario() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("Digite o tipo do funcionario 1-Assalariado 2-Comissionado 3-Horista:");
		String entrada = teclado.nextLine();
		
		if(entrada.equals("1")) {
			
			EmpregadoAssalariado novo = new EmpregadoAssalariado();
			novo.cria();
			if(existeEmpregadoPorCpf(novo.getCpf()) == 1) {
				System.out.print("\nJá existe um empregado com esse cpf\ncpf definido como n/a\n");
				novo.setCpf("n/a");
			}
			AgendaFinalDoMes padrao = new AgendaFinalDoMes();
			novo.setAgenda(padrao);
			funcionarios.add(novo);
			
		}
		else if(entrada.equals("2")) {
			EmpregadoAssalariadoComissionado novo = new EmpregadoAssalariadoComissionado();
			novo.cria();
			if(existeEmpregadoPorCpf(novo.getCpf()) == 1) {
				System.out.print("Já existe um empregado com esse cpf\n");
				novo.setCpf("n/a");
			}
			AgendaSexta padrao = new AgendaSexta(14,"bi-semanal");
			novo.setAgenda(padrao);
			funcionarios.add(novo);
			
		}
		else if(entrada.equals("3")) {
			EmpregadoHorista novo = new EmpregadoHorista();
			novo.cria();
			if(existeEmpregadoPorCpf(novo.getCpf()) == 1) {
				System.out.print("Já existe um empregado com esse cpf\n");
				novo.setCpf("n/a");
			}
			AgendaSexta padrao = new AgendaSexta(7,"semanal");
			novo.setAgenda(padrao);
			funcionarios.add(novo);
			
		}
		else {
			System.out.print("\ntipo não encontrado");
			return;
		}
		System.out.print("\nEsse funcionário pertecene ao sindicato? "
						+ "\ndigite 1 para sim e para 2 não: ");
		entrada = teclado.nextLine();
		if(entrada.equals("1")) {
			if(sindicato.size()!=0) {
				mostraOsSindicatos();
				int i;
				System.out.print("\nDigite o número do sindicato: ");
				i = teclado.nextInt();
				if(i>0 && i<=sindicato.size()) {
					int tamanho = funcionarios.size()-1;
					Empregado aux = funcionarios.get(tamanho);
					try {
						Sindicato clone = (Sindicato) sindicato.get(i-1).clone();
						aux.setSindicato(clone);
					}	catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
				else {
					System.out.print("\número do sindicato não encontrado");
				}
				
			}
			else {
				System.out.print("\nNenhum sindicato casdastrado");
			}
		}

		
	}
	
	public void desfazCriaFuncionario() {
		int tamanho = funcionarios.size();
		if(tamanho>0) {
			tamanho--;
			funcionarios.remove(tamanho);
			System.out.print("funcionario foi removido" );

		}
		else {
			System.out.print("Não existe funcionario");

		}
		System.out.print("\n" );
		
	}

	public void criaSindicato() {
		
		Sindicato novoSindicato;
		Scanner teclado = new Scanner(System.in);
		System.out.print("Digite o nome do sindicato:" );
		String nome = teclado.next();
		System.out.print("Digite a taxa sindical:" );
		double taxaSindical = teclado.nextDouble();
		novoSindicato = new Sindicato(taxaSindical,nome);
		sindicato.add(novoSindicato);
		System.out.print("Sindicato foi adicionado" );
		System.out.print("\n" );
		
	}
	
	public void desfazSindicatoCriado() {
		int tamanho = sindicato.size();
		if(tamanho>0) {
			tamanho--;
			sindicato.remove(tamanho);
			System.out.print("Sindicato foi removido" );

		}
		else {
			System.out.print("Não existe sindicato" );

		}
		System.out.print("\n" );
	}
	
	public void mostraOsSindicatos() {
		int i = 1;
		if(sindicato.size()==0) {
			System.out.print("Nenhum sindicato casdastrado");
		}
		System.out.print("Sindicatos cadastrados:\n");
		for(Sindicato aux: sindicato) {
			System.out.println(i+":"+aux);
			i++;
		}
		System.out.println();
	}
	
	public void mostraOsFuncionarios() {
		
		System.out.printf("\nfuncionarios cadastrados:%d\n\n",funcionarios.size());
		for(Empregado aux: funcionarios) {
			System.out.println(aux);
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	public void excluiFuncionarios() {
		System.out.print("digite o cpf do funcionário");
		int achou = 0;
		Scanner teclado = new Scanner(System.in);
		String cpf = teclado.next();
		for(Empregado aux: funcionarios) {
			if(aux.getCpf().equals(cpf)) {
				funcionariosExcluidos.add(aux);
				funcionarios.remove(aux);
				System.out.print("O funcionário foi removido\n");
				System.out.println(aux);
				achou = 1;
				break;
			}
		}
		if(achou == 0) {
			System.out.print("O funcionário não foi encontrado\n");

		}
	}
	
	public void desfazExcluiFuncionarios() {
		if(funcionariosExcluidos.size()==0) return;
		Empregado aux = funcionariosExcluidos.get(0);
		funcionarios.add(aux);
		System.out.print("O funcionário foi recuperado\n");
		System.out.print(aux);
		funcionariosExcluidos.remove(0);
		return;

	}
	
	public Empregado buscaPorCpf(String cpf) {
		for(Empregado aux: funcionarios) {
			if(aux.getCpf().equals(cpf)) {
				return aux;
			}
		}
		return null;
	}
	
	public void postarVenda() {
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("Digite o cpf do funcionário: ");
		String cpf = teclado.next();
		Empregado empregado = buscaPorCpf(cpf);
		if(empregado == null) {
			System.out.print("O funcionário não foi encontrado\n");
			return;
		}
		if(empregado.getTipo() != 2){
			System.out.println("Não é um empregado comissionado");
			return;
		} 
		System.out.println("Empregado:");
		System.out.print(empregado);
		ResultadoDeVenda venda = new ResultadoDeVenda();
		venda.cria();
		EmpregadoAssalariadoComissionado aux = (EmpregadoAssalariadoComissionado)empregado;
		aux.adicionarVenda(venda);
		System.out.print("A venda foi adicionada\n");
		System.out.println(venda);
		history.push(empregado);
		vendas.push(venda);
		aux.atulizaVenda(venda.getValor());
	}
	
	public void desfazPostarVenda() {
		
		if(history == null) return;
		EmpregadoAssalariadoComissionado empregado = (EmpregadoAssalariadoComissionado)history.pop();
		ResultadoDeVenda venda = vendas.pop();
		empregado.removerVenda(venda);
		empregado.setVendasBrutas(empregado.getVendasBrutas()-venda.getValor());
		System.out.println("Venda desfeita\n"+venda.toString()+empregado.toString());
	}
	
	public void postarTaxaDeServico() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("Digite o cpf do funcionário: ");
		String cpf = teclado.next();
		Empregado empregado = buscaPorCpf(cpf);
		if(empregado == null) {
			System.out.print("O funcionário não foi encontrado\n");
			return;
		}
		if(empregado.getSindicato() == null) {
			System.out.print("O funcionário não tem sindicato\n");
			return;
		}
		System.out.println("Empregado:");
		System.out.print(empregado);
		System.out.print("\nDigite o valor da taxa adicional:");
		double valor = teclado.nextDouble();
		empregado.getSindicato().setAdditionalTaxes(valor);
		System.out.print("\nTaxa adicionada");
		history.push(empregado);
		
	}
	
	public void desfazPostarTaxaDeServico() {
		Empregado empregado = history.pop();
		empregado.getSindicato().remove_ultimo();
	}

	public void postarCartaoDePonto() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("Digite o cpf do funcionário: ");
		String cpf = teclado.next();
		Empregado empregado = buscaPorCpf(cpf);
		if(empregado == null) {
			System.out.print("O funcionário não foi encontrado\n");
			return;
		}
		if(empregado.getTipo() != 3) {
			System.out.print("O funcionário não é horista\n");
			return;
		}
		EmpregadoHorista aux = (EmpregadoHorista) empregado;
		CartaoDePonto ponto = new CartaoDePonto();
		ponto.cria();
		aux.atualiza(ponto.getHorasTrabalhadas(),ponto.getHorasExtras());
		aux.getCartaoDePonto().add(ponto);
		history.push(aux);
		
	}
	
	public void desfazerPostarCartaoDePonto() {
		EmpregadoHorista aux = (EmpregadoHorista)history.pop();
		CartaoDePonto ponto;
		ponto = aux.pegaUltimo();
		aux.desatualiza(ponto.getHorasTrabalhadas(),ponto.getHorasExtras());
		aux.remove_ultimo();
		System.out.print("O cartão foi apagado do empregado:\n"+aux);
	}
	
	public void alterarEmpregado() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("\nDigite o cpf do funcionário: ");
		String cpf = teclado.next();
		Empregado empregado = buscaPorCpf(cpf);
		if(empregado == null) {
			System.out.print("\nO funcionário não foi encontrado\n");
			return;
		}
		try {
			Empregado clone = (Empregado) empregado.clone();
			clones.push(clone);
		}	catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.print("Empregado:\n");
		System.out.print(empregado);
		System.out.print("Digite novas informações ou deixe em branco para não fazer alterações\n");
		String var;
		System.out.print("Digite o nome: ");
		var = teclado.nextLine();
		var = teclado.nextLine();
		if(!var.equals("")) {
			empregado.setNome(var);
		}
		System.out.print("Digite o endereço: ");
		var = teclado.nextLine();
		if(!var.equals("")) {
			empregado.setEndereco(var);
		}
		System.out.println("Digite o método de pagamento: ");
		var = teclado.nextLine();
		if(!var.equals("")) {
			empregado.setMetodoPagamento(var);
		}
		System.out.print("Esse funcionário pertecene ao sindicato? "
						+ "\nDigite 1 para sim e para não 2: ");
		String entrada = teclado.nextLine();
		if(entrada.equals("1")) {
			if(sindicato.size()!=0) {
				mostraOsSindicatos();
				int i;
				System.out.print("\nDigite o número do sindicato: ");
				i = teclado.nextInt();
				if(i>0 && i<=sindicato.size()) {
					try {
						Sindicato clone = (Sindicato) sindicato.get(i-1).clone();
						if(empregado.getSindicato() != null)
						clone.setServicosAdicionais(empregado.getSindicato().getServicosAdicionais());
						empregado.setSindicato(clone);
					}	catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
				else {
					System.out.print("\número do sindicato não encontrado");
				}
				
			}
			else {
				System.out.print("\nNenhum sindicato casdastrado");
			}
		}
		System.out.println();
		System.out.println(empregado);
		history.push(empregado);
		
	}
	
	int existeEmpregadoPorCpf(String cpf) {
		for(Empregado aux: funcionarios) {
			if(aux.getCpf().equals(cpf)) {
				return 1;
			}
		}
		return 0;
	}
	
	public void desfazAlterarEmpregado() {
		
		if(clones.isEmpty()) return;
		if(history.empty()) return;
		Empregado aux = history.pop();
		funcionarios.remove(aux);
		aux = clones.pop();
		funcionarios.add(aux);
		System.out.print("Alterações desfeitas\n");
		System.out.print(aux);
		
	}
	
	public void CriaAgenda() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("\nEscolha para criar: 1-semanal 2-mensal\n");
		String var = teclado.nextLine();
		if(var.equals("1")) {
			System.out.print("Escolha para criar:\r\n"
					+ "segunda = 1;\r\n"
					+ "terça   = 2;\r\n"
					+ "quarta  = 3;\r\n"
					+ "quinta  = 4;\r\n"
					+ "sexta   = 5;\r\n");
			var = teclado.nextLine();
			int dia = Integer.parseInt(var);
			if(dia>0 && dia < 6) {
				AjudaCriarAgendaSemanal nova = new AjudaCriarAgendaSemanal(dia+1);
				agenda.add(nova);
				nova.print();
				return;
			}else {
				System.out.println("\nDia não valido\n");
				return;
			}
		}
		else if(var.equals("2")) {
			System.out.print("\nDigite o dia: ");
			var = teclado.nextLine();
			int dia = Integer.parseInt(var);
			if(dia>0 && dia < LocalDate.now().plus(1,ChronoUnit.MONTHS).lengthOfMonth()) {
				AjudaCriarAgendaMensal nova = new AjudaCriarAgendaMensal(dia);
				agenda.add(nova);
				nova.print();
				return;
			}else {
				System.out.println("\nDia não valido\n");
				return;
			}
		}
		
	}
	
	public void alteraAgenda() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("\nDigite o cpf do funcionário: ");
		String cpf = teclado.next();
		Empregado empregado = buscaPorCpf(cpf);
		if(empregado == null) {
			System.out.print("\nO funcionário não foi encontrado\n");
			return;
		}
		System.out.println("\nEmpregado:\n");
		System.out.print(empregado);
		System.out.println();
		int i = 1;
		System.out.print("\nEscolha uma agenda:\n");
		for(AjudaCriarAgenda aux: agenda) {
			System.out.printf("%d: ",i++);
			aux.print();
		}
		i = teclado.nextInt();
		if(i>0 && i<=agenda.size()) {
			empregado.setAgenda(agenda.get(i-1).retornaAgenda());
		}
		System.out.println(empregado);
		history.push(empregado);
		
	}
	
	public void rodarFolha() {
		System.out.printf("\nFuncionários a ser pago:\n");
		for(Empregado empregado : funcionarios) {
			if(empregado.getAgenda().diaPagamento().equals(LocalDate.now())) {			
				System.out.print(empregado);
				System.out.printf("Valor liquído: %.2f\n", empregado.rendimento());
				empregado.getAgenda().atualiza();
				empregado.limpaPagamento();
				System.out.print("\n\n");
			}
		}
	}
	
	
}
