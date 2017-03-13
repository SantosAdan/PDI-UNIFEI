package pacote25901.MODEL;

import java.awt.Point;

public class Circulo {
	
	private Point centro;
	private int raio;
	
	public Circulo(Point centro, int raio){
		this.centro = centro;
		this.raio = raio;
	}
	
	public Point getCentro() {
		return centro;
	}
	public void setCentro(Point centro) {
		this.centro = centro;
	}
	public int getRaio() {
		return raio;
	}
	public void setRaio(int raio) {
		this.raio = raio;
	}
	
	

}
