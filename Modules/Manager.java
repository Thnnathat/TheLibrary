//!Very hard code.
package Modules;
import java.util.Scanner;
import Modules.BooksLists.Head;
import Modules.BooksLists.Node;
import Modules.PersonList.PersonHead;
public class Manager {
    BooksLists book = new BooksLists();
    Head head = book.CreateList();

    public boolean AddBook(String isbn, String title, int Quantity) {
        Node node = book.FindNode(head, isbn);
        if (node != null) {
            if(node.Detail[0].equalsIgnoreCase(isbn)) {
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
        Node node = book.FindNode(head, isbn);
        if (node != null) {
            if(node.Detail[0].equalsIgnoreCase(isbn)) {
                node.Requests -= Requests;
                return true;
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

    public void DeleteBooks(String item) {
        book.DeleteBetween(head, item);
    }

    public void showBooks() {
        book.Traverse(head);
    }

    public boolean Barrowing(String isbn, String title, String item[]) {
        Node node = book.FindNode(head, isbn, title);
        boolean bool;
        if (node != null) {
            PersonHead per = node.per;
            if(node.Detail[0].equalsIgnoreCase(isbn) || node.Detail[1].equalsIgnoreCase(title)) {
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

    public boolean Cancel(String isbn, String title, String item[]) {
        Node node = book.FindNode(head, isbn);
        if (node != null) {
            PersonList person = node.person;
            PersonHead per = node.per;
            if(node.Detail[0].equalsIgnoreCase(isbn) || node.Detail[1].equalsIgnoreCase(title)){
                person.DeleteBetween(per, item);
                node.Requests -= 1;
                return true;
            }
        }
        return false;
    }

    public boolean NeedBorrow() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("หนังสือไม่ว่าง ต้องการจองหนังสือหรือไม่");
            System.out.print(">>> ");
            String choises = sc.nextLine();
            if (choises.equalsIgnoreCase("ใช่")) {
                return true;
            } else if (choises.equalsIgnoreCase("ไม่")){
                return false;
            } else {
                continue;
            }
        }
    }
}
