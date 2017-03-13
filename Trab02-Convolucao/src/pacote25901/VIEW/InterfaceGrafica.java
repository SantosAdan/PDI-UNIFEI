package pacote25901.VIEW;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import pacote25901.CONTROLLER.MainController;

public class InterfaceGrafica {

	private JFrame   baseFrame;
	private JPanel   basePanel;
	private JPanel   outputPanel;

	private JButton  btEnd, btLimpar, btSalva;
	private JButton  btColorir;
	
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
		outputPanel.setBackground(Color.lightGray);

		// BUTTON PANEL
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(0, 50));

		// PANEL TITLE
		JLabel titulo;
		titulo = new JLabel("TRABALHO 2 - Convolução");
		titulo.setForeground(Color.black);
		titulo.setFont(new Font("Dialog", Font.BOLD, 20));
		titlePanel.add(titulo);

		//Botão para desenhar com Bezier
		btColorir = addAButton("Colorir", "btColorir", buttonPanel);
		btColorir.addActionListener((ActionListener) ctrlApp);
		
		//Botão para salvar arquivo
		btSalva = addAButton("Salva", "btSalva", buttonPanel);
		btSalva.addActionListener((ActionListener) ctrlApp);
		
		//Botão para limpar a aplicacao
		btLimpar = addAButton("Limpar", "btLimpar", buttonPanel);
		btLimpar.addActionListener((ActionListener) ctrlApp);
		
		//Botão para finalizar a aplicação
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
		
//		JOptionPane.showMessageDialog(null,"Clique na tela uma vez para marcar o primeiro ponto e mais uma vez para marcar o segundo ponto.");
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

}
