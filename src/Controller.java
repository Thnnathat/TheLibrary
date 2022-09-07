package src;
import Modules.Manager;
import java.util.Scanner;
public class Controller {
    Manager manager = new Manager();

    public void ManagerMenu() {
        menu : while (true) {
            System.out.println("ตัวเลือก\n1.เพิ่มหนังสือ\n2.คืนหนังสือ\n3.บุคคลที่ยืมหนังสือ\n4.ลบหนังสือ\n0.กลับ");
            System.out.print(">>>");
            Scanner sc = new Scanner(System.in);
            int choises = sc.nextInt();
            switch (choises) {
                case 0 : 
                    break menu; 
                case 1 : 
                    InsertBook();
                    break;
                case 2 : 
                    Back();
                    break;
                case 3 : 
                    Person();
                    break;
                case 4 : 
                    DeleteBookList();
                    break;
                case 5 : 
                    BookLists();
                    break;
                default : 
                    System.out.println("โปรดเลือตามตัวเลือก");
                    continue;
            }
        }
    }

//!Method for manager.
    public void InsertBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print(">Isbn: ");
        String isbn = sc.nextLine();
        System.out.print(">Title: ");
        String title = sc.nextLine();
        System.out.print(">Quantity: ");
        int Quantity = sc.nextInt();
        boolean bool = manager.addBook(isbn, title, Quantity); 
    }

    public void Back() {
        Scanner sc = new Scanner(System.in);
        System.out.print(">Isbn: ");
        String isbn = sc.nextLine();
        System.out.print(">Quantity: ");
        int Quantity = sc.nextInt();
        boolean bool = manager.ReturnBook(isbn, Quantity);
    }

    public void Person() {
        Scanner sc = new Scanner(System.in);
        System.out.print(">Isbn: ");
        String isbn = sc.nextLine();
        manager.FindPerson(isbn);
    }

    public void DeleteBookList() { 
        Scanner sc = new Scanner(System.in);
        System.out.print(">Isbn: ");
        String item [] = {sc.nextLine()};
        manager.DeleteBooks(item);
    }

    public void BookLists() {
        manager.showBooks();
    }

    public void User() {
        menu : while (true) {
            System.out.println("ตัวเลือก\n1.ยืมหนังสือ\n2.ยกเลิกการยืมหนังสือ\n3.หนังสือที่ยีมอยู่\n0.กลับ");
            System.out.print(">>>");
            Scanner sc = new Scanner(System.in);
            int choises = sc.nextInt();
            switch (choises) {
                case 0 : 
                    break menu; 
                case 1 : 
                    InsertBook();
                    break;
                case 2 : 
                    Back();
                    break;
                case 3 : 
                    Person();
                    break;
                default : 
                    System.out.println("โปรดเลือตามตัวเลือก");
                    continue;
            }
        }
    }

//!Method for User.
    public void Borrow() {
        Scanner sc = new Scanner(System.in);
        System.out.print(">Isbn: ");
        String isbn = sc.nextLine();
        System.out.print(">Title: ");
        String title = sc.nextLine();
    }

    public void Cancel() {
        Scanner sc = new Scanner(System.in);
        System.out.print(">Isbn: ");
        String isbn = sc.nextLine();
        System.out.print(">Title: ");
        String title = sc.nextLine();
    }

    public void Borrowed() {
        System.out.println("Pass");
    }
}
