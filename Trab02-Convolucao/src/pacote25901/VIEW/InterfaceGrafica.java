package pacote25901.VIEW;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import pacote25901.CONTROLLER.MainController;

public class InterfaceGrafica {

	private JFrame   baseFrame;
	private JPanel   basePanel;
	private JPanel   outputPanel;

	private JButton  btEnd, btLimpar, btSalva, btCarrega, btInstrucoes;
	private JButton  btColorir, btColorirTemplate, btTrocaCor, btConvoluir;
	
	private Graphics desenho;
		
	
	//*******************************************************************************************
	public InterfaceGrafica(MainController ctrlApp)
	{
		JPanel  buttonPanel;
		JPanel  titlePanel;
		
		// LAYOUT
		baseFrame = new JFrame();
		baseFrame.setLayout(new BoxLayout(baseFrame.getContentPane(), BoxLayout.Y_AXIS));

		baseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // FITS PANEL TO THE ACTUAL MONITOR SIZE
		baseFrame.setUndecorated(true); // TURN OFF ALL THE PANEL BORDERS

		basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());

		// TITLE PANEL
		titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(0, 30));

		// OUTPUT PANEL
		outputPanel = new JPanel();
		outputPanel.setLayout(new BorderLayout());
		outputPanel.setBackground(Color.DARK_GRAY);

		// BUTTON PANEL
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(0, 50));

		// PANEL TITLE
		JLabel titulo;
		titulo = new JLabel("TRABALHO 2 - Convolu��o | Adan Santos - 25901");
		titulo.setForeground(Color.black);
		titulo.setFont(new Font("Dialog", Font.BOLD, 20));
		titlePanel.add(titulo);

		// Bot�o para exibir instru��es do app
		btInstrucoes =  addAButton("Instru��es", "btInstrucoes", buttonPanel);
		btInstrucoes.addActionListener((ActionListener) ctrlApp);
		
		//Bot�o para colorir
		btColorir = addAButton("Colorir Imagem", "btColorir", buttonPanel);
		btColorir.addActionListener((ActionListener) ctrlApp);
		
		//Bot�o para colorir template
		btColorirTemplate = addAButton("Colorir Template", "btColorirTemplate", buttonPanel);
		btColorirTemplate.addActionListener((ActionListener) ctrlApp);
		
		//Bot�o para trocar a cor
		btTrocaCor = addAButton("Trocar Cor", "btTrocaCor", buttonPanel);
		btTrocaCor.addActionListener((ActionListener) ctrlApp);
		
		//Bot�o para convolu��o
		btConvoluir = addAButton("Realizar CONVOLU��O", "btConvoluir", buttonPanel);
		btConvoluir.addActionListener((ActionListener) ctrlApp);
		
		//Bot�o para salvar arquivo
		btSalva = addAButton("Salvar Imagem", "btSalva", buttonPanel);
		btSalva.addActionListener((ActionListener) ctrlApp);
		
		//Bot�o para carregar do arquivo
		btCarrega = addAButton("Carregar Imagem", "btCarrega", buttonPanel);
		btCarrega.addActionListener((ActionListener) ctrlApp);
		
		//Bot�o para limpar a aplicacao
		btLimpar = addAButton("Limpar", "btLimpar", buttonPanel);
		btLimpar.addActionListener((ActionListener) ctrlApp);
		
		//Bot�o para finalizar a aplica��o
		btEnd = addAButton("Fechar", "btEnd", buttonPanel);
		btEnd.addActionListener((ActionListener) ctrlApp);

		// MOUSE LISTENERS
		outputPanel.addMouseListener((MouseListener) ctrlApp);
		outputPanel.addMouseMotionListener((MouseMotionListener) ctrlApp);

		// VISIBLE PANELS
		baseFrame.add(basePanel);

		basePanel.add(titlePanel, BorderLayout.PAGE_START);
		basePanel.add(outputPanel, BorderLayout.CENTER);
		basePanel.add(buttonPanel, BorderLayout.PAGE_END);

		baseFrame.setVisible(true);
		
		exibeInstrucoes();
	}

	//*******************************************************************************************
	// METODO UTILIZADO PARA ADICIONAR UM BOTAO A UM CONTAINER DO PROGRAMA

	private JButton addAButton( String textoBotao, String textoControle, Container container) 
	{
		JButton botao;

		botao = new JButton( textoBotao );
		botao.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(botao);

		botao.setActionCommand( textoControle );

		return ( botao );
	}

	public void showPanel() 
	{
		basePanel.setVisible(true);
	}


	//*******************************************************************************************
	public Graphics iniciarGraphics()
	{

		desenho = outputPanel.getGraphics();
		
		return ( desenho );
	}
	
	// Limpa o painel de desenho
	public void limpaOutputPanel() {
		outputPanel.repaint();
	}

	//*******************************************************************************************
	public void exibeInstrucoes() {
		String mensagem = "1- Clique em um ponto da tela e arraste o mouse at� outro ponto para definir o tamanho da IMAGEM\n";
		mensagem += "2- Clique em um ponto da tela e arraste o mouse at� outro ponto para definir o tamanho do TEMPLATE/M�SCARA\n";
		mensagem += "3- Clique no bot�o \"Colorir Imagem\" e ent�o clique nos quadrados para definir a cor do pixel\n";
		mensagem += "4- Para selecionar uma cor diferente pressione o bot�o \"Trocar Cor\"\n";
		mensagem += "5- Clique no bot�o \"Colorir Template\" e ent�o clique nos quadrados para definir a cor do pixel\n";
		mensagem += "6- Clique no bot�o \"Convoluir\" para realizar a convolu��o";
		
		JOptionPane.showMessageDialog(null, mensagem, "Instru��es do Aplicativo", JOptionPane.INFORMATION_MESSAGE);
	}

}
