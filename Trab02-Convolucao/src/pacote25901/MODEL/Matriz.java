package pacote25901.MODEL;

import java.awt.Color;
import java.awt.Point;

public class Matriz {
	private Point pontoInicial, pontoFinal;
	private Color color;
	private int linha, coluna;
	
	// CONSTRUTOR
	public Matriz() {}
	
	public Matriz(Point pontoInicial, Point pontoFinal, Color color, int linha, int coluna) {
		this.pontoInicial = pontoInicial;
		this.pontoFinal = pontoFinal;
		this.color = color;
		this.linha = linha;
		this.coluna = coluna;
	}
	
	// GETTERS and SETTERS
	public Point getPontoInicial() {
		return pontoInicial;
	}

	public void setPontoInicial(Point pontoInicial) {
		this.pontoInicial = pontoInicial;
	}

	public Point getPontoFinal() {
		return pontoFinal;
	}

	public void setPontoFinal(Point pontoFinal) {
		this.pontoFinal = pontoFinal;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	
}
