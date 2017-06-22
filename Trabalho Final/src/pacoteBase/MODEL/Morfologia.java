package pacoteBase.MODEL;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Morfologia {
	public static int[][] binarizar(BufferedImage bi) {
		int[][] imagemMorfologia = new int[bi.getWidth()][bi.getHeight()];
		Color c;
		for (int j = 0; j < bi.getWidth(); j++){
			for (int k = 0; k < bi.getHeight(); k++){
				c =  new Color(bi.getRGB(j, k));
				if((c.getRed() + c.getBlue() + c.getGreen())/3 > 138){
					imagemMorfologia[j][k] = (int)0;
				}
				else{
					imagemMorfologia[j][k] = (int)1;
				}

			}
		}
		
		return imagemMorfologia;
		
	}
	
	public static BufferedImage normalizar(int[][] imagemMorfologia) {
		BufferedImage normalizada = new BufferedImage(imagemMorfologia.length, imagemMorfologia[0].length, BufferedImage.TYPE_BYTE_GRAY);
		for(int i=0; i<imagemMorfologia.length; i++) {
			for(int j=0;j<imagemMorfologia[0].length; j++) {
				if(imagemMorfologia[i][j] == 0) {
					normalizada.setRGB(i, j, new Color(255, 255, 255).getRGB());
				} else {
					normalizada.setRGB(i, j, new Color(0, 0, 0).getRGB());
				}
			}
		}
		
		return normalizada;
	}
}
