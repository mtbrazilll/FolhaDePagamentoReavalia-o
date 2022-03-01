package comandos;

import empregados.Empregado;
import empregados.EmpregadoAssalariadoComissionado;

public class CriaEmpregadoAssalariadoComissionado implements CriaEmpregado{
	
	public Empregado criaEmpregado() {
		EmpregadoAssalariadoComissionado novo = new EmpregadoAssalariadoComissionado();
		novo.cria();
		return novo;
		
	}
	public void alteraEmpregado(Empregado empregado) {
		EmpregadoAssalariadoComissionado aux = (EmpregadoAssalariadoComissionado) empregado;
		
	}

}
