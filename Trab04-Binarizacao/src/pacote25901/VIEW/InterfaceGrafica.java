package pacote25901.VIEW;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import pacote25901.CONTROLLER.*;

public class InterfaceGrafica extends JFrame {
	public JButton btEsquerda, btDireita, btEspelhar, btFrente, btTras, btCima, btBaixo, btCarrega, btSalva, btZoomIn, btZoomOut, btFechar, btLimpar;
	private JPanel basePanel, botoesTransformacao, botoesImagem, input, output, sliderPanel, inputBtn, outputBtn;
	public JPanel inputPanel, outputPanel;
	private Border limite;
	private TitledBorder entrada, saida;
	public JSlider sliderLinha;

	public InterfaceGrafica(MainController appControl) {

		this.setTitle("Trabalho 4 - Binarizacao - Adan Santos - 25901");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setSize(new Dimension(1024, 768));
		this.setLayout(new BorderLayout());

		layoutPanels();

		botoesImagem(appControl);
		botoesTransformacoes(appControl);

		this.pack();
		this.setVisible(true);
		this.setResizable(true);
	}

	private void layoutPanels() {
		basePanel = new JPanel(new GridLayout(1, 2, 10, 10));
		
		input = new JPanel(new BorderLayout());
		output = new JPanel(new BorderLayout());
		sliderPanel = new JPanel(new BorderLayout());
		
		inputPanel = new JPanel(new BorderLayout());
		outputPanel = new JPanel(new BorderLayout());
		
		inputBtn = new JPanel(new BorderLayout());
		outputBtn = new JPanel(new BorderLayout());

		limite = BorderFactory.createLineBorder(Color.gray, 0);
		
		entrada = BorderFactory.createTitledBorder(limite, "ENTRADA");
		entrada.setTitleJustification(TitledBorder.CENTER);
		entrada.setTitleFont(new Font("Times New Roman", Font.BOLD, 18));
		
		saida = BorderFactory.createTitledBorder(limite, "SAIDA");
		saida.setTitleJustification(TitledBorder.CENTER);
		saida.setTitleFont(new Font("Times New Roman", Font.BOLD, 18));
		
		input.setBorder(entrada);
		input.add(inputPanel, BorderLayout.CENTER);
		
		output.setBorder(saida);
		output.add(outputPanel, BorderLayout.CENTER);
		
		sliderLinha = new JSlider(JSlider.HORIZONTAL, 0, 255, 1);
		sliderLinha.setEnabled(false);
		sliderLinha.setMajorTickSpacing(15);
		sliderLinha.setMinorTickSpacing(5);
		sliderLinha.setPaintTicks(true);
		sliderLinha.setPaintLabels(true);
		
		sliderPanel.add(sliderLinha, BorderLayout.CENTER);
		
		basePanel.add(input);
		basePanel.add(output);

		add(basePanel);
		add(inputBtn, BorderLayout.WEST);
		add(outputBtn, BorderLayout.EAST);
		add(sliderPanel, BorderLayout.PAGE_END);
	}

	private void botoesTransformacoes(MainController appControl) {

		botoesTransformacao = new JPanel(new GridBagLayout());
		
		//botao para mover a imagem para cima
		btCima = new JButton("Cima");
		btCima.setActionCommand("Cima");
		btCima.setPreferredSize(new Dimension(100, 30));
		btCima.setEnabled(false);
		btCima.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacao, btCima, 0, 0, 1, 1, GridBagConstraints.NORTH);
		
		//botao para mover a imagem para baixo
		btBaixo = new JButton("Baixo");
		btBaixo.setActionCommand("Baixo");
		btBaixo.setPreferredSize(new Dimension(100, 30));
		btBaixo.setEnabled(false);
		btBaixo.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacao, btBaixo, 0, 1, 1, 1, GridBagConstraints.NORTH);
		
		//Botao Zoom In
		btZoomIn = new JButton("Aumentar");
		btZoomIn.setActionCommand("ZoomIn");
		btZoomIn.setPreferredSize(new Dimension(100, 30));
		btZoomIn.setEnabled(false);
		btZoomIn.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacao, btZoomIn, 0, 2, 1, 1, GridBagConstraints.NORTH);
		
		//Botao Zoom Out
		btZoomOut = new JButton("Diminuir");
		btZoomOut.setActionCommand("ZoomOut");
		btZoomOut.setPreferredSize(new Dimension(100, 30));
		btZoomOut.setEnabled(false);
		btZoomOut.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacao, btZoomOut, 0, 3, 1, 1, GridBagConstraints.NORTH);
		
		//Botao para Rotacionar a imagem para esquerda
		btEsquerda = new JButton("Rotacao <");
		btEsquerda.setActionCommand("Esquerda");
		btEsquerda.setPreferredSize(new Dimension(100, 30));
		btEsquerda.setEnabled(false);
		btEsquerda.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacao, btEsquerda, 0, 4, 1, 1, GridBagConstraints.NORTH);
		
		//Botao para Rotacaionar a imagem para direita
		btDireita = new JButton("Rotacao >");
		btDireita.setActionCommand("Direita");
		btDireita.setPreferredSize(new Dimension(100, 30));
		btDireita.setEnabled(false);
		btDireita.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacao, btDireita, 0, 5, 1, 1, GridBagConstraints.NORTH);
		
		//Botao para mover a imagem para frente
		btFrente = new JButton("Deslocar >");
		btFrente.setActionCommand("Frente");
		btFrente.setPreferredSize(new Dimension(100, 30));
		btFrente.setEnabled(false);
		btFrente.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacao, btFrente, 0, 6, 1, 1, GridBagConstraints.NORTH);
		
		//Botao para mover a imagem para tras
		btTras = new JButton("Deslocar <");
		btTras.setActionCommand("Tras");
		btTras.setPreferredSize(new Dimension(100, 30));
		btTras.setEnabled(false);
		btTras.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacao, btTras, 0, 7, 1, 1, GridBagConstraints.NORTH);
				
		//Botao para espelhar a imagem
		btEspelhar = new JButton("Espelhar");
		btEspelhar.setActionCommand("Espelhar");
		btEspelhar.setPreferredSize(new Dimension(100, 30));
		btEspelhar.setEnabled(false);
		btEspelhar.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacao, btEspelhar, 0, 8, 1, 1, GridBagConstraints.NORTH);
		
		sliderLinha.addChangeListener(appControl);
		
		outputBtn.add(botoesTransformacao, BorderLayout.EAST);
	}

	private void botoesImagem(MainController appControl) {

		// Painel da barra de ferramentas
		botoesImagem = new JPanel(new GridBagLayout());

		//Botao para carregar a imagem
		btCarrega = new JButton("Abrir");
		btCarrega.setActionCommand("Carregar");
		btCarrega.setPreferredSize(new Dimension(80, 30));
		btCarrega.addActionListener(appControl);
		adicionarBotoesGrid(botoesImagem, btCarrega, 0, 1, 1, 1, GridBagConstraints.NORTH);
		
		/*Botao para salvar a imagem
		btSalva = new JButton("Salvar");
		btSalva.setActionCommand("Salvar");
		btSalva.setPreferredSize(new Dimension(80, 30));
		btSalva.addActionListener(appControl);
		adicionarBotoesGrid(botoesImagem, btSalva, 0, 2, 1, 1, GridBagConstraints.NORTH);*/
		
		btLimpar = new JButton("Limpar");
		btLimpar.setActionCommand("Limpar");
		btLimpar.setPreferredSize(new Dimension(80, 30));
		btLimpar.addActionListener(appControl);
		adicionarBotoesGrid(botoesImagem, btLimpar, 0, 3, 1, 1, GridBagConstraints.NORTH);
		
		btFechar = new JButton("Fechar");
		btFechar.setActionCommand("Fechar");
		btFechar.setPreferredSize(new Dimension(80, 30));
		btFechar.addActionListener(appControl);
		adicionarBotoesGrid(botoesImagem, btFechar, 0, 4, 1, 1, GridBagConstraints.NORTH);

		inputBtn.add(botoesImagem, BorderLayout.WEST);
	}

	public void ativaBotoesImagem(boolean habilitar) {
				
		btEsquerda.setEnabled(habilitar);
		btDireita.setEnabled(habilitar);
		
		btZoomIn.setEnabled(habilitar);
		btZoomOut.setEnabled(habilitar);
		
		btFrente.setEnabled(habilitar);
		btTras.setEnabled(habilitar);
		
		btCima.setEnabled(habilitar);
		btBaixo.setEnabled(habilitar);
		
		btEspelhar.setEnabled(habilitar);		
		sliderLinha.setEnabled(habilitar);
	}

	private void adicionarBotoesGrid(JPanel panel, JComponent comp, int X, int Y, int largura, int altura, int localizacao) {

		GridBagConstraints gridConstraints = new GridBagConstraints();

		gridConstraints.gridx = X;
		gridConstraints.gridy = Y;
		gridConstraints.gridwidth = largura;
		gridConstraints.gridheight = altura;
		gridConstraints.weightx = 0;
		gridConstraints.weighty = 0;
		gridConstraints.insets = new Insets(4, 4, 4, 4);
		gridConstraints.anchor = localizacao;

		panel.add(comp, gridConstraints);
	}
	
	public void paintComponent(JPanel panel, BufferedImage img) {
		int x = (panel.getWidth() - img.getWidth()) / 2;
		int y = (panel.getHeight() - img.getHeight()) / 2;
		Graphics g = panel.getGraphics();

		panel.paintComponents(g);
		//clearTela(panel);
		g.drawImage(img, x, y, this);
	}
	
	public void showImage(boolean panel, BufferedImage img) {
		if (img != null) {
			if (!panel) {
				paintComponent(input, img);
			} else {
				paintComponent(output, img);
			}

		}
	}
	
	// Limpa o painel de desenho
	public void clearScreen() {
		output.repaint();
		input.repaint();
	}

}
