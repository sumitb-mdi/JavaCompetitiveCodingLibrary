package DataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by sumit on 23/01/17.
 */
public class SBM_Heap_WithList<T extends Comparable> {
    public enum HEAP_TYPE {
        MAX,
        MIN
    }

    private List<T> heap;
    private HEAP_TYPE heapType;

    public SBM_Heap_WithList(List<T> list, HEAP_TYPE heapType) {
        this.heap = new ArrayList<>(list);
        this.heapType = heapType;
        buildHeap();
    }

    public SBM_Heap_WithList(T[] arr, HEAP_TYPE heapType) {
        this.heap = new ArrayList<>(Arrays.asList(arr));
        this.heapType = heapType;
        buildHeap();
    }


    private void heapify(int index) {
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);
        int largestNodeIndex = index;
        if (leftChildIndex < this.heap.size() && ((this.heapType == HEAP_TYPE.MAX ? 1 : -1) * (this.heap.get(largestNodeIndex).compareTo(this.heap.get(leftChildIndex))) < 0)) {
            largestNodeIndex = leftChildIndex;
        }
        if (rightChildIndex < this.heap.size() && ((this.heapType == HEAP_TYPE.MAX ? 1 : -1) * (this.heap.get(largestNodeIndex).compareTo(this.heap.get(rightChildIndex))) < 0)) {
            largestNodeIndex = rightChildIndex;
        }
        if (largestNodeIndex != index) {
            // heapify needed.

            //Swap :
            swapData(index, largestNodeIndex);


            //Heapify recursively.
            heapify(largestNodeIndex);
        }
    }

    private void swapData(int index1, int index2) {
        T temp = this.heap.get(index1);
        this.heap.set(index1, this.heap.get(index2));
        this.heap.set(index2, temp);
    }

    private int getLeftChildIndex(int index) {
        return (2 * index) + 1;
    }

    private int getRightChildIndex(int index) {
        return (2 * index) + 2;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private void buildHeap() {
        for (int i = (this.heap.size() / 2) - 1; i >= 0; i--) {
            heapify(i);
        }
    }


    public void add(T element) {
        this.heap.add(element);
        int currentIndex = this.heap.size() - 1;  //where the new element got added.
        int parentIndex = getParentIndex(currentIndex);

        while (parentIndex >= 0 && (((this.heapType == HEAP_TYPE.MAX ? 1 : -1)) * this.heap.get(parentIndex).compareTo(this.heap.get(currentIndex))) < 0) {
            swapData(parentIndex, currentIndex);
            currentIndex = parentIndex;
            parentIndex = getParentIndex(currentIndex);
        }
    }

    public Optional<T> remove(T element) {
        Optional<T> removedElement = Optional.empty();
        for (int i = 0; i < this.heap.size(); i++) {
            if (this.heap.get(i).equals(element)) {
                swapData(i, this.heap.size() - 1);
                removedElement = Optional.of(this.heap.remove(this.heap.size() - 1));
                heapify(i);
                break;
            }
        }
        return removedElement;
    }

    public Optional<T> getTopElement() {
        if (this.heap.size() > 0) {
            return Optional.of(this.heap.get(0));
        } else {
            return Optional.empty();
        }
    }


    public void print() {
        System.out.println("Printing heap.");
        for (T t : this.heap) {
            System.out.print(" " + t);
        }
        System.out.println();
    }

    //Driver method for testing.
    public static void main(String[] args) {
        System.out.println("Hello !!");
        SBM_Heap_WithList<Integer> maxHeap_withList = new SBM_Heap_WithList<>(new Integer[]{1, 2, 3, 4, 6, 7, 8}, HEAP_TYPE.MIN);
        maxHeap_withList.print();
        maxHeap_withList.add(5);
        maxHeap_withList.print();
        maxHeap_withList.remove(7);
        maxHeap_withList.print();
    }
}
