package shapes;

/**
 * A Cylinder shape.
 * Extends Shape and implements base area and volume calculations.
 * @author Group Riju
 */
public class Cylinder extends Shape {
    private double radius; 

    /**
     * Constructor for Cylinder.
     * @param height The height of the cylinder.
     * @param radius The radius of the cylinder.
     */
    public Cylinder(double height, double radius) {
        super(height);
        this.radius = radius;
    }

    /**
     * Gets the radius of the cylinder.
     * @return The radius.
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public double calcBaseArea() {
    	return Math.PI * Math.pow(getRadius(), 2);
    }

    @Override
    public double calcVolume() {
        return Math.PI * Math.pow(radius, 2) * getHeight();
    }

    @Override
    public String toString() {
        return String.format(
            "Cylinder [Height: %.2f, Radius: %.2f, Base Area: %.2f, Volume: %.2f]",
            getHeight(), getRadius(), calcBaseArea(), calcVolume()
        );
    }
}
