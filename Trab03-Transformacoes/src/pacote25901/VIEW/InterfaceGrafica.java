package pacote25901.VIEW;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import pacote25901.CONTROLLER.*;

public class InterfaceGrafica extends JFrame {
	public JButton btEsquerda, btDireita, btEspelhar, btFrente, btTras, btCima, btBaixo, btTrocaCor, btCarrega, btSalva, btZoomIn, btZoomOut, btGeraGrid, btFechar;
	private JPanel basePanel, botoesTransformacaoTop, botoesTransformacaoBottom, botoesImagem, input, output;
	public JPanel inputPanel, outputPanel;
	private Border limite;
	private TitledBorder entrada, saida;
	private JLabel linhas, colunas;
	public JLabel cor;

	public InterfaceGrafica(MainController appControl) {

		this.setTitle("Trabalho 3 - Transformações - Adan Santos - 25901");
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
		
		inputPanel = new JPanel(new BorderLayout());
		outputPanel = new JPanel(new BorderLayout());

		limite = BorderFactory.createLineBorder(Color.gray, 0);
		
		entrada = BorderFactory.createTitledBorder(limite, "ENTRADA");
		entrada.setTitleJustification(TitledBorder.CENTER);
		entrada.setTitleFont(new Font("Times New Roman", Font.BOLD, 18));
		
		saida = BorderFactory.createTitledBorder(limite, "SAÍDA");
		saida.setTitleJustification(TitledBorder.CENTER);
		saida.setTitleFont(new Font("Times New Roman", Font.BOLD, 18));
		
		input.setBorder(entrada);
		input.add(inputPanel, BorderLayout.CENTER);
		
		output.setBorder(saida);
		output.add(outputPanel, BorderLayout.CENTER);
		
		basePanel.add(input);
		basePanel.add(output);

		add(basePanel);
	}

	private void botoesTransformacoes(MainController appControl) {

		botoesTransformacaoTop = new JPanel(new GridBagLayout());
		botoesTransformacaoBottom = new JPanel(new GridBagLayout());
		
		//botao para mover a imagem para cima
		btCima = new JButton("Deslocar Cima");
		btCima.setActionCommand("Cima");
		btCima.setPreferredSize(new Dimension(120, 30));
		btCima.setEnabled(false);
		btCima.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacaoTop, btCima, 1, 0, 1, 1, GridBagConstraints.NORTH);
		
		//botao para mover a imagem para baixo
		btBaixo = new JButton("Deslocar Baixo");
		btBaixo.setActionCommand("Baixo");
		btBaixo.setPreferredSize(new Dimension(120, 30));
		btBaixo.setEnabled(false);
		btBaixo.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacaoTop, btBaixo, 2, 0, 1, 1, GridBagConstraints.NORTH);
		
		//Botao Zoom In
		btZoomIn = new JButton("Aumentar");
		btZoomIn.setActionCommand("ZoomIn");
		btZoomIn.setPreferredSize(new Dimension(120, 30));
		btZoomIn.setEnabled(false);
		btZoomIn.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacaoBottom, btZoomIn, 1, 0, 1, 1, GridBagConstraints.NORTH);
		
		//Botao Zoom Out
		btZoomOut = new JButton("Diminuir");
		btZoomOut.setActionCommand("ZoomOut");
		btZoomOut.setPreferredSize(new Dimension(120, 30));
		btZoomOut.setEnabled(false);
		btZoomOut.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacaoBottom, btZoomOut, 2, 0, 1, 1, GridBagConstraints.NORTH);
		
		//Botao para Rotacionar a imagem para esquerda
		btEsquerda = new JButton("Rotacao Esquerda");
		btEsquerda.setActionCommand("Esquerda");
		btEsquerda.setPreferredSize(new Dimension(120, 30));
		btEsquerda.setEnabled(false);
		btEsquerda.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacaoBottom, btEsquerda, 3, 0, 1, 1, GridBagConstraints.NORTH);
		
		//Botao para Rotacaionar a imagem para direita
		btDireita = new JButton("Rotacao Direita");
		btDireita.setActionCommand("Direita");
		btDireita.setPreferredSize(new Dimension(120, 30));
		btDireita.setEnabled(false);
		btDireita.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacaoBottom, btDireita, 4, 0, 1, 1, GridBagConstraints.NORTH);
		
		//Botao para mover a imagem para frente
		btFrente = new JButton("Deslocar Direita");
		btFrente.setActionCommand("Frente");
		btFrente.setPreferredSize(new Dimension(120, 30));
		btFrente.setEnabled(false);
		btFrente.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacaoTop, btFrente, 3, 0, 1, 1, GridBagConstraints.NORTH);
		
		//Botao para mover a imagem para tras
		btTras = new JButton("Deslocar Esquerda");
		btTras.setActionCommand("Tras");
		btTras.setPreferredSize(new Dimension(120, 30));
		btTras.setEnabled(false);
		btTras.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacaoTop, btTras, 4, 0, 1, 1, GridBagConstraints.NORTH);
				
		//Botao para espelhar a imagem
		btEspelhar = new JButton("Espelhar");
		btEspelhar.setActionCommand("Espelhar");
		btEspelhar.setPreferredSize(new Dimension(120, 30));
		btEspelhar.setEnabled(false);
		btEspelhar.addActionListener(appControl);
		adicionarBotoesGrid(botoesTransformacaoBottom, btEspelhar, 5, 0, 1, 1, GridBagConstraints.NORTH);
		
		output.add(botoesTransformacaoTop, BorderLayout.PAGE_START);
		output.add(botoesTransformacaoBottom, BorderLayout.PAGE_END);
	}

	private void botoesImagem(MainController appControl) {

		// Painel da barra de ferramentas
		botoesImagem = new JPanel(new GridBagLayout());

		//Botao para carregar a imagem
		btCarrega = new JButton("Carregar");
		btCarrega.setActionCommand("Carregar");
		btCarrega.setPreferredSize(new Dimension(120, 30));
		btCarrega.addActionListener(appControl);
		adicionarBotoesGrid(botoesImagem, btCarrega, 0, 2, 1, 1, GridBagConstraints.NORTH);
		
		//Botao para salvar a imagem
		btSalva = new JButton("Salvar");
		btSalva.setActionCommand("Salvar");
		btSalva.setPreferredSize(new Dimension(120, 30));
		btSalva.addActionListener(appControl);
		adicionarBotoesGrid(botoesImagem, btSalva, 0, 1, 1, 1, GridBagConstraints.NORTH);
		
		//Botao para trocar a cor
		btTrocaCor = new JButton("Trocar Cor");
		btTrocaCor.setActionCommand("TrocarCor");
		btTrocaCor.setPreferredSize(new Dimension(120, 30));
		btTrocaCor.addActionListener(appControl);
		adicionarBotoesGrid(botoesImagem, btTrocaCor, 0, 3, 1, 1, GridBagConstraints.NORTH);
		
		//Botao para gerar o grid da imagem
		btGeraGrid = new JButton("Gerar Imagem");
		btGeraGrid.setActionCommand("GerarGrid");
		btGeraGrid.setPreferredSize(new Dimension(120, 30));
		btGeraGrid.addActionListener(appControl);
		adicionarBotoesGrid(botoesImagem, btGeraGrid, 0, 0, 1, 1, GridBagConstraints.NORTH);
		
		//Label para mostrar a cor selecionada
		cor = new JLabel();
		cor.setPreferredSize(new Dimension(120, 30));
		cor.setOpaque(true);
		cor.setBackground(Color.BLUE);
		adicionarBotoesGrid(botoesImagem, cor, 0, 4, 1, 1, GridBagConstraints.NORTH);
		
		btFechar = new JButton("Fechar");
		btFechar.setActionCommand("Fechar");
		btFechar.setPreferredSize(new Dimension(120, 30));
		btFechar.addActionListener(appControl);
		adicionarBotoesGrid(botoesImagem, btFechar, 0, 5, 1, 1, GridBagConstraints.NORTH);

		input.add(botoesImagem, BorderLayout.WEST);
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

	public Color selecionarCor(Color corPadrao) {
		Color cor = JColorChooser.showDialog(null, "Selecionar Cor", corPadrao);
		return cor;
	}

	public void setLabelColor(JLabel label, Color cor) {
		label.setBackground(cor);
	}

	public void addGrid(JPanel container, JPanel panel) {
		container.add(panel);
	}

	public Color getInitialColor(JLabel label) {
		return label.getBackground();
	}

	public void aviso(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Alerta!", JOptionPane.WARNING_MESSAGE);
	}

}
