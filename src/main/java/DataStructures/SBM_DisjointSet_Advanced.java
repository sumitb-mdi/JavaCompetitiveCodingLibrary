package DataStructures;

import java.util.HashMap;

/**
 * Created by sumit on 27/01/17.
 */
public class SBM_DisjointSet_Advanced {
    private static class Node {
        public int data, rank;   // rank is the Approximate height of the tree.
        public Node parent;

        public Node(int data) {
            this.data = data;
            this.rank = 0;
            this.parent = this;
        }
    }

    private final Node[] nodes;
    private final HashMap<Node, Integer> disjointSetsMap;


    public SBM_DisjointSet_Advanced(int totalNodes) {
        this(totalNodes, false);
    }

    public SBM_DisjointSet_Advanced(int totalNodes, boolean toCreateNodes) {
        this.nodes = new Node[totalNodes];
        this.disjointSetsMap = new HashMap<>();
        if (toCreateNodes) {
            for (int i = 0; i < totalNodes; i++) {
                this.nodes[i] = makeSet(i);
            }
        }
    }

    // Creates a Set with only one element in it.    (Making it final, since being called from constructor.)
    public final Node makeSet (int data) {
        Node node = new Node(data);
        this.nodes[data] = node;
        this.disjointSetsMap.put(node, 1);   // 1 denotes that number of elements in this set is 1.
        return node;
    }

    public Node findSet (int data) {
        return findSet(this.nodes[data]);
    }

    public Node union (int data1, int data2) {
        return union(this.nodes[data1], this.nodes[data2]);
    }

    // Returns the representative element of the set. Uses path compression.
    // Remember, although path compression do change the height of the tree.
    // We don't update that, since it is a time consuming operation.
    // Algorithm performs almost same with approximate height. (ranks).
    private Node findSet (Node node) {
        if (node == node.parent) {
            return node.parent;
        }

        //Apply path compression.
        node.parent = findSet(node.parent);
        return node.parent;
    }

    private Node union (Node node1, Node node2) {
        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        // Already in the same set.
        if (parent1 == parent2) {
            return parent1;
        }

        int cardinality1 = this.disjointSetsMap.remove(parent1);
        int cardinality2 = this.disjointSetsMap.remove(parent2);

        if (parent1.rank > parent2.rank) {
            this.disjointSetsMap.put(parent1, cardinality1 + cardinality2);
            parent2.parent = parent1;
            return parent1;
        } else if (parent1.rank < parent2.rank) {
            this.disjointSetsMap.put(parent2, cardinality1 + cardinality2);
            parent1.parent = parent2;
            return parent2;
        } else {
            parent2.parent = parent1;
            this.disjointSetsMap.put(parent1, cardinality1 + cardinality2);
            parent1.rank += 1;
            return parent1;
        }
    }

    public int getTotalNumberOfSets () {
        return this.disjointSetsMap.size();
    }

    public HashMap<Node, Integer> getDisjointSetsMap() {
        return disjointSetsMap;
    }
}
