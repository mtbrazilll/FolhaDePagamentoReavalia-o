package comandos;

public class CriaAgendaDePagamento implements Comando{
	
	DepartamentoDeFinancas departamentoDeFinancas;
	
	public CriaAgendaDePagamento(DepartamentoDeFinancas departamentoDeFinancas) {
		this.departamentoDeFinancas = departamentoDeFinancas;
	}
	
	@Override
	public boolean executar() {
		return departamentoDeFinancas.CriaAgendaDePagamento() && false;
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
