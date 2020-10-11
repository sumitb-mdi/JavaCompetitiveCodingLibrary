package DataStructures;

import java.io.PrintWriter;

/**
 * Created by sumit on 12/01/17.
 */
public class SBM_LinkedList<T> {
    public static class SBM_LinkedListNode<T> {
        public T data;
        public SBM_LinkedListNode<T> next, previous;

        public SBM_LinkedListNode(T data, SBM_LinkedListNode<T> next, SBM_LinkedListNode<T> previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }

    private SBM_LinkedListNode<T> head;
    private SBM_LinkedListNode<T> tail;
    private static PrintWriter printWriter = new PrintWriter(System.out);


    public SBM_LinkedListNode<T> getHead() {
        return this.head;
    }

//    public SBM_LinkedListNode sortedInsert (T data) {
//        SBM_LinkedListNode currentNode = this.head;
//        while (currentNode != null) {
//            if ((Integer)currentNode.data <(Integer)data)
//                currentNode = currentNode.next;
//            else break;
//        }
//        if (currentNode == null){
//            return this.insertAtEnd(data);
//        }
//        else return this.insertBefore(currentNode, data);
//    }
//
//    public void sortedDelete (T data) {
//        SBM_LinkedListNode currentNode = this.head;
//        while (currentNode != null) {
//            if ((Integer)currentNode.data == (Integer)data) break;
//        }
//        deleteNode(currentNode);
//
//    }

    public SBM_LinkedListNode insertAtEnd (T data) {
        SBM_LinkedListNode<T> node = createNode(data);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            node.previous = this.tail;
            this.tail.next = node;
            this.tail = node;
        }
        return node;
    }

    public SBM_LinkedListNode insertAfter (SBM_LinkedListNode node, T data) {
        if (this.head == null) throw new IllegalStateException("DataStructures.SBM_LinkedList is empty.");
        if (node == null) throw new IllegalArgumentException("Given node is null.");
        SBM_LinkedListNode<T> newNode = createNode(data);
        if (node == this.tail) { // it is a tail node.
            newNode.previous = node;
            node.next = newNode;
            this.tail = newNode;
        } else {
            SBM_LinkedListNode nextNode = node.next;
            newNode.previous = node;
            newNode.next = nextNode;

            nextNode.previous = newNode;
            node.next = newNode;
        }
        return newNode;
    }


    public SBM_LinkedListNode insertBefore (SBM_LinkedListNode node, T data) {
        if (this.head == null) throw new IllegalStateException("DataStructures.SBM_LinkedList is empty.");
        if (node == null) throw new IllegalArgumentException("Given node is null.");
        SBM_LinkedListNode<T> newNode = createNode(data);
        if (node == this.head) {
            this.head.previous = newNode;
            newNode.next = this.head;
            this.head = newNode;
        } else {
            SBM_LinkedListNode previousNode = node.previous;
            newNode.next = node;
            newNode.previous = previousNode;

            node.previous = newNode;
            previousNode.next = newNode;
        }

        return newNode;
    }



    public void deleteNode (SBM_LinkedListNode node) {
        if (this.head == null) throw new IllegalStateException("DataStructures.SBM_LinkedList is empty.");
        if (node == null) throw new IllegalArgumentException("Given node is null");
        if (node == this.head) {
            //delete head.
            if (this.tail == this.head) {
                //make tail also null;
                this.tail = null;
            }
            this.head = this.head.next;
        } else {
            node.previous.next = node.next;
            if (node.next != null) {
                node.next.previous = node.previous;
            } else {
                this.tail = node.previous;  //update tail.
            }

        }
    }


    public void printList () {
        SBM_LinkedListNode currentNode = this.head;
        while (currentNode != null) {
            if (currentNode == this.head) {
                printWriter.print(currentNode.data);
            } else {
                printWriter.print(" " + currentNode.data);
            }
            currentNode = currentNode.next;
        }
        printWriter.println();

        printWriter.flush();
    }

    private SBM_LinkedListNode createNode (T data) {
        return new SBM_LinkedListNode<>(data, null, null);
    }

}