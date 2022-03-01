package ResultadoDeVenda;


import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ResultadoDeVenda {
	
	private String descricao = "Não informado";
	private double valor;
	private LocalDate data;
	private LocalTime hora;
	
	public ResultadoDeVenda(double valor, LocalDate data,String descricao){
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
    }
	public ResultadoDeVenda(){
    }
	public ResultadoDeVenda(double valor,String descricao){
        this.valor = valor;
        this.data = LocalDate.now();
        this.hora = LocalTime.now();
        this.descricao = descricao;
    }

    public ResultadoDeVenda(double valor){
        this.valor = valor;
        this.data = LocalDate.now();
    }
    
    public double getValor() {
    	return valor;
    }
    
    public String getDescricao() {
    	return descricao;
    }
    
    public void setValor(double valor) {
    	this.valor = valor;
    }
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void cria() {
		Scanner teclado = new Scanner(System.in);
		this.data = LocalDate.now();
        this.hora = LocalTime.now();
        System.out.print("\n\nDigite uma discrição da venda:");
		String descricao = teclado.nextLine();
		System.out.print("Digite o valor da venda:");
		double valor = teclado.nextDouble();
		setDescricao(descricao);
		setValor(valor);
		
	}
	@Override
	public String toString()
	{
		return String.format("Descrição: %s%nvalor: $%,.2f%ndata:%s hora:%s",
				getDescricao(),getValor(),getData(),getHora().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		
	}
    
    
}