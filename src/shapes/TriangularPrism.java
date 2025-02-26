package shapes;

/**
 * A Triangular Prism.
 * A 3D shape triangular prism with a triangular base and a given height.
 * It extends the Prism class and implements the calculation of its base area.
 * 
 * @author Group Riju
 */
public class TriangularPrism extends Prism
{
	
	/**
     * Constructs a TriangularPrism with the height and side length.
     * @param height The height of the triangular prism.
     * @param side The length of one side of the equilateral triangular base.
     */
	public TriangularPrism(double height, double side) {
		super(height, side);
	}

	@Override
	public double calcBaseArea() {
		return (Math.sqrt(3) / 4) * Math.pow(getSide(), 2);
	}
	
	@Override
    public String toString() {
        return String.format(
            "TriangularPrism [Height: %.2f, Side: %.2f, Base Area: %.2f, Volume: %.2f]",
            getHeight(), getSide(), calcBaseArea(), calcVolume()
        );
    }
}
