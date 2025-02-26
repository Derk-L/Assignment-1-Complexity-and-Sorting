package shapes;

/**
 * Represents an Octagonal Prism.
 * An 3D octagonal prism with an octagonal base and a given height.
 * It extends the Prism class and implements the calculation of its base area.
 * 
 * @author Group Riju
 */
public class OctagonalPrism extends Prism
{
	/**
     * Constructs an OctagonalPrism with the specified height and side length.
     * @param height The height of the octagonal prism.
     * @param side The length of one side of the octagonal base.
     */
	public OctagonalPrism(double height, double side) {
		super(height, side);
	}

	@Override
	public double calcBaseArea() {
		return 2 * (1 + Math.sqrt(2)) * Math.pow(getSide(), 2);
	}
	
	@Override
    public String toString() {
        return String.format(
            "OctagonalPrism [Height: %.2f, Side: %.2f, Base Area: %.2f, Volume: %.2f]",
            getHeight(), getSide(), calcBaseArea(), calcVolume()
        );
    }
}
