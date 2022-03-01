package comandos;

public class LancarCartaoDePonto implements Comando{
	
	DepartamentoDeControleDeTempo departamentoDeControleDeTempo;
	
	public LancarCartaoDePonto(DepartamentoDeControleDeTempo departamentoDeControleDeTempo) {
		this.departamentoDeControleDeTempo = departamentoDeControleDeTempo;
	}
	
	@Override
	public boolean executar() {
		return departamentoDeControleDeTempo.lancaCartaoDePonto();
	}
	@Override
	public boolean desfazer() {
		return departamentoDeControleDeTempo.desfazLancarCartaoDePonto();
	}
	@Override
	public boolean refazer() {
		return departamentoDeControleDeTempo.refazLancarCartaoDePonto();
	}
	
}
