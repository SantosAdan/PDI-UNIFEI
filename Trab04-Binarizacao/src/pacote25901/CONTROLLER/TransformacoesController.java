package pacote25901.CONTROLLER;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class TransformacoesController {

	private int largura, altura;
	private BufferedImage img;
	protected int anguloRotacao = 0, taxaCresc = 0;
	protected int movimenta_X = 0, movimenta_Y = 0;

	public TransformacoesController(BufferedImage img) {
		this.img = img;
		this.largura = img.getWidth();
		this.altura = img.getHeight();
	}

	/**
	 * @return imagem espelhada. Método que realiza a operaçao de espelhamento.
	 * 
	 */
	public BufferedImage espelhar() {

		// Inicializa uma nova BufferedImage com as configurações
		BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);

		// Cria a imagem espelhada pixel a pixel
		for (int y = 0; y < altura; y++) {
			for (int lx = 0, rx = largura - 1; lx < largura; lx++, rx--) {
				// Pega o valor do pixel e seta o pixel espelhado na imagem
				novaImagem.setRGB(rx, y, img.getRGB(lx, y));
			}
		}
		
		img = novaImagem;
		
		return novaImagem;
	}

	/**
	 * @param img
	 *            Imagem. Método para setar as configurações da Imagem
	 */
	public void setImg(BufferedImage img) {
		this.img = img;
		this.largura = img.getWidth();
		this.altura = img.getHeight();
	}

	/**
	 * 
	 * @param flag
	 *            Condição para análise. Método que realiza a operação de
	 *            Zoom(escalamento) da imagem
	 */
	public BufferedImage zoom(Boolean flag) {

		// Verifica se aumenta ou diminui a escala
		// zoomIn True, zoomOut False
		if (flag)
			taxaCresc += 10;
		else
			taxaCresc -= 10;

		// Inicializa uma nova BufferedImage com as configurações
		BufferedImage novaImagem = new BufferedImage(largura + taxaCresc, altura + taxaCresc,
				BufferedImage.TYPE_INT_ARGB);

		// +1 adicionado para um problema de arredondamento
		int proporcaoX = (int) ((largura << 16) / (largura + taxaCresc)) + 1;
		int proporcaoY = (int) ((altura << 16) / (altura + taxaCresc)) + 1;

		int x2, y2;

		// Faz a escala da imagem preenchendo a vizinhança
		for (int i = 0; i < (altura + taxaCresc); i++) {
			for (int j = 0; j < largura + taxaCresc; j++) {
				x2 = ((j * proporcaoX) >> 16);
				y2 = ((i * proporcaoY) >> 16);
				novaImagem.setRGB(j, i, img.getRGB(x2, y2));
			}
		}

		return novaImagem;
	}

	/**
	 * 
	 * @param distanciaX
	 *            distância em x para movimentar
	 * @param distanciaY
	 *            distância em y para movimentar. Método que translada uma
	 *            imagem com base nos parametros recebidos.
	 */
	public BufferedImage transladar(int distanciaX, int distanciaY) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		int cgx = (int) ((width - largura) / 2);
		int cgy = (int) ((height - altura) / 2);
		
		// Incrementos de altura e largura
		movimenta_X += distanciaX;
		movimenta_Y += distanciaY;

		// Inicializa uma nova BufferedImage com as configurações
		BufferedImage novaImagem = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB);
	
		for (int x = 0; x < largura; x++) {
			for (int y = 0; y < altura; y++) {
				// Pega o valor do pixel e seta o pixel espelhado na imagem
				novaImagem.setRGB(cgx + x + movimenta_X, cgy + y + movimenta_Y, img.getRGB(x, y));
			}
		}


		return novaImagem;
	}

	/**
	 * @param flag
	 *            Condição para análise. Método para rotacionar a imagem
	 */
	public BufferedImage rotacionar(Boolean flag) {

		// Verifica para que lado rotacionar a imagem
		// True, rotação à direita. False, rotação à esquerda
		if (flag)
			anguloRotacao += 15;
		else
			anguloRotacao -= 15;

		// Especificações
		double angle = Math.toRadians(anguloRotacao);
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		double x0 = 0.5 * (largura - 1); // Ponto para rotação,
		double y0 = 0.5 * (altura - 1); // no centro geométrico da imagem

		// Inicializa uma nova BufferedImage com as configurações
		BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < largura; x++) {
			for (int y = 0; y < altura; y++) {
				double a = x - x0;
				double b = y - y0;
				int xx = (int) (+a * cos - b * sin + x0);
				int yy = (int) (+a * sin + b * cos + y0);

				// Gera o pixel(x, y) da mesma cor que o pixel (xx, yy), se não
				// for borda
				if (xx >= 0 && xx < largura && yy >= 0 && yy < altura)
					novaImagem.setRGB(x, y, img.getRGB(xx, yy));
			}
		}

		return novaImagem;
	}
}
