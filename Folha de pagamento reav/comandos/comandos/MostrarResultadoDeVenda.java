package comandos;

public class MostrarResultadoDeVenda implements Comando{
	
	DepartamentoDeVendas departamentoDeVendas;
	
	public MostrarResultadoDeVenda(DepartamentoDeVendas departamentoDeVendas) {
		this.departamentoDeVendas = departamentoDeVendas;
	}
	
	@Override
	public boolean executar() {
		return departamentoDeVendas.mostraResultadoDeVenda();
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
