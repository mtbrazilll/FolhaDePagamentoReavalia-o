package comandos;

import empregados.*;

public class CriaEmpregadoHorista implements CriaEmpregado{
	
	public Empregado criaEmpregado() {
		EmpregadoHorista novo = new EmpregadoHorista();
		novo.cria();
		return novo;
	}
	
	public void alteraEmpregado(Empregado empregado) {
		EmpregadoHorista aux = (EmpregadoHorista) empregado;
		
	}

}
