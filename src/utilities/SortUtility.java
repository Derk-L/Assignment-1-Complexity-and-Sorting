package utilities;
 
import java.util.Comparator;
import java.util.List;
/**
 * Utility class that provides sorting algorithms for sorting a list of elements.
 * 
 * Sorting Algorithms:
 * - Bubble Sort
 * - Selection Sort
 * - Insertion Sort
 * - Merge Sort
 * - Quick Sort
 * - Heap Sort
 *
 * @author Group Riju
 */
public class SortUtility {
   
	/**
     * Sorts the list using Bubble Sort.
     *
     * @param <T> The type of elements in the list.
     * @param list The list to be sorted.
     * @param comparator The comparator defining the sorting order.
     */
    public static <T> void bubbleSort(List<T> list, Comparator<T> comparator) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    swap(list, j, j + 1);
                }
            }
        }
    }
 
    /**
     * Sorts the list using Selection Sort.
     *
     * @param <T> The type of elements in the list.
     * @param list The list to be sorted.
     * @param comparator The comparator defining sorting order.
     */
    public static <T> void selectionSort(List<T> list, Comparator<T> comparator) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(list.get(j), list.get(minIdx)) < 0) {
                    minIdx = j;
                }
            }
            swap(list, i, minIdx);
        }
    }
 
    /**
     * Sorts the list using Insertion Sort.
     *
     * @param <T> The type of elements in the list.
     * @param list The list to be sorted.
     * @param comparator The comparator defining the sorting order.
     */
    public static <T> void insertionSort(List<T> list, Comparator<T> comparator) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            T key = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
 
    /**
     * Sorts the list using Merge Sort.
     *
     * @param <T> The type of elements in the list.
     * @param list The list to be sorted.
     * @param comparator The comparator defining the sorting order.
     */
    public static <T> void mergeSort(List<T> list, Comparator<T> comparator) {
        if (list.size() < 2) return;
        int mid = list.size() / 2;
        List<T> left = list.subList(0, mid);
        List<T> right = list.subList(mid, list.size());
        mergeSort(left, comparator);
        mergeSort(right, comparator);
        merge(list, left, right, comparator);
    }
   
    /**
     * Merges two sorted sublists into one sorted list.
     *
     * @param <T> The type of elements in the list.
     * @param list The original list to be merged.
     * @param left The left sublist.
     * @param right The right sublist.
     * @param comparator The comparator defining the sorting order.
     */
    private static <T> void merge(List<T> list, List<T> left, List<T> right, Comparator<T> comparator) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) list.set(k++, left.get(i++));
        while (j < right.size()) list.set(k++, right.get(j++));
    }
   
    /**
     * Sorts the list using Quick Sort.
     *
     * @param <T> The type of elements in the list.
     * @param list The list to be sorted.
     * @param low The starting index.
     * @param high The ending index.
     * @param comparator The comparator defining the sorting order.
     */
    public static <T> void quickSort(List<T> list, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pi = partition(list, low, high, comparator);
            quickSort(list, low, pi - 1, comparator);
            quickSort(list, pi + 1, high, comparator);
        }
    }
   
    /**
     * Partitions the list for Quick Sort.
     *
     * @param <T> The type of elements in the list.
     * @param list The list to be partitioned.
     * @param low The starting index.
     * @param high The ending index.
     * @param comparator The comparator defining the sorting order.
     * @return The partition index.
     */
    private static <T> int partition(List<T> list, int low, int high, Comparator<T> comparator) {
        T pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) < 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }
 
    /**
     * Sorts the list using Heap Sort.
     *
     * @param <T> The type of elements in the list.
     * @param list The list to be sorted.
     * @param comparator The comparator defining the sorting order.
     */
    public static <T> void heapSort(List<T> list, Comparator<T> comparator) {
        int n = list.size();
        for (int i = n / 2 - 1; i >= 0; i--) heapify(list, n, i, comparator);
        for (int i = n - 1; i > 0; i--) {
            swap(list, 0, i);
            heapify(list, i, 0, comparator);
        }
    }
   
    /**
     * Heapifies a subtree for Heap Sort.
     *
     * @param <T> The type of elements in the list.
     * @param list The list to be heapified.
     * @param n The size of the heap.
     * @param i The root index.
     * @param comparator The comparator defining the sorting order.
     */
    private static <T> void heapify(List<T> list, int n, int i, Comparator<T> comparator) {
        int largest = i, left = 2 * i + 1, right = 2 * i + 2;
        if (left < n && comparator.compare(list.get(left), list.get(largest)) > 0) largest = left;
        if (right < n && comparator.compare(list.get(right), list.get(largest)) > 0) largest = right;
        if (largest != i) {
            swap(list, i, largest);
            heapify(list, n, largest, comparator);
        }
    }
 
    /**
     * Swaps two elements in the list.
     *
     * @param <T> The type of elements in the list.
     * @param list The list containing the elements.
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    private static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

	


