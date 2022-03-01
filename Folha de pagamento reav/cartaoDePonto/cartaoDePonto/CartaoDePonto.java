package cartaoDePonto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class CartaoDePonto {
	
	private double horasExtras = 0; 
	private double horasTrabalhadas = 0; 
	private LocalTime horaInicio = null;
	private LocalTime horaSaida = null;
	private LocalDate data = null;
	
	public CartaoDePonto() {}
	
	public double getHorasExtras() {
		return horasExtras;
	}
	public void setHorasExtras(double horasExtras) {
		this.horasExtras = horasExtras;
	}
	public double getHorasTrabalhadas() {
		return horasTrabalhadas;
	}
	public void setHorasTrabalhadas(double horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	public LocalTime getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(LocalTime horaSaida) {
		this.horaSaida = horaSaida;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public static int horaTotal(int horaI, int minutosI, int horaF, int minutosF) {
		minutosI = minutosI + (60*horaI);
		minutosF = minutosF + (60*horaF);
		if(minutosI < minutosF) {
			minutosF = minutosF - minutosI;
		}
		else{
			minutosF = (minutosF - minutosI)+1440;
		}
		return minutosF;
	}
	
	public void cria() {
		Scanner teclado = new Scanner(System.in);
		int horaI,minutosI;
		int horaF,minutosF;
		System.out.print("\nDigite a hora de entrada no formato hh mm: ");
		horaI = teclado.nextInt();
		minutosI = teclado.nextInt();
		this.horaInicio = LocalTime.of(horaI, minutosI);
		System.out.print("\nDigite a hora de entrada no formato hh mm:");
		horaF = teclado.nextInt();
		minutosF = teclado.nextInt();
		this.horaSaida = LocalTime.of(horaF, minutosF);;
		this.data = LocalDate.now();
		int minutosTotal = horaTotal(horaI,minutosI,horaF,minutosF);
		if(minutosTotal>480) {
			horasTrabalhadas = 480;
			horasExtras = minutosTotal - horasTrabalhadas;
		}
		else{
			horasTrabalhadas = minutosTotal;
			horasExtras = 0;
		}
		System.out.printf("Cartão de ponto adicionado\nhora trabalhada: %.2f hora extra: %.2f\n",
				horasTrabalhadas/60, horasExtras/60);
		
	}
	
	@Override
	public String toString()
	{
		return String.format("Data: %s horas trabalhadas: %.2f horas extras: %.2f\n",
				getData(),horasTrabalhadas/60,horasExtras/60);
		
	}

	

	
    

}
