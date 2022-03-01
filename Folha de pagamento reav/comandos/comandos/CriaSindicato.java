package comandos;

public class CriaSindicato implements Comando{
	
	
	
	DepartamentoSindical departamentoSindical;
	
	public CriaSindicato(DepartamentoSindical departamentoSindical) {
		this.departamentoSindical = departamentoSindical;
	}
	
	@Override
	public boolean executar() {
		return departamentoSindical.criaSindicato() && false;
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
