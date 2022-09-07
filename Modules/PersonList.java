package Modules;
public class PersonList {
    
    public static class Node {
        String Item [] = new String[3]; //Id Auth Name
        int Status;
        Node Next;
    }

    public static class PersonHead {
        int Length;
        Node First, Last;
    }

    public PersonHead CreateList() {
        PersonHead head = new PersonHead();
        head.Length = 0;
        head.First = null;
        head.Last = null;
        return head;
    }

    public boolean AddLast(PersonHead head, String item [], int status) {
        Node node = new Node();
        for (int i=0;i<item.length;i++) {
            node.Item[i] = item[i];
        }
        node.Next = null;
        if (head.Length == 0) {
            head.First = node;
            head.Last = node;
        } else {
            head.Last.Next = node;
            head.Last = node; 
        }
        head.Length += 1;
        return true;
    }

    public void Traverse(PersonHead head) {
        Node node;
        node = head.First;
        if (head.Length > 0) {
            while (node != null) {
                for (int i=0;i<node.Item.length;i++) {
                    System.out.print(node.Item[i]+" ");
                }
                System.out.println("");
                node = node.Next;
            }
        } else {
            System.out.println("null");
        }
    }

    public Node FindNode(PersonHead head, String item []) {
        Node node;
        node = head.First;
        while (node != null) {
            if (node.Item[0].equals(item[0]) && node.Item[1].equals(item[1])) {
                break;
            }
            node = node.Next;
        }
        return node; //ถ้าพบโหนดขอมูลที่เราต้องการแล้ว ให้ return โหนดตัวนั้น
    }

    public boolean DeleteFirst(PersonHead head) {
        Node node;
        if (head.Length == 0) {
            return false;
        }
        node = head.First;
        head.First = node.Next;
        node = null;
        System.gc();
        head.Length -= 1;
        if (head.Length <= 1) {
            head.Last = head.First;
        }
        return true;
    }

    public boolean DeleteBetween(PersonHead head, String item []) {
        Node prev;
        Node node;
        node = head.First;
        prev = null;
        if (head.Length == 0) {
            return false;
        }
        while (node != null) {
            if (node.Item[0].equals(item[0]) && node.Item[1].equals(item[1])) {
                prev.Next = node.Next;
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
