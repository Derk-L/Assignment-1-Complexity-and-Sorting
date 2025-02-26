package shapes;

/**
 *A pentagonal Prism.
 * A 3D pentagonal prism with a pentagonal base and a given height.
 * It extends the Prism class and implements the calculation of its base area.
 * 
 * @author Group Riju
 */

public class PentagonalPrism extends Prism
{
	/**
     * Constructs a PentagonalPrism with the specified height and side length.
     * @param height The height of the pentagonal prism.
     * @param side The length of one side of the pentagonal base.
     */
	public PentagonalPrism(double height, double side) {
		super(height, side);
	}

	@Override
	public double calcBaseArea() {
		return (5.0 / 4) * Math.pow(getSide(), 2) * Math.tan(Math.toRadians(54));
	}
	
	@Override
    public String toString() {
        return String.format(
            "PentagonalPrism [Height: %.2f, Side: %.2f, Base Area: %.2f, Volume: %.2f]",
            getHeight(), getSide(), calcBaseArea(), calcVolume()
        );
    }
}
