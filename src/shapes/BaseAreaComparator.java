package shapes;

import java.util.Comparator;

/**
 * Comparator for sorting shapes by base area in descending order.
 * 
 * @author Group Riju
 */
public class BaseAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return Double.compare(s2.calcBaseArea(), s1.calcBaseArea());
    }
}