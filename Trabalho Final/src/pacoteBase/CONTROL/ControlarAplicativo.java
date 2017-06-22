package pacoteBase.CONTROL;


import pacoteBase.MODEL.GaborFiltro;
import pacoteBase.MODEL.ImageUtils;
import pacoteBase.MODEL.ImpressaoDigital;
import pacoteBase.MODEL.MorfologiaPreenchimentoBuraco;
import pacoteBase.VIEW.*;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;



public class ControlarAplicativo implements ActionListener {

	private MontarPainelInicial pnCenario;
	private Graphics            desenhoCentro, desenhoDireita;
	private ControlarImagem     controleImagem;
	private String              nomeArquivoImagemDada;

	private char[][]            imagemAtual;

	private boolean             estadoDesenho;

	public static final int OPERACAO_ABRIR = 1, OPERACAO_SALVAR = 2;


	//*******************************************************************************************
	public ControlarAplicativo( )
	{
		pnCenario = new MontarPainelInicial( this );
		pnCenario.showPanel();
		estadoDesenho  = false;
	}


	//*******************************************************************************************
	// METODO PARA CONTROLE DOS BOTOES DO APLICATIVO

	public void actionPerformed(ActionEvent e)
	{
		String comando, nomeArquivo;

		comando = e.getActionCommand();

		// DEFINE AMBIENTE GRAFICO
		if ( !estadoDesenho ) {
			pnCenario.iniciarGraphics();
			desenhoCentro = pnCenario.getDesenhoCentro();
			desenhoDireita = pnCenario.getDesenhoDireita();
		}

		// ENDS THE PROGRAM
		if(comando.equals("botaoFim")) {
			System.exit(0);	
		}

		// INICIA O PROGRAMA
		if(comando.equals("botaoImagem")) {
			nomeArquivoImagemDada = pnCenario.escolherArquivo ( OPERACAO_ABRIR );
			carregarImagem();
		}

		// BOTAO MORFOLOGIAA *****************************************
		if ( comando.equals( "botaoOpMorfologica" ) && estadoDesenho ) {

			if(pnCenario.isTransictionsSelected()){   
				//if (pnCenario.getbtOpMorfologica2() ==2){

				char[][] temp = controleImagem.copiarImagem(imagemAtual);

				imagemAtual = /*ImageUtils.bufferedToBytes(
						Morfologia.normalizar(
								MorfologiaEsqueleto.aplicar(
										Morfologia.binarizar(
												controleImagem.transformarMatriz2Buffer(imagemAtual)))));*/
						MorfologiaPreenchimentoBuraco.aplicar(imagemAtual);
				pnCenario.limpaPainelCen();
				controleImagem.mostrarImagem(temp, desenhoCentro);
				pnCenario.limpaPainelDir();
			}

			if (pnCenario.isNewImageSelected())
				imagemAtual = MorfologiaPreenchimentoBuraco.aplicar(imagemAtual);

			pnCenario.limpaPainelDir();
			controleImagem.mostrarImagem(imagemAtual, desenhoDireita);	
		}

		// FILTRO DE GABORRR ****************************************************
		if ( comando.equals( "botaoAcao1" )&& estadoDesenho  )  {


			BufferedImage bufferedImage = controleImagem.transformarMatriz2Buffer ( imagemAtual );


			//*******************************************************
			//APLICA FILTRO GABOR DE ACORDO COM PARAMETRO SELECIONADO
			
			int wavelength = pnCenario.getGaborWaveLenght();
			double rads = pnCenario.getGaborRad();
			
			if(!pnCenario.isBotaoGaborTodosAngulos()) {
				bufferedImage = (BufferedImage) new GaborFiltro(wavelength, new double[] {rads}, 100, 0, 1, 3, 3).filter(bufferedImage, null);
			} else {
				int NUM_ANGULOS = 10;
				double[] angulos = new double[NUM_ANGULOS];
				for(int i =0; i< NUM_ANGULOS; i++) {
					angulos[i] = i*(Math.PI/NUM_ANGULOS);
				}
				bufferedImage = (BufferedImage) new GaborFiltro(wavelength, angulos, 100, 0, 1, 3, 3).filter(bufferedImage, null);
			}
			
			imagemAtual = ImageUtils.bufferedToBytes(bufferedImage);
			controleImagem.mostrarImagem(imagemAtual, desenhoDireita);
		} 
		
		if(comando.equals("botaoInverter") && estadoDesenho) {
			imagemAtual = ImageUtils.inverterBinaria(imagemAtual);
			controleImagem.mostrarImagem(imagemAtual, desenhoDireita);
		}

		//ZOOM **************************************************

		if (comando.equals("botaoZoom") && estadoDesenho) {

			int percentageZoom = 0; // porcentagem de zoom escolhida

			if(pnCenario.getBotaoZoomIn25() == 2){
				percentageZoom = 25;		
			}

			if(pnCenario.getBotaoZoomIn50() == 2){
				percentageZoom = 50;		
			}

			if (pnCenario.getBotaoZoomIn75() == 2) {				
				percentageZoom = 75;
			}

			if(pnCenario.getBotaoZoomIn100() == 2){
				percentageZoom = 100;
			}

			if (pnCenario.getBotaoZoomOut25() == 2){
				percentageZoom = -25;
			}

			if(pnCenario.getBotaoZoomOut50() == 2){
				percentageZoom = -50;
			}

			if(pnCenario.getBotaoZoomOut75() == 2){
				percentageZoom = -75;
			}
			if(pnCenario.getBotaoZoomOut95() == 2){
				percentageZoom = -95;
			}
			//TEXTFILD
			if(pnCenario.getbtZoomText()!=0){
				percentageZoom = pnCenario.getbtZoomText();

			}

			controlZoom(percentageZoom);	

		}
		
		// RECONHECER PLACA 
		if(comando.equals("botaoReconhecerPlaca") && estadoDesenho) {
			BufferedImage bi = controleImagem.transformarMatriz2Buffer(imagemAtual);
			
			int[][] dadosDigital = ImpressaoDigital.reconhecerTracos(bi);
			if(dadosDigital != null) {
				controleImagem.setDadosDigital(dadosDigital);
				controleImagem.mostrarImagem(imagemAtual, desenhoDireita);
				
				int[] ocorrencias = new int[15];
				for(int i = 0; i < ocorrencias.length; i++)
					ocorrencias[i] = 0;
				
				for(int i =0; i < dadosDigital.length; i++) {
					for(int j = 0; j < dadosDigital[0].length; j++) {
						ocorrencias[dadosDigital[i][j]]++;
					}
				}
				
				for(int i = 0; i < ocorrencias.length; i++)
					System.out.println(String.format("%d - %d ocorrencias", i, ocorrencias[i]));
				
			}
		}

		if ( comando.equals( "botaoSalva" ) && estadoDesenho ) {
			nomeArquivo = pnCenario.escolherArquivo ( OPERACAO_SALVAR );
			controleImagem.gravarImagem( nomeArquivo, imagemAtual );
		}

		if ( comando.equals( "botaoReset" ) && estadoDesenho ) {
			pnCenario.limpaPainelCen();
			carregarImagem();
			pnCenario.resetaSistema();
		}
	}



	private void carregarImagem() {

		if ( nomeArquivoImagemDada != null ) {
			controleImagem = new ControlarImagem( nomeArquivoImagemDada, desenhoCentro );
			estadoDesenho  = true;
			imagemAtual    = controleImagem.criarImagemCinza(controleImagem.getImagemDada());

			pnCenario.mudarBotoes();
			pnCenario.limpaPainelDir();
			controleImagem.mostrarImagem ( imagemAtual, desenhoDireita );

		}
	}


	private void controlZoom(int percentageZoom) {		

		if(pnCenario.isTransictionsSelected()){   

			char[][] temp = controleImagem.copiarImagem(imagemAtual);

			imagemAtual = controleImagem.aplicaZoom(imagemAtual, percentageZoom);
			pnCenario.limpaPainelCen();
			controleImagem.mostrarImagem(temp, desenhoCentro);
			pnCenario.limpaPainelDir();
		}

		if (pnCenario.isNewImageSelected()){
			imagemAtual = controleImagem.aplicaZoom(imagemAtual, percentageZoom);

			pnCenario.limpaPainelDir();
			controleImagem.mostrarImagem(imagemAtual, desenhoDireita);
		}

	}

}