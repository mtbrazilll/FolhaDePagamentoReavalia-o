package comandos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import Agenda.*;
import empregados.Empregado;
import sindicato.Sindicato;
import vencimento.Vencimento;

public class DepartamentoDeFinancas {
	
	private Stack<Empregado> historyD = new Stack<Empregado>();
	private Stack<Empregado> historyR = new Stack<Empregado>();
	private Stack<Empregado> clones = new Stack<Empregado>();
	private Stack<Empregado> clonesR = new Stack<Empregado>();
	private ArrayList <AjudaCriarAgenda> agenda = null;
	private DepartamentoDeFuncionarios departamentoDeFuncionarios;
	
	public DepartamentoDeFinancas(){
		agenda = new ArrayList<AjudaCriarAgenda>();
		agenda.add(new AjudaCriarAgendaMensalFinal());
		agenda.add(new AjudaCriarAgendaBisemanal());
		agenda.add(new AjudaCriarAgendaSextaSemanal());
	}
	
	public boolean rodarFolhaDePagamento() {
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("Digite o dia: ");
		String dia = teclado.next();
		System.out.print("Digite o mês: ");
		String mes = teclado.next();
		System.out.print("Digite o ano: ");
		String ano = teclado.next();
		LocalDate ver = LocalDate.of(Integer.valueOf(ano), Integer.valueOf(mes), Integer.valueOf(dia));
		System.out.println(ver);
		if(dia == null) {
			System.out.println("Data inválida\n\n");
			return false;
		}
		System.out.print("Empregado a ser pago:\n\n");
		int sinal = 0;
		for(Empregado empregado: departamentoDeFuncionarios.getFuncionarios()) {
			if(empregado.getAgenda().diaPagamento().isEqual(ver)) {
				try {
					Empregado clone = (Empregado) empregado.clone();
					AgendaDePagamento cloneAgenda = (AgendaDePagamento) empregado.getAgenda().clone();
					cloneAgenda.configuraAgenda();
					clone.setAgenda(cloneAgenda);
					if(clone != null) {
						clones.push(clone);
						historyD.push(empregado);
						System.out.print(empregado);
						System.out.print("\n");
						double pagamento = empregado.rendimento();
						System.out.printf("Valor pago: %.2f\n",pagamento);
						System.out.printf("Método de pagamento: %s\n",empregado.getMetodoPagamento());
						empregado.limpaPagamento();
						empregado.getAgenda().atualiza();
						Vencimento vencimento = new Vencimento();
						vencimento.setValorTotal(pagamento);
						empregado.getVencimento().add(vencimento);
						System.out.print("\n");
						sinal = 1;
					}	
				}	catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}

			}	
		}
		if(sinal == 1) return true;
		return false;
		
	}
	
	public boolean desfazRodarFolhaDePagamento() {
		if(clones.isEmpty()) return false;
		while(!clones.isEmpty()) {
			Empregado aux1 = historyD.pop();
			Empregado aux2 = clones.pop();
			clonesR.push(aux1);
			historyR.push(aux2);
			departamentoDeFuncionarios.getFuncionarios().remove(aux1);
			departamentoDeFuncionarios.getFuncionarios().add(aux2);
			//AgendaDePagamento cloneAgenda = aux1.getAgenda();
			//aux1.setAgenda(aux2.getAgenda());
			//aux2.setAgenda(cloneAgenda);
			System.out.print("Pagamento desfeito\n\n");
			System.out.print(aux2);
			System.out.print("\n");
		}
		return true;
	}
	
	public boolean refazRodarFolhaDePagamento() {
		if(clonesR.isEmpty()) return false;
		while(!clonesR.isEmpty()) {
			Empregado aux1 = historyR.pop();
			Empregado aux2 = clonesR.pop();
			clones.push(aux1);
			historyD.push(aux2);
			departamentoDeFuncionarios.getFuncionarios().remove(aux1);
			departamentoDeFuncionarios.getFuncionarios().add(aux2);
			System.out.print(aux2);
			System.out.printf("\nValor pago: %.2f\n",aux2.ultimoPagamento());
			System.out.printf("Método de pagamento: %s\n",aux2.getMetodoPagamento());
			System.out.print("\n");
		}
		return true;
	}

	
	public boolean mostraAgendaDePagamento() {
		if(!agenda.isEmpty()) {
			int i = 0;
			for(AjudaCriarAgenda aux: agenda) {
				System.out.printf("%d: ",i++);
				aux.print();
			}
		}
		else {
			System.out.printf("Não existe nenhuma agenda de pagamento\n");
		}
		return true;
		
	}
	
	public AgendaDePagamento retornaAgendaDePagamento(int escolha) {
		if(escolha >= 0 && escolha < agenda.size() && !agenda.isEmpty()) {
			return agenda.get(escolha).retornaAgenda();	
		}
		return null;
	}
	
	public boolean CriaAgendaDePagamento() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("1-semanal 1 \n2-semanal 2 \n3-mensal \n\nEscolha para criar: ");
		String var = teclado.nextLine();
		if(var.equals("1")) {
			System.out.print(""
					+ "segunda = 1;\r\n"
					+ "terça   = 2;\r\n"
					+ "quarta  = 3;\r\n"
					+ "quinta  = 4;\r\n"
					+ "sexta   = 5;\r\n"
					+ "Escolha para criar: ");
			var = teclado.nextLine();
			System.out.println();
			int dia = Integer.parseInt(var);
			if(dia>0 && dia < 6) {
				AjudaCriarAgendaSemanal nova = new AjudaCriarAgendaSemanal(dia+1);
				agenda.add(nova);
				nova.print();
				return true;
			}else {
				System.out.println("\nDia não valido\n");
				return false;
			}
		}
		else if(var.equals("1")) {
			System.out.print(""
					+ "segunda = 1;\r\n"
					+ "terça   = 2;\r\n"
					+ "quarta  = 3;\r\n"
					+ "quinta  = 4;\r\n"
					+ "sexta   = 5;\r\n"
					+ "Escolha para criar: ");
			var = teclado.nextLine();
			System.out.println();
			int dia = Integer.parseInt(var);
			if(dia>0 && dia < 6) {
				AjudaCriarAjudaBiSemanal nova = new AjudaCriarAjudaBiSemanal(dia+1);
				agenda.add(nova);
				nova.print();
				return true;
			}else {
				System.out.println("\nDia não valido\n");
				return false;
			}
		}

		else if(var.equals("3")) {
			System.out.print("Digite o dia do mês: ");
			var = teclado.nextLine();
			int dia = Integer.parseInt(var);
			if(dia>0 && dia < LocalDate.now().plus(1,ChronoUnit.MONTHS).lengthOfMonth()) {
				AjudaCriarAgendaMensal nova = new AjudaCriarAgendaMensal(dia);
				agenda.add(nova);
				nova.print();
				return true;
			}else {
				System.out.println("\nDia não valido\n");
				return false;
			}
		}
		return false;
	}
	
	public boolean excluiAgendaDepagamento() {
		if(!agenda.isEmpty()) {
			Scanner teclado = new Scanner(System.in);
			mostraAgendaDePagamento();
			System.out.print("Digite o número da agenda: ");
			int escolha = teclado.nextInt();
			System.out.print("\n");
			if(escolha >= 0 && escolha < agenda.size())
			agenda.remove(escolha);
		}
		else {
			System.out.printf("Não existe nenhuma agenda de pagamento\n");
		}
		return true;
		
	}

	public AjudaCriarAgenda getAjudaCriarAgenda(int opc) {
		if(opc>= 0 && opc < agenda.size()) return agenda.get(opc);
		return null;
	}

	public void setAgenda(ArrayList<AjudaCriarAgenda> agenda) {
		this.agenda = agenda;
	}
	
	public boolean alterarAgendaDePagamento() {
		Empregado empregado =  departamentoDeFuncionarios.buscaPorCpf();
		if(empregado == null) return false;
		Scanner teclado = new Scanner(System.in);
		mostraAgendaDePagamento();
		System.out.print("Digite um número: ");
		System.out.print("\n");
		String var = teclado.nextLine();
		int opc = Integer.valueOf(var);
		if(retornaAgendaDePagamento(opc) == null) {
			System.out.print("Número inválido\n\n");
			return false;
		}
		empregado.setAgenda(retornaAgendaDePagamento(opc));
		System.out.println(empregado);
		System.out.print("Agenda alterada\n\n");
		return true;
		
	}

	public void setDepartamentoDeFuncionarios(DepartamentoDeFuncionarios departamentoDeFuncionarios) {
		this.departamentoDeFuncionarios = departamentoDeFuncionarios;
	}
	
	
}
