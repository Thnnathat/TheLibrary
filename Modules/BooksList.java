package Modules;
import Modules.PersonList.PerNode;
import Modules.PersonList.PersonHead;;

public class BooksList {

    public static class Node {
        String Detail [] = new String[2];
        int Quantity = 0;
        int Requests = 0;
        PersonList person = new PersonList();
        PersonHead per = person.CreateList();
        Node Next;
    }

    public static class Head {
        int Length;
        Node First, Last;
    }

//*1 */
    public Head CreateList() {
        Head head = new Head();
        head.Length = 0;
        head.First = null;
        head.Last = null;
        return head;
    }

//*2 */
    public boolean AddFirst(Head head, String Detail [], int Quantity) {
        Node node = new Node();
        for (int i=0;i<Detail.length;i++) {
            node.Detail[i] = Detail[i];
        }
        node.Quantity = Quantity;
        node.Requests = 0;
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

//*3 */
    public boolean AddLast(Head per, String item [], int quantity, int requests) {
        Node node = new Node();
        for (int i=0;i<item.length;i++) {
            node.Detail[i] = item[i];
        }
        node.Quantity = quantity;
        node.Requests = requests;
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

//*4 */
    public void Traverse(Head head) {
        int queue = 0;
        Node node;
        node = head.First;
        if (head.Length > 0) {
            System.out.println("ISBN\tTitle\t\tQuantity\tRequests\tQueues");
            while (node != null) {
                for (int i=0;i<node.Detail.length;i++) {
                    System.out.print(node.Detail[i]+"\t");
                }
                if (node.Requests > node.Quantity){
                    queue = node.Requests - (node.Quantity % node.Requests);
                }
                System.out.printf("%d\t\t"+"%d\t\t%d\n",node.Quantity,node.Requests,queue);
                node = node.Next;
            }
        } else {
            System.out.println("null");
        }
    }

//*5 */
//*Overload ท่องไปทุกโหนด รวมทั้ง Sub Node*/
    public void Traverse(Head head, String item[]) {
        Node node;
        PersonList person;
        PersonHead per;
        PerNode perNode;
        node = head.First;
        if (head.Length > 0) {
            System.out.println("ISBN\t\tTitle\tStatus");
            while (node != null) {
                person = node.person;
                per = node.per;
                perNode = person.FindNode(per, item);
                if (per.Length > 0) {
                    if (perNode != null) {
                        if (perNode.Item[0].equalsIgnoreCase(item[0]) && perNode.Item[1].equalsIgnoreCase(item[1])) {
                            for (int i=0;i<node.Detail.length;i++) {
                                System.out.print(node.Detail[i]+"\t\t");
                            }
                            if (perNode.Status == 1) {
                                System.out.println("ถึงคิวแล้ว");
                            } else if (perNode.Status == 0){
                                System.out.println("ยังไม่ถึงคิว");
                            }
                        }
                    }
                }
                node = node.Next;
            }
            System.out.println("--------------------");
        } else {
            System.out.println("null");
            System.out.println("--------------------");
        }
    }

//*6 */
    public String[] SaveFile(Head head) {
        Node node;
        PersonList person;
        PersonHead per;
        String data;
        node = head.First;
        String[] arr = new String[head.Length];
        int n = 0;
        if (head.Length > 0) {
            while (node != null) {
                data = "";
                person = node.person;
                per = node.per;
                for (int i=0;i<node.Detail.length;i++) {
                    data += node.Detail[i]+",";
                }
                data += node.Quantity+","+node.Requests+",";
                if (per.Length > 0) {
                    data += person.SendData(per);
                }
                arr[n] = data;
                node = node.Next;
                n += 1;
            }
        }
        return arr;
    }

//*7 */
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

//*8 */
    //*Overload สำหรับแค่ isbn*/
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

//*9 */
    public boolean EditNode (Head head, String isbn, int Quantity, int Requests) {
        Node node;
        node = FindNode(head, isbn);
        if (node != null) {
            node.Quantity= Quantity;
            node.Requests = Requests;
            return true;
        }
        return false;
    }

//*10 */
    public boolean DeleteBetween(Head head, String item) { //Method for manager.
        Node prev;
        Node node;
        node = head.First;
        prev = null;
        if (head.Length == 0) {
            return false;
        }
        while (node != null) {
            if (node.Detail[0].equals(item)) {
                if (prev == null) {
                    head.First = node.Next;
                } else {
                    prev.Next = node.Next;
                }
                node.Next = null; 
                System.gc();
                return true;
            } else {
                prev = node;
                node = node.Next;
            }
        }
        return false;
    }
}
