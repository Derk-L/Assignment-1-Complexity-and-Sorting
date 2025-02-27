package shapes;

import java.util.Comparator;

/**
 * Comparator for sorting shapes by base area.
 * 
 * @author Group Riju
 */
public class BaseAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return Double.compare(s1.calcBaseArea(), s2.calcBaseArea());
    }
}