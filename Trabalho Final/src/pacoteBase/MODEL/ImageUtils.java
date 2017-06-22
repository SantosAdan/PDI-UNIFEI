package pacoteBase.MODEL;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageUtils {
	
	
	public static int getLargura(char[][] imagem) {
		return getNColunas(imagem);
	}
	
	public static int getAltura(char[][] imagem) {
		return getNLinhas(imagem);
	}
	
	public static int getNColunas(char[][] imagem) {
		return imagem.length;
	}
	
	public static int getNLinhas(char[][] imagem) {
		if(imagem == null || imagem[0] == null) {
			return 0;
		}
		
		return imagem[0].length;
	}
	
	
	public static char[][] bufferedToBytes(BufferedImage bi) {
		char[][] output = new char[bi.getWidth()][bi.getHeight()];
		for(int i = 0; i < bi.getWidth(); i++) {
			for(int j = 0; j < bi.getHeight(); j++) {
				output[i][j] = (char) bi.getRGB(i, j);
			}
		}
		
		return output;
	}
	
	
	public static char[][] copiarImagem ( char[][] imagemFonte)
	{
		int nColImg =getNColunas(imagemFonte),
				nLinImg = getNLinhas(imagemFonte);
		int      x, y;
		char[][] imagemDestino;

		imagemDestino = new char[nColImg][nLinImg];

		for ( x = 0; x < nColImg; x++ ) 
			for ( y = 0; y < nLinImg; y++ )
				imagemDestino[x][y] = imagemFonte[x][y];

		return ( imagemDestino );
	}
	
	
	
	public static char[][] binarizar(BufferedImage bi) {
		char[][] output = new char[bi.getWidth()][bi.getHeight()];
		char avg;
		Color c;
		for(int i = 0; i < bi.getWidth(); i++) {
			for(int j = 0; j < bi.getHeight(); j++) {
				c = new Color(bi.getRGB(i, j));
				avg = (char)(c.getRed() + c.getGreen() + c.getBlue()/3);
				if(avg>130) {
					output[i][j] = 0xFF;
				} else {
					output[i][j] = 0;
				}
			}
		}
		
		return output;
	}
	
	
	
	public static char[][] binarizar(char[][] imagem) {
		char[][] output = copiarImagem(imagem);

		for(int i = 0; i < imagem.length; i++) {
			for(int j = 0; j < imagem[0].length; j++) {
				if(output[i][j] > 150) {
					output[i][j] = 0xFF;
				}
			}
		}
		
		return output;
	}
	
	public static char[][] intToChar(int[][] imagem) {
		char[][] output = new char[imagem.length][imagem[0].length];

		for(int i = 0; i < imagem.length; i++) {
			for(int j = 0; j < imagem[0].length; j++) {
				output[i][j] = (char) imagem[i][j];
			}
		}
		
		return output;
	}
	
	public static char[][] inverterBinaria(char[][] imagem) {
		char[][] imagemCopiada = copiarImagem(imagem);
		for(int i  =0 ; i < imagem.length; i++) {
			for(int j = 0; j < imagem[0].length; j++) {
				if(imagemCopiada[i][j] > 130) {
					imagemCopiada[i][j] = 0;
				} else {
					imagemCopiada[i][j] = 0xFF;
				}
			}
		}
		return imagemCopiada;
	}
	
	
}
