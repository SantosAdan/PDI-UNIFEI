package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.*;
import pacote25901.VIEW.*;

public class MainController implements ActionListener, ChangeListener {

	private InterfaceGrafica appView;
	private ImagemGridController gridImg, gridImgTransformada;
	private Boolean imgSet;
	private Color imgLabelColor;
	private TransformacoesController transformacoes;
	private BufferedImage img;

	public MainController() {
		appView = new InterfaceGrafica(this);
		imgSet = false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Abre uma imagem previamente salva
		if (e.getActionCommand().equals("Carregar")) {
			transformacoes = new TransformacoesController();
			abrirImagem();
		}
		else if (e.getActionCommand().equals("Salvar")) {
			if (imgSet)
				gridImg.salvarImagem("IMAGEM");
			else// Emite aviso de imagem vazia
				appView.aviso("Primeiro faça uma imagem para depois salvar!");
		}
		else if (e.getActionCommand().equals("GerarGrid")) {
			JTextField linhas = new JTextField();
			JTextField colunas = new JTextField();
			Object[] message = {
			    "Linhas:", linhas,
			    "Colunas:", colunas
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Tamanho da Imagem", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				
				int numeroLinhas = Integer.parseInt(linhas.getText());
				int numeroColunas = Integer.parseInt(colunas.getText());
				appView.outputPanel.removeAll();
				appView.outputPanel.repaint();
				appView.outputPanel.revalidate();
				transformacoes = new TransformacoesController();
				imageGrid(numeroLinhas, numeroColunas);
			} 
		}
		else if (e.getActionCommand().equals("TrocarCor")) {
			imgLabelColor = appView.selecionarCor(appView.getInitialColor(appView.cor));
			appView.setLabelColor(appView.cor, imgLabelColor);
			gridImg.setColor(imgLabelColor);
		}
		else if (e.getActionCommand().equals("ZoomIn")) {
			iniciaTransformacao();
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.escalar(true));
		}
		else if (e.getActionCommand().equals("ZoomOut")) {
			iniciaTransformacao();
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.escalar(false));
		}
		else if (e.getActionCommand().equals("Direita")) {
			img = gridImg.getImg();
			transformacoes.setImg(img);
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.rotacionar(false));
		}
		else if (e.getActionCommand().equals("Esquerda")) {
			iniciaTransformacao();
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.rotacionar(true));
		}
		else if (e.getActionCommand().equals("Espelhar")) {
			iniciaTransformacao();
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.espelhar());
		}
		else if (e.getActionCommand().equals("Frente")) {
			iniciaTransformacao();
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.transladar(-1, 0));
		}
		else if (e.getActionCommand().equals("Tras")) {
			iniciaTransformacao();
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.transladar(1, 0));
		}
		else if (e.getActionCommand().equals("Cima")) {
			iniciaTransformacao();
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.transladar(0, 1));
		}
		else if (e.getActionCommand().equals("Baixo")) {
			iniciaTransformacao();
			imgTransformadaGrid(img.getHeight(), img.getWidth(), transformacoes.transladar(0, -1));
		}
		else if (e.getActionCommand().equals("Fechar")) {
			System.exit(0);
		}
	}

	private void imageGrid(int lin, int col) {
		appView.ativaBotoesImagem(true);
		gridImg = new ImagemGridController(lin, col, false, null);
		appView.inputPanel.removeAll();
		appView.inputPanel.repaint();
		gridImg.setColor(appView.getInitialColor(appView.cor));
		appView.inputPanel.revalidate();
		appView.addGrid(appView.inputPanel, gridImg.criarGrid());
		appView.setLabelColor(appView.cor, appView.getInitialColor(appView.cor));

		// Checar se a imagem existe ou nao
		if (lin > 0 && col > 0)
			imgSet = true;
		else
			imgSet = false;
	}

	/**
	 * Abre uma imagem.
	 */
	private void abrirImagem() {
		appView.ativaBotoesImagem(true);
		gridImg = new ImagemGridController(0, 0, true, null);
		appView.inputPanel.removeAll();
		appView.inputPanel.repaint();
		appView.addGrid(appView.inputPanel, gridImg.criarGrid());
		gridImg.setColor(Color.BLUE);
		appView.inputPanel.revalidate();
		imgSet = true;
	}

	private void imgTransformadaGrid(int lin, int col, BufferedImage img) {
		gridImgTransformada = new ImagemGridController(lin, col, true, img);
		appView.outputPanel.removeAll();
		appView.outputPanel.repaint();
		appView.outputPanel.revalidate();
		appView.addGrid(appView.outputPanel, gridImgTransformada.criarGrid());
	}

	private void iniciaTransformacao() {
		img = gridImg.getImg();
		transformacoes.setImg(img);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
