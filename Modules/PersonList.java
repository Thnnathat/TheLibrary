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
        PersonHead per = new PersonHead();
        per.Length = 0;
        per.First = null;
        per.Last = null;
        return per;
    }

    public boolean AddLast(PersonHead per, String item [], int status) {
        Node node = new Node();
        for (int i=0;i<item.length;i++) {
            node.Item[i] = item[i];
        }
        node.Next = null;
        if (per.Length == 0) {
            per.First = node;
            per.Last = node;
        } else {
            per.Last.Next = node;
            per.Last = node; 
        }
        per.Length += 1;
        return true;
    }

    public void Traverse(PersonHead per) {
        Node node;
        node = per.First;
        if (per.Length > 0) {
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

    public Node FindNode(PersonHead per, String item []) {
        Node node;
        node = per.First;
        while (node != null) {
            if (node.Item[0].equals(item[0]) && node.Item[1].equals(item[1])) {
                break;
            }
            node = node.Next;
        }
        return node; //ถ้าพบโหนดขอมูลที่เราต้องการแล้ว ให้ return โหนดตัวนั้น
    }

    public boolean DeleteFirst(PersonHead per) {
        Node node;
        if (per.Length == 0) {
            return false;
        }
        node = per.First;
        per.First = node.Next;
        node = null;
        System.gc();
        per.Length -= 1;
        if (per.Length <= 1) {
            per.Last = per.First;
        }
        return true;
    }

    public boolean DeleteBetween(PersonHead per, String item []) {
        Node prev = new Node();
        Node node = new Node();
        node = per.First;
        prev = null;
        if (per.Length == 0) {
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
