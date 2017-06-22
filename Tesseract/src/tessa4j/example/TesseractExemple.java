package tessa4j.example;

import java.io.File;
import net.sourceforge.tess4j.*;

public class TesseractExemple {

	public static void main(String[] args) {
		
		File imageFile = new File("eurotext.png");
		ITesseract instance = new Tesseract();
		
		try {
			String result = instance.doOCR(imageFile);
			System.out.println(result);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
		
	}
}
