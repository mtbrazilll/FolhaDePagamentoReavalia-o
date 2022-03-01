package comandos;

public class RemoveEmpregado implements Comando{
	
	DepartamentoDeFuncionarios departamentoDeFuncionarios;
	
	public RemoveEmpregado(DepartamentoDeFuncionarios departamentoDeFuncionarios) {
		this.departamentoDeFuncionarios = departamentoDeFuncionarios;
	}
	
	@Override
	public boolean executar() {
		return departamentoDeFuncionarios.excluiEmpregado();
	}
	@Override
	public boolean desfazer() {
		return departamentoDeFuncionarios.desfazExcluiEmpregado();
	}
	@Override
	public boolean refazer() {
		return departamentoDeFuncionarios.refazExcluiEmpregado();
	}


}
