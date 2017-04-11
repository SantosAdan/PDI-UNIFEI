package pacote25901.VIEW;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;

public class ImagemGrid extends JPanel {

	private Color cor;
	private JPanel[][] grid;
	private int linhas, colunas;
	private BufferedImage imagem;
	private Boolean pressed;
	private Boolean abrir;

	public ImagemGrid(int nLin, int nCol, Boolean abrir, BufferedImage imagem) {
		this.linhas = nLin;
		this.colunas = nCol;
		this.abrir = abrir;
		this.imagem = imagem;
		this.pressed = false;
	}

	/**
	 * Devolve uma célula específica do grid.
	 * 
	 * @param i linha
	 * @param j coluna
	 * @return celula
	 */
	public JPanel getCelula(int i, int j) {
		return this.grid[i][j];
	}

	/**
	 * Método que gera um grid de acordo com os parâmetros passados.
	 * 
	 * @param abrir caso deseje abrir uma imagem
	 * @return grid gerado
	 */
	public JPanel gerarGrid() {

		// Caso esteja abrindo a imagem, o grid recebe o tamanho da imagem
		if (abrir && imagem != null) {
			this.linhas = imagem.getHeight();
			this.colunas = imagem.getWidth();
		}

		// Novo grid e suas propriedades
		grid = new JPanel[linhas][colunas];
		setLayout(new GridBagLayout());

		// Propriedade do gerenciado de layout
		GridBagConstraints gbc = new GridBagConstraints();

		// Então o grid é gerado
		for (int lin = 0; lin < linhas; lin++) {
			for (int col = 0; col < colunas; col++) {
				gbc.gridx = col;
				gbc.gridy = lin;

				// A cada iteração uma nova célula é gerada e armazenada no grid
				JPanel celula = desenhaCelula();
				grid[lin][col] = celula;

				// Gerando as bordas do grid
				Border border = null;

				if (lin < linhas - 1) {
					if (col < colunas - 1)
						border = new MatteBorder(1, 1, 0, 0, new Color(33, 33, 33));
					else
						border = new MatteBorder(1, 1, 0, 1, new Color(33, 33, 33));
				} else {
					if (col < colunas - 1)
						border = new MatteBorder(1, 1, 1, 0, new Color(33, 33, 33));
					else
						border = new MatteBorder(1, 1, 1, 1, new Color(33, 33, 33));
				}

				// Adicionando as bordas e a célula ao painel
				celula.setBorder(border);
				add(celula, gbc);
			}
		}

		// Caso esteja abrindo uma imagem setamos as cores das células para
		// as cores dos pixels da imagem.
		if (abrir) {
			for (int col = 0; col < colunas; col++) {
				for (int lin = 0; lin < linhas; lin++) {
					grid[lin][col].setBackground(new Color(imagem.getRGB(col, lin)));
				}
			}
		}

		return this;
	}

	/**
	 * Desenha uma nova célula (componente unitário do grid), e seta suas
	 * propriedades.
	 * 
	 * @return nova celula
	 */
	private JPanel desenhaCelula() {
		JPanel celula = new JPanel();

		celula.setPreferredSize(new Dimension(15, 15));
		celula.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pressed = true;
				celula.setBackground(cor);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				pressed = false;
				celula.setBackground(cor);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (pressed)
					celula.setBackground(cor);
			}

		});

		return celula;
	}

	/**
	 * Seta a cor para o grid.
	 * 
	 * @param cor selecionada
	 */
	public void setColor(Color cor) {
		this.cor = cor;
	}

}
