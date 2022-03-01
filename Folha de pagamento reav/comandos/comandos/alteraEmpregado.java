package comandos;

public class alteraEmpregado implements Comando{
	
	
	DepartamentoDeFuncionarios departamentoDeFuncionarios;
	
	public alteraEmpregado(DepartamentoDeFuncionarios departamentoDeFuncionarios) {
		this.departamentoDeFuncionarios = departamentoDeFuncionarios;
	}
	
	@Override
	public boolean executar() {
		return departamentoDeFuncionarios.alteraEmpregado();
	}
	@Override
	public boolean desfazer() {
		return departamentoDeFuncionarios.desfazAlteraEmpregado();
	}
	@Override
	public boolean refazer() {
		return departamentoDeFuncionarios.refazAlteraEmpregado();
	}


}
