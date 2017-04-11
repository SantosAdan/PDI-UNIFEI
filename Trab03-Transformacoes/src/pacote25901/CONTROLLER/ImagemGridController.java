package pacote25901.CONTROLLER;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import pacote25901.VIEW.*;

public class ImagemGridController {

	private ImagemGrid grid;
	private BufferedImage imagem;
	private int linhas, colunas;

	public ImagemGridController(int nLin, int nCol, Boolean abrir, BufferedImage img) {
		this.linhas = nLin;
		this.colunas = nCol;

		// Caso esteja abrindo a imagem, setamos o tamanho do grid navemente
		if (abrir && abrirImagem(img) == 0) {
			this.linhas = imagem.getHeight();
			this.colunas = imagem.getWidth();
		}

		grid = new ImagemGrid(nLin, nCol, abrir, imagem);
	}

	/**
	 * Método que gera um grid de acordo com os parâmetros.
	 * 
	 * @param abrir caso deseje abrir uma imagem
	 * @return gird gerado
	 */
	public JPanel criarGrid() {
		return grid.gerarGrid();
	}

	/**
	 * Seta a cor para o grid.
	 * 
	 * @param cor selecionada
	 */
	public void setColor(Color cor) {
		grid.setColor(cor);
	}

	/**
	 * Gera uma BufferedImage com os dados do grid.
	 * 
	 * @return imagem do grid
	 */
	private BufferedImage img() {
		BufferedImage img = new BufferedImage(colunas, linhas, BufferedImage.TYPE_INT_RGB);
		int[] pixels = img.getRGB(0, 0, colunas, linhas, null, 0, colunas);

		// Percorre o JPanel salvando cada célula como um pixel
		for (int col = 0; col < colunas; col++)
			for (int lin = 0; lin < linhas; lin++)
				pixels[colunas * lin + col] = grid.getCelula(lin, col).getBackground().getRGB();

		// Gera uma imagem RGB
		img.setRGB(0, 0, colunas, linhas, pixels, 0, colunas);

		return img;
	}

	/**
	 * Salva os pixels como imagem unica.
	 *
	 * @param nome da imagem
	 * @param extensao da imagem
	 * @param tipo (IMAGEM ou TEMPLATE)
	 */
	private void salvaPixels(String nome, String extensao, String tipo) {
		try {
			ImageIO.write(img(), extensao, new File(nome + " [" + tipo + "]" + "." + extensao));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para navegar pela estrutura de diretórios e carregar uma imagem.
	 * 
	 */
	private int abrirImagem(BufferedImage img) {
		String nomeArqLido = null;

		// Esse trecho só é ativado quando é feita a convolução
		if (img != null) {
			imagem = img;
			return 0;
		}

		// Localizando o arquivo
		JFileChooser arquivo = new JFileChooser();
		File diretorio = new File("..\\");
		arquivo.setCurrentDirectory(diretorio);
		arquivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int saida = arquivo.showOpenDialog(arquivo);

		// Fazendo a leitura do arquivo
		if (saida == JFileChooser.APPROVE_OPTION) {
			File nomeArq = arquivo.getSelectedFile();
			nomeArqLido = nomeArq.toString();
		} else if (saida == JFileChooser.CANCEL_OPTION)
			return 1;

		// Aloca a Imagem carregada
		try {
			imagem = ImageIO.read(new File(nomeArqLido));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Selecina a caminho para onde deverá ser salva a nova imagem.
	 * 
	 * @param tipo pode ser IMAGEM ou TEMPLATE
	 */
	public void salvarImagem(String tipo) {
		File fileToSave = null;

		JFileChooser fileChooser = new JFileChooser();
		int userSelection = fileChooser.showSaveDialog(grid);

		if (userSelection == JFileChooser.APPROVE_OPTION)
			fileToSave = fileChooser.getSelectedFile();
		else if (userSelection == JFileChooser.CANCEL_OPTION)
			return;

		salvaPixels(fileToSave.toString(), "png", tipo);
	}
	
	public BufferedImage getImg(){
		return img();
	}

}
