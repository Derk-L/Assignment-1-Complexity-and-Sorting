package shapes;

/**
 * Represents a Square Prism.
 * A 3D square prism  with a square base and a given height.
 * It extends the Prism class and implements the calculation of its base area.
 * 
 * @author Group Riju
 */
public class SquarePrism extends Prism
{
	/**
     * Constructor for the SquarePrism with the height and side length.
     * @param height The height of the square prism.
     * @param side The length of the square's side.
     */
	public SquarePrism(double height, double side) {
		super(height, side);
	}

	@Override
	public double calcBaseArea() {
		return getSide() * getSide();
	}

	@Override
	public String toString() {
	    return String.format(
	        "SquarePrism [ Base Area: %.2f, Side: %.2f, Volume: %.2f, Height: %.2f ]",
	        calcBaseArea(), getSide(), calcVolume(), getHeight()
	    );
	}
}
