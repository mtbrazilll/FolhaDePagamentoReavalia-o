package sindicato;

import java.util.UUID;

import java.util.ArrayList;

public class Sindicato implements Cloneable{
    private UUID identificacao;
    private double taxaSindical;
    private ArrayList<Double> servicosAdicionais = null;
    private String nome;
    
    
    public Sindicato(double taxaSindical, String nome){
        this.identificacao = UUID.randomUUID();
        this.taxaSindical = taxaSindical;
        this.servicosAdicionais = new ArrayList();
        this.nome = nome;
    }
    
    public Sindicato(){
        this.identificacao = UUID.randomUUID();
        this.servicosAdicionais = new ArrayList();
    }
    
    public void setAdditionalTaxes(double servicoAdicional) {
        this.servicosAdicionais.add(servicoAdicional);
    }
    public double getAdditionalTaxes(int posicao) {
    	if(posicao >= 0 && posicao <servicosAdicionais.size()) {
    		 return this.servicosAdicionais.get(posicao);
    	}
       return -1;
    }
    public void remove_ultimo() {
    	int i = servicosAdicionais.size()-1;
    	if(i>=0) {
    		System.out.println("taxa: "+servicosAdicionais.get(i)+" removido");
    		this.servicosAdicionais.remove(i);
    	}
        
    }
    public int quantidadeDeServicosAdicionais() {
    	return servicosAdicionais.size();
    }
	public double getTaxaSindical() {
		return taxaSindical;
	}
	public void setTaxaSindical(double taxaSindical) {
		this.taxaSindical = taxaSindical;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public UUID getIdentificacao() {
		return identificacao;
	}
	public void setIdentificacao(UUID identificacao) {
		this.identificacao = identificacao;
	}
	public ArrayList<Double> getServicosAdicionais() {
		return servicosAdicionais;
	}
	public void setServicosAdicionais(ArrayList<Double> servicosAdicionais) {
		this.servicosAdicionais = servicosAdicionais;
	}
	@Override
	public String toString()
	{
		return String.format("identificação: %s taxa: %.2f", getIdentificacao(), getTaxaSindical());
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
    
    
}
