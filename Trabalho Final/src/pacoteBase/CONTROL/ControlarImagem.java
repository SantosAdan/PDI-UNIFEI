package pacoteBase.CONTROL;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

import pacoteBase.MODEL.ImageUtils;




public class ControlarImagem {

	private int           nLinImagem;
	private int           nColImagem;
	private BufferedImage imagemDada;

	private int[][] dadosDigital;



	//*******************************************************************************************
	public ControlarImagem( String   nomeArquivoImagemDada,
			Graphics desenho
			)
	{
		imagemDada = lerImagem ( nomeArquivoImagemDada );
		if ( imagemDada != null ) {
			mostrarImagem ( imagemDada, desenho );
		}
	}

	//*******************************************************************************************
	// METODO PARA GERAR A IMAGEM RASTER EM NIVEIS DE CINZA A PARTIR DA IMAGEM BUFERIZADA COLORIDA

	public char[][] criarImagemCinza ( BufferedImage imagem ) 
	{
		int    x, y, r, g, b;
		Raster imagemRasterEntrada;
		char   valorSaida;
		char[][] imagemCinza;

		// DIMENSOES DA MATRIZ CINZA
		nColImagem  = imagem.getWidth(null);
		nLinImagem  = imagem.getHeight(null);
		imagemCinza = new char[nColImagem][nLinImagem];

		// DEFININDO IMAGENS INTERMEDIARIAS
		imagemRasterEntrada = imagem.getRaster();

		// CRIANDO IMAGEM CINZA
		for ( y = 0; y < nLinImagem; y++ ) {
			for ( x = 0; x < nColImagem; x++ ) {

				// LENDA DADOS DAS BANDAS DA IMAGEM DADA
				r = imagemRasterEntrada.getSample(x,y,0);  // le dado da banda 0 da imagem de entrada 

				try {
					g = imagemRasterEntrada.getSample(x,y,1);  // le dado da banda 1 da imagem de entrada 
					b = imagemRasterEntrada.getSample(x,y,2);  // le dado da banda 2 da imagem de entrada
				} catch ( ArrayIndexOutOfBoundsException e) {
					g = r;
					b = r;
				}

				//  GERANDO NIVEL DE CINZA 
				valorSaida = (char)((r+g+b)/3);
				imagemCinza[x][y] = valorSaida;

			}
		}

		return imagemCinza;
	}


	//************************************************************************
	// METODO DE ZOOM REPLICACAO PIXEL
	public char[][] aplicaZoom(char[][] tempZoom, int auxZoom)
	{
		int linhaEn = ImageUtils.getNLinhas(tempZoom);
		int colunaEn = ImageUtils.getNColunas(tempZoom);
		int n = 0, d = 0, j, k; // n e d sao os fatores de ampliacao ou reducao da imagem

		//PARAMETRO ZOOM DO TEXTFILD
		if(auxZoom >0)
		{	
			n= 100+auxZoom;
			d= 100;

			linhaEn =(int) ((float)linhaEn*((float)(n)/100));
			colunaEn = (int) ((float)colunaEn*((float)(n)/100));
		}
		else
		{
			n=100-Math.abs(auxZoom);
			d= 100;	

			linhaEn =(int) ((float)linhaEn*((float)(n)/100));
			colunaEn = (int) ((float)colunaEn*((float)(n)/100));

		}


		//APLICANDO ZOOM

		char [][] imagemZoom = new char [colunaEn][linhaEn];


		for (j = 0; j < colunaEn-1; j++){
			for (k = 0; k < linhaEn-1; k++){
				imagemZoom[j][k] = tempZoom[j*d/n][k*d/n];
			}
		}

		return imagemZoom;
	}


	//******************************************************************************************
	public char[][] copiarImagem ( char[][] imagemFonte)
	{
		int nColImg = ImageUtils.getNColunas(imagemFonte),
				nLinImg = ImageUtils.getNLinhas(imagemFonte);
		int      x, y;
		char[][] imagemDestino;

		imagemDestino = new char[nColImg][nLinImg];

		for ( x = 0; x < nColImg; x++ ) 
			for ( y = 0; y < nLinImg; y++ )
				imagemDestino[x][y] = imagemFonte[x][y];

		return ( imagemDestino );
	}





	//*******************************************************************************************
	private BufferedImage lerImagem ( String nomeArquivo )
	{
		File          arquivoImagem;
		BufferedImage imagem;

		// INICIALIZANDO VARIAVEIS
		imagem        = null;
		arquivoImagem = new File(nomeArquivo);

		// LEITURA DA IMAGEM
		try {
			imagem = ImageIO.read( arquivoImagem );
		} catch (IOException e) {
			System.out.println ( "imagem " + nomeArquivo + " nao existe");
		}

		return ( imagem );
	}







	/*public void mostrarImagem(BufferedImage imagemM, Graphics desenho)
	{
		desenho.drawImage( imagemM, 0, 0, imagemM.getWidth(), imagemM.getHeight(),  null );

		
	}*/

	public void mostrarImagem  ( BufferedImage imagem,Graphics desenho )
	{
		int imageWidth, imageHeight, x, sx, y, sy, cell, dx, dy;
		int cells[] = {0, 1, 2, 3};
		imageWidth  = imagem.getWidth(null);
		imageHeight = imagem.getHeight(null);
		for ( x = 0; x < 2; x++ ) {
			sx = x * imageWidth;
			for ( y = 0; y < 2; y++ ) {
				sy   = y * imageHeight;
				cell = cells[x*2+y];
				dx   = (cell / 2) * imageWidth;
				dy   = (cell % 2) * imageHeight;
				desenho.drawImage( imagem, dx, dy, x + imageWidth, dy + imageHeight,  
						sx, sy,  sx + imageWidth, sy + imageHeight, null );
			}
		}
		
		if(dadosDigital != null) {
			Graphics2D g = (Graphics2D) desenho;
			int CIRCLE_SIZE = 7;
			for(int i = 0; i < dadosDigital.length; i++) {
				for(int j = 0; j < dadosDigital[0].length; j++) {
					g.setStroke(new BasicStroke(2));
					if(dadosDigital[i][j] == 3) {
						g.setColor(Color.RED);
						g.drawOval(i-CIRCLE_SIZE/2, j-CIRCLE_SIZE/2, CIRCLE_SIZE, CIRCLE_SIZE);
					} else if(dadosDigital[i][j] == 1) {
						g.setColor(Color.GREEN);
						//g.drawOval(i-CIRCLE_SIZE, j-CIRCLE_SIZE, CIRCLE_SIZE, CIRCLE_SIZE);
					}

				}
			}
		}
	}

	public void mostrarImagem(char[][] imagemM, Graphics desenho)
	{
		mostrarImagem(transformarMatriz2Buffer(imagemM), desenho); 
	}





	//*******************************************************************************************
	public BufferedImage transformarMatriz2Buffer ( char[][] imagemM)
	{
		int nLin = ImageUtils.getNLinhas(imagemM), nCol = ImageUtils.getNColunas(imagemM);
		int            x, y;
		char           valorSaida;
		WritableRaster imagemRasterSaida;
		BufferedImage  imagemB;

		imagemB           = new BufferedImage( nCol, nLin, BufferedImage.TYPE_BYTE_GRAY ); 
		imagemRasterSaida = imagemB.getRaster();

		for ( y = 0; y < nLin; y++ ) {
			for ( x = 0; x < nCol; x++ ) {
				valorSaida = imagemM[x][y];
				imagemRasterSaida.setSample( x, y, 0, valorSaida );
			}
		}

		return ( imagemB );
	}





	//*******************************************************************************************
	public void gravarImagem (String nomeArquivo, char[][] imagemM)
	{
		File          arquivoImagem;
		BufferedImage imagemB;

		imagemB = transformarMatriz2Buffer ( imagemM );

		// INICIALIZANDO VARIAVEIS
		arquivoImagem = new File(nomeArquivo + ".jpg" );

		// LEITURA DA IMAGEM
		try {
			ImageIO.write( imagemB, "jpg", arquivoImagem );
		} catch (IOException e) {
			System.out.println ( "imagem " + nomeArquivo + " nao existe");
		}
	}








	public BufferedImage getImagemDada() {
		return imagemDada;
	}

	//*******************************************************************************************
	public int getNLin()
	{
		return ( nLinImagem );
	}

	//*******************************************************************************************
	public int getNCol()
	{
		return ( nColImagem );
	}

	//*******************************************************************************************

	public int getLin()
	{
		return ( nLinImagem );
	}

	//*******************************************************************************************
	public int getCol()
	{
		return ( nColImagem );
	}

	//*******************************************************************************************


	public void setDadosDigital(int[][] dadosDigital) {
		this.dadosDigital = dadosDigital;
	}
}
