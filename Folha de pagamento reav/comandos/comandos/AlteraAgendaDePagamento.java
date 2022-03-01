package comandos;

public class AlteraAgendaDePagamento implements Comando{
	
	
	DepartamentoDeFinancas departamentoDeFinancas;
	
	public AlteraAgendaDePagamento(DepartamentoDeFinancas departamentoDeFinancas) {
		this.departamentoDeFinancas = departamentoDeFinancas;
	}
	
	@Override
	public boolean executar() {
		return departamentoDeFinancas.alterarAgendaDePagamento() && false;
	}
	@Override
	public boolean desfazer() {
		return false;
	}
	@Override
	public boolean refazer() {
		return false;
	}


}
