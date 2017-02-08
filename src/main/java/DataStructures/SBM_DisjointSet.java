package DataStructures;

/**
 * Created by sumit on 18/01/17.
 */
public class SBM_DisjointSet {
    public static class Node {
        public int data, rank;   // rank is the Approximate height of the tree.
        public Node parent;

        public Node(int data) {
            this.data = data;
            this.rank = 0;
            this.parent = this;
        }
    }

    // Creates a Set with only one element in it.
    public Node makeSet (int data) {
        return new Node(data);
    }

    // Returns the representative element of the set. Uses path compression.
    // Remember, although path compression do change the height of the tree.
    // We don't update that, since it is a time consuming operation.
    // Algorithm performs almost same with approximate height. (ranks).
    public Node findSet (Node node) {
        if (node == node.parent) {
            return node.parent;
        }

        //Apply path compression.
        node.parent = findSet(node.parent);
        return node.parent;
    }

    public Node union (Node node1, Node node2) {
        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        // Already in the same set.
        if (parent1 == parent2) {
            return parent1;
        }

        if (parent1.rank > parent2.rank) {
            parent2.parent = parent1;
            return parent1;
        } else if (parent1.rank < parent2.rank) {
            parent1.parent = parent2;
            return parent2;
        } else {
            parent2.parent = parent1;
            parent1.rank += 1;
            return parent1;
        }
    }


    public static void main(String[] args) {

    }
}
