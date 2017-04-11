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

	public double encontraAltura(Point p1, Point p2)
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

	public double encontraLargura(Point p1, Point p2)
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
	
	public Point[][] paraMatriz(ArrayList<Matriz> listaMatrizes, int tamanho){
        Point[][] matrizRetorno = new Point[tamanho+1][tamanho+1];
       
        for(int u=0;u<tamanho*tamanho;u++){
        	Matriz matriz = listaMatrizes.get(u);
        	matrizRetorno[matriz.getLinha()][matriz.getColuna()] = matriz.getPontoInicial();
        }
       
        return matrizRetorno;
    }
   
    public Color[][] paraMatrizCores(ArrayList<Matriz> listaMatrizes, int tamanho){
        Color[][] matrizRetorno = new Color[tamanho+1][tamanho+1];
       
        for(int u=0;u<tamanho*tamanho;u++){
        	Matriz matriz = listaMatrizes.get(u);
        	matrizRetorno[matriz.getLinha()][matriz.getColuna()] = matriz.getColor();
        }
       
        return matrizRetorno;
    }
    
    private ArrayList<Matriz> atualizaPosicoes(ArrayList<Matriz> lista, int tamanho)
    {
    	ArrayList<Matriz> listaMatrizes = new ArrayList<Matriz>();
        int counter = 0;
        for(int i=1;i<=tamanho;i++){
            for(int u=1;u<=tamanho;u++){
            	Matriz matriz = lista.get(counter);
            	matriz.setLinha(i);
            	matriz.setColuna(u);
                listaMatrizes.add(matriz);
                counter++;
            }
        }
        return listaMatrizes;
    }
    public ArrayList<Matriz> desenhaMatrizLoad(ArrayList<Matriz> listaMatrizes, int tamanho){
        int altura = (int) encontraAltura(listaMatrizes.get(0).getPontoInicial(),listaMatrizes.get(listaMatrizes.size()-1).getPontoFinal());
        int largura = (int) encontraLargura(listaMatrizes.get(0).getPontoInicial(),listaMatrizes.get(listaMatrizes.size()-1).getPontoFinal());
        
        int divisaoAltura = altura/tamanho;
        int divisaoLargura = largura/tamanho;
        for(int x=0;x<tamanho*tamanho;x++){
            desenho.setColor(Color.BLACK);
            desenho.drawRect(listaMatrizes.get(x).getPontoInicial().x, listaMatrizes.get(x).getPontoInicial().y, divisaoLargura, divisaoAltura);
            desenho.setColor(listaMatrizes.get(x).getColor());
            desenho.fillRect(listaMatrizes.get(x).getPontoInicial().x, listaMatrizes.get(x).getPontoInicial().y, divisaoLargura, divisaoAltura);
        }
        listaMatrizes = atualizaPosicoes(listaMatrizes,tamanho);
        return listaMatrizes;
       
    }
    
    
	// ========================================================================================================
	public ArrayList<Matriz> desenhaMatriz(Point pontoInicial, Point pontoFinal, int tamanho)
	{
		final int largura, altura;
		int larguraQuadrado, alturaQuadrado, xAux, yAux;
		ArrayList<Matriz> listaMatrizes = new ArrayList<Matriz>();
		Matriz novaMatriz;
		
		// DESENHA RETÂNGULO PRINCIPAL
		largura = (int) encontraLargura(pontoInicial, pontoFinal);
		altura = (int) encontraAltura(pontoInicial, pontoFinal);
		
		// CALCULA A ESCALA DA IMAGEM
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
				novaMatriz = new Matriz(new Point(xAux, yAux), new Point(xAux+larguraQuadrado, yAux+alturaQuadrado), Color.LIGHT_GRAY, i, j);
				listaMatrizes.add(novaMatriz);
				
				xAux += larguraQuadrado; // Desloca o x para o x do ponto inicial do próximo retângulo
				
			}
			yAux += alturaQuadrado; // Desloca o y para o y do ponto inicial do próximo retângulo
			xAux = novoInicial.x; // Desloca o x para o Ponto Inicial
		}
		
		return listaMatrizes;
	}
	
	public void desenhaMatrizLista(ArrayList<Matriz> listaMatrizes, Point pontoInicial, Point pontoFinal, int tamanho)
	{
		int largura, altura, larguraQuadrado, alturaQuadrado;
		
		largura = (int) encontraLargura(pontoInicial, pontoFinal);
		altura = (int) encontraAltura(pontoInicial, pontoFinal);
		// CALCULA A ESCALA DA IMAGEM
		larguraQuadrado = largura/tamanho;
		alturaQuadrado = altura/tamanho;
				
		for(Matriz matrizAux : listaMatrizes) {
			desenho.setColor(matrizAux.getColor());
			desenho.drawRect(matrizAux.getPontoInicial().x, matrizAux.getPontoInicial().y, larguraQuadrado, alturaQuadrado);
			desenho.fillRect(matrizAux.getPontoInicial().x, matrizAux.getPontoInicial().y, larguraQuadrado, alturaQuadrado);
		}
	}
	
	/**
	 * 
	 * @param listaMatrizes
	 * @param ponto
	 * @return
	 */
	private Matriz verificaContido(ArrayList<Matriz> listaMatrizes, Point ponto)
	{
		Matriz matrizEncontrada = new Matriz();
		
		for(Matriz matriz : listaMatrizes) {
			
			if( (ponto.x > matriz.getPontoInicial().x && ponto.y > matriz.getPontoInicial().y) && (ponto.x < matriz.getPontoFinal().x && ponto.y < matriz.getPontoFinal().y) ) {
				matrizEncontrada = matriz;
			}
		}
		
		return matrizEncontrada;
	}
	
	/**
	 * 
	 * @param listaMatrizes
	 * @param ponto
	 * @return
	 */
	private ArrayList<Matriz> atualizaLista(ArrayList<Matriz> listaMatrizes, Point ponto)
	{	
		Matriz matrizEncontrada = new Matriz();
		
		for(Matriz matriz : listaMatrizes) {
			
			if( (ponto.x > matriz.getPontoInicial().x && ponto.y > matriz.getPontoInicial().y) && (ponto.x < matriz.getPontoFinal().x && ponto.y < matriz.getPontoFinal().y) ) {
				matrizEncontrada = matriz;
			}
		}
		
		listaMatrizes.remove(matrizEncontrada);
		return listaMatrizes;
	}
	
	/**
	 * 
	 * @param listaMatrizes
	 * @param pontoSelecionado
	 * @param cor
	 * @return
	 */
	public ArrayList<Matriz> colorirMatriz(ArrayList<Matriz> listaMatrizes, Point pontoSelecionado, Color cor)
	{
		ArrayList<Matriz> novaLista = new ArrayList<Matriz>();
		Matriz matrizColorir = verificaContido(listaMatrizes, pontoSelecionado); // Encontra região que deve ser colorida
		
		if(matrizColorir != null) {
			Point pontoInicial = matrizColorir.getPontoInicial();
			Point pontoFinal = matrizColorir.getPontoFinal();
			
			int largura = (int) encontraLargura(pontoInicial, pontoFinal);
			int altura = (int) encontraAltura(pontoInicial, pontoFinal);
			
			novaLista = atualizaLista(listaMatrizes, pontoSelecionado); // Remove região que deve ser colorida da lista
			matrizColorir.setColor(cor);
			novaLista.add(matrizColorir); // Adiciona na lista a nova região com a cor correta
			
			desenho.setColor(cor);
			desenho.fillRect(pontoInicial.x, pontoInicial.y, largura, altura); // Colore a região com a cor escolhida
		}
		
		return novaLista;
	}
	
	
	public ArrayList<Matriz> realizaConvolucao(ArrayList<Matriz> listaMatrizes, ArrayList<Matriz> listaTemplate, int tamImagem, int tamTemplate)
	{
		ArrayList<Matriz> novaLista = new ArrayList<Matriz>();
		int[][] imagem = new int[tamImagem+1][tamImagem+1];
		int[][] template = new int[tamTemplate+1][tamTemplate+1];
		
		int[][] convolucao = new int[tamImagem+1][tamImagem+1];
		
		if(listaMatrizes.size() > 0 && listaTemplate.size() > 0) {
			
			for(Matriz aux : listaMatrizes) {
				imagem[aux.getLinha()][aux.getColuna()] = aux.getColor().getRGB();
			}
			
			for(Matriz aux : listaTemplate) {
				template[aux.getLinha()][aux.getColuna()] = aux.getColor().getRGB();
			}
			
			// CONVOLUÇÃO
			int proporcaoLargura = tamTemplate >>> 1;
			int proporcaoAltura = tamTemplate >>> 1;
			
			for(int i = tamImagem-1; i >= 0; i--) {
				
				for(int j = tamImagem-1; j >= 0; j--) {
					
					double novoValor = 0.0;
					for(int m = tamTemplate-1; m >= 0; m--) {
						
						for(int n = tamTemplate-1; n >= 0; n--) {
							
							int ii = i+m-proporcaoLargura;
							int jj = j+n-proporcaoAltura;
							
							if(ii >=0 && ii < tamImagem && jj >=0 && jj < tamImagem) {
								novoValor += template[m][n] * imagem[ii][jj];
							}
							
						}
					}
					convolucao[i][j] = (int) Math.round(novoValor);
				}
			}
			// FIM DA CONVOLUÇÃO
			
			// Converte matriz para lista
			for(int i = 0; i < tamImagem; i++) {
				for(int j = 0; j < tamImagem; j++) {
					
					for(Matriz aux : listaMatrizes) {
						if(aux.getLinha() == i+1 && aux.getColuna() == j+1) {
							aux.setColor(new Color(convolucao[i][j]));
							novaLista.add(aux);
						}
					}
					
				}
			}
		}
		
		return novaLista;
	}
}
