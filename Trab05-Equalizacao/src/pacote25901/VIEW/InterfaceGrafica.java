package pacote25901.VIEW;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import pacote25901.CONTROLLER.*;

public class InterfaceGrafica extends JFrame {
	private static final long serialVersionUID = 1L;
	public JButton btCarrega, btEqualizar, btFechar, btLimpar;
	public JPanel basePanel, botoesTransformacao, botoesImagem, input, output, inputBtn;
	public JPanel inputPanel, outputPanel, histogramaPanel, histogramaOutputPanel, inBottomPanel, outBottomPanel;
	private Border limite;
	private TitledBorder entrada, saida;
	public JSlider sliderLinha;

	public InterfaceGrafica(MainController appControl) {

		this.setTitle("Trabalho 5 - Equalizacao - Adan Santos - 25901");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());

		layoutPanels();

		botoesImagem(appControl);
		histogramaOutputPanel(appControl);

		this.pack();
		this.setVisible(true);
		this.setResizable(true);
	}

	private void layoutPanels() {
		basePanel = new JPanel(new GridLayout(1, 2, 10, 10));
		
		input = new JPanel(new BorderLayout());
		output = new JPanel(new BorderLayout());
		
		inputPanel = new JPanel(new BorderLayout());
		outputPanel = new JPanel(new BorderLayout());
		inBottomPanel = new JPanel(new BorderLayout());
		outBottomPanel = new JPanel(new BorderLayout());
		
		inputBtn = new JPanel(new BorderLayout());

		limite = BorderFactory.createLineBorder(Color.gray, 0);
		
		entrada = BorderFactory.createTitledBorder(limite, "ENTRADA");
		entrada.setTitleJustification(TitledBorder.CENTER);
		entrada.setTitleFont(new Font("Times New Roman", Font.BOLD, 18));
		
		saida = BorderFactory.createTitledBorder(limite, "SAIDA");
		saida.setTitleJustification(TitledBorder.CENTER);
		saida.setTitleFont(new Font("Times New Roman", Font.BOLD, 18));
		
		input.setBorder(entrada);
		input.add(inputPanel, BorderLayout.CENTER);
		input.add(inBottomPanel, BorderLayout.SOUTH);
		
		output.setBorder(saida);
		output.add(outputPanel, BorderLayout.CENTER);
		output.add(outBottomPanel, BorderLayout.SOUTH);
		
		basePanel.add(input);
		basePanel.add(output);

		add(basePanel);
		add(inputBtn, BorderLayout.NORTH);
		setBackground(Color.LIGHT_GRAY);
	}

	private void histogramaOutputPanel(MainController appControl) {
		histogramaOutputPanel = new JPanel(new BorderLayout());
		histogramaOutputPanel.setEnabled(true);
		
		histogramaOutputPanel.setPreferredSize(new Dimension(outBottomPanel.getWidth(), 100));
		outBottomPanel.add(histogramaOutputPanel, BorderLayout.SOUTH);
	}

	private void botoesImagem(MainController appControl) {

		// Painel da barra de ferramentas
		botoesImagem = new JPanel(new GridBagLayout());

		//Botao para carregar a imagem
		btCarrega = new JButton("Abrir");
		btCarrega.setActionCommand("Carregar");
		btCarrega.setPreferredSize(new Dimension(80, 30));
		btCarrega.addActionListener(appControl);
		adicionarBotoesGrid(botoesImagem, btCarrega, 0, 0, 1, 1, GridBagConstraints.CENTER);
		
		btEqualizar = new JButton("Equalizar");
		btEqualizar.setActionCommand("Equalizar");
		btEqualizar.setPreferredSize(new Dimension(100, 30));
		btEqualizar.setEnabled(false);
		btEqualizar.addActionListener(appControl);
		adicionarBotoesGrid(botoesImagem, btEqualizar, 1, 0, 1, 1, GridBagConstraints.CENTER);
		
		btLimpar = new JButton("Limpar");
		btLimpar.setActionCommand("Limpar");
		btLimpar.setPreferredSize(new Dimension(80, 30));
		btLimpar.addActionListener(appControl);
		adicionarBotoesGrid(botoesImagem, btLimpar, 2, 0, 1, 1, GridBagConstraints.CENTER);
		
		btFechar = new JButton("Fechar");
		btFechar.setActionCommand("Fechar");
		btFechar.setPreferredSize(new Dimension(80, 30));
		btFechar.addActionListener(appControl);
		adicionarBotoesGrid(botoesImagem, btFechar, 3, 0, 1, 1, GridBagConstraints.CENTER);

		inputBtn.add(botoesImagem, BorderLayout.CENTER);
		
		histogramaPanel = new JPanel(new BorderLayout());
		histogramaPanel.setEnabled(true);
		
		histogramaPanel.setPreferredSize(new Dimension(inBottomPanel.getWidth(), 100));
		inBottomPanel.add(histogramaPanel, BorderLayout.SOUTH);
	}

	public void ativaBotoesImagem(boolean habilitar) {	
		btEqualizar.setEnabled(habilitar);
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
				paintComponent(inputPanel, img);
			} else {
				paintComponent(outputPanel, img);
			}

		}
	}
	
	// Limpa o painel de desenho
	public void clearScreen() {
		output.repaint();
		input.repaint();
	}

}
