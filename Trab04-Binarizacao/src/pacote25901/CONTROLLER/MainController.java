package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.event.*;
import pacote25901.VIEW.*;

public class MainController implements ActionListener, ChangeListener {

	private InterfaceGrafica appView;
	private TransformacoesController transformacoesCtrl;
	private BufferedImage img, imageBinarizada;

	public MainController() {
		appView = new InterfaceGrafica(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Abre uma imagem previamente salva
		if (e.getActionCommand().equals("Carregar")) {
			img = abrirImagem();
			appView.showImage(false, img);
			appView.showImage(true, img);
			
			appView.ativaBotoesImagem(true);
			
			appView.inputPanel.revalidate();
			appView.outputPanel.revalidate();
		}
		
		else if (e.getActionCommand().equals("Salvar")) {

		}
		
		else if (e.getActionCommand().equals("ZoomIn")) {
			appView.showImage(true, transformacoesCtrl.zoom(true));
		}
		
		else if (e.getActionCommand().equals("ZoomOut")) {
			appView.showImage(true, transformacoesCtrl.zoom(false));
		}
		
		else if (e.getActionCommand().equals("Direita")) {
			appView.showImage(true, transformacoesCtrl.rotacionar(false));
		}
		
		else if (e.getActionCommand().equals("Esquerda")) {
			appView.showImage(true, transformacoesCtrl.rotacionar(true));
		}
		
		else if (e.getActionCommand().equals("Espelhar")) {
			appView.showImage(true, transformacoesCtrl.espelhar());
		}
		
		else if (e.getActionCommand().equals("Frente")) {
			appView.showImage(true, transformacoesCtrl.transladar(1, 0));
		}
		
		else if (e.getActionCommand().equals("Tras")) {
			appView.showImage(true, transformacoesCtrl.transladar(-1, 0));
		}
		
		else if (e.getActionCommand().equals("Cima")) {
			appView.showImage(true, transformacoesCtrl.transladar(0, -1));
		}
		
		else if (e.getActionCommand().equals("Baixo")) {
			appView.showImage(true, transformacoesCtrl.transladar(0, 1));
		}
		
		else if (e.getActionCommand().equals("Limpar")) {
			appView.clearScreen();
		}
		
		else if (e.getActionCommand().equals("Fechar")) {
			System.exit(0);
		}
	}
	
	public void stateChanged(ChangeEvent e) {
		JSlider slider = (JSlider) e.getSource();

		if (slider == appView.sliderLinha) {
			imageBinarizada = binarizacao(img, appView.sliderLinha.getValue());
			appView.showImage(true, imageBinarizada);
			transformacoesCtrl = new TransformacoesController(imageBinarizada);
		}
	}

	/**
	 * Abre uma imagem.
	 */
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

	public static BufferedImage binarizacao(BufferedImage image, int limiar) {
		int width = image.getWidth();
		int height = image.getHeight();
		
		BufferedImage output = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = (int) ((rgb & 0x00FF0000) >>> 16);
				int g = (int) ((rgb & 0x0000FF00) >>> 8);
				int b = (int) (rgb & 0x000000FF);
				int media = (r + g + b) / 3;
				Color white = new Color(255, 255, 255);
				Color black = new Color(0, 0, 0);
				// como explicado no artigo, no threshold definimos um limiar,
				// que é um valor "divisor de águas"
				// pixels com valor ABAIXO do limiar viram pixels PRETOS,
				// pixels com valor ACIMA do limiar viram pixels BRANCOS
				if (media < limiar)
					output.setRGB(i, j, black.getRGB());
				else
					output.setRGB(i, j, white.getRGB());
			}
		}
		return output;
	}
}
