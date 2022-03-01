package comandos;

public interface Comando {
	
	//public void executar();
	public boolean  executar();
	public boolean  desfazer();
	public boolean  refazer();
}
