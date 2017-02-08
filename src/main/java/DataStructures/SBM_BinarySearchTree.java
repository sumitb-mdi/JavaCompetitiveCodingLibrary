package DataStructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sumit on 18/01/17.
 */
public class SBM_BinarySearchTree {
    public static class BST {
        public static class Node {
            public int data;
            public Node left, right;

            public Node(int data) {
                this.data = data;
            }
        }

        private Node root;


        public void insertNode (int data) {
            Node node = new Node(data);
            if (root == null) {
                root = node;
            } else {
                insertNode(node, this.root);
            }
        }

        public int getHeight () {
            return getHeight(this.root);
        }

        public List<Integer> getPathFromRootForKey (int key) {
            List<Integer> path = new ArrayList<>();
            fillPathForKey(this.root, path, key);
            Collections.reverse(path);  //From root to node.
            return path;
        }

        private void insertNode (Node node, Node parent) {
            if (node.data <= parent.data) {
                //insert on left
                if (parent.left == null) {
                    parent.left = node;
                } else {
                    insertNode(node, parent.left);
                }
            } else {
                //insert on right
                if (parent.right == null) {
                    parent.right = node;
                } else {
                    insertNode(node, parent.right);
                }
            }
        }


        private int getHeight (Node node) {
            if (node == null) return 0;
            int leftSideHeight = getHeight(node.left);
            int rightSideHeight = getHeight(node.right);
            return (leftSideHeight > rightSideHeight ? leftSideHeight : rightSideHeight) + 1;
        }

        private boolean fillPathForKey (Node node, List<Integer> path, int key) {
            if (node == null) return false;
            if (node.data == key) {
                path.add(node.data);
                return true;
            } else if (node.data < key) {
                if (fillPathForKey(node.right, path, key)) {
                    path.add(node.data);
                    return true;
                } else {
                    return false;
                }
            } else {
                if (fillPathForKey(node.left, path, key)) {
                    path.add(node.data);
                    return true;
                } else {
                    return false;
                }
            }
        }
    }


    //Driver:
    public static void main(String[] args) {
        BST bst = new BST();
        bst.insertNode(4);
        bst.insertNode(7);
        bst.insertNode(8);
        bst.insertNode(6);
        bst.insertNode(3);

        List<Integer> path = bst.getPathFromRootForKey(3);
        path.forEach(System.out::println);
    }
}
