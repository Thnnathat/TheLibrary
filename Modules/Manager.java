//!Very hard code.
package Modules;
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
            String Detail [] = {isbn, title};
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
        PersonHead per = node.per;
        PersonList person = node.person;
        if (node != null) {
            if(node.Detail[0].equalsIgnoreCase(isbn)) {
                for (int i=0;i<node.Detail.length;i++){
                    System.out.print(node.Detail[i]+"\t\t");
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

    public int Barrowing(String isbn, String title, String item[]) {
        Node node = book.FindNode(head, isbn, title);
        if (node != null) {
            PersonHead per = node.per;
            if(node.Detail[0].equalsIgnoreCase(isbn) || node.Detail[1].equalsIgnoreCase(title)) {
                if(node.Quantity > node.Requests){
                    node.Requests += 1;
                    return 1;//ยืมได้ 
                } else {
                    node.person.AddLast(per, item, 0);
                }
            } else {
                return 3;//ไม่มีหนังสือ
            } 
        }
        return 0; //ไม่มีห้องสมุด
    }

    public void Cancel(String isbn) {
        
    }
}
