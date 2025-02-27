package appDomain;
 
 
import shapes.*;
import utilities.SortUtility;
import java.io.*;
import java.util.*;
 
//TODO Auto-generated method stub
 
        // refer to demo001 BasicFileIO.java for a simple example on how to
        // read data from a text file
 
        // refer to demo01 Test.java for an example on how to parse command
        // line arguments and benchmarking tests
 
        // refer to demo02 Student.java for comparable implementation, and
        // NameCompare.java or GradeCompare for comparator implementations
 
        // refer to demo02 KittySort.java on how to use a custom sorting
        // algorithm on a list of comparables to sort using either the
        // natural order (comparable) or other orders (comparators)
 
        public class AppDriver {
            public static void main(String[] args) {
                if (args.length < 3) {
                    System.out.println("Usage: java AppDriver <filename> <compareType> <sortType>");
                    return;
                }
               
                String fileName = args[0];
                char compareType = args[1].charAt(0);
                char sortType = args[2].charAt(0);
                List<Shape> shapes = loadShapesFromFile(fileName);
               
                if (shapes.isEmpty()) {
                    System.out.println("No shapes loaded from file.");
                    return;
                }
               
                Comparator<Shape> comparator = getComparator(compareType);
                if (comparator == null) {
                    System.out.println("Invalid comparison type.");
                    return;
                }
               
                long startTime = System.nanoTime();
                applySortingAlgorithm(shapes, sortType, comparator);
                long endTime = System.nanoTime();
               
                System.out.println("Sorted Shapes:");
                for (Shape shape : shapes) {
                    System.out.println(shape);
                }
               
                System.out.println("Sorting Time: " + (endTime - startTime) / 1e6 + " ms");
            }
       
            private static List<Shape> loadShapesFromFile(String fileName) {
                List<Shape> shapes = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        shapes.add(parseShape(line));
                    }
                } catch (IOException e) {
                    System.out.println("Error reading file: " + e.getMessage());
                }
                return shapes;
            }
           
            private static Shape parseShape(String line) {
                String[] parts = line.split(",");
                String type = parts[0];
                double height = Double.parseDouble(parts[1]);
                double base = Double.parseDouble(parts[2]);
               
                switch (type.toLowerCase()) {
                    case "cone": return new Cone(height, base);
                    case "cylinder": return new Cylinder(height, base);
                    case "squareprism": return new SquarePrism(height, base);
                    case "triangularprism": return new TriangularPrism(height, base);
                    default: throw new IllegalArgumentException("Unknown shape: " + type);
                }
            }
           
            private static Comparator<Shape> getComparator(char compareType) {
                switch (compareType) {
                    case 'h': return Comparator.naturalOrder();
                    case 'b': return new BaseAreaComparator();
                    case 'v': return new VolumeComparator();
                    default: return null;
                }
            }
           
            private static void applySortingAlgorithm(List<Shape> shapes, char sortType, Comparator<Shape> comparator) {
                switch (sortType) {
                    case 'b': SortUtility.bubbleSort(shapes, comparator); break;
                    case 's': SortUtility.selectionSort(shapes, comparator); break;
                    case 'i': SortUtility.insertionSort(shapes, comparator); break;
                    case 'm': SortUtility.mergeSort(shapes, comparator); break;
                    case 'q': SortUtility.quickSort(shapes, 0, shapes.size() - 1, comparator); break;
                    case 'h': SortUtility.heapSort(shapes, comparator); break;
                    default: System.out.println("Invalid sorting type.");
                }
            }
        }
