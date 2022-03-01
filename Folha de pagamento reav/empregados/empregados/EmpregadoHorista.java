package empregados;

import sindicato.Sindicato;


import java.util.*;
import cartaoDePonto.CartaoDePonto;

public class EmpregadoHorista extends Empregado{
	
	private double salarioPorHora = 0;
	private double horasTrabalhadas = 0;
	private double horasExtas = 0;
	private ArrayList<CartaoDePonto> cartaoDePonto;
	
	public EmpregadoHorista(String nome,String cpf, String endereco,String metodoPagamento, double salarioPorHora, Sindicato sindicato)
	{
		super(nome, cpf, endereco, metodoPagamento, sindicato);
		this.salarioPorHora = salarioPorHora;
		this.horasTrabalhadas = 0;
		this.horasExtas = 0;
		cartaoDePonto = new ArrayList();
	}
	
	public EmpregadoHorista() {
		super();
		cartaoDePonto = new ArrayList();
	}
	
	public double getSalarioPorHora() {
		return this.salarioPorHora;
	}
	public double getHorasTrabalhadas() {
		return this.horasTrabalhadas;
	}
	public double getHorasExtras() {
		return this.horasExtas;
	}
	public void setSalarioPorHora(double salarioPorHora) {
		this.salarioPorHora = salarioPorHora;
	}
	public void setHorasTrabalhadas(double horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}
	public void setHorasExtras(double horasExtas) {
		this.horasExtas = horasExtas;
	}
	
	public void atualiza(double horasTrabalhadas, double horasExtras) {
		this.horasExtas = this.horasExtas + horasExtras;
		this.horasTrabalhadas = this.horasTrabalhadas + horasTrabalhadas;
	}
	public void desatualiza(double horasTrabalhadas, double horasExtras) {
		this.horasExtas = this.horasExtas - horasExtras;
		this.horasTrabalhadas = this.horasTrabalhadas - horasTrabalhadas;
	}
	
	public ArrayList<CartaoDePonto> getCartaoDePonto() {
		return cartaoDePonto;
	}

	public void setCartaoDePonto(ArrayList<CartaoDePonto> cartaoDePonto) {
		this.cartaoDePonto = cartaoDePonto;
	}
	
	public void remove_ultimo() {
    	int i = cartaoDePonto.size()-1;
    	if(i>=0) {
    		System.out.println("cartão de ponto removido");
    		this.cartaoDePonto.remove(i);
    	}
        
    }
	public CartaoDePonto pegaUltimo() {
    	int i = cartaoDePonto.size()-1;
    	return this.cartaoDePonto.get(i);  
    }

	@Override
	public double rendimento()
	
	{
		double aux = 0;
		if(getSindicato() != null ) {
			aux = getSindicato().getTaxaSindical();
			if(!getSindicato().getServicosAdicionais().isEmpty()) {
				for(double var: getSindicato().getServicosAdicionais()) {
					aux = var + aux;
				}
			}
		}
		return (getSalarioPorHora()*(getHorasTrabalhadas()/60)+
				((getHorasExtras()*getSalarioPorHora()/60)*1.5))-aux;
	}
	
	public void limpaPagamento() {
		if(getSindicato() != null && !getSindicato().getServicosAdicionais().isEmpty()) {
			getSindicato().getServicosAdicionais().clear();
		}
		setHorasExtras(0);
		setHorasTrabalhadas(0);
		
	}
	
	@Override
	public String toString()
	{
		return String.format("empregado horista: %s%n%s: $%,.2f; %s: %,.2f%nhoras extras: %,.2f",
				super.toString(),"salario Por Hora",getSalarioPorHora(),
				"horas trabalhadas", getHorasTrabalhadas()/60,getHorasExtras()/60);
	}
	
	@Override
	public void cria() {

		Scanner teclado = new Scanner(System.in);
		System.out.print("\nDigite o nome do funcionário:");
		String nome = teclado.nextLine();
		setNome(nome);
		System.out.print("\nDigite o cpf:");
		String cpf = teclado.nextLine();
		setCpf(cpf);
		System.out.print("\nDigite o endereço:");
		String endereco = teclado.nextLine();
		setEndereco(endereco);
		System.out.print("\nDigite o metodo de pagamento:");
		String metodoPagamento = teclado.nextLine();
		setMetodoPagamento(metodoPagamento);
		System.out.print("\nSalario por hora:");
		double valor = teclado.nextDouble();
		setSalarioPorHora(valor);
		setTipo(3);
		
		
	}
	public void mostraCartaoDePonto() {
		if(cartaoDePonto.isEmpty()) {
			System.out.println("Não tem nenhum ponto.");
			return;
		}
		for(CartaoDePonto aux: cartaoDePonto) {
			System.out.println(aux);
		}
	}
}
