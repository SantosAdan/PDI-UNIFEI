package pacoteBase.VIEW;

import java.awt.*;
import java.io.File;


import javax.swing.*;
	import pacoteBase.CONTROL.ControlarAplicativo;

public class MontarPainelInicial  {

	private JFrame   baseFrame;
	private JPanel   basePanel;
	private JPanel   outputPanel, outputPanelEsq, outputPanelCen, outputPanelDir;
	private JPanel   controlePanelGabor;
	private JPanel   controlePanelGabor2;
	private JPanel   controlePanelOpMorfologica;
	private JPanel   controlePanelVisualImagens;

	private JPanel   controlePanelZoomIn;
	private JPanel   controlePanelZoomOut;

	private JButton  btZoom;
	private JButton  btGabor;
	private JButton  btSalva;
	private JButton  btReset;
	private JButton  btOperacao;
	private JButton btInverterCores;
	private JButton btReconhecer;


	private JRadioButton btOpMorfologica1; 
	private JRadioButton btOpMorfologica2;
	private JRadioButton btOpMorfologica3; 
	private ButtonGroup  btRdOpMorfologica;


	private JRadioButton  btGabor0;
	private JRadioButton  btGabor1;
	private JRadioButton  btGabor2;
	private JRadioButton  btGabor3;
	private JRadioButton  btGabor4;
	private ButtonGroup   btRdGabor;

	

	private JRadioButton  btGabor0Grau;
	private JRadioButton  btGabor45Grau;
	private JRadioButton  btGabor90Grau;
	private JRadioButton  btGabor135Grau;
	private JRadioButton  btGaborTodosAngulos;
	private ButtonGroup   btRdGabor2;
	
	
	private JRadioButton  btZoomInOff;
	private JRadioButton  btZoomIn25;
	private JRadioButton  btZoomIn50;
	private JRadioButton  btZoomIn75;
	private JRadioButton  btZoomIn100;
	private JRadioButton  btAcao25;
	private JRadioButton  btAcao26;
	private ButtonGroup   btRdZoom;
	

	private JRadioButton  btVisualNewImg;
	private JRadioButton  btVisualAllImg;
	private ButtonGroup   btRdVisualImg;

	
	private JRadioButton btZoomOut25; 
	private JRadioButton btZoomOut50;
	private JRadioButton btZoomOut75;
	private JRadioButton btZoomOut95;
	
	private JLabel btZoomTextLb;
	private JTextField btZoomText;

	private Graphics      desenhoCen;
	private Graphics      desenhoDir;

	
	private final Color COR_FUNDO_TITULO = Color.DARK_GRAY;
	private final Color COR_LETRA_TITULO = Color.white;
	private final Color COR_FUNDO_BORDA = Color.white;

	//*******************************************************************************************
	public MontarPainelInicial( ControlarAplicativo controlePrograma )
	{
		JPanel  buttonPanel;
		JPanel  titlePanel;
		JPanel  acao3Panel;
		JPanel  acao1Panel;
		JPanel  zoomInPanel;
		JPanel  visualImagensPanel;
		JPanel  zoomOutPanel;

		// LAYOUT
		baseFrame = new JFrame();
		baseFrame.setLayout( new BoxLayout( baseFrame.getContentPane(), BoxLayout.Y_AXIS) );

		baseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // FITS PANEL TO THE ACTUAL MONITOR SIZE
		baseFrame.setUndecorated(true);  // TURN OFF ALL THE PANEL BORDERS 

		basePanel = new JPanel();
		basePanel.setLayout( new BorderLayout() );

		// TITLE PANEL
		titlePanel = new JPanel();
		titlePanel.setPreferredSize( new Dimension ( 0, 50 ) );
		titlePanel.setBackground( COR_FUNDO_TITULO );

		// OUTPUT PANEL
		outputPanel = new JPanel();
		outputPanel.setLayout( new BorderLayout() );

		outputPanelEsq = new JPanel();
		outputPanelEsq.setPreferredSize( new Dimension ( 130, 0 ) );
		outputPanelEsq.setLayout( new BoxLayout (outputPanelEsq, BoxLayout.Y_AXIS));
		outputPanelEsq.setBackground( COR_FUNDO_BORDA );

		outputPanelCen = new JPanel();
		outputPanelCen.setBackground( new Color ( 220, 220, 210 ) );
		outputPanelCen.setLayout( new BorderLayout() );

		outputPanelDir = new JPanel();
		outputPanelDir.setBackground( new Color ( 210, 200, 200 ) );
		outputPanelDir.setPreferredSize( new Dimension ( 580, 0 ) );
		outputPanelDir.setLayout( new BorderLayout() );

		// BUTTON PANEL
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize( new Dimension ( 0, 50 ) );
		buttonPanel.setBackground( COR_FUNDO_BORDA );

		// PANEL TITLE
		JLabel titulo;
		titulo = new JLabel( "Reconhecimento de Placas de Automóveis");
		titulo.setForeground(COR_LETRA_TITULO);
		titulo.setFont(new Font("Dialog", Font.BOLD, 25));
		titlePanel.add(titulo);

		// ADDING BUTTONS
		addButtonToContainer ( "CARREGAR IMAGEM", "botaoImagem", buttonPanel, true, controlePrograma );
		btReset = addButtonToContainer ( "RESET", "botaoReset", buttonPanel, false, controlePrograma );
		btGabor = addButtonToContainer ( "FILTRO GABOR", "botaoAcao1", buttonPanel, false, controlePrograma );
		btZoom = addButtonToContainer ( "ZOOM", "botaoZoom", buttonPanel, false, controlePrograma );
		btOperacao = addButtonToContainer ( "OPERACAO MORFOLOGICA", "botaoOpMorfologica", buttonPanel, false, controlePrograma );
		btSalva = addButtonToContainer ( "SALVAR", "botaoSalva", buttonPanel, false, controlePrograma );
		btInverterCores = addButtonToContainer("INVERTER", "botaoInverter", buttonPanel, false, controlePrograma);
		btReconhecer = addButtonToContainer("RECONHECER", "botaoReconhecerPlaca", buttonPanel, false, controlePrograma);
		addButtonToContainer ( "SAIR", "botaoFim", buttonPanel, true, controlePrograma );

		btReconhecer.setVisible(false);
		btInverterCores.setVisible(false);
		
		// ADDING RADIO BUTTON PARA CONTROLE DA VISUALIZACAO DAS IMAGENS
		controlePanelVisualImagens = new JPanel();
		controlePanelVisualImagens.setBackground( COR_FUNDO_BORDA );
		controlePanelVisualImagens.setMaximumSize( new Dimension ( 130, 65 ) );
		
		outputPanelEsq.add( controlePanelVisualImagens );

		btVisualNewImg = new JRadioButton ( "Nova imagem", true );
		btVisualAllImg = new JRadioButton ( "Transicoes", false );

		btRdVisualImg = new ButtonGroup();
		btRdVisualImg.add(btVisualNewImg);
		btRdVisualImg.add(btVisualAllImg);

		btVisualNewImg.addActionListener(controlePrograma);
		btVisualAllImg.addActionListener(controlePrograma);
		
		btVisualNewImg.setActionCommand("newImage");
	    btVisualAllImg.setActionCommand("transitions");

		visualImagensPanel = new JPanel();
		visualImagensPanel.setPreferredSize( new Dimension ( 120, 60 ) );
		visualImagensPanel.setLayout(new GridLayout(2, 1));

		visualImagensPanel.add( btVisualNewImg );
		visualImagensPanel.add( btVisualAllImg );

		visualImagensPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Controle Imagem"));

		controlePanelVisualImagens.add(visualImagensPanel);
		controlePanelVisualImagens.setVisible(false);
		
		
		// ADDING RADIO BUTTON PARA CONTROLE DA OPERA��O MORFOLOGICA
		controlePanelOpMorfologica = new JPanel();
		controlePanelOpMorfologica.setBackground( COR_FUNDO_BORDA );
		controlePanelOpMorfologica.setMaximumSize( new Dimension ( 130, 65 ) );
		outputPanelEsq.add( controlePanelOpMorfologica );

		btOpMorfologica3 = new JRadioButton ( "OFF", false );
		btOpMorfologica1 = new JRadioButton ( "ON", true );
		btOpMorfologica2 = new JRadioButton ( "OFF", false );

		btRdOpMorfologica = new ButtonGroup();
		//btRdOpMorfologica.add(btOpMorfologica3);
		btRdOpMorfologica.add(btOpMorfologica1);
		btRdOpMorfologica.add(btOpMorfologica2);

		btOpMorfologica1.addActionListener(controlePrograma);
		btOpMorfologica2.addActionListener(controlePrograma);

		acao3Panel = new JPanel();
		acao3Panel.setPreferredSize( new Dimension ( 120, 60 ) );
		acao3Panel.setLayout(new GridLayout(2, 1));
		
		//acao3Panel.add( btOpMorfologica3 );
		acao3Panel.add( btOpMorfologica1 );
		acao3Panel.add( btOpMorfologica2 );

		acao3Panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Op. Morfologica"));

		controlePanelOpMorfologica.add(acao3Panel);
		controlePanelOpMorfologica.setVisible(false);

		//**********************************************************************
		
		// ADDING RADIO BUTTON PARA CONTROLE FILTRO
		controlePanelGabor = new JPanel();
		controlePanelGabor.setBackground( COR_FUNDO_BORDA );
		controlePanelGabor.setMaximumSize( new Dimension ( 130, 110 ) );
		outputPanelEsq.add( controlePanelGabor );

		char l = (char)955;
		
		btGabor0 = new JRadioButton ( "OFF", true );
		btGabor1 = new JRadioButton ( "1 "+l, false );
		btGabor2 = new JRadioButton ( "2 "+l, false );
		btGabor3 = new JRadioButton ( "3 "+l, false );
		btGabor4 = new JRadioButton ( "4 "+l, false );
	
		
		
		btRdGabor = new ButtonGroup();
	
		
	//	btRdGabor.add(btGabor0 );
		btRdGabor.add(btGabor1 );
		btRdGabor.add(btGabor2 );		
		btRdGabor.add(btGabor3 );		
		btRdGabor.add(btGabor4 );		
	

		btGabor1.addActionListener(controlePrograma);
		btGabor2.addActionListener(controlePrograma);
		btGabor3.addActionListener(controlePrograma);
		btGabor4.addActionListener(controlePrograma);
	
		
		btGabor1.setActionCommand( "botaoGabor1" );
		btGabor2.setActionCommand( "botaoGabor2" );
		btGabor3.setActionCommand( "botaoGabor3" );
		btGabor4.setActionCommand( "botaoGabor4" );
	
		
	

		acao1Panel = new JPanel();
		acao1Panel.setPreferredSize( new Dimension ( 120, 100) );
		acao1Panel.setLayout(new GridLayout(4, 1));

	//	acao1Panel.add( btGabor0 );
		acao1Panel.add( btGabor1 );
		acao1Panel.add( btGabor2 );
		acao1Panel.add( btGabor3 );
		acao1Panel.add( btGabor4 );
	

		acao1Panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Filtro Comprimento Onda"));

		controlePanelGabor.add(acao1Panel);
		controlePanelGabor.setVisible(false);
		
//************************************************************************
	
		// ADDING RADIO BUTTON PARA CONTROLE FILTRO
		controlePanelGabor2 = new JPanel();
		controlePanelGabor2.setBackground( COR_FUNDO_BORDA );
		controlePanelGabor2.setMaximumSize( new Dimension ( 130, 150 ) );
		outputPanelEsq.add( controlePanelGabor2 );

		char l2 = (char)952;
		

		btGabor0Grau = new JRadioButton ( "0 "+l2, false );
		btGabor45Grau = new JRadioButton ( "45 "+l2, false );
		btGabor90Grau = new JRadioButton ( "90 "+l2, false );
		btGabor135Grau = new JRadioButton ( "135 "+l2, false );
		btGaborTodosAngulos = new JRadioButton("Todos", false);
		
		
		btRdGabor2 = new ButtonGroup();
	
		
	//	btRdGabor.add(btGabor02 );
		btRdGabor2.add(btGabor0Grau );
		btRdGabor2.add(btGabor45Grau );		
		btRdGabor2.add(btGabor90Grau );		
		btRdGabor2.add(btGabor135Grau );	
		btRdGabor2.add(btGaborTodosAngulos);
	

		btGabor0Grau.addActionListener(controlePrograma);
		btGabor45Grau.addActionListener(controlePrograma);
		btGabor90Grau.addActionListener(controlePrograma);
		btGabor135Grau.addActionListener(controlePrograma);

		
		btGabor0Grau.setActionCommand( "botaoGabor12" );
		btGabor45Grau.setActionCommand( "botaoGabor22" );
		btGabor90Grau.setActionCommand( "botaoGabor32" );
		btGabor135Grau.setActionCommand( "botaoGabor42" );
		btGaborTodosAngulos.setActionCommand( "botaoGaborTodosAngulos" );
	
		
	

		acao1Panel = new JPanel();
		acao1Panel.setPreferredSize( new Dimension ( 120, 130 ) );
		acao1Panel.setLayout(new GridLayout(5, 1));

		//acao1Panel.add( btGabor02 );
		acao1Panel.add( btGabor0Grau );
		acao1Panel.add( btGabor45Grau );
		acao1Panel.add( btGabor90Grau );
		acao1Panel.add( btGabor135Grau );
		acao1Panel.add( btGaborTodosAngulos );
	

		acao1Panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Filtro Orientacao"));

		controlePanelGabor2.add(acao1Panel);
		controlePanelGabor2.setVisible(false);		
		
		
//*******************************************************************
		
		
		// ADDING RADIO BUTTON PARA CONTROLE DE ZOOM IN
		
		controlePanelZoomIn = new JPanel();
		controlePanelZoomIn.setBackground( COR_FUNDO_BORDA );
		controlePanelZoomIn.setMaximumSize( new Dimension ( 130, 110 ) );
		//outputPanelEsq.add( controlePanelZoomIn );
		
		
		btZoomInOff = new JRadioButton ("OFF", true);
		btZoomIn25 = new JRadioButton ("25% in", false);
		btZoomIn50 = new JRadioButton ("50% in", false );
		btZoomIn75 = new JRadioButton ("75% in", false );
		btZoomIn100 = new JRadioButton ("100% in ", false );
		btAcao25 = new JRadioButton ("50% OUT", false );
		btAcao26 = new JRadioButton ("75% OUT", false );

		btRdZoom = new ButtonGroup();
		btRdZoom.add(btZoomInOff);
		btRdZoom.add(btZoomIn25);
		btRdZoom.add(btZoomIn50);
		btRdZoom.add(btZoomIn75);
		btRdZoom.add(btZoomIn100);
		btRdZoom.add(btAcao25);
		btRdZoom.add(btAcao26);

		btZoomIn25.addActionListener(controlePrograma);
		btZoomIn50.addActionListener(controlePrograma);
		btZoomIn75.addActionListener(controlePrograma);
		btZoomIn100.addActionListener(controlePrograma);
		btAcao25.addActionListener(controlePrograma);
		btAcao26.addActionListener(controlePrograma);

		btZoomInOff.setActionCommand( "botaoZoomInOff" );
		btZoomIn25.setActionCommand( "botaoZoomIn25" );
		btZoomIn50.setActionCommand( "botaoZoomIn50" );
		btZoomIn75.setActionCommand( "botaoZoomIn75" );
		btZoomIn100.setActionCommand( "botaoZoomIn100" );
		btAcao25.setActionCommand( "botaoAcao25" );
    	btAcao26.setActionCommand( "botaoAcao26" );

		zoomInPanel = new JPanel();
		zoomInPanel.setPreferredSize( new Dimension ( 120, 105 ) );
		zoomInPanel.setLayout(new GridLayout(5, 1));
		
		zoomInPanel.add( btZoomInOff );
		zoomInPanel.add( btZoomIn25 );
		zoomInPanel.add( btZoomIn50 );
		zoomInPanel.add( btZoomIn75 );
		zoomInPanel.add( btZoomIn100 );
		

		zoomInPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Zoom"));

		controlePanelZoomIn.add(zoomInPanel);
		controlePanelZoomIn.setVisible(false);

		// ADDING RADIO BUTTON PARA CONTROLE DE ZOOM OUT
		controlePanelZoomOut = new JPanel();
		controlePanelZoomOut.setBackground( COR_FUNDO_BORDA );
		controlePanelZoomOut.setMaximumSize( new Dimension ( 130, 135 ) );
		outputPanelEsq.add( controlePanelZoomOut );


		btZoomOut25 = new JRadioButton ( "25% out", false );
		btZoomOut50 = new JRadioButton ( "50% out", false );
		btZoomOut75 = new JRadioButton ( "75% out", false );
		btZoomOut95 = new JRadioButton ( "95% out", false );
	
		
		btRdZoom.add(btZoomOut25);
		btRdZoom.add(btZoomOut50);
		btRdZoom.add(btZoomOut75);
		btRdZoom.add(btZoomOut95);

	
		
		btZoomOut25.addActionListener(controlePrograma);
		btZoomOut50.addActionListener(controlePrograma);
		btZoomOut75.addActionListener(controlePrograma);
		btZoomOut95.addActionListener(controlePrograma);


		zoomOutPanel = new JPanel();
		zoomOutPanel.setPreferredSize( new Dimension ( 120, 125 ) );
		zoomOutPanel.setLayout(new GridLayout(6, 1));
		
		
		

		
		zoomOutPanel.add( btZoomOut25 );
		zoomOutPanel.add( btZoomOut50 );
		zoomOutPanel.add( btZoomOut75 );
		zoomOutPanel.add( btZoomOut95 );
	
		btZoomTextLb = new JLabel("-100 ate 100: ");
		btZoomText = new JTextField("0");
		zoomOutPanel.add(btZoomTextLb);
		zoomOutPanel.add(btZoomText);

		
		
		
		zoomOutPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Zoom"));

		controlePanelZoomOut.add(zoomOutPanel);
		controlePanelZoomOut.setVisible(false);

		
		

		// VISIBLE PANELS
		outputPanel.add( outputPanelEsq, BorderLayout.LINE_START );
		outputPanel.add( outputPanelCen, BorderLayout.CENTER );
		outputPanel.add( outputPanelDir, BorderLayout.LINE_END );

		basePanel.add( titlePanel, BorderLayout.PAGE_START );
		basePanel.add( outputPanel, BorderLayout.CENTER );
		basePanel.add( buttonPanel, BorderLayout.PAGE_END );

		baseFrame.add(basePanel);
		baseFrame.setVisible(true);
	}
	
	public MontarPainelInicial(){
	}

	//*******************************************************************************************
	public void limpaPainelCen ()
	{
		outputPanelCen.removeAll();
		outputPanelCen.update( getDesenhoCentro() );
	}

	//*******************************************************************************************
	public void limpaPainelDir ()
	{
		outputPanelDir.removeAll();
		outputPanelDir.update( getDesenhoDireita() );
	}

	//*******************************************************************************************
	// METODO UTILIZADO PARA ADICIONAR UM BOTAO A UM CONTAINER DO PROGRAMA

	private JButton addButtonToContainer( String              textoBotao,
                                String              textoControle,
                                Container           container,
                                boolean             estado,
                                ControlarAplicativo controlePrograma
                              ) 
	{
		JButton botao;

		botao = new JButton( textoBotao );
		botao.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(botao);

		botao.setEnabled(estado);

		botao.setActionCommand( textoControle );

		botao.addActionListener( controlePrograma );

		return ( botao );
	}

	//*******************************************************************************************
	public void mudarBotoes() 
	{
		btZoom.setEnabled(true);
		btGabor.setEnabled(true);
		btSalva.setEnabled(true);
		btReset.setEnabled(true);
		btOperacao.setEnabled(true);
		btInverterCores.setEnabled(true);
		btReconhecer.setEnabled(true);
		
		controlePanelOpMorfologica.setVisible(true);
		controlePanelGabor.setVisible(true);
		controlePanelGabor2.setVisible(true);
		controlePanelZoomIn.setVisible(true);
		controlePanelVisualImagens.setVisible(true);
		controlePanelZoomOut.setVisible(true);
		btInverterCores.setVisible(false);
		btReconhecer.setVisible(false);
		
	}

	//*******************************************************************************************
	// METODO PARA APRESENTAR O MENU DE ESCOLHA DE ARQUIVOS
	// 1 - PARA LEITURA
	// 2 - PARA GRAVACAO
	
	public String escolherArquivo ( int operacao )   
	{
		int          retorno;
		String       caminhoArquivo;
		JFileChooser arquivo;

		retorno = 0;
		arquivo = new JFileChooser(new File("./imagens"));

		// TIPO DE OPERACAO A SER REALIZADA
		switch ( operacao ) {
		case 1:
			retorno = arquivo.showOpenDialog(null);
			break;

		case 2:
			retorno = arquivo.showSaveDialog(null);
		}

		// OPERACAO
		caminhoArquivo = null;

		if(retorno == JFileChooser.APPROVE_OPTION){
			try {
				caminhoArquivo = arquivo.getSelectedFile().getAbsolutePath();
			}	catch (ArrayIndexOutOfBoundsException e) {
			    System.out.println("erro: " + e);
			}
		} 

		return ( caminhoArquivo );
	}

	//*******************************************************************************************
	// METODO PARA MOSTRAR O FRAME BASICO

	public void showPanel() 
	{
		basePanel.setVisible(true);
	}


	//*******************************************************************************************
	public void desativarPainelAcao1()
	{
		controlePanelGabor.setVisible(false);
	}

	//*******************************************************************************************
	public void iniciarGraphics()
	{
		desenhoCen = outputPanelCen.getGraphics();
		desenhoDir = outputPanelDir.getGraphics();
	}

	//*******************************************************************************************
	public Graphics getDesenhoCentro()
	{
		return ( desenhoCen );
	}

	//*******************************************************************************************
	public Graphics getDesenhoDireita()
	{
		return ( desenhoDir );
	}

	//******************************************************************************************
	
	public boolean isTransictionsSelected() 
	{
		return btVisualAllImg.isSelected();
	}

	
	//******************************************************************************************

	// VERIFICA SE BOTAO NEW IMAGE ESTA SELECIONADO NO PAINEL
	public boolean isNewImageSelected(){
		
		return btVisualNewImg.isSelected();
	}
	
	//******************************************************************************************
	//VERIFICA SE BOTAO GABOR ESTA SELECIONADO NO PAINEL
	
	public int getGaborWaveLenght(){

		if(btGabor1.isSelected()) return 1;
		if(btGabor2.isSelected()) return 2;
		if(btGabor3.isSelected()) return 3;
		if(btGabor4.isSelected()) return 4;
		return 1;
	}
	
	
	

	// PARAMETROS 2 DE GABOR ***********************
	
	public double getGaborRad() {
		if(btGabor0Grau.isSelected()) return 0;
		if(btGabor45Grau.isSelected()) return Math.PI/4;
		if(btGabor90Grau.isSelected()) return Math.PI/2;
		if(btGabor135Grau.isSelected()) return Math.PI/0.75;
		return 0;
	}
	
	public boolean isBotaoGabor0Grau(){
		return btGabor0Grau.isSelected();
	}
	
	public boolean isBotaoGabor45Grau(){
		return btGabor45Grau.isSelected();
	}
	
	public boolean isBotaoGabor90Grau(){
		return btGabor90Grau.isSelected();
	}
	
	public boolean isBotaoGabor135Grau(){
		return btGabor135Grau.isSelected();
	}	
	
	public boolean isBotaoGaborTodosAngulos() {
		return btGaborTodosAngulos.isSelected();
	}
	
	
public int getbtOpMorfologica2(){
		
		int btOpMorfologica2 = 1;
		
		if(btZoomIn25.isSelected()) btOpMorfologica2 = 2;
		
		return (btOpMorfologica2);
	}
	
	
	//******************************************************************************************
	//VERIFICA SE BOTAO ZOOM IN 25% ESTA SELECIONADO NO PAINEL
	public int getBotaoZoomIn25(){
		
		int botaoZoomIn25 = 1;
		
		if(btZoomIn25.isSelected()) botaoZoomIn25 = 2;
		
		return (botaoZoomIn25);
	}


	//******************************************************************************************

	//VERIFICA SE BOTAO ZOOM IN 50% ESTA SELECIONADO NO PAINEL
	public int getBotaoZoomIn50(){
		
		int botaoZoomIn50 = 1;
		
		if(btZoomIn50.isSelected()) botaoZoomIn50 = 2;
		
		return (botaoZoomIn50);
	}

	//******************************************************************************************

	//VERIFICA SE BOTAO ZOOM IN 75% ESTA SELECIONADO NO PAINEL
	public int getBotaoZoomIn75(){
		
		int botaoZoomIn75 = 1;
		
		if(btZoomIn75.isSelected()) botaoZoomIn75 = 2;
		
		return (botaoZoomIn75);
	}

	//******************************************************************************************

	//VERIFICA SE BOTAO ZOOM IN 100% ESTA SELECIONADO NO PAINEL
	public int getBotaoZoomIn100(){
		
		int botaoZoomIn100 = 1;
		
		if(btZoomIn100.isSelected()) botaoZoomIn100 = 2;
		
		return (botaoZoomIn100);
	}

	//******************************************************************************************

	//VERIFICA SE BOTAO ZOOM OUT 25% ESTA SELECIONADO NO PAINEL
	public int getBotaoZoomOut25(){
		
		int botaoZoomOut25 = 1;
		
		if(btZoomOut25.isSelected()) botaoZoomOut25 = 2;
		
		return (botaoZoomOut25);
	}

	//******************************************************************************************

	//VERIFICA SE BOTAO ZOOM OUT 50% ESTA SELECIONADO NO PAINEL
	public int getBotaoZoomOut50(){
		
		int botaoZoomOut50 = 1;
		
		if(btZoomOut50.isSelected()) botaoZoomOut50 = 2;
		
		return (botaoZoomOut50);
	}

	//******************************************************************************************

	//VERIFICA SE BOTAO ZOOM OUT 75% ESTA SELECIONADO NO PAINEL
	public int getBotaoZoomOut75(){
		
		int botaoZoomOut75 = 1;
		
		if(btZoomOut75.isSelected()) botaoZoomOut75 = 2;
		
		return (botaoZoomOut75);
	}
	
	
	public int getBotaoZoomOut95(){
		
		int botaoZoomOut95 = 1;
		
		if(btZoomOut95.isSelected()) botaoZoomOut95 = 2;
		
		return (botaoZoomOut95);
	}
	
	
	
	public int getbtZoomText(){
		
		int btZoomIn2 = Integer.parseInt(btZoomText.getText());
		//btZoomIn.setText("0");
		
		return (btZoomIn2);
	}
	

	//******************************************************************************************
	public void resetaSistema()
	{
		btGabor0.setSelected(true);
		btZoomInOff.setSelected(true);
		btZoomText.setText("0");
		btOpMorfologica3.setSelected(true);
		btVisualNewImg.setSelected(true);
	}
	
	//******************************************************************************************


	public int getBtZoomOut()
	{
		return Integer.parseInt(btZoomText.getText());
	}
	
	

}





