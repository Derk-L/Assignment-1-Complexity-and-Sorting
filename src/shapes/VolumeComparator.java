package shapes;

import java.util.Comparator;

/**
 * Comparator for sorting shapes by volume in descending order.
 * 
 * @author Group Riju
 */
public class VolumeComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return Double.compare(s2.calcVolume(), s1.calcVolume()); 
    }
}
