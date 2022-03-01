package comandos;

import empregados.Empregado;
import empregados.EmpregadoAssalariado;

public class CriaEmpregadoAssalariado implements CriaEmpregado{
	
	public Empregado criaEmpregado() {
		EmpregadoAssalariado novo = new EmpregadoAssalariado();
		novo.cria();
		return novo;
		
	}
	public void alteraEmpregado(Empregado empregado) {
		EmpregadoAssalariado aux = (EmpregadoAssalariado) empregado;
		
	}
}
