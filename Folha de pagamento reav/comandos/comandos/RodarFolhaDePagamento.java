package comandos;

public class RodarFolhaDePagamento implements Comando{

	DepartamentoDeFinancas departamentoDeFinancas;
	
	public RodarFolhaDePagamento(DepartamentoDeFinancas departamentoDeFinancas) {
		this.departamentoDeFinancas = departamentoDeFinancas;
	}
	
	@Override
	public boolean executar() {
		return departamentoDeFinancas.rodarFolhaDePagamento();
	}
	@Override
	public boolean desfazer() {
		return departamentoDeFinancas.desfazRodarFolhaDePagamento();
	}
	@Override
	public boolean refazer() {
		return departamentoDeFinancas.refazRodarFolhaDePagamento();
	}

}
