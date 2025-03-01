package appDomain;
 
 
import shapes.*;
import utilities.SortUtility;
import java.io.*;
import java.util.*;
 
/**
 * Main method that handles command-line arguments, loads shapes, sorts them,
 * and displays the results.
 *
 * @param args Command-line arguments (-fres/filename -t<compareType> -s<sortType>)
 */
public class AppDriver {
    public static void main(String[] args) {
        // Variables to hold command-line inputs
        String fileName = null;
        Character compareType = null;
        Character sortType = null;
        
        boolean displaySorted = true;
        
        // Parse all command line arguments (order insensitive)
        for (String arg : args) {
            String lowerArg = arg.toLowerCase();
            if (lowerArg.startsWith("-f")) {
                // Extract file name (everything after "-f")
                fileName = arg.substring(2);
            } else if (lowerArg.startsWith("-t")) {
                // Extract comparison type (the character immediately following "-t")
                if (arg.length() > 2) {
                    compareType = Character.toLowerCase(arg.charAt(2));
                }
            } else if (lowerArg.startsWith("-s")) {
                // Extract sort type (the character immediately following "-s")
                if (arg.length() > 2) {
                    sortType = Character.toLowerCase(arg.charAt(2));
                    // Map 'z' (your custom sort) to 'h' (heap sort in our case)
                    if (sortType == 'z') {
                        sortType = 'h';
                    }
                }
            }
        }
        
        // Validate that all required arguments were provided
        if (fileName == null || compareType == null || sortType == null) {
            System.out.println("Usage: java -jar Sort.jar -f<file_name> -t<compareType> -s<sortType>");
            System.out.println("Example: java -jar Sort.jar -fshapes1.txt -Tv -Sb");
            return;
        }
        
        // Incorrect command lines display message to user
        if(compareType != 'h' && compareType != 'a' && compareType != 'v' ) 
        {
        	System.out.println("The compare type " + compareType + " is not a valid option. Valid options are Height (h), base area (a), volume (v).");
        	displaySorted = false;
        }
        
        if(sortType != 'b' && sortType != 's' && sortType != 'i' && sortType != 'm' && sortType != 'q' && sortType != 'h') 
        {
        	System.out.println("The sort type " + sortType + " is not a valid option. Valid options are Bubble (b), Selection (s), Insertion (i), Merge (m), Quick (q), Heap (z).");
        	displaySorted = false;
        }
        
        // Load shapes from file
        List<Shape> shapes = loadShapesFromFile(fileName);
        if (shapes.isEmpty()) {
            System.out.println("No shapes loaded from file.");
            return;
        }
        
        // Get the appropriate comparator
        Comparator<Shape> comparator = getComparator(compareType);
        if (comparator == null) {
            System.out.println("Invalid comparison type.");
            return;
        }
        
        // Sort the shapes and benchmark the sorting time
        long startTime = System.nanoTime();
        applySortingAlgorithm(shapes, sortType, comparator);
        long endTime = System.nanoTime();
        
        
        if(displaySorted == true) {
	        // Print only: first, every 1000th, and the last sorted shape
	        System.out.println("Selected Sorted Shapes (first element, every 1000th, and last element):");
	        int size = shapes.size();
	        for (int i = 0; i < size; i++) {
	            if (i == 0 || i % 1000 == 0 || i == size - 1) {
	                System.out.println("Index " + i + ": " + shapes.get(i));
	            }
	            
	            // DEBUGGING LINE - COMMENT OUT
	            //System.out.println("Index " + i + ": " + shapes.get(i));
	        }
	        
	        System.out.println("Sorting Time: " + (endTime - startTime) / 1e6 + " ms");
        }
    }
    
    /**
     * Reads a file and loads shape data into a list.
     *
     * @param fileName The name of the file containing shape data.
     * @return A list of Shape objects.
     */
    private static List<Shape> loadShapesFromFile(String fileName) {
        List<Shape> shapes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
            	String[] parts = line.trim().split("\\s+");
            	if (parts.length == 3) 
            	{
            		shapes.add(parseShape(line));
            	}
            	
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return shapes;
    }
    
    /**
     * Parses a line of shape data and creates a corresponding Shape object.
     *
     * @param line A string representing a shape (e.g., "Cone 29639.64 29106.68").
     * @return A Shape object corresponding to the parsed line.
     * @throws IllegalArgumentException If the shape type is unknown.
     */
    private static Shape parseShape(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid shape format: " + line);
        }
        String type = parts[0];
        double height = Double.parseDouble(parts[1]);
        double base = Double.parseDouble(parts[2]);
        switch (type.toLowerCase()) {
            case "cone": return new Cone(height, base);
            case "cylinder": return new Cylinder(height, base);
            case "squareprism": return new SquarePrism(height, base);
            case "triangularprism": return new TriangularPrism(height, base);
            case "pentagonalprism": return new PentagonalPrism(height, base);
            case "octagonalprism": return new OctagonalPrism(height, base);
            case "pyramid": return new Pyramid(height, base);
            default: throw new IllegalArgumentException("Unknown shape: " + type);
        }
    }
    
    /**
     * Returns the appropriate comparator for sorting based on the compare type.
     *
     * @param compareType The comparison type ('h' for height, 'a' for base area, 'v' for volume).
     * @return A Comparator for sorting shapes, or null if an invalid type is provided.
     */
    private static Comparator<Shape> getComparator(char compareType) {
        switch (compareType) {
            case 'h': return (s1, s2) -> Double.compare(s2.getHeight(), s1.getHeight());
            case 'a': return new BaseAreaComparator();  
            case 'v': return new VolumeComparator();
            default:  return null;
        }
    }
    
    /**
     * Applies the selected sorting algorithm to the given list of shapes.
     *
     * @param shapes    The list of shapes to be sorted.
     * @param sortType  The sorting algorithm type ('b', 's', 'i', 'm', 'q', 'h').
     * @param comparator The comparator used to compare shapes.
     */
    private static void applySortingAlgorithm(List<Shape> shapes, char sortType, Comparator<Shape> comparator) {
        switch (sortType) {
            case 'b': SortUtility.bubbleSort(shapes, comparator); break;
            case 's': SortUtility.selectionSort(shapes, comparator); break;
            case 'i': SortUtility.insertionSort(shapes, comparator); break;
            case 'm': SortUtility.mergeSort(shapes, comparator); break;
            case 'q': SortUtility.quickSort(shapes, 0, shapes.size() - 1, comparator); break;
            case 'h': SortUtility.heapSort(shapes, comparator); break;
            default: break;
        }
    }
}