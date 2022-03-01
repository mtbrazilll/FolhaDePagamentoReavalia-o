package empregados;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.LocalDate;
import sindicato.Sindicato;
import ResultadoDeVenda.ResultadoDeVenda;

public class EmpregadoAssalariadoComissionado extends EmpregadoAssalariado{
	
	
	private double taxaComissao = 0; // porcentagem da comiss�o
	private double vendasBrutas = 0; // vendas brutas mensais 
	private ArrayList<ResultadoDeVenda> produtosVendidos =  new ArrayList();

	
	public EmpregadoAssalariadoComissionado(String nome,String cpf, String endereco,String metodoPagamento, 
			double salarioFixo,Sindicato sindicato,double taxaComissao)
	{
		super(nome,cpf,endereco,metodoPagamento,salarioFixo,sindicato);
		if (taxaComissao <= 0.0 || taxaComissao >= 1.0) // valida 
			throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");
		this.vendasBrutas = 0;
		this.taxaComissao = taxaComissao;
		
		
	}
	
	public EmpregadoAssalariadoComissionado() {
		super();
		
	}
	
	public double getTaxaComissao() {
		return taxaComissao;
	}
	
	public double getVendasBrutas() {
		return vendasBrutas;
	}
	public void setTaxaComissao(double taxaComissao) {
		this.taxaComissao = taxaComissao;
	}
	
	public void setVendasBrutas(double vendasBrutas) {
		this.vendasBrutas = vendasBrutas;
	}
	
	public void adicionarVenda(double valor, String descricao){
		produtosVendidos.add(new ResultadoDeVenda(valor,descricao));
    }
	public void adicionarVenda(double valor){
		produtosVendidos.add(new ResultadoDeVenda(valor));
    }
	
	
	public void adicionarVenda(ResultadoDeVenda venda){
		produtosVendidos.add(venda);
    }
	
	public void removerVenda(ResultadoDeVenda venda){
		produtosVendidos.remove(venda);
    }
	
	public void removerVenda(int posicao) {
		if(posicao>=0 && posicao<produtosVendidos.size() ) {
			produtosVendidos.remove(posicao);
		}
	}
	
	public void atulizaVenda(double valor) {
		
		setVendasBrutas(getVendasBrutas()+valor);
	}
	
	@Override
	public double rendimento()
	{	
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
		return (getSalarioFixo()*diasTrabalhados/ ((double)getAgenda().getPeriodo())  )+(getVendasBrutas()*((getTaxaComissao()/100)))-aux;
	}
	
	public void limpaPagamento() {
		if(getSindicato() != null && !getSindicato().getServicosAdicionais().isEmpty()) {
			getSindicato().getServicosAdicionais().clear();
		}
		setVendasBrutas(0);
	}
	
	@Override
	public String toString()
	{
		return String.format("%s %s%n%s: $%,.2f; %s: %.2f",
				"comissionado", super.toString(),
				"vendas bruta", getVendasBrutas(),
				"comis�o", getTaxaComissao());
	}
	

	
	@Override
	public void cria() {
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("\nDigite o nome do funcion�rio:");
		String nome = teclado.nextLine();
		setNome(nome);
		System.out.print("\nDigite o cpf:");
		String cpf = teclado.nextLine();
		setCpf(cpf);
		System.out.print("\nDigite o endere�o:");
		String endereco = teclado.nextLine();
		setEndereco(endereco);
		System.out.print("\nDigite o m�todo de pagamento:");
		String metodoPagamento = teclado.nextLine();
		setMetodoPagamento(metodoPagamento);
		System.out.print("\nSal�rio fixo:");
		double salario = teclado.nextDouble();
		setSalarioFixo(salario);
		System.out.print("\nTaxa de Comiss�o em porcentagem:");
		double valor = teclado.nextDouble();
		setTaxaComissao(valor);
		setTipo(2);
		setPrimeiroDia(LocalDate.now());
	
		
	}
	
	public void remove(ResultadoDeVenda venda) {
		produtosVendidos.remove(venda);
	}
	public void mostraResultadoDeVenda() {
		if(produtosVendidos.isEmpty()) {
			System.out.print("Nenhuma venda realizada\n\n");
		}
		else {
			for(ResultadoDeVenda aux: produtosVendidos) {
				System.out.print(aux);
				System.out.print("\n\n");
			}
		}
	}

}
