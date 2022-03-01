package comandos;

import java.util.*;

public class Invoker {
	
	
	private DepartamentoSindical departamentoSindical = new DepartamentoSindical();
	private DepartamentoDeFinancas departamentoDeFinancas = new DepartamentoDeFinancas();
	private DepartamentoDeFuncionarios departamentoDeFuncionarios = new DepartamentoDeFuncionarios(departamentoDeFinancas,departamentoSindical);
	private DepartamentoDeVendas departamentoDeVendas = new DepartamentoDeVendas();
	private DepartamentoDeControleDeTempo departamentoDeControleDeTempo = new DepartamentoDeControleDeTempo();
	
	
	private  Map<String, Comando> comandos = new HashMap<String,Comando>();
	private  Stack<Comando> historyD = new Stack<Comando>();
	private  Stack<Comando> historyR = new Stack<Comando>();
	
	{
		
	comandos.put("1",  new CriaFuncionario(departamentoDeFuncionarios));
	comandos.put("2",  new RemoveEmpregado(departamentoDeFuncionarios));
	comandos.put("3",  new LancarCartaoDePonto(departamentoDeControleDeTempo));
	comandos.put("4",  new LancarResultadoDeVenda(departamentoDeVendas));
	comandos.put("5",  new LancarTaxaDeServico(departamentoSindical));
	comandos.put("6",  new alteraEmpregado(departamentoDeFuncionarios));
	comandos.put("7",  new RodarFolhaDePagamento(departamentoDeFinancas));
	comandos.put("11", new AlteraAgendaDePagamento(departamentoDeFinancas));
	comandos.put("10", new CriaAgendaDePagamento(departamentoDeFinancas));
	comandos.put("a",  new MostrarOsFuncionarios(departamentoDeFuncionarios));
	
	departamentoDeVendas.setEmpregados(departamentoDeFuncionarios);
	departamentoDeControleDeTempo.setDepartamentoDeFuncionarios(departamentoDeFuncionarios);
	departamentoSindical.setDepartamentoDeFuncionarios(departamentoDeFuncionarios);
	departamentoDeFinancas.setDepartamentoDeFuncionarios(departamentoDeFuncionarios);
	
	}
	
	public void invoke(String comando) {
		Comando a = comandos.get(comando);
		if(a != null) {
			if(a.executar()) {
				System.out.print("comando executado\n");
				historyD.push(a);
				historyR.clear();
			}
		}
		
	}
	
	public  void desfazer() {
		if(!historyD.empty()) {
			Comando a = historyD.pop();
			if(a != null ) {
				if(a.desfazer()) {
					System.out.print("comando desfeito\n");
					historyR.push(a);
				}
			}
			
		}
	}
	public  void refazer() {
		if(!historyR.empty()) {
			Comando a = historyR.pop();
			if(a != null ) {
				if(a.refazer()) {
					System.out.print("comando refeito\n");
					historyD.push(a);
				}
			}
			
		}
	}

		
 }
