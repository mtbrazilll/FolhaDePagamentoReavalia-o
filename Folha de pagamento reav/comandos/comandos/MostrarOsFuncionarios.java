package comandos;

public class MostrarOsFuncionarios implements Comando{
	
		
		DepartamentoDeFuncionarios departamentoDeFuncionarios;
		
		public MostrarOsFuncionarios(DepartamentoDeFuncionarios departamentoDeFuncionarios) {
			this.departamentoDeFuncionarios = departamentoDeFuncionarios;
		}
		
		@Override
		public boolean executar() {
			return departamentoDeFuncionarios.mostraOsFuncionarios() && false;
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
