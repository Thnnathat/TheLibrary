package Modules;
import Modules.PersonList.PersonHead;;

public class BooksLists {

    public static class Node {
        PersonList person = new PersonList();
        PersonHead per = person.CreateList();
        String Detail [] = new String[2];
        int Quantity;
        int Requests = 0;
        Node Next;
        public int length;
    }

    public static class Head {
        int Length;
        Node First, Last;
    }

    public Head CreateList() {
        Head head = new Head();
        head.Length = 0;
        head.First = null;
        head.Last = null;
        return head;
    }

    public boolean Addfirst(Head head, String Detail [], int Quantity) {
        Node node = new Node();
        for (int i=0;i<Detail.length;i++) {
            node.Detail[i] = Detail[i];
        }
        node.Quantity = Quantity;
        node.Next = head.First;
        if (head.Length == 0) {
            head.First = node;
            head.Last = node;
        } else {
            head.First = node;
        }
        head.Length += 1;
        return true;
    }

    public void Traverse(Head head) {
        Node node;
        node = head.First;
        if (head.Length > 0) {
            while (node != null) {
                for (int i=0;i<node.Detail.length;i++) {
                    System.out.print(node.Detail[i]+" ");
                }
                System.out.printf("%d "+"%d",node.Quantity,node.Requests);
                System.out.println("");
                node = node.Next;
            }
        } else {
            System.out.println("null");
        }
    }

    public Node FindNode(Head head, String isbn, String title) { //Method for manager.
        Node node;
        node = head.First;
        while (node != null) {
            if (node.Detail[0].equalsIgnoreCase(isbn) || node.Detail[1].equalsIgnoreCase(title)) {
                break;
            }
            node = node.Next;
        }
        return node; //ถ้าพบโหนดขอมูลที่เราต้องการแล้ว ให้ return โหนดตัวนั้น
    }

    //*Overload สำหรับแค่ isbn */
    public Node FindNode(Head head, String isbn) { //Method for manager.
        Node node;
        node = head.First;
        while (node != null) {
            if (node.Detail[0].equalsIgnoreCase(isbn)) {
                break;
            }
            node = node.Next;
        }
        return node; //ถ้าพบโหนดขอมูลที่เราต้องการแล้ว ให้ return โหนดตัวนั้น
    }

    public boolean EditNode (Head head, String isbn, int Quantity, int Requests) {
        Node node = new Node();
        node = FindNode(head, isbn);
        if (node != null) {
            node.Quantity= Quantity;
            node.Requests = Requests;
            return true;
        }
        return false;
    }

    public boolean EditNode (Head head, String isbn, int Quantity) {
        Node node = new Node();
        node = FindNode(head, isbn);
        if (node != null) {
            node.Quantity= Quantity;
            return true;
        }
        return false;
    }

    public boolean DeleteBetween(Head head, String item) { //Method for manager.
        Node prev;
        Node node;
        node = head.First;
        Node buff = node.Next;
        prev = null;
        if (head.Length == 0) {
            return false;
        }
        while (node != null) {
            if (node.Detail[0].equals(item)) {
                if (prev == null) {
                    head.First = head.Last = node.Next;
                } else {
                    prev.Next = node.Next;
                }
                node.Next = null; 
                System.gc();
                break;
            } else {
                prev = node;
                node = node.Next;
            }
        }
        return true;
    }
}
