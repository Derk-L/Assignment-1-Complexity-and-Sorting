package shapes;

/**
 * The Cone Shape.
 * Extends Shape and implements base area and volume calculations.
 * @author Group Riju
 */

public class Cone extends Shape
{
	private double radius;
	
	/**
     * Constructor for Cone.
     * @param height The height of the cone.
     * @param radius The radius of the cone.
     */
	public Cone(double height, double radius) {
		super(height);
		this.radius = radius;
	}
	
	/**
     * Gets the radius of the cone.
     * @return The radius.
     */
	public double getRadius() {
		return radius;
	}

	@Override
	public double calcBaseArea() {
		return Math.PI * Math.pow(radius, 2);
	}
	
	
	@Override
	public double calcVolume() {
		return (Math.PI * Math.pow(radius, 2) * getHeight()) / 3;
	}
	
	@Override
    public String toString() {
        return String.format(
            "Cone [Height: %.2f, Radius: %.2f, Base Area: %.2f, Volume: %.2f]",
            getHeight(), getRadius(), calcBaseArea(), calcVolume()
        );
    }
}
