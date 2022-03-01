package comandos;

public class LancarTaxaDeServico implements Comando{
	
	DepartamentoSindical departamentoSindical;
	
	public LancarTaxaDeServico(DepartamentoSindical departamentoSindical) {
		this.departamentoSindical = departamentoSindical;
	}
	
	@Override
	public boolean executar() {
		return departamentoSindical.lancaServicoAdicional();
	}
	@Override
	public boolean desfazer() {
		return departamentoSindical.desfazLancaSericoAdicional();
	}
	@Override
	public boolean refazer() {
		return departamentoSindical.refazLancaSericoAdicional();
	}


}
