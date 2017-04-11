package pacote25901.CONTROLLER;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import pacote25901.VIEW.*;

public class MainController implements ActionListener {

	private final static int COLOR_RANGE = 256;
	private InterfaceGrafica appView;
	private BufferedImage img, imageEscalaCinza, imageEqualizada;
	private int[] histogram = new int[COLOR_RANGE];
	private int[] histogramEqualizado = new int[COLOR_RANGE];

	public MainController() {
		appView = new InterfaceGrafica(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Abre uma imagem previamente salva
		if (e.getActionCommand().equals("Carregar")) {
			img = abrirImagem();
			imageEscalaCinza = escalaDeCinza(img);
			appView.showImage(false, imageEscalaCinza);
			calcHistogram(img, histogram, "input");	
			
			appView.ativaBotoesImagem(true);
			
			appView.inputPanel.revalidate();
			appView.outputPanel.revalidate();
		}
		
		else if (e.getActionCommand().equals("Equalizar")) {
			imageEqualizada = histogramEqualization(imageEscalaCinza);
			appView.showImage(true, imageEqualizada);
			calcHistogram(imageEqualizada, histogramEqualizado, "output");
			
			appView.inputPanel.revalidate();
			appView.outputPanel.revalidate();
		}
		
		else if (e.getActionCommand().equals("Limpar")) {
			appView.clearScreen();
			appView.ativaBotoesImagem(false);
		}
		
		else if (e.getActionCommand().equals("Fechar")) {
			System.exit(0);
		}
	}

	private BufferedImage abrirImagem() {
		String nomeArqLido = null;

		// Localiza o arquivo
		JFileChooser arquivo = new JFileChooser();
		File diretorio = new File("..\\");
		arquivo.setCurrentDirectory(diretorio);
		arquivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		// Atribui a saida ao arquivo
		int saida = arquivo.showOpenDialog(arquivo);

		// Faz a leitura do arquivo
		if (saida == JFileChooser.APPROVE_OPTION) {
			File nomeArq = arquivo.getSelectedFile();
			nomeArqLido = nomeArq.toString();
		} else if (saida == JFileChooser.CANCEL_OPTION)
			return null;
		BufferedImage novaimagem = null;
		// Carrega a imagem
		try {
			novaimagem = ImageIO.read(new File(nomeArqLido));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return novaimagem;
	}
	
	public static BufferedImage escalaDeCinza(BufferedImage imagem) {
		// pegar largura e altura da imagem
		int width = imagem.getWidth();
		int height = imagem.getHeight();

		int media = 0;
		// laço para varrer a matriz de pixels da imagem
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) { // rgb recebe o valor RGB do
												// pixel em questão
				int rgb = imagem.getRGB(i, j);
				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				// media dos valores do RGB
				// será o valor do pixel na nova imagem
				media = (r + g + b) / 3;

				// criar uma instância de Color
				Color color = new Color(media, media, media);
				// setar o valor do pixel com a nova cor
				imagem.setRGB(i, j, color.getRGB());
			}
		}
		return imagem;
	}
	
	public void calcHistogram(BufferedImage imagem, int[] histogram, String painel) {
		int width = imagem.getWidth();
		int height = imagem.getHeight();
		int maximo = 0;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                int index = rgbParaCinza(imagem.getRGB(w, h));
                histogram[index]++;
            }
        }
        
        for (int i = 0; i < 256; i++) {
			if (histogram[i] > maximo)
				maximo = histogram[i];
		}
        
        desenhaHistograma(histogram, maximo, painel);
        
    }

	
	private static int rgbParaCinza(int rgb) {
		return (int) (0.333333333333333 * ((rgb >> 16) & 0xFF) + 0.3333333333333333 * ((rgb >> 8) & 0xFF)
				+ 0.333333333333333 * (rgb & 0xFF));
	}
	
	
	private void desenhaHistograma(int[] escala, int maximo, String painel){
		
		int height = 0;
		int width = 0;
		Graphics2D g2;
		
		if(painel=="input"){
			g2 = (Graphics2D) appView.histogramaPanel.getGraphics();
			g2.setColor(Color.LIGHT_GRAY);
			height = appView.histogramaPanel.getHeight();
			width =  appView.histogramaPanel.getWidth();
		}
		else{
			g2 = (Graphics2D) appView.histogramaOutputPanel.getGraphics();
			g2.setColor(Color.LIGHT_GRAY);
			height = appView.histogramaOutputPanel.getHeight();
			width =  appView.histogramaOutputPanel.getWidth();
		}
		g2.fillRect(0, 0, width, height);
		g2.setStroke(new BasicStroke(2));
		double xIntervalo = (width / 256);

		g2.setColor(Color.BLACK);

		for (int j = 0; j < 255; j++) {
			int valor = (int) (((double) escala[j] / (double) maximo) * height);
			int valor2 = (int) (((double) escala[j + 1] / (double) maximo) * height);

			g2.drawLine((int) (j * xIntervalo) + 53, height - valor,
					(int) ((j + 1) * xIntervalo) + 53, height - valor2);

			g2.drawLine((int) (j * xIntervalo) + 53, height, 
					(int) (j * xIntervalo) + 53, height - valor);
		}
	}
	
	private static BufferedImage histogramEqualization(BufferedImage original) {
		 
        int red;
        int green;
        int blue;
        int newPixel = 0;
 
        ArrayList<int[]> histRGB = histogramEqualizationRGB(original);
 
        BufferedImage histogramEQ = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
 
        for(int i=0; i<original.getWidth(); i++) {
            for(int j=0; j<original.getHeight(); j++) {
 
                red = new Color(original.getRGB (i, j)).getRed();
                green = new Color(original.getRGB (i, j)).getGreen();
                blue = new Color(original.getRGB (i, j)).getBlue();
 
                red = histRGB.get(0)[red];
                green = histRGB.get(1)[green];
                blue = histRGB.get(2)[blue];
 
                newPixel = corparaRgb(red, green, blue);
 
                                histogramEQ.setRGB(i, j, newPixel);
 
            }
        }
        
        histogramEQ = escalaDeCinza(histogramEQ);
        return histogramEQ;
 
    }
 
    private static ArrayList<int[]> histogramEqualizationRGB(BufferedImage input) {
 
        ArrayList<int[]> imageHist = imageHistogram(input);
 
        ArrayList<int[]> imageLUT = new ArrayList<int[]>();
 
        int[] rhistogram = new int[256];
        int[] ghistogram = new int[256];
        int[] bhistogram = new int[256];
 
        for(int i=0; i<rhistogram.length; i++) rhistogram[i] = 0;
        for(int i=0; i<ghistogram.length; i++) ghistogram[i] = 0;
        for(int i=0; i<bhistogram.length; i++) bhistogram[i] = 0;
 
        long sumr = 0;
        long sumg = 0;
        long sumb = 0;
 
        float scale_factor = (float) (255.0 / (input.getWidth() * input.getHeight()));
 
        for(int i=0; i<rhistogram.length; i++) {
            sumr += imageHist.get(0)[i];
            int valr = (int) (sumr * scale_factor);
            if(valr > 255) {
                rhistogram[i] = 255;
            }
            else rhistogram[i] = valr;
 
            sumg += imageHist.get(1)[i];
            int valg = (int) (sumg * scale_factor);
            if(valg > 255) {
                ghistogram[i] = 255;
            }
            else ghistogram[i] = valg;
 
            sumb += imageHist.get(2)[i];
            int valb = (int) (sumb * scale_factor);
            if(valb > 255) {
                bhistogram[i] = 255;
            }
            else bhistogram[i] = valb;
        }
 
        imageLUT.add(rhistogram);
        imageLUT.add(ghistogram);
        imageLUT.add(bhistogram);
 
        return imageLUT;
 
    }
 
    public static ArrayList<int[]> imageHistogram(BufferedImage input) {
 
        int[] rhistogram = new int[256];
        int[] ghistogram = new int[256];
        int[] bhistogram = new int[256];
 
        for(int i=0; i<rhistogram.length; i++) rhistogram[i] = 0;
        for(int i=0; i<ghistogram.length; i++) ghistogram[i] = 0;
        for(int i=0; i<bhistogram.length; i++) bhistogram[i] = 0;
 
        for(int i=0; i<input.getWidth(); i++) {
            for(int j=0; j<input.getHeight(); j++) {
 
                int red = new Color(input.getRGB (i, j)).getRed();
                int green = new Color(input.getRGB (i, j)).getGreen();
                int blue = new Color(input.getRGB (i, j)).getBlue();
 
                rhistogram[red]++; ghistogram[green]++; bhistogram[blue]++;
 
            }
        }
 
        ArrayList<int[]> hist = new ArrayList<int[]>();
        hist.add(rhistogram);
        hist.add(ghistogram);
        hist.add(bhistogram);
 
        return hist;
 
    }
 
    private static int corparaRgb(int red, int green, int blue) {
 
        int newPixel = 0;
        newPixel += red; newPixel = newPixel << 16;
        newPixel += green; newPixel = newPixel << 8;
        newPixel += blue;
 
        return newPixel;
 
    }
}
