package comandos;

public class LancarResultadoDeVenda implements Comando{
	
	DepartamentoDeVendas departamentoDeVendas;
	
	public LancarResultadoDeVenda(DepartamentoDeVendas departamentoDeVendas) {
		this.departamentoDeVendas = departamentoDeVendas;
	}
	
	@Override
	public boolean executar() {
		return departamentoDeVendas.lancarResultadoDeVenda();
	}
	@Override
	public boolean desfazer() {
		return departamentoDeVendas.desfazLancarResultadoDeVenda();
	}
	@Override
	public boolean refazer() {
		return departamentoDeVendas.refazLancarResultadoDeVenda();
	}

}
