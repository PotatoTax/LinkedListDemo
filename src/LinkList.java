import org.w3c.dom.Node;

import java.awt.*;

public class LinkList extends EasyApp
{
    private Button bFirstNode;
    private Button bAddNode;
    private Button bShowHead;
    private Button bShowTail;
    private Button bShowList;
    private Button bDeleteNode;
    private Button bCountNodes;
    private Button bQuit;

    private Node head = null;
    private Node tail = null;

    public static void main(String[] args)
    {  new LinkList(); }

    private LinkList() {
        bFirstNode = addButton("First Word",40,50,100,50,this);
        bAddNode   = addButton("Add Word",40,100,100,50,this);
        bShowHead  = addButton("Show Head",40,150,100,50,this);
        bShowTail  = addButton("Show Tail",40,200,100,50,this);
        bShowList  = addButton("Show List",40,250,100,50,this);
        bDeleteNode  = addButton("Delete Node",40,300,100,50,this);
        bCountNodes = addButton("Count Nodes", 40, 350, 100, 50, this);
        bQuit      = addButton("Quit",40,400,100,50,this);

        bQuit.setBackground(Color.red);
        setSize(200,400);
        setVisible(true);
    }

    public void actions(Object source, String command) {
        if (source == bQuit){ System.exit(0); }
        else if (source == bFirstNode) { firstNode();}
        else if (source == bAddNode  ) { addNode();  }
        else if (source == bShowList ) { showList(); }
        else if (source == bShowHead ) { showHead(); }
        else if (source == bShowTail ) { showTail(); }
        else if (source == bDeleteNode) { deleteNode(); }
        else if (source == bCountNodes) { countNodes(); }
    }

    static class Node {
        String name = "";
        int age = 0;
        Node next = null;
    }

    private void firstNode() {
        head = new Node();
        head.next = null;
        head.name = input("First word");
        tail = head;
    }

    private void addNode() {
        Node toAdd = new Node();
        toAdd.name = input("New word");
        Node current = head;
        if (toAdd.name.compareToIgnoreCase(current.name) <= 0) {
            toAdd.next = head;
            head = toAdd;
        } else if (toAdd.name.compareToIgnoreCase(tail.name) >= 0) {
            tail.next = toAdd;
            tail.next.next = null;
            tail = tail.next;
        } else {
            while (current.next != null) {
                if (toAdd.name.compareToIgnoreCase(current.name) >= 0 &&
                    toAdd.name.compareToIgnoreCase(current.next.name) <= 0) {
                    toAdd.next = current.next;
                    current.next = toAdd;
                    break;
                }
                current = current.next;
            }
        }
    }

    private void deleteNode() {
        String toDelete = input("Node to delete");
        if (toDelete.equalsIgnoreCase(head.name)) {
            head = head.next;
        }
        else {
            Node current = head.next;
            Node previous = head;
            while (current != null) {
                if (current.name.equalsIgnoreCase(toDelete)) {
                    previous.next = current.next;
                    break;
                }
                current = current.next;
                previous = previous.next;
            }
        }
    }

    private void showHead() {
        if (head != null) {
            output(head.name);
        }
    }

    private void showTail() {
        if (tail != null) {
            output(tail.name);
        }
    }

    private void showList() {
        if (head != null) {
            Node current = head;
            do {
                output(current.name);
                current = current.next;
            } while (current != null);
        }
    }

    private void countNodes() {
        int count = 0;
        Node current = head;
        while (current != null) {
            current = current.next;
            count++;
        }
        output(count + " Nodes");
    }
}
