package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import pacote25901.MODEL.Matriz;

public class MatrizController {
	private Graphics desenho;
	
	public ArrayList<Point> listaTodosPontos = new ArrayList<Point> ();
	public ArrayList<Point> listaPontos = new ArrayList<Point> ();

	//CONSTRUTOR
	public MatrizController(Graphics desenho) {
		this.desenho = desenho;
	}

	/**
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	private double encontraAltura(Point p1, Point p2)
	{
		double y1, y2, largura;
		
		y1 = p1.y;
		y2 = p2.y;
		
		if(y1 <= y2) {
			largura = y2 - y1;
		} else {
			largura = y1 - y2;
		}
		
		return largura;
	}
	
	/**
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	private double encontraLargura(Point p1, Point p2)
	{
		double x1, x2, altura;
		
		x1 = p1.x;
		x2 = p2.x;
		
		if(x1 <= x2) {
			altura = x2 - x1;
		} else {
			altura = x1 - x2;
		}
		
		return altura;
	}
	
	public Matriz verificaContido(ArrayList<Matriz> listaMatrizes, Point ponto)
	{
		Matriz matrizEncontrada = new Matriz();
		
		for(Matriz matriz : listaMatrizes) {
			
			if( (ponto.x > matriz.getPontoInicial().x && ponto.y > matriz.getPontoInicial().y) && (ponto.x < matriz.getPontoFinal().x && ponto.y < matriz.getPontoFinal().y) ) {
				matrizEncontrada = matriz;
			}
		}
		
		return matrizEncontrada;
	}
	
	public ArrayList<Matriz> desenhaMatriz(Point pontoInicial, Point pontoFinal)
	{
		final int largura, altura, tamanho;
		int larguraQuadrado, alturaQuadrado, xAux, yAux;
		ArrayList<Matriz> listaMatrizes = new ArrayList<Matriz>();
		Matriz novaMatriz;
		
		// DESENHA RETÂNGULO PRINCIPAL
		largura = (int) encontraLargura(pontoInicial, pontoFinal);
		altura = (int) encontraAltura(pontoInicial, pontoFinal);
		
		// CALCULA A ESCALA DA IMAGEM
		tamanho = 5;
		larguraQuadrado = largura/tamanho;
		alturaQuadrado = altura/tamanho;
		
		// SWAP DOS PONTOS
		if(pontoInicial.x < pontoFinal.x && pontoInicial.y > pontoFinal.y) {
			xAux = pontoInicial.x;
			yAux = pontoFinal.y;
		}
		else if(pontoInicial.x > pontoFinal.x && pontoInicial.y < pontoFinal.y) {
			xAux = pontoFinal.x;
			yAux = pontoInicial.y;
		}
		else if(pontoInicial.x > pontoFinal.x && pontoInicial.y > pontoFinal.y) {
			xAux = pontoFinal.x;
			yAux = pontoFinal.y;
		}
		else {
			xAux = pontoInicial.x;
			yAux = pontoInicial.y;
		}
		
		
		Point novoInicial = new Point(xAux, yAux);
		
		// DESENHA OS RETÂNGULOS QUE IRÃO COMPOR A MATRIZ
		for(int i = 1; i <= tamanho; i++)
		{
			for(int j = 1; j <= tamanho; j++)
			{
				desenho.drawRect(xAux, yAux, larguraQuadrado, alturaQuadrado);
				
				// Adiciona o retângulo na lista de matrizes
				novaMatriz = new Matriz(new Point(xAux, yAux), new Point(xAux+larguraQuadrado, yAux+alturaQuadrado), Color.BLACK);
				listaMatrizes.add(novaMatriz);
				
				xAux += larguraQuadrado; // Desloca o x para o x do ponto inicial do próximo retângulo
				
			}
			yAux += alturaQuadrado; // Desloca o y para o y do ponto inicial do próximo retângulo
			xAux = novoInicial.x; // Desloca o x para o Ponto Inicial
		}
		
		return listaMatrizes;
	}
	
	public void colorirMatriz(Matriz matrizColorir, Color cor)
	{
		Point pontoInicial = matrizColorir.getPontoInicial();
		Point pontoFinal = matrizColorir.getPontoFinal();
		
		int largura = (int) encontraLargura(pontoInicial, pontoFinal);
		int altura = (int) encontraAltura(pontoInicial, pontoFinal);
		
		desenho.setColor(cor);
		desenho.fillRect(pontoInicial.x, pontoInicial.y, largura, altura);
	}
}
