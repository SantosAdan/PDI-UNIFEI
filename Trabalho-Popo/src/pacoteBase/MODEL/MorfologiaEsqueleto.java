package pacoteBase.MODEL;

/* Implementado utilizando algoritmo de afinamento. Explicação disponivel em: http://homepages.inf.ed.ac.uk/rbf/HIPR2/thin.htm */
public class MorfologiaEsqueleto {
	
	private static final int EROSAO = 0;//, DILATACAO = 1;
	
	

	
	private static int[][] prunning(int[][] imagemMorfologia) {
		int[][] p1 = new int[][] {
			{0, 0, 0},
			{0, 1, 0},
			{0, 2, 2}
		};
		int[][] p2 = new int[][] {
			{0, 0, 0},
			{2, 1, 0},
			{2, 0, 0}
		};
		int[][] p3 = new int[][] {
			{2, 2, 0},
			{0, 1, 0},
			{0, 0, 0}
		};
		int[][] p4 = new int[][] {
			{0, 0, 2},
			{0, 1, 2},
			{0, 0, 0}
		};
		
		int[][] p5 = new int[][] {
			{0, 0, 0},
			{0, 1, 0},
			{2, 2, 0}
		};
		int[][] p6 = new int[][] {
			{2, 0, 0},
			{2, 1, 0},
			{0, 0, 0}
		};
		int[][] p7 = new int[][] {
			{0, 2, 2},
			{0, 1, 0},
			{0, 0, 0}
		};
		int[][] p8 = new int[][] {
			{0, 0, 0},
			{0, 1, 2},
			{0, 0, 2}
		};
		
		aplicarMorfologia(imagemMorfologia, p1, EROSAO);
		aplicarMorfologia(imagemMorfologia, p5, EROSAO);
		
		aplicarMorfologia(imagemMorfologia, p2, EROSAO);
		aplicarMorfologia(imagemMorfologia, p6, EROSAO);
		
		aplicarMorfologia(imagemMorfologia, p3, EROSAO);
		aplicarMorfologia(imagemMorfologia, p7, EROSAO);
		
		aplicarMorfologia(imagemMorfologia, p4, EROSAO);
		aplicarMorfologia(imagemMorfologia, p8, EROSAO);
		
		return imagemMorfologia;
	}
	
	
	public static int[][] aplicar(int[][] tempMorfologia)
	{
		
		int linhaEn = tempMorfologia.length;
		int colunaEn = tempMorfologia[0].length;

		int [][] imagemMorfologia = new int [linhaEn][colunaEn];
		
		for(int i = 0; i < linhaEn; i++)
			for(int j=0; j< colunaEn; j++)
				imagemMorfologia[i][j] = tempMorfologia[i][j];
		
		
				
	
		int [][]b1 = new int[][] { 
			{0, 0, 0}, 
			{2, 1, 2}, 
			{1, 1, 1} 
		}; 
		
		int [][]b2 = new int[][] { 
			{1, 2, 0}, 
			{1, 1, 0}, 
			{1, 2, 0} 
		};
		
		int [][]b3 = new int[][] { 
			{1, 1, 1}, 
			{2, 1, 2}, 
			{0, 0, 0}
		};
		
		int [][]b4 = new int[][] { 
			{0, 2, 1}, 
			{0, 1, 1}, 
			{0, 2, 1} 
		};
		
		//Elemento 2
		int [][]b5 = new int[][] { 
			{2, 0, 0}, 
			{1, 1, 0}, 
			{2, 1, 2} 
		}; 
		
		int [][]b6 = new int[][] { 
			{2, 1, 2}, 
			{1, 1, 0}, 
			{2, 0, 0} 
		}; 
		
		int [][]b7 = new int[][] { 
			{2, 1, 2}, 
			{0, 1, 1}, 
			{0, 0, 2}
		}; 
		
		int [][]b8 = new int[][] { 
			{0, 0, 2}, 
			{0, 1, 1}, 
			{2, 1, 2} 
		}; 
		
		
		
		
		boolean modificou = false;
		int i = 0;
		
		do {
			modificou = aplicarMorfologia(imagemMorfologia, b1, EROSAO) ||
			aplicarMorfologia(imagemMorfologia, b5, EROSAO) ||
			
			aplicarMorfologia(imagemMorfologia, b2, EROSAO) ||
			aplicarMorfologia(imagemMorfologia, b6, EROSAO) ||
			
			aplicarMorfologia(imagemMorfologia, b3, EROSAO) ||
			aplicarMorfologia(imagemMorfologia, b7, EROSAO) ||
			
			aplicarMorfologia(imagemMorfologia, b4, EROSAO) ||
			aplicarMorfologia(imagemMorfologia, b8, EROSAO);
			
			if(i < 2) {
				prunning(imagemMorfologia);
			}
			i++;
		} while(modificou);

		
		return imagemMorfologia;
	}
	
	
	private static boolean aplicarMorfologia(int[][] imagem, int[][] b, int aplicar) {
		boolean modificou = false;
		for (int j = 0; j < imagem.length-1; j++){
			for (int k = 0; k < imagem[0].length-1; k++){
				if(imagem[j][k] == b[1][1]){
					if(verificaForma(imagem, j, k, b)){
						imagem[j][k] = aplicar;
						modificou = true;
					}
				}
			}
		}
		return modificou;
	}

	//******************************************************************************************
	// METODO VERIFICA FORMA
	private static boolean verificaForma(int[][] imagem, int j, int k, int[][] b)
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
				if(b[x][y] !=  (int)2){
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
