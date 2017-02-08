package DataStructures;

/**
 * Created by sumit on 24/01/17.
 */
public class SBM_SegmentTree {
    private final int[] segmentTree;
    private final int originalArrLength;

    public SBM_SegmentTree(final int[] originalArr) {
        this.originalArrLength = originalArr.length;
        segmentTree = new int[getMaxSizeOfSegmentTree(this.originalArrLength)];
        constructSegmentTree(originalArr, 0, 0, originalArr.length - 1);
    }

    private int getMaxSizeOfSegmentTree(int lengthOfArr) {
        int X = (int)Math.ceil(Math.log(lengthOfArr) / Math.log(2));
        return (int)((2 * Math.pow(2, X)) - 1);
    }


    private int getMid (int start, int end) {
        return (start + end) / 2;
    }


    private int constructSegmentTree (final int[] originalArr, int treeCurrentIndex, int arrStartIndex, int arrEndIndex) {
        if (arrStartIndex >= originalArr.length && arrEndIndex >= originalArr.length && arrStartIndex > arrEndIndex) {
            return 0;
        }

        if (arrStartIndex == arrEndIndex) {
            this.segmentTree[treeCurrentIndex] = originalArr[arrStartIndex];
            return this.segmentTree[treeCurrentIndex];
        }

        int mid = getMid(arrStartIndex, arrEndIndex);
        int currentsLeftChildIndex = getLeftChildIndex(treeCurrentIndex);
        int currentRightChildIndex = getRightChildIndex(treeCurrentIndex);

        this.segmentTree[treeCurrentIndex] = constructSegmentTree(originalArr, currentsLeftChildIndex, arrStartIndex, mid) +
                                            constructSegmentTree(originalArr, currentRightChildIndex, mid + 1, arrEndIndex);

        return this.segmentTree[treeCurrentIndex];
    }

    private int getRightChildIndex(int index) {
        return (2 * index) + 2;
    }

    private int getLeftChildIndex(int index) {
        return (2 * index) + 1;
    }

    private int getSumOfRange (int currentTreeIndex, int arrStartIndex, int arrEndIndex, final int queriedLeftArrIndex, final int queriedRightArrIndex) {
        if (arrStartIndex >= queriedLeftArrIndex && arrEndIndex <= queriedRightArrIndex) {
            // Complete In Range
            return this.segmentTree[currentTreeIndex];
        }

        if (arrStartIndex > queriedRightArrIndex || arrEndIndex < queriedLeftArrIndex) {
            // Out of Range
            return 0;
        }
        int mid = getMid(arrStartIndex, arrEndIndex);
        return getSumOfRange(getLeftChildIndex(currentTreeIndex), arrStartIndex, mid, queriedLeftArrIndex, queriedRightArrIndex) +
                getSumOfRange(getRightChildIndex(currentTreeIndex), mid + 1, arrEndIndex, queriedLeftArrIndex, queriedRightArrIndex);
    }

    private void addValueToArrIndex (int valueToAdd, int arrIndex, int currentTreeIndex, int arrStartIndex, int arrEndIndex) {
        if (arrIndex <= arrEndIndex && arrIndex >= arrStartIndex) {
            this.segmentTree[currentTreeIndex] += valueToAdd;

            if (arrStartIndex != arrEndIndex) {
                int mid = getMid(arrStartIndex, arrEndIndex);
                addValueToArrIndex(valueToAdd, arrIndex, getLeftChildIndex(currentTreeIndex), arrStartIndex, mid);
                addValueToArrIndex(valueToAdd, arrIndex, getRightChildIndex(currentTreeIndex), mid + 1, arrEndIndex);
            }
        }
    }


    public int getSumOfRange (final int queriedLeftArrIndex, final int queriedRightArrIndex) {
        return getSumOfRange(0, 0, this.originalArrLength - 1, queriedLeftArrIndex, queriedRightArrIndex);
    }

    public void addValueToArrIndex (int valueToAdd, int arrIndex) {
        addValueToArrIndex(valueToAdd, arrIndex, 0, 0, this.originalArrLength - 1);
    }


    // Test Driver method.
    public static void main(String[] args) {
        int arr[] = new int[] {1, 3, 5, 7, 9, 11};
        SBM_SegmentTree segmentTree = new SBM_SegmentTree(arr);

        System.out.println(segmentTree.getSumOfRange(0, 4));
        System.out.println(segmentTree.getSumOfRange(2, 4));

        segmentTree.addValueToArrIndex(2, 1);

        System.out.println(segmentTree.getSumOfRange(1, 2));
        System.out.println(segmentTree.getSumOfRange(4, 4));
    }
}
