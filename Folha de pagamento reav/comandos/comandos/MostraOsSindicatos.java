package comandos;

public class MostraOsSindicatos implements Comando{
	
	
	
	DepartamentoSindical departamentoSindical;
	
	public MostraOsSindicatos(DepartamentoSindical departamentoSindical) {
		this.departamentoSindical = departamentoSindical;
	}
	
	@Override
	public boolean executar() {
		return departamentoSindical.mostraOsSindicatos() && false;
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
