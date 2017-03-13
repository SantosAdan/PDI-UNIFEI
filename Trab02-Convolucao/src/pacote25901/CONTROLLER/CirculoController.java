package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.Graphics;

import pacote25901.MODEL.Circulo;

public class CirculoController {
	private Graphics desenho;

	//CONSTRUTOR
	public CirculoController(Graphics desenho) {
		this.desenho = desenho;
	}

	// BRESENHAM'S DRAW CIRCLE ALGORITHM
	public void bresenhamCirculo(Circulo circulo, Color color) {
		int x, y, x0, y0, err;
		
		x0 = (int) circulo.getCentro().getX();
		y0 = (int) circulo.getCentro().getY();
		
		x = circulo.getRaio();
		y = 0;
		err = 0;
		
		desenho.setColor(color);
		while(x >= y) {
			
			// 1º Quadrante
			desenho.drawLine(x0 + y, y0 - x, x0 + y, y0 - x);
			desenho.drawLine(x0 + x, y0 - y, x0 + x, y0 - y);

			// 2º Quadrante
			desenho.drawLine(x0 - x, y0 - y, x0 - x, y0 - y);
			desenho.drawLine(x0 - y, y0 - x, x0 - y, y0 - x);
			
			// 3º Quadrante
			desenho.drawLine(x0 - y, y0 + x, x0 - y, y0 + x);
			desenho.drawLine(x0 - x, y0 + y, x0 - x, y0 + y);
			
			// 4º Quadrante
			desenho.drawLine(x0 + x, y0 + y, x0 + x, y0 + y);
			desenho.drawLine(x0 + y, y0 + x, x0 + y, y0 + x);
						
			y += 1;
	        err += 1 + 2*y;
	        if (2*(err-x) + 1 > 0)
	        {
	            x -= 1;
	            err += 1 - 2*x;
	        }
		}
	}
}
