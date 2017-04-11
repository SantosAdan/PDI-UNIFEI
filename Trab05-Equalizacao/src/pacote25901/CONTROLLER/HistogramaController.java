package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HistogramaController {
	private int SIZE = 256;
	// Red, Green, Blue
	private int NUMBER_OF_COLOURS = 3;

	public final int RED = 0;
	public final int GREEN = 1;
	public final int BLUE = 2;

	private int[][] colourBins;
	private volatile boolean loaded = false;
	private int maxY;

	/**
	 * 
	 * @param Path
	 *            of image to create Histogram of.
	 */
	public HistogramaController() {
		colourBins = new int[NUMBER_OF_COLOURS][];

		for (int i = 0; i < NUMBER_OF_COLOURS; i++) {
			colourBins[i] = new int[SIZE];
		}

		loaded = false;
	}

	public void load(BufferedImage img) throws IOException {

		// Reset all the bins
		for (int i = 0; i < NUMBER_OF_COLOURS; i++) {
			for (int j = 0; j < SIZE; j++) {
				colourBins[i][j] = 0;
			}
		}

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				Color c = new Color(img.getRGB(x, y));

				colourBins[RED][c.getRed()]++;
				colourBins[GREEN][c.getGreen()]++;
				colourBins[BLUE][c.getBlue()]++;
			}
		}

		maxY = 0;

		for (int i = 0; i < NUMBER_OF_COLOURS; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (maxY < colourBins[i][j]) {
					maxY = colourBins[i][j];
				}
			}
		}

		loaded = true;
	}

	public int[][] getcolourBins() {
		return colourBins;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getRED() {
		return RED;
	}

	public int getBLUE() {
		return BLUE;
	}

	public int getGREEN() {
		return GREEN;
	}
}
