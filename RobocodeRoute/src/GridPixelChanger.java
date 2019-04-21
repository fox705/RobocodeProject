
public class GridPixelChanger {

	public static double gridToPixel(int grid) {

		return (grid * 64) + 32;
	}

	public static int pixelToGrid(double pixel) {

		return (int) (pixel - 32) / 64;
	}

}
