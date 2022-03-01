package empregados;
import java.util.*;

import ResultadoDeVenda.ResultadoDeVenda;
import java.time.LocalDate;
import sindicato.Sindicato;
import vencimento.Vencimento;
import Agenda.AgendaDePagamento;

public abstract class Empregado implements Cloneable  {
	
	

	private  String nome = "sem nome";
	private  String endereco = "n/a";
	private  String cpf = "n/a";
	private  String metodoPagamento = "n/a";
	private final UUID id;
	private Sindicato sindicato = null;
	private LocalDate primeiroDia;
	
	AgendaDePagamento agenda;
	ArrayList<Vencimento> vencimento = null;
	private int tipo;
	
	
	
	public abstract void limpaPagamento();
	public abstract double rendimento();
	public abstract void cria();
	
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	// construtor
	public Empregado(String nome,String cpf, String endereco, String metodoPagamento)
	{
		this.nome = nome;
		this.endereco = endereco;
		this.metodoPagamento = metodoPagamento;
		this.cpf = cpf;
		this.id = UUID.randomUUID();
	}
	
	public Empregado(String nome,String cpf, String endereco, String metodoPagamento
			,Sindicato sindicato)
	{
		this.nome = nome;
		this.endereco = endereco;
		this.metodoPagamento = metodoPagamento;
		this.cpf = cpf;
		this.id = UUID.randomUUID();
		this.sindicato = sindicato;
		this.vencimento = new ArrayList();
	
	}
	
	public Empregado() {
		this.id = UUID.randomUUID();
		this.vencimento = new ArrayList();
		
	}

	// retorna o nome
	public String getNome()
	{
		return nome;
	} 
	// retorna o número do cpf
	public String getCpf()
	{
		return cpf;
	}
	// retorna o método de pagamento
	public String getMetodoPagamento()
	{
		return metodoPagamento;
	}
	// retorna o endereco
	public String getEnderecof()
	{
		return endereco;
	}
	
	// configura metodo de pagamento
	public void setMetodoPagamento(String metodoPagamento)
	{
		this.metodoPagamento = metodoPagamento;
	}
	// retorna o endereço
	public String getEndereco() {
		return endereco;
	}
	// configura o endereço
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	// retorna o sindicato
	public Sindicato getSindicato() {
		return sindicato;
	}

	public void setSindicato(Sindicato sindicato) {
		 if(sindicato != null ) this.sindicato = sindicato;
	}
	
	public void saiDoSindicato() {
		 this.sindicato = null;
	}

	// retorna o id
	public UUID getId() {
		return id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	//adiciona taxa de serviços
	public void adicionaTaxaDeSercico(double valor) {
        if (valor >= 0){
        	sindicato.setAdditionalTaxes(valor);
        }
        else{
            System.out.println("valor inválido");
        }
    }
	// retorna a representação de String do objeto Employee
	@Override
	public String toString()
	{
		if(getSindicato()!= null) {
			return String.format("%s %ncpf: %s %nEndereço: %s %nSindicato: %s %nMétodo de Pagamento: %s%n%s", 
					getNome(), getCpf(), getEndereco(), getSindicato(), getMetodoPagamento(),getAgenda());
		}
		return String.format("%s %ncpf: %s %nEndereço: %s %nMétodo de Pagamento: %s%n%s", 
		getNome(), getCpf(), getEndereco(), getMetodoPagamento(),getAgenda());
	}

	public LocalDate getPrimeiroDia() {
		return primeiroDia;
	}

	public void setPrimeiroDia(LocalDate primeiroDia) {
		this.primeiroDia = primeiroDia;
	}

	public ArrayList<Vencimento> getVencimento() {
		return vencimento;
	}

	public void setVencimento(ArrayList<Vencimento> vencimento) {
		this.vencimento = vencimento;
	}

	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public AgendaDePagamento getAgenda() {
		return agenda;
	}

	public void setAgenda(AgendaDePagamento agenda) {
		if(agenda != null) this.agenda = agenda;
	}
	
	public double ultimoPagamento() {
		if(vencimento.size()>0) {
			return vencimento.get(vencimento.size()-1).getValorTotal();
		}
		else {
			return 0;
		}
	}
	
	
	
	

	
	
	
	
	
	
	

	
	
	

}
