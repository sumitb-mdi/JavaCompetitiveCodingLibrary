package DataStructures; /**
 * Created by sumit on 25/01/17.
 */

/**
 * Created by sumit on 25/01/17.
 */

class SBM_MaxSegmentTree {
    private final int[] segmentTree;
    private final int originalArrLength;

    public SBM_MaxSegmentTree(final int[] originalArr) {
        this.originalArrLength = originalArr.length;
        segmentTree = new int[getMaxSizeOfSegmentTree(this.originalArrLength)];
        constructSegmentTree(originalArr, 0, 0, originalArr.length - 1);
    }

    private int getMaxSizeOfSegmentTree(int lengthOfArr) {
        int X = (int) Math.ceil(Math.log(lengthOfArr) / Math.log(2));
        return (int) ((2 * Math.pow(2, X)) - 1);
    }

    private int getMax (int a, int b) {
        return a > b ? a : b;
    }

    private int getMid(int start, int end) {
        return (start + end) / 2;
    }


    private int constructSegmentTree(final int[] originalArr, int treeCurrentIndex, int arrStartIndex, int arrEndIndex) {
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

        int leftSideMax = constructSegmentTree(originalArr, currentsLeftChildIndex, arrStartIndex, mid);
        int rightSideMax = constructSegmentTree(originalArr, currentRightChildIndex, mid + 1, arrEndIndex);

        this.segmentTree[treeCurrentIndex] = getMax(leftSideMax, rightSideMax);

        return this.segmentTree[treeCurrentIndex];
    }

    private int getRightChildIndex(int index) {
        return (2 * index) + 2;
    }

    private int getLeftChildIndex(int index) {
        return (2 * index) + 1;
    }


    private int getMinFromRange(int currentTreeIndex, int arrStartIndex, int arrEndIndex, final int queriedLeftArrIndex, final int queriedRightArrIndex) {
        if (arrStartIndex >= queriedLeftArrIndex && arrEndIndex <= queriedRightArrIndex) {
            // Complete In Range
            return this.segmentTree[currentTreeIndex];
        }

        if (arrStartIndex > queriedRightArrIndex || arrEndIndex < queriedLeftArrIndex) {
            // Out of Range
            return Integer.MIN_VALUE;
        }


        int mid = getMid(arrStartIndex, arrEndIndex);
        int leftSideMin = getMinFromRange(getLeftChildIndex(currentTreeIndex), arrStartIndex, mid, queriedLeftArrIndex, queriedRightArrIndex);
        int rightSideMin = getMinFromRange(getRightChildIndex(currentTreeIndex), mid + 1, arrEndIndex, queriedLeftArrIndex, queriedRightArrIndex);

        return getMax(leftSideMin, rightSideMin);
    }


    private int updateValue(int newValue, int arrIndex, int currentTreeIndex, int arrStartIndex, int arrEndIndex) {
        if (arrIndex == arrStartIndex && arrIndex == arrEndIndex) {
            this.segmentTree[currentTreeIndex] = newValue;
        } else if (arrIndex <= arrEndIndex && arrIndex >= arrStartIndex) {
            int mid = getMid(arrStartIndex, arrEndIndex);
            int leftSideValue = updateValue(newValue, arrIndex, getLeftChildIndex(currentTreeIndex), arrStartIndex, mid);
            int rightSideValue = updateValue(newValue, arrIndex, getRightChildIndex(currentTreeIndex), mid + 1, arrEndIndex);
            this.segmentTree[currentTreeIndex] = getMax(leftSideValue, rightSideValue);

        }
        return this.segmentTree[currentTreeIndex];
    }


    public int getMinFromRange(final int queriedLeftArrIndex, final int queriedRightArrIndex) {
        return getMinFromRange(0, 0, this.originalArrLength - 1, queriedLeftArrIndex, queriedRightArrIndex);
    }


    public void updateValue(int newValue, int arrIndex) {
        updateValue(newValue, arrIndex, 0, 0, this.originalArrLength - 1);
    }


    public void printSegmentTree() {
        for (int i : this.segmentTree) {
            System.out.print(" " + i);
        }
        System.out.println();
    }


    public static void main(String[] args) throws Exception {
        int arr[] = new int[]{1, 3, 5, 7, 9, 11};
        SBM_MaxSegmentTree segmentTree = new SBM_MaxSegmentTree(arr);

        segmentTree.printSegmentTree();

        System.out.println(segmentTree.getMinFromRange(0, 4));
        System.out.println(segmentTree.getMinFromRange(2, 4));

        segmentTree.updateValue(2, 1);

        segmentTree.printSegmentTree();

        System.out.println(segmentTree.getMinFromRange(1, 2));
        System.out.println(segmentTree.getMinFromRange(4, 4));
    }

}




