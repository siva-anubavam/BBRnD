package mypackage;


import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.container.HorizontalFieldManager;

public class CustomEditField extends HorizontalFieldManager {

	private EditField rightButton;

	protected Bitmap img_Header = null;
	protected EditField header_FieldRight = null;

	String headerText;

	public CustomEditField(String text) {
		super(USE_ALL_WIDTH);
		header_FieldRight = new EditField(BasicEditField.NO_NEWLINE);
		setRightButton(header_FieldRight);			
		Font f = null;
		FontFamily ff[] = FontFamily.getFontFamilies();
		f = ff[0].getFont(FontFamily.SCALABLE_FONT, 20).derive(Font.PLAIN);		
		header_FieldRight.setFont(f);		
		header_FieldRight.setPadding(20, 2, 20, 2);	
		add(header_FieldRight);		
	}

	protected void paintBackground(Graphics g) {
		img_Header = Bitmap.getBitmapResource("inputbox.png");
		img_Header = resizeBitmap(img_Header, img_Header.getWidth() + 30,
				img_Header.getHeight() + 10);
		g.drawBitmap(15, 0, img_Header.getWidth(), img_Header.getHeight(),
				img_Header, 0, 0);
		g.setColor(Color.BLACK);
		g.setFont(getFont().derive(Font.PLAIN, 15));
	}

	protected void sublayout(int maxWidth, int maxHeight) {
		super.sublayout(maxWidth, maxHeight);
		EditField rightButton = getRightButton();
		if (rightButton != null && equals(rightButton.getManager())) {
			int y = 0;
			setPositionChild(rightButton, 20, y);
		}
	}

	private EditField getRightButton() {
		return rightButton;
	}

	private void setRightButton(EditField rightButton) {
		this.rightButton = rightButton;
	}

	public static Bitmap resizeBitmap(Bitmap image, int width, int height) {
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();

		// Need an array (for RGB, with the size of original image)
		int rgb[] = new int[imageWidth * imageHeight];

		// Get the RGB array of image into "rgb"
		image.getARGB(rgb, 0, imageWidth, 0, 0, imageWidth, imageHeight);

		// Call to our function and obtain rgb2
		int rgb2[] = rescaleArray(rgb, imageWidth, imageHeight, width, height);

		// Create an image with that RGB array
		Bitmap temp2 = new Bitmap(width, height);

		temp2.setARGB(rgb2, 0, width, 0, 0, width, height);

		return temp2;
	}

	private static int[] rescaleArray(int[] ini, int x, int y, int x2, int y2) {
		int out[] = new int[x2 * y2];

		for (int yy = 0; yy < y2; yy++) {
			int dy = yy * y / y2;
			for (int xx = 0; xx < x2; xx++) {
				int dx = xx * x / x2;
				out[(x2 * yy) + xx] = ini[(x * dy) + dx];
			}
		}
		return out;
	}
}