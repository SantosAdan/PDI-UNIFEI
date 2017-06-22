package pacoteBase.CONTROL;

public class FloodFill {
	
	  public void apply(int[][] picture, int colorToReplace, int colorToPaint, int x, int y) {
	    validatePicture(picture);

	    int currentColor = getValueAt(picture, x, y);
	    if (currentColor == colorToReplace) {
	      picture[x][y] = colorToPaint;
	      apply(picture, colorToReplace, colorToPaint, x + 1, y);
	      apply(picture, colorToReplace, colorToPaint, x - 1, y);
	      apply(picture, colorToReplace, colorToPaint, x, y + 1);
	      apply(picture, colorToReplace, colorToPaint, x, y - 1);
	    }
	  }

	  private void validatePicture(int[][] picture) {
	    if (picture == null) {
	      throw new IllegalArgumentException("Você precisa de uma imagem válida.");
	    }
	  }

	  private static int getValueAt(int[][] picture, int x, int y) {
	    if (x < 0 || y < 0 || x > picture.length || y > picture[x].length) {
	      return -1;
	    } else {
	      return picture[x][y];
	    }
	  }
}
