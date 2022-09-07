//!Very hard code.
package Modules;
import Modules.BooksLists.Head;
import Modules.BooksLists.Node;
public class Manager {
    BooksLists book = new BooksLists();
    Head head = book.CreateList();

    public boolean addBook(String isbn, String title, int Quantity) {
        Node data = book.FindNode(head, isbn);
        if (data != null) {
            if(data.Detail[0].equalsIgnoreCase(isbn)) {
                data.Quantity += Quantity;
                return true;
            } 
            return false;
        } else {
            String Detail [] = {isbn, title};
            boolean bool = book.Addfirst(head, Detail, Quantity);
            return bool; 
        }
    }

    public boolean ReturnBook(String isbn, int Quantity) {
        boolean bool = book.EditNode(head, isbn, Quantity);
        return bool;
    }

    public void FindPerson(String isbn) {

    }

    public void DeleteBooks(String item []) {
        book.DeleteBetween(head, item);
    }

    public void showBooks() {
        book.Traverse(head);
    }

    public void Barrowing(String isbn) {

    }

    public void Cancel(String isbn) {
        
    }
}
