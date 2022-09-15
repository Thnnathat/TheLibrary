package Modules;
public class PersonList {
    
    public String[] table = {"Id", "Auth", "Name"};
    public static class PerNode {
        String Item [] = new String[3]; //Id Auth Name
        int Status;
        PerNode Next;
    }

    public static class PersonHead {
        int Length;
        PerNode First, Last;
    }

    public PersonHead CreateList() {
        PersonHead per = new PersonHead();
        per.Length = 0;
        per.First = null;
        per.Last = null;
        return per;
    }

    public boolean AddFirst(PersonHead per, String item[], int status) {
        PerNode node = new PerNode();
        for (int i=0;i<item.length;i++) {
            node.Item[i] = item[i];
        }
        node.Status = status;
        node.Next = per.First;
        if (per.Length == 0) {
            per.First = node;
            per.Last = node;
        } else {
            per.First = node;
        }
        per.Length += 1;
        return true;
    }

//*comapre Array primitive data type variable.
    public boolean AddLast(PersonHead per, String item [], int status) {
        PerNode node = new PerNode();
        for (int i=0;i<item.length;i++) {
            node.Item[i] = item[i];
        }
        node.Status = status;
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

//!comapre Array primitive data type variable.
    public boolean AddLast(PersonHead per, String id, String auth, String name, int status) {
        PerNode node = new PerNode();
        node.Item[0] = id;
        node.Item[1] = auth;
        node.Item[2] = name;
        node.Status = status;
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
        PerNode node;
        node = per.First;
        if (per.Length > 0) {
            while (node != null) {
                for (int i=0;i<node.Item.length;i++) {
                    System.out.print(this.table[i]+": "+node.Item[i]+" ");
                }
                System.out.println(node.Status);
                node = node.Next;
            }
        } else {
            System.out.println("null");
        }
    }

    public String SendData(PersonHead per) {
        String data = "";
        PerNode node;
        int n = 0;
        node = per.First;
        if (per.Length > 0) {
            while (node != null) {
                for (int i=0;i<node.Item.length;i++) {
                    data += node.Item[i];
                    data += ",";
                }
                data += node.Status;
                if (n+1 < per.Length) {
                    data += ",";
                }
                node = node.Next;
                n += 1;
            }
        } else {
            return "null";
        }
        return data;
    }

    public PerNode FindNode(PersonHead per, String item []) {
        PerNode node;
        node = per.First;
        while (node != null) {
            if (node.Item[0].equals(item[0]) && node.Item[1].equals(item[1])) {
                break;
            }
            node = node.Next;
        }
        return node; //ถ้าพบโหนดขอมูลที่เราต้องการแล้ว ให้ return โหนดตัวนั้น
    }

    public PerNode FindNode(PersonHead per, String id, String auth, String name) {
        PerNode node;
        node = per.First;
        while (node != null) {
            if (node.Item[0].equals(id) && node.Item[1].equals(name)) {
                break;
            }
            node = node.Next;
        }
        return node; //ถ้าพบโหนดขอมูลที่เราต้องการแล้ว ให้ return โหนดตัวนั้น
    }

    public boolean DeleteFirst(PersonHead per) {
        PerNode node;
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

    public boolean DeleteBetween(PersonHead per, String item[]) { //Method for manager.
        PerNode prev;
        PerNode node;
        node = per.First;
        prev = null;
        if (per.Length == 0) {
            return false;
        }
        while (node != null) {
            if (node.Item[0].equals(item[0]) && node.Item[1].equals(item[1])) {
                if (prev == null) {
                    per.First = node.Next;
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
