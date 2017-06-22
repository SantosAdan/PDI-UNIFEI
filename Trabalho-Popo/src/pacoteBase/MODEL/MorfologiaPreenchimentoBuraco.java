package pacoteBase.MODEL;

public class MorfologiaPreenchimentoBuraco {

	
	public static char[][] aplicar(char[][] tempMorfologia)
	{

		int  j, k; 
	
			
		int linhaEn = ImageUtils.getNLinhas(tempMorfologia);
		int colunaEn = ImageUtils.getNColunas(tempMorfologia);

		char [][] imagemMorfologia = new char [colunaEn][linhaEn];
		
		
		for (j = 0; j < colunaEn-1; j++){
			for (k = 0; k < linhaEn-1; k++){
				if((int)tempMorfologia[j][k] < 128){
					imagemMorfologia[j][k] = (char)0;
				}
				else{
					imagemMorfologia[j][k] = (char)255;
				}

			}
		}
				
	
		char [][]b1 = new char[][] { 
			{(char)2, (char)255, (char)2}, 
			{(char)255, (char)0, (char)255}, 
			{(char)2, (char)255, (char)2} 
		}; 
	
		char [][]b2 = new char[][] { 
			{(char)255, (char)2, (char)255}, 
			{(char)2, (char)0, (char)2}, 
			{(char)255, (char)2, (char)255} 
		}; 
		
		char [][]b3 = new char[][] { 
			{(char)2, (char)2, (char)2}, 
			{(char)255, (char)0, (char)255}, 
			{(char)2, (char)2, (char)2} 
		}; 
		
		char [][]b4 = new char[][] { 
			{(char)2, (char)255, (char)2}, 
			{(char)2, (char)0, (char)2}, 
			{(char)2, (char)255, (char)2} 
		}; 
		
		char [][]b9 = new char[][] { 
			{(char)2, (char)2, (char)255}, 
			{(char)2, (char)0, (char)2}, 
			{(char)255, (char)2, (char)2} 
		}; 
		
		char [][]b10 = new char[][] { 
			{(char)255, (char)2, (char)2}, 
			{(char)2, (char)0, (char)2}, 
			{(char)2, (char)2, (char)255} 
		}; 
		
		
		char [][]b5 = new char[][] { 
			{(char)2, (char)0, (char)2}, 
			{(char)0, (char)255, (char)0}, 
			{(char)2, (char)0, (char)2} 
		}; 
	
		char [][]b6 = new char[][] { 
			{(char)0, (char)2, (char)0}, 
			{(char)2, (char)255, (char)2}, 
			{(char)0, (char)2, (char)0} 
		}; 
		
		char [][]b7 = new char[][] { 
			{(char)2, (char)2, (char)2}, 
			{(char)0, (char)255, (char)0}, 
			{(char)2, (char)2, (char)2} 
		}; 
		
		char [][]b8 = new char[][] { 
			{(char)2, (char)0, (char)2}, 
			{(char)2, (char)255, (char)2}, 
			{(char)2, (char)0, (char)2} 
		}; 
		
		char [][]b11 = new char[][] { 
			{(char)2, (char)2, (char)0}, 
			{(char)2, (char)255, (char)2}, 
			{(char)0, (char)2, (char)2} 
		}; 
		
		char [][]b12 = new char[][] { 
			{(char)0, (char)2, (char)2}, 
			{(char)2, (char)255, (char)2}, 
			{(char)2, (char)2, (char)0} 
		}; 
		
		
		// Elementos estruturantes
		
	
		/*char [][]b1 = new char[][] { 
			{(char)255, (char)255, (char)2}, 
			{(char)255, (char)0, (char)0}, 
			{(char)2, (char)0, (char)0} 
		}; 
		
		char [][]b2 = new char[][] { 
			{(char)2, (char)255, (char)255}, 
			{(char)0, (char)0, (char)255}, 
			{(char)0, (char)0, (char)2} 
		};
		
		
		char [][]b3 = new char[][] { 
			{(char)255, (char)2, (char)0}, 
			{(char)255, (char)0, (char)0}, 
			{(char)255, (char)2, (char)0} 
		};
		
	
		char [][]b4 = new char[][] { 
			{(char)0, (char)0, (char)2}, 
			{(char)0, (char)0, (char)255}, 
			{(char)2, (char)255, (char)255} 
		};
		
	
		char [][]b5 = new char[][] { 
			{(char)255, (char)255, (char)255}, 
			{(char)2, (char)0, (char)2}, 
			{(char)0, (char)0, (char)0} 
		};
		

		char [][]b6 = new char[][] { 
			{(char)2, (char)0, (char)0}, 
			{(char)255, (char)0, (char)0}, 
			{(char)255, (char)255, (char)2} 
		};
		

		char [][]b7 = new char[][] { 
			{(char)0, (char)2, (char)255}, 
			{(char)0, (char)0, (char)255}, 
			{(char)0, (char)2, (char)255} 
		};
		
		
		char [][]b8 = new char[][] { 
			{(char)255, (char)255, (char)2}, 
			{(char)255, (char)0, (char)0}, 
			{(char)2, (char)0, (char)0} 
		};
		*/
		
		
		
		
		for (j = 0; j < colunaEn-1; j++){
			for (k = 0; k < linhaEn-1; k++){
				if(imagemMorfologia[j][k] == b1[1][1]){
					if(verificaForma(imagemMorfologia, j, k, b1)){
						imagemMorfologia[j][k] = (char)255;
					}
				}
			}
		}
		
	

		for (j = 0; j < colunaEn-1; j++){
			for (k = 0; k < linhaEn-1; k++){
				if(imagemMorfologia[j][k] == b2[1][1]){
					if(verificaForma(imagemMorfologia, j, k, b2)){
						imagemMorfologia[j][k] = (char)255;
					}
				}
			}
		}
		
		for (j = 0; j < colunaEn-1; j++){
			for (k = 0; k < linhaEn-1; k++){
				if(imagemMorfologia[j][k] == b3[1][1]){
					if(verificaForma(imagemMorfologia, j, k, b3)){
						imagemMorfologia[j][k] = (char)255;
					}
				}
			}
		}
		
		for (j = 0; j < colunaEn-1; j++){
			for (k = 0; k < linhaEn-1; k++){
				if(imagemMorfologia[j][k] == b4[1][1]){
					if(verificaForma(imagemMorfologia, j, k, b4)){
						imagemMorfologia[j][k] = (char)255;
					}
				}
			}
		}
		
		for (j = 0; j < colunaEn-1; j++){
			for (k = 0; k < linhaEn-1; k++){
				if(imagemMorfologia[j][k] == b9[1][1]){
					if(verificaForma(imagemMorfologia, j, k, b4)){
						imagemMorfologia[j][k] = (char)255;
					}
				}
			}
		}
		
		for (j = 0; j < colunaEn-1; j++){
			for (k = 0; k < linhaEn-1; k++){
				if(imagemMorfologia[j][k] == b10[1][1]){
					if(verificaForma(imagemMorfologia, j, k, b4)){
						imagemMorfologia[j][k] = (char)255;
					}
				}
			}
		}
		
		for (j = 0; j < colunaEn-1; j++){
			for (k = 0; k < linhaEn-1; k++){
				if(imagemMorfologia[j][k] == b5[1][1]){
					if(verificaForma(imagemMorfologia, j, k, b5)){
						imagemMorfologia[j][k] = (char)0;
					}
				}
			}
		}
		
		for (j = 0; j < colunaEn-1; j++){
			for (k = 0; k < linhaEn-1; k++){
				if(imagemMorfologia[j][k] == b6[1][1]){
					if(verificaForma(imagemMorfologia, j, k, b6)){
						imagemMorfologia[j][k] = (char)0;
					}
				}
			}
		}
		
		for (j = 0; j < colunaEn-1; j++){
			for (k = 0; k < linhaEn-1; k++){
				if(imagemMorfologia[j][k] == b7[1][1]){
					if(verificaForma(imagemMorfologia, j, k, b7)){
						imagemMorfologia[j][k] = (char)0;
					}
				}
			}
		}
		
		for (j = 0; j < colunaEn-1; j++){
			for (k = 0; k < linhaEn-1; k++){
				if(imagemMorfologia[j][k] == b11[1][1]){
					if(verificaForma(imagemMorfologia, j, k, b7)){
						imagemMorfologia[j][k] = (char)0;
					}
				}
			}
		}
		
		for (j = 0; j < colunaEn-1; j++){
			for (k = 0; k < linhaEn-1; k++){
				if(imagemMorfologia[j][k] == b12[1][1]){
					if(verificaForma(imagemMorfologia, j, k, b7)){
						imagemMorfologia[j][k] = (char)0;
					}
				}
			}
		}
		
		
		for (j = 0; j < colunaEn-1; j++){
			for (k = 0; k < linhaEn-1; k++){
				if(imagemMorfologia[j][k] == b8[1][1]){
					if(verificaForma(imagemMorfologia, j, k, b8)){
						imagemMorfologia[j][k] = (char)0;
					}
				}
			}
		}
		
		
		
		return imagemMorfologia;
	}

	//******************************************************************************************
	// METODO VERIFICA FORMA
	private static boolean verificaForma(char[][] imagem, int j, int k, char[][] b)
	{
		int lmin, lmax, cmin, cmax, m, n, x = 0, y = 0;
		lmin = j-1;
		lmax = j+1;
		cmin = k-1;
		cmax = k+1;

		if(lmin < 0 || lmax > imagem.length-1 || cmin < 0 || cmax > imagem[0].length-1){
			return false;
		}
		
		for (m = lmin; m <= lmax; m++){
			y = 0;
			for (n = cmin; n <= cmax; n++){
				if(b[x][y] !=  (char)2){
					if(imagem[m][n] != b[x][y]){
						return false;
					}
				}
				y++;
			}
			x++;
		}
		return true;
	}

}
