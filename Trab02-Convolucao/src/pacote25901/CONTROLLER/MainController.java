package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import pacote25901.MODEL.*;
import pacote25901.VIEW.InterfaceGrafica;

public class MainController implements ActionListener, MouseListener, MouseMotionListener {
	
	private InterfaceGrafica pnCenario;
	private Point pontoInicial, pontoFinal, pontoSelecionado;
	
	private ArrayList<Matriz> listaMatrizes = new ArrayList<Matriz>();
	private final String DRAWING = "drawing";
	private final String COLORING = "coloring";
	private String status;
	
	ArrayList<Point> listaPontos = new ArrayList<Point> ();
	ArrayList<Point> listaPontosLoad = new ArrayList<Point> ();
	ArrayList<Point> listaPontosSave = new ArrayList<Point> ();
	ArrayList<Point> listaaux = new ArrayList<Point> ();
	ArrayList<Point> listaTodosPontos = new ArrayList<Point>();
	
	//****************************************************************************	
	public MainController()
	{
		pnCenario = new InterfaceGrafica( this );
		pnCenario.showPanel();
		status = DRAWING;
	}

	//**************************************************************************** 
	public void actionPerformed(ActionEvent e)
	{
		String  comando;
		comando = e.getActionCommand();

		// ENDS THE PROGRAM
		if(comando.equals("btEnd")) {
			System.exit(0);	
		}
		else if(comando.equals("btColorir")){
			status = COLORING;
		}
		else if(comando.equals("btCarrega")){		
//			preenchimentocontrol preenchimentoCtrl = new preenchimentocontrol (pnCenario.iniciarGraphics());
//	        int i;
			carregaArquivo();
			
//			listaaux = preenchimentoCtrl.ordena(listaTodosPontos);
//			preenchimentoCtrl.preenchimento(listaaux);
//			for(i=0;i<listaTodosPontos.size();i++){
//				System.out.println(listaTodosPontos.get(i));
//			}
//			System.out.println(i);
	        	        

		}
		else if(comando.equals("btSalva")){
			saveFile();
			
		}
		else if(comando.equals("btLimpar")){
			listaMatrizes.clear();
			status = DRAWING;
			pnCenario.limpaOutputPanel();
		}
			
	}	
	
	//Metodo para desenhar um circulo ao clicar
	public void desenhaCirculo(Point p, Color color) {
		CirculoController ctrlCirculo = new CirculoController(pnCenario.iniciarGraphics());
		Circulo circulo = new Circulo(p, 5);
		
		ctrlCirculo.bresenhamCirculo(circulo, color);
	}
	
	// LER ARQUIVO DE PONTOS
	private String carregaArquivo()
	{
		String         registro, registro_lido[], nomeArquivoLido,
		               registro_aux, mensagem;
		FileReader     fr;
		BufferedReader br;
		JFileChooser   escolheArquivos;
		File           nomeArquivo, diretorio;
		int            i, xa, ya, xb, yb, xc, yc, qL, resultado, linhaLida[], tamanho;
		Point          pa, pb, pc;

		// ABRINDO ARQUIVO DE DADOS
		escolheArquivos = new JFileChooser();
		diretorio = new File ( "..\\" );
		escolheArquivos.setCurrentDirectory(diretorio);
		escolheArquivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		resultado = escolheArquivos.showOpenDialog(escolheArquivos);
		nomeArquivoLido = null;
		MatrizController matrizCtrl = new MatrizController(pnCenario.iniciarGraphics());
		
		if ( resultado != JFileChooser.CANCEL_OPTION ) {

			nomeArquivo = escolheArquivos.getSelectedFile();
			try {	

				fr        = new FileReader ( nomeArquivo );
				br        = new BufferedReader ( fr );
				linhaLida = new int[8];

				registro  = br.readLine();
				while ( registro != null ) {

					registro_lido = registro.split(" ");
					qL = 0;
					tamanho = registro_lido.length;
					for ( i = 0; i < tamanho; i++ ) {
						registro_aux = registro_lido[i];
						if( registro_aux != null && registro_aux.length() > 0 ) {
							linhaLida[qL] = Integer.parseInt( registro_aux );
							qL++;
						}
					}

					xa   = linhaLida[0];
					ya   = linhaLida[1];
					pa   = new Point ( xa, ya );
					xb   = linhaLida[2];
					yb   = linhaLida[3];
					pb   = new Point ( xb, yb );
					xc   = linhaLida[4];
					yc   = linhaLida[5];
					pc   = new Point ( xc, yc );
					listaPontosLoad.add(pc);
					listaPontosLoad.add(pa);
					listaPontosLoad.add(pb);
					
					listaPontosLoad.clear();
								
					// NOVO REGISTRO
					registro = br.readLine();
				}
				listaTodosPontos = matrizCtrl.listaTodosPontos;
				br.close();

				nomeArquivoLido = nomeArquivo.toString();

			} catch ( FileNotFoundException e ) {
				mensagem = "File " + nomeArquivo + " does not exist";
				JOptionPane.showMessageDialog( null, mensagem, "", 
                    JOptionPane.INFORMATION_MESSAGE );
			} catch ( IOException e ) {
				mensagem = "Error at file: " + nomeArquivo;
				JOptionPane.showMessageDialog( null, mensagem, "", 
                  JOptionPane.INFORMATION_MESSAGE );
			}
		}

		return nomeArquivoLido;
	}
	
	// SALVAR ARQUIVO DE PONTOS
	public String saveFile()
	{
		String         nomeArquivoSalvo, mensagem;
		BufferedWriter     out = null;
		JFileChooser   escolheArquivos;
		File           nomeArquivo, diretorio;
		int            resultado;
		// ABRINDO ARQUIVO DE DADOS
		escolheArquivos = new JFileChooser();
		diretorio = new File ( "..\\" );
		escolheArquivos.setCurrentDirectory(diretorio);
		escolheArquivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		resultado = escolheArquivos.showOpenDialog(escolheArquivos);
		nomeArquivoSalvo = null;

		if ( resultado != JFileChooser.CANCEL_OPTION ) {

			nomeArquivo = escolheArquivos.getSelectedFile();

			try {	

				out = new BufferedWriter(new FileWriter ( nomeArquivo ));
//				for(int k=0;k<listaPontosSave.size();k++){
//					System.out.println(listaPontosSave.get(k).getX()+"/"+listaPontosSave.get(k).getY());
//					out.write( (int) listaPontosSave.get(k).getX()+" ");
//					out.write( (int) listaPontosSave.get(k).getY()+" ");
//				}
				for(int k=0;k<listaaux.size();k++){
					out.write( (int) listaaux.get(k).getX()+" ");
					out.write( (int) listaaux.get(k).getY()+" ");
				}
				out.newLine();
				out.close();

				nomeArquivoSalvo = nomeArquivo.toString();

			} catch ( FileNotFoundException e ) {
				mensagem = "File " + nomeArquivo + " does not exist";
				JOptionPane.showMessageDialog( null, mensagem, "", 
                    JOptionPane.INFORMATION_MESSAGE );
			} catch ( IOException e ) {
				mensagem = "Error at file: " + nomeArquivo;
				JOptionPane.showMessageDialog( null, mensagem, "", 
                  JOptionPane.INFORMATION_MESSAGE );
			}
		}

		return nomeArquivoSalvo;
	}

	/**
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	private int distancia(Point p1, Point p2)
	{
		double distancia;
		double x1, x2, y1, y2;

		x1 = p1.getX();
		y1 = p1.getY();
		x2 = p2.getX();
		y2 = p2.getY();
		
		distancia = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		
		return (int) distancia;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if(status.equals(DRAWING)) {
			pontoInicial = e.getPoint();
			desenhaCirculo(pontoInicial, Color.RED);
		}
		else if(status.equals(COLORING)) {
			pontoSelecionado = e.getPoint();
			
			MatrizController matrizCtrl = new MatrizController(pnCenario.iniciarGraphics());
			Matriz matrizColorir = matrizCtrl.verificaContido(listaMatrizes, pontoSelecionado);
			matrizCtrl.colorirMatriz(matrizColorir, Color.BLUE);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		if(status.equals(DRAWING)) {
			pontoFinal = e.getPoint();
			desenhaCirculo(pontoFinal, Color.RED);
			
			desenhaCirculo(pontoInicial, Color.LIGHT_GRAY);
			desenhaCirculo(pontoFinal, Color.LIGHT_GRAY);
			
			// DESENHA RETANGULO
			MatrizController matrizCtrl = new MatrizController(pnCenario.iniciarGraphics());
			listaMatrizes = matrizCtrl.desenhaMatriz(pontoInicial, pontoFinal);
		}
		else if(status.equals(COLORING)) {
			
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
}
