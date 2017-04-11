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

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import pacote25901.MODEL.*;
import pacote25901.VIEW.InterfaceGrafica;

public class MainController implements ActionListener, MouseListener, MouseMotionListener {
	
	private InterfaceGrafica pnCenario;
	private Point pontoInicialImagem, pontoFinalImagem, pontoInicialTemplate, pontoFinalTemplate, pontoSelecionado;
	
	private ArrayList<Matriz> listaMatrizes = new ArrayList<Matriz>();
	private ArrayList<Matriz> listaTemplate = new ArrayList<Matriz>();
	private ArrayList<Matriz> listaConvolucao = new ArrayList<Matriz>();
	private final String DRAWING = "drawing";
	private final String DRAWING_TEMPLATE = "drawing template";
	private final String COLORING = "coloring";
	private final String COLORING_TEMPLATE = "coloring template";
	private final String IDLE = "idle";
	private String status;
	private Color corSelecionada;
	
	Point[][] matrizPontos = new Point[5][5];
    Color[][] matrizCores = new Color[5][5];
    ArrayList<Point> listaPontosLoad = new ArrayList<Point> ();
    ArrayList<Color> listaCoresLoad = new ArrayList<Color> ();
	
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
		else if(comando.equals("btInstrucoes")){
			pnCenario.exibeInstrucoes();
		}
		else if(comando.equals("btColorir")){
			status = COLORING;
			corSelecionada = JColorChooser.showDialog(null, "Escolha uma cor", Color.BLACK);
		}
		else if(comando.equals("btColorirTemplate")){
			status = COLORING_TEMPLATE;
			corSelecionada = JColorChooser.showDialog(null, "Escolha uma cor", Color.BLACK);
		}
		else if(comando.equals("btTrocaCor")) {
			corSelecionada = JColorChooser.showDialog(null, "Escolha uma cor", Color.BLACK);
		}
		else if(comando.equals("btConvoluir")) {
			MatrizController matrizCtrl = new MatrizController(pnCenario.iniciarGraphics());
			listaConvolucao = matrizCtrl.realizaConvolucao(listaMatrizes, listaTemplate, 5, 3);
			
			//pnCenario.limpaOutputPanel();
			matrizCtrl.desenhaMatrizLista(listaConvolucao, pontoInicialImagem, pontoFinalImagem, 5);
			
		}
		else if(comando.equals("btCarrega")) {		
			carregaArquivo();
			
			MatrizController matrizCtrl = new MatrizController(pnCenario.iniciarGraphics());
			System.out.println(listaMatrizes.get(0).getPontoInicial());
			listaMatrizes = matrizCtrl.desenhaMatrizLoad(listaMatrizes, 5);
			pontoInicialImagem = listaMatrizes.get(0).getPontoInicial();
			pontoFinalImagem = listaMatrizes.get(listaMatrizes.size()-1).getPontoFinal();
			
			status = DRAWING_TEMPLATE;
		}
		else if(comando.equals("btSalva")) {
			MatrizController matrizCtrl = new MatrizController(pnCenario.iniciarGraphics());
			matrizPontos = matrizCtrl.paraMatriz(listaMatrizes, 5);
			matrizCores = matrizCtrl.paraMatrizCores(listaMatrizes, 5);
			saveFile();
			
		}
		else if(comando.equals("btLimpar")){
			listaMatrizes.clear();
			listaTemplate.clear();
			listaConvolucao.clear();
			
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
        int            i, altura=0, largura=0, qL, resultado, linhaLida[], tamanhoRegistro;
 
        // ABRINDO ARQUIVO DE DADOS
        escolheArquivos = new JFileChooser();
        diretorio = new File ( "..\\" );
        escolheArquivos.setCurrentDirectory(diretorio);
        escolheArquivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        resultado = escolheArquivos.showOpenDialog(escolheArquivos);
        nomeArquivoLido = null;
       
        if ( resultado != JFileChooser.CANCEL_OPTION ) {
 
            nomeArquivo = escolheArquivos.getSelectedFile();
            try {  
                listaMatrizes.clear();
                fr        = new FileReader ( nomeArquivo );
                br        = new BufferedReader ( fr );
                linhaLida = new int[10];
 
                registro  = br.readLine();
                while ( registro != null ) {
 
                    registro_lido = registro.split(" ");
                    qL = 0;
                    tamanhoRegistro = registro_lido.length;
                    for ( i = 0; i < tamanhoRegistro; i++ ) {
                        registro_aux = registro_lido[i];
                        if( registro_aux != null && registro_aux.length() > 0 ) {
                            linhaLida[qL] = Integer.parseInt( registro_aux );
                            qL++;
                        }
                    }
                    if(tamanhoRegistro==10){
                        listaPontosLoad.add(new Point(linhaLida[0],linhaLida[1]));
                        listaPontosLoad.add(new Point(linhaLida[2],linhaLida[3]));
                        listaPontosLoad.add(new Point(linhaLida[4],linhaLida[5]));
                        listaPontosLoad.add(new Point(linhaLida[6],linhaLida[7]));
                        listaPontosLoad.add(new Point(linhaLida[8],linhaLida[9]));
                    }else if(tamanhoRegistro==5){
                        listaCoresLoad.add(new Color(linhaLida[0]));
                        listaCoresLoad.add(new Color(linhaLida[1]));
                        listaCoresLoad.add(new Color(linhaLida[2]));
                        listaCoresLoad.add(new Color(linhaLida[3]));
                        listaCoresLoad.add(new Color(linhaLida[4]));
                    }else{
                        altura = linhaLida[0];
                        largura = linhaLida[1];
                    }      
                   
 
                    // NOVO REGISTRO
                    registro = br.readLine();
                }
                for(int x=0;x<5*5;x++){
                    Matriz matriz =  new Matriz();
                    matriz.setPontoInicial(listaPontosLoad.get(x));
                    matriz.setPontoFinal(new Point((int) listaPontosLoad.get(x).getX()+largura,(int) listaPontosLoad.get(x).getY()+altura ));
                    matriz.setColor(listaCoresLoad.get(x));
                   
                    listaMatrizes.add(matriz);
                }
 
               
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
        MatrizController matrizCtrl = new MatrizController(pnCenario.iniciarGraphics());
       
        if ( resultado != JFileChooser.CANCEL_OPTION ) {
 
            nomeArquivo = escolheArquivos.getSelectedFile();
 
            try {  
            	int tamanho = 5;
                out = new BufferedWriter(new FileWriter ( nomeArquivo ));              
                for(int i=1;i<=tamanho;i++){
                    for(int u=1;u<=tamanho;u++){
                        out.write((int) matrizPontos[i][u].getX()+" "+ (int) matrizPontos[i][u].getY()+" ");
                    }
                    out.newLine();
                }
                for(int i=1;i<=tamanho;i++){
                    for(int u=1;u<=tamanho;u++){
                        out.write((int) matrizCores[i][u].getRGB()+" ");
                    }
                    out.newLine();
                }
                int altura = (int) matrizCtrl.encontraAltura(listaMatrizes.get(0).getPontoInicial(), listaMatrizes.get(0).getPontoFinal());
                int largura = (int) matrizCtrl.encontraLargura(listaMatrizes.get(0).getPontoInicial(), listaMatrizes.get(0).getPontoFinal());
                out.write(altura+" "+largura);
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
		// DESENHANDO IMAGEM
		if(status.equals(DRAWING)) {
			pontoInicialImagem = e.getPoint();
			desenhaCirculo(pontoInicialImagem, Color.RED);
		}
		// DESENHANDO TEMPLATE
		else if(status.equals(DRAWING_TEMPLATE)) {
			pontoInicialTemplate = e.getPoint();
			desenhaCirculo(pontoInicialTemplate, Color.RED);
		}
		// COLORINDO IMAGEM
		else if(status.equals(COLORING)) {
			pontoSelecionado = e.getPoint();
			
			MatrizController matrizCtrl = new MatrizController(pnCenario.iniciarGraphics());
			listaMatrizes = matrizCtrl.colorirMatriz(listaMatrizes, pontoSelecionado, corSelecionada);
		}
		// COLORINDO TEMPLATE
		else if(status.equals(COLORING_TEMPLATE)) {
			pontoSelecionado = e.getPoint();
			
			MatrizController matrizCtrl = new MatrizController(pnCenario.iniciarGraphics());
			listaTemplate = matrizCtrl.colorirMatriz(listaTemplate, pontoSelecionado, corSelecionada);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// DESENHANDO IMAGEM
		if(status.equals(DRAWING)) {
			pontoFinalImagem = e.getPoint();
			desenhaCirculo(pontoFinalImagem, Color.RED);
			
			desenhaCirculo(pontoInicialImagem, Color.DARK_GRAY);
			desenhaCirculo(pontoFinalImagem, Color.DARK_GRAY);
			
			// DESENHA RETANGULO
			MatrizController matrizCtrl = new MatrizController(pnCenario.iniciarGraphics());
			listaMatrizes = matrizCtrl.desenhaMatriz(pontoInicialImagem, pontoFinalImagem, 5);
			
			status = DRAWING_TEMPLATE;
		}
		// DESENHANDO TEMPLATE
		else if(status.equals(DRAWING_TEMPLATE)) {
			pontoFinalTemplate = e.getPoint();
			desenhaCirculo(pontoFinalTemplate, Color.RED);
			
			desenhaCirculo(pontoInicialTemplate, Color.DARK_GRAY);
			desenhaCirculo(pontoFinalTemplate, Color.DARK_GRAY);
			
			// DESENHA RETANGULO
			MatrizController matrizCtrl = new MatrizController(pnCenario.iniciarGraphics());
			listaTemplate = matrizCtrl.desenhaMatriz(pontoInicialTemplate, pontoFinalTemplate, 3);
			
			status = IDLE;
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
