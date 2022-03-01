package empregados;
import java.util.Scanner;

import ResultadoDeVenda.ResultadoDeVenda;
import sindicato.Sindicato;

public class EmpregadoAssalariado extends Empregado {
	
	private double salarioFixo = 0;
	
	
	public EmpregadoAssalariado(String nome,String cpf,String endereco, String metodoPagamento, 
								double salario,Sindicato sindicato)
	{
		super(nome, cpf, endereco, metodoPagamento,sindicato);
		if(salario < 0.0) {
			throw new IllegalArgumentException("O salário deve ser maior que 0.0");
		}
		this.salarioFixo = salario;
	}
	public EmpregadoAssalariado(String nome,String cpf,String endereco, 
								String metodoPagamento,double salario)
	{	
		super(nome, cpf, endereco, metodoPagamento);
		if(salario < 0.0) {
			throw new IllegalArgumentException("O salário deve ser maior que 0.0");
		}
		this.salarioFixo = salario;

	}
	
	public EmpregadoAssalariado() {
		super();
	}
	
	// ajeita o salário
	public void setSalarioFixo(double salario) {
		if(salario < 0.0) {
			throw new IllegalArgumentException("O salário deve ser maior que 0.0");
		}
		this.salarioFixo = salario;
	}
	public double getSalarioFixo() {
		return salarioFixo;
	}
	public void limpaPagamento() {
		if(getSindicato() != null && !getSindicato().getServicosAdicionais().isEmpty()) {
			getSindicato().getServicosAdicionais().clear();
		}
	}
	
	@Override
	public double rendimento() {
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("Digite a quantidade de dias trabalhados: ");
		double diasTrabalhados = teclado.nextDouble();
		System.out.print("\n");
		double aux = 0;
		if(getSindicato() != null ) {
			aux = getSindicato().getTaxaSindical();
			if(!getSindicato().getServicosAdicionais().isEmpty()) {
				for(double var: getSindicato().getServicosAdicionais()) {
					aux = var + aux;
				}
			}
		}
		return (getSalarioFixo()*diasTrabalhados/ ((double)getAgenda().getPeriodo()) )-aux;
	}
	
	@Override
	public String toString()
	{
		return String.format("Empregado Assalariado: %s%n%s: $%,.2f", 
		super.toString(), "Salário mensal", getSalarioFixo());
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
		System.out.print("\nSalario o Fixo em reais:  ");
		double valor = teclado.nextDouble();
		setSalarioFixo(valor);
		setTipo(1);
		
	}
	
}
