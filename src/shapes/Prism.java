package shapes;

/**
 * Abstract class of a Prism shape.
 * Extends Shape and requires base area calculation from subclasses.
 * 
 * @author Group Riju
 */
public abstract class Prism extends Shape
{
	private double side;
	
	/**
     * Constructor for Prism.
     * @param height The height of the prism.
     * @param side The length of a side of the base shape.
     */
	public Prism(double height, double side) {
		super(height);
		this.side = side;
	}
	
	/**
     * Gets the side length of the base.
     * @return The side length.
     */
	public double getSide() {
		return side;
	}

	@Override
	public double calcVolume() {
		return calcBaseArea() * getHeight();
	}
	
	@Override
    public abstract double calcBaseArea();
}
