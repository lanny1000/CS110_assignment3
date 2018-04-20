import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public class ImageProcessing {

	public static void main(String[] args) {
		ImageUtils utils = new ImageUtils();
		
		Color[][] image = utils.loadImage("src/LennaCV.png");
		utils.addImage(image, "Original image");
		
		Color[][] tmp = addStripes(image);
		utils.addImage(tmp, "Add stripes");
		
		Color[][] tmp2 = zoomIn(tmp);
		utils.addImage(tmp2, "Zoom in");
		
		Color[][] tmp3 = rotate(tmp);
		utils.addImage(tmp3, "Rotate");
		
		Color[][] tmp4 = pixelate(image);
		utils.addImage(tmp4, "Pixelate");
		
		Color[][] tmp5 = replaceWithBlue(image);
		utils.addImage(tmp5, "Replace with blue");
		
		Color[][] tmp6 = reverseRgb(image);
		utils.addImage(tmp6, "Reverse rgb order");
		
		Color[][] tmp7 = addGrayscale(image);
		utils.addImage(tmp7, "Grayscale");
		
		Color[][] tmp8 = colorFeather(tmp7);
		utils.addImage(tmp8, "Color feather");
		
		Color[][] tmp9 = addPurple(image);
		utils.addImage(tmp9, "Add purple lines to tmp5");
		utils.display();

	}
	
	public static Color[][] addStripes(Color[][] image) {
		Color[][] tmp = ImageUtils.cloneArray(image);

		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				Color pixel = tmp[i][j];
				int r = pixel.getRed()/2;
				int g = pixel.getGreen()/7;
				int b = pixel.getBlue();

				if (j > 0 && j < 50) {
					tmp[i][j] = new Color(r,g,b/7);
				}
				if (j > 100 && j < 150) {
					tmp[i][j] = new Color(r,g,b);
				}
				if (j > 200 && j < 255) {
					tmp[i][j] = new Color(123,g,b);
				}
				if (i > 300 && i < 450) {
					tmp[i][j] = new Color(r/3,g,b);
				}
			}
		}
		return tmp;
	}
	
	public static Color[][] zoomIn(Color[][] image) {
		Color[][] tmp = ImageUtils.cloneArray(image);
		Color[][] square = new Color[tmp.length][tmp[0].length];
		int k = 2;

		for (int j = tmp.length - 1; j >= 0; j--) {
			for (int i = 0; i < tmp[0].length; i++) {
				square[i][j] = tmp[i/k][j/k];
			}
		}
		return square;
	}
	
	public static Color[][] rotate(Color[][] image) {
		Color[][] tmp = ImageUtils.cloneArray(image);
		Color[][] square = new Color[tmp.length][tmp[0].length];

		for (int j = tmp.length - 1; j >= 0; j--) {
			for (int i = 0; i < tmp[0].length; i++) {
				square[i][j] = tmp[j][i];
			}
		}
		return square;
	}
	
	public static Color[][] pixelate(Color[][] image) {
		Color[][] tmp = ImageUtils.cloneArray(image);

		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				int rand = ThreadLocalRandom.current().nextInt(0, tmp.length - 1);

				if (i%2 == 0 && j%2 == 0) {
					tmp[i][j] = tmp[rand][rand];
				}
			}
		}
		return tmp;
	}
	
	public static Color[][] replaceWithBlue(Color[][] image) {
		Color[][] tmp = ImageUtils.cloneArray(image);
		
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				Color pixel = tmp[i][j];
				int b = pixel.getBlue();

				tmp[i][j] = new Color(b,b,b);
			}
		}
		return tmp;
	}
	
	public static Color[][] reverseRgb(Color[][] image) {
		Color[][] tmp = ImageUtils.cloneArray(image);
		
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				Color pixel = tmp[i][j];
				int r = pixel.getRed();
				int g = pixel.getGreen();
				int b = pixel.getBlue();

				tmp[i][j] = new Color(b,g,r);
			}
		}
		return tmp;
	}
	
	public static Color[][] addGrayscale(Color[][] image) {
		Color[][] tmp = ImageUtils.cloneArray(image);
		
		for(int i = 0; i < tmp.length; i++) {
			for(int j = 0; j < tmp[i].length; j++) {
				Color pixel = tmp[i][j];
				int r = pixel.getRed();
				int g = pixel.getGreen();
				int b = pixel.getBlue();
				int grayScale = (r+g+b)/3;
				
				tmp[i][j] = new Color(grayScale,grayScale,grayScale);
			}
		}
		
		return tmp;
		
	}
	
	public static Color[][] colorFeather(Color[][] tmp) {
		ImageUtils utils = new ImageUtils();
		Color[][] image = utils.loadImage("src/LennaCV.png");
		
		for(int i = 0; i < tmp.length; i++) {
			for(int j = 0; j < tmp[i].length; j++) {
				Color pixel = image[i][j];
				int r = pixel.getRed();
				int g = pixel.getGreen();
				int b = pixel.getBlue();
				
				if (r >= 50 && r <= 150 && g <= 140) {
					tmp[i][j] = new Color(r,g,b);
				}
			}
		}
		
		return tmp;
		
	}
	
	public static Color[][] addPurple(Color[][] image) {
		Color[][] tmp = ImageUtils.cloneArray(image);
		
		for(int i = 0; i < tmp.length; i++) {
			for(int j = 0; j < tmp[i].length; j++) {
				Color pixel = tmp[i][j];
				int b = pixel.getBlue();
				
				if (i%2 == 0) {
					tmp[i][j] = new Color(102,0,153);
				}
				else {
					tmp[i][j] = new Color(b,b,b);
				}
			}

		}
		return tmp;
	}

}
