package shapes;

/**
 * The Pyramid shape.
 * Extends Shape and implements base area and volume calculations.
 * 
 * @author Group Riju
 */
public class Pyramid extends Shape
{
	private double side;
	
	/**
     * Constructor for Pyramid.
     * @param height The height of the pyramid.
     * @param side The length of a side of the square base.
     */
	public Pyramid(double height, double side) {
		super(height);
		this.side = side;
	}
	
	/**
     * Gets the side length of the square base.
     * @return The side length.
     */
	public double getSide() {
		return side;
	}

	@Override
	public double calcBaseArea() {
		return Math.pow(getSide(), 2);
	}

	@Override
	public double calcVolume() {
		return (1.0 / 3) * calcBaseArea() * getHeight();
	}
	
	@Override
    public String toString() {
        return String.format(
            "Pyramid [Height: %.2f, Side: %.2f, Base Area: %.2f, Volume: %.2f]",
            getHeight(), getSide(), calcBaseArea(), calcVolume()
        );
    }
}
