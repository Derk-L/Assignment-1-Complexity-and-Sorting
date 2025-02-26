package shapes;
/**
 * Super class for shapes height, volume and area.
 * Implements Comparable to allow sorting by height.
 * @author Group Riju
 */

public abstract class Shape implements Comparable<Shape>
{
	private double height;
	
	 /**
     * Constructor for Shape.
     * @param height The height of the shape.
     */
	public Shape(double height) {
		super();
		this.height = height;
	}
	
	/**
     * Gets the height of the shape.
     * @return height of the shape.
     */
	public double getHeight() {
		return height;
	}
	
	@Override
	public int compareTo(Shape other) {
		return Double.compare(this.height, other.height);
	}
	
	/**
     * Abstract method to calculate the base area of a shape or prism.
     * @return the base area.
     */
	public abstract double calcBaseArea();
	
	/**
     * Abstract method to calculate the volume of a shape or prism.
     * @return the volume.
     */
	public abstract double calcVolume();

}
