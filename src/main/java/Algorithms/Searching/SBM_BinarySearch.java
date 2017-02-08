package Algorithms.Searching;

/**
 * Created by sumit on 28/01/17.
 */
public class SBM_BinarySearch {

    // Assumption : No number is repeated.
    public static int binarySearchLowestIndexGreaterThanEqualToTarget(final int[] arr, int start, int end, int target) {
        if (start >= end || start == end - 1) {
            while (start < arr.length) {
                if (arr[start] >= target)
                    return start;
                start += 1;
            }
            return -1;
        }

        int mid = (start + end) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) {
            return binarySearchLowestIndexGreaterThanEqualToTarget(arr, mid, end, target);
        } else {
            return binarySearchLowestIndexGreaterThanEqualToTarget(arr, start, mid, target);
        }
    }


    public static int binarySearch (final int[] arr, int start, int end, int target) {
        if (start < 0 || end >= arr.length || start > end) {
            return -1;
        }

        int mid = (start + end) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] > target) {
            return binarySearch(arr, start, mid - 1, target);
        } else {
            return binarySearch(arr, mid + 1, end, target);
        }
    }

    public static int smallestNumGreaterThanEqualTo (final int[] arr, int start, int end, int target) {
        if (start < 0 || end > arr.length) {
            System.out.println("Help");
            return -1;
        }

        if (arr[start] > target) {
            return start;
        }

        if (start == end) {
            if (arr[start] > target) return start;
            else {
                while (arr[start] < target) start += 1;
                return start;
            }
        }

        int mid = (start + end) / 2;
        if (arr[mid] == target) {
//            while (arr[mid] == target) mid += 1;
            return mid;
        } else if (arr[mid] < target) {
            return smallestNumGreaterThanEqualTo(arr, mid + 1, end, target);
        } else {
            return smallestNumGreaterThanEqualTo(arr, start, mid - 1, target);
        }
    }

    public static int greatestNumSmallerThanEqualTo (final int[] arr, int start, int end, int target) {
        if (start < 0 || end > arr.length) {
            System.out.println("Help");
            return -1;
        }

        if (arr[end] < target) {
            return end;
        }

        if (start == end) {
            if (arr[start] < target) return start;
            else {
                while (arr[start] > target) start -=1;
                return start;
            }

        }

        int mid = (start + end) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return greatestNumSmallerThanEqualTo(arr, mid + 1, end, target);
        } else {
            return greatestNumSmallerThanEqualTo(arr, start, mid - 1, target);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 5, 7, 9, 12, 20};
        int index = greatestNumSmallerThanEqualTo(arr, 0, arr.length - 1, 2);
        System.out.println(index);

    }
}
