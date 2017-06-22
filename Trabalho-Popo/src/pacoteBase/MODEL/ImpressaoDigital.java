package pacoteBase.MODEL;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;



public class ImpressaoDigital {


	
	/* Nao utilizado */
	public static int[][] getFastParallelAlgoSkeleton(int img_bin[][]) throws Exception
	{
		int i,j,k,A,B;
		//clockwise
		int di[]=new int[]{0,0, -1,-1,0,1,1,1,0,-1};//1st 2 elements are useless
		int dj[]=new int[]{0,0, 0,1,1,1,0,-1,-1,-1};
		boolean EVEN=true;

		BufferedImage debugImg=new BufferedImage(img_bin.length, img_bin[0].length, BufferedImage.TYPE_INT_ARGB);

		int skeleton[][]=new int[img_bin.length][img_bin[0].length];
		for(i=1;i<img_bin.length-1;i++)
			for(j=1;j<img_bin[i].length-1;j++)
			{
				skeleton[i][j]=0;
				A=img_bin[i][j];
				B=0;
				for(k=2;k<=9;k++)// from P2+P3+...+P9
				B+=img_bin[i+di[k]][j+dj[k]];

				// A=1  AND 3<= B <=6?
				if(A==1 && 3<=B && B<=6)
				{
					if(     !EVEN
							&&
							img_bin[i+di[2]][j+dj[2]]*
							img_bin[i+di[4]][j+dj[4]]*
							img_bin[i+di[6]][j+dj[6]] == 0
							&&
							img_bin[i+di[4]][j+dj[4]]*
							img_bin[i+di[6]][j+dj[6]]*
							img_bin[i+di[8]][j+dj[8]] == 0)

						skeleton[i][j]=1;


					if(     EVEN
							&&
							img_bin[i+di[2]][j+dj[2]]*
							img_bin[i+di[4]][j+dj[4]]*
							img_bin[i+di[8]][j+dj[8]] == 0
							&&
							img_bin[i+di[2]][j+dj[2]]*
							img_bin[i+di[6]][j+dj[6]]*
							img_bin[i+di[8]][j+dj[8]] == 0)

						skeleton[i][j]=1;


				}

				if(skeleton[i][j]==0)
					debugImg.setRGB(i, j, new Color(255, 255, 255).getRGB());
				else
					debugImg.setRGB(i, j, new Color(0, 0, 0).getRGB());

				EVEN=!EVEN;
			}

		ImageIO.write(debugImg, "png", new File("Imagem Afinada.png"));
		return skeleton;
	}
	 

	private static int[][] esqueletizar(int[][] imagem) throws Exception {
		int[][] skeleton = MorfologiaEsqueleto.aplicar(imagem);
		ImageIO.write(matrixToBuffer(skeleton), "png", new File("Imagem Afinada.png"));
		return skeleton;
	}


	public static int[][] getCN(int skeleton[][])//after thinning
	{
		int di[]=new int[]{0,-1,-1,-1,0,1,1,1};
		int dj[]=new int[]{1,1,0,-1,-1,-1,0,1};

		int i,j,k;

		
		
		int CN[][]=new int[skeleton.length][skeleton[0].length];
		for(i=1;i<skeleton.length-1;i++) {
			for(j=1;j<skeleton[0].length-1;j++)
			{
				CN[i][j]=0;
				for(k=0;k<8;k++) {
					CN[i][j]+=Math.abs(skeleton[i+di[k]][j+dj[k]] - skeleton[i+di[(k+1)%8]][j+dj[(k+1)%8]]);

				}
				CN[i][j] = (int) CN[i][j]/2;
				
			}
		}
		
		return CN;
	}


	

	public static BufferedImage matrixToBuffer(int[][] imgBin) {
		int i, j;
		BufferedImage debugImg=new BufferedImage(imgBin.length,imgBin[0].length, BufferedImage.TYPE_INT_ARGB);
		for(i=0;i<imgBin.length;i++) {
			for(j=0;j<imgBin[i].length;j++) {
				if(imgBin[i][j]==0)
					debugImg.setRGB(i, j, new Color(255, 255, 255).getRGB());
				else
					debugImg.setRGB(i, j, new Color(0, 0, 0).getRGB());
			}
		}

		return debugImg;
	}

	public static int[][] reconhecerTracos(BufferedImage imagem)
	{
		

		try {
			int[][] cn =getCN(
					esqueletizar(
							Morfologia.binarizar(imagem)));
					

			
			return cn;
		} catch(Exception ex) {
			ex.printStackTrace(System.out);;
			return null;
		}


	}
}
