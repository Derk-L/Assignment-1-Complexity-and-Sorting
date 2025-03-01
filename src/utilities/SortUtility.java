package utilities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class SortUtility {
   
    // Bubble Sort
    public static <T> void bubbleSort(List<T> list, Comparator<T> comparator) {
        if (list == null || comparator == null)
            throw new IllegalArgumentException("List and comparator must not be null.");
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    swap(list, j, j + 1);
                }
            }
        }
    }
 
    // Selection Sort
    public static <T> void selectionSort(List<T> list, Comparator<T> comparator) {
        if (list == null || comparator == null)
            throw new IllegalArgumentException("List and comparator must not be null.");
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
 
    // Insertion Sort
    public static <T> void insertionSort(List<T> list, Comparator<T> comparator) {
        if (list == null || comparator == null)
            throw new IllegalArgumentException("List and comparator must not be null.");
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
 
    // Merge Sort
    public static <T> void mergeSort(List<T> list, Comparator<T> comparator) {
        if (list == null || comparator == null)
            throw new IllegalArgumentException("List and comparator must not be null.");
        if (list.size() < 2) return;
        int mid = list.size() / 2;
        // Create independent copies of the sublists to avoid subList view issues.
        List<T> left = new ArrayList<>(list.subList(0, mid));
        List<T> right = new ArrayList<>(list.subList(mid, list.size()));
        mergeSort(left, comparator);
        mergeSort(right, comparator);
        merge(list, left, right, comparator);
    }
   
    private static <T> void merge(List<T> list, List<T> left, List<T> right, Comparator<T> comparator) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        // Append any remaining elements.
        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }
   
    // Quick Sort
    public static <T> void quickSort(List<T> list, int low, int high, Comparator<T> comparator) {
        if (list == null || comparator == null)
            throw new IllegalArgumentException("List and comparator must not be null.");
        if (low < high) {
            // Randomized pivot selection: choose a random index between low and high.
            Random rand = new Random();
            int randomIndex = low + rand.nextInt(high - low + 1);
            swap(list, randomIndex, high);
            int pi = partition(list, low, high, comparator);
            quickSort(list, low, pi - 1, comparator);
            quickSort(list, pi + 1, high, comparator);
        }
    }
   
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
 
    // Heap Sort
    public static <T> void heapSort(List<T> list, Comparator<T> comparator) {
        if (list == null || comparator == null)
            throw new IllegalArgumentException("List and comparator must not be null.");
        int n = list.size();
        // Build max heap.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i, comparator);
        }
        // Extract elements one by one from the heap.
        for (int i = n - 1; i > 0; i--) {
            swap(list, 0, i);
            heapify(list, i, 0, comparator);
        }
    }
   
    private static <T> void heapify(List<T> list, int n, int i, Comparator<T> comparator) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && comparator.compare(list.get(left), list.get(largest)) > 0) {
            largest = left;
        }
        if (right < n && comparator.compare(list.get(right), list.get(largest)) > 0) {
            largest = right;
        }
        if (largest != i) {
            swap(list, i, largest);
            heapify(list, n, largest, comparator);
        }
    }
 
    // Swap helper method
    private static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
