package DataStructures;

/**
 * Created by sumit on 19/01/17.
 */
public class SBM_MinHeap {

    private static void heapify (int[] arr, int size, int index) {
        int leftChildIndex = (2 * index) + 1;
        int rightChildIndex = (2 * index) + 2;
        int largestNodeIndex = index;
        int temp;
        if (leftChildIndex < size && arr[leftChildIndex] < arr[largestNodeIndex]) {
            largestNodeIndex = leftChildIndex;
        }
        if (rightChildIndex < size && arr[rightChildIndex] < arr[largestNodeIndex]) {
            largestNodeIndex = rightChildIndex;
        }
        if (largestNodeIndex != index) {
            // heapify needed.

            //Swap :
            temp = arr[index];
            arr[index] = arr[largestNodeIndex];
            arr[largestNodeIndex] = temp;

            //Heapify recursively.
            heapify(arr, size, largestNodeIndex);
        }
    }

    public static void buildMinHeap (int[] arr) {
        for (int i = (arr.length / 2) - 1; i >= 0; i--) {
            heapify(arr, arr.length, i);
        }
    }


    //Driver method - for testing.

}
