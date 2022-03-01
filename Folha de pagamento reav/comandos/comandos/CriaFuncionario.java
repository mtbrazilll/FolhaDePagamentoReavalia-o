package comandos;

public class CriaFuncionario implements Comando{
	
	DepartamentoDeFuncionarios departamentoDeFuncionarios;
	
	public CriaFuncionario(DepartamentoDeFuncionarios departamentoDeFuncionarios) {
		this.departamentoDeFuncionarios = departamentoDeFuncionarios;
	}
	
	@Override
	public boolean executar() {
		return departamentoDeFuncionarios.criaEmpregado();
	}
	@Override
	public boolean desfazer() {
		return departamentoDeFuncionarios.desfazCriaEmpregado();
	}
	@Override
	public boolean refazer() {
		return departamentoDeFuncionarios.refazCriaEmpregado();
	}

	
}
