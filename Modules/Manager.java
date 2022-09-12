//!Very hard code.
package Modules;
import java.util.Scanner;
import Modules.BooksLists.Head;
import Modules.BooksLists.Node;
import Modules.PersonList.PerNode;
import Modules.PersonList.PersonHead;

public class Manager {
    BooksLists book = new BooksLists();
    Head head = book.CreateList();

    public boolean SetQueue(Node node) {
        if (node != null) {
            PersonHead per = node.per;
            PerNode perNode;
            perNode = per.First;
            while (node.Quantity > node.Requests && perNode != null){
                perNode.Status = 1;     
                perNode = perNode.Next;
                node.Requests += 1;
            }
            return true;
        }
        return false;
    }

    public boolean AddBook(String isbn, String title, int Quantity) {
        Node node = book.FindNode(head, isbn);
        if (node != null) {
            if(node.Detail[0].equalsIgnoreCase(isbn)) {
                SetQueue(node);
                node.Quantity += Quantity;
                return true;
            } 
            return false;
        } else {
            String[] Detail = {isbn, title};
            boolean bool = book.Addfirst(head, Detail, Quantity);
            return bool; 
        }
    }

    public boolean ReturnBook(String isbn, int Requests) {
        Node node = book.FindNode(head, isbn); //ถ้าไม่มีหนังสือเล่มที่จะคืนในคลัง จะได้ null
        if (node != null) {//ถ้า null จะไม่ทำงานใดๆ
            if(node.Detail[0].equalsIgnoreCase(isbn)) {
                if(node.Requests > 0) {
                    node.Requests -= Requests;
                    SetQueue(node);
                    return true;
                }
            } 
        }
        return false;
    }

    public void FindPerson(String isbn) {
        Node node = book.FindNode(head, isbn);
        if (node != null) {
            PersonHead per = node.per;
            PersonList person = node.person;
            if(node.Detail[0].equalsIgnoreCase(isbn)) {
                for (int i=0;i<node.Detail.length;i++){
                    System.out.print(node.Detail[i]+"\t\t\n");
                }
                person.Traverse(per);
            }
        }
    }

    public boolean DeleteBooks(String item) {
        if (book != null) {
            boolean bool = book.DeleteBetween(head, item);
            return bool;
        }
        return false;
    }

    public void showBooks() {
        if (book != null) {
            book.Traverse(head);
        }
    }

    public boolean Borrowing(String isbn, String title, String item[]) {
        Node node = book.FindNode(head, isbn, title);
        boolean bool;
        if (node != null) {
            PersonHead per = node.per;
            if(node.Detail[0].equalsIgnoreCase(isbn) || node.Detail[1].equalsIgnoreCase(title)) { //isbn, title
                if(node.Quantity > node.Requests){
                    node.Requests += 1;
                    return true;//ยืมได้ 
                } else {
                    bool = NeedBorrow();
                    if (bool) {
                        node.person.AddLast(per, item, 0);
                        node.Requests += 1;
                        return true;
                    } else {
                        return false;//ยืมไม่ได้ หรือไม่ได้ยืม
                    }
                }
            }
        }
        return false;
    }

    //*Overload Borrowing method for bypass the question */
    public boolean Borrowing(String isbn, String item[]) { //!Method for developer.
        Node node = book.FindNode(head, isbn);
        if (node != null) {
            PersonHead per = node.per;
            if(node.Detail[0].equalsIgnoreCase(isbn)) { //isbn, title
                if(node.Quantity > node.Requests){
                    node.Requests += 1;
                    return true;//ยืมได้ 
                } else {
                    node.person.AddLast(per, item, 0);
                    node.Requests += 1;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean Cancel(String isbn, String title, String item[]) {
        Node node = book.FindNode(head, isbn);
        if (node != null) {
            PersonList person = node.person;
            PersonHead per = node.per;
            PerNode perNode = person.FindNode(per, item);
            if(node.Detail[0].equalsIgnoreCase(isbn) || node.Detail[1].equalsIgnoreCase(title)){
                if (perNode != null) {
                    if(perNode.Item[0].equalsIgnoreCase(item[0])) { //!น่าจะมีปัญหา *แก้แล้วรอทดสอบ
                        boolean bool = person.DeleteBetween(per, item);//ถ้าคนนั้นๆ มีอยู่ในPerson(Node)จริง จะ return True.
                        if (bool) { //ต้องเป็นคนที่อยู่ใน PersonList ใน Book(Node) นั้นๆ ถึงจะทำงาน
                            node.Requests -= 1;
                            SetQueue(node);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean GetBook() {
        return false;
    }

    public void FindBorrowed(String item[]) {
        book.Traverse(head, item);
    }

    public boolean NeedBorrow() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("หนังสือไม่ว่าง ต้องการจองหนังสือหรือไม่ y/n");
            System.out.print("-> ");
            String choises = sc.nextLine().toLowerCase();
            if (choises.startsWith("y")) {
                return true;
            } else if (choises.startsWith("n")){
                return false;
            } else {
                continue;
            }
        }
    }
}
