package DataStructures;

import DataStructures.SBM_LinkedList;

/**
 * Created by sumit on 12/01/17.
 */
public class SBM_LinkedListDriver {
    public static void main(String[] args) {
        SBM_LinkedList<Integer> linkedList = new SBM_LinkedList<>();

        SBM_LinkedList.SBM_LinkedListNode node1 = linkedList.insertAtEnd(1);
        SBM_LinkedList.SBM_LinkedListNode node2 = linkedList.insertAtEnd(2);
        SBM_LinkedList.SBM_LinkedListNode node3 = linkedList.insertAtEnd(3);

        linkedList.deleteNode(node3);
        SBM_LinkedList.SBM_LinkedListNode node4 = linkedList.insertAtEnd(4);

        linkedList.deleteNode(node1);

        SBM_LinkedList.SBM_LinkedListNode node5 =  linkedList.insertAfter(node4, 5);

        linkedList.deleteNode(node5);

        SBM_LinkedList.SBM_LinkedListNode node0 =  linkedList.insertBefore(node4, 0);


        linkedList.printList();
    }
}
