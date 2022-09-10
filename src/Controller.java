package src;
import Modules.Manager;

import java.io.File;
import java.util.Scanner; 
public class Controller {
    Manager manager = new Manager();

    public void ManagerMenu() {
        menu1 : while (true) {
            System.out.println("ตัวเลือก\n1.เพิ่มหนังสือ\n2.คืนหนังสือ\n3.บุคคลที่ยืมหนังสือ\n4.ลบหนังสือ\n5.หนังสือทั้งหมด\n6.เพิ่มชุดหนังสือ\n7.รับหนังสือ\n0.กลับ");
            System.out.print(">>> ");
            try {
                Scanner sc = new Scanner(System.in);
                int choises = sc.nextInt();
                System.out.println("--------------------");
                switch (choises) {
                    case 0 : 
                        break menu1; 
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
                    case 6 :
                        AddBookList();
                        break;
                    case 7 :
                        GetBook();
                    default : 
                        System.out.println("โปรดป้อนตัวเลือก");
                        continue;
                }
            } catch (Exception e) {
                System.err.println(">>>ป้อนตัวเลขเท่านั้น<<<");
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
        boolean bool = manager.AddBook(isbn, title, Quantity); 
        System.out.println("--------------------");
    }

    public void Back() {
        Scanner sc = new Scanner(System.in);
        System.out.print(">Isbn: ");
        String isbn = sc.nextLine();
        System.out.print(">Quantity: ");
        int Quantity = sc.nextInt();
        boolean bool = manager.ReturnBook(isbn, Quantity);
        System.out.println("--------------------");
    }

    public void Person() {
        Scanner sc = new Scanner(System.in);
        System.out.print(">Isbn: ");
        String isbn = sc.nextLine();
        manager.FindPerson(isbn);
        System.out.println("--------------------");
    }

    public void DeleteBookList() { 
        Scanner sc = new Scanner(System.in);
        System.out.print(">Isbn: ");
        String item = sc.nextLine();
        System.out.println("--------------------");
        manager.DeleteBooks(item);
        System.out.println("--------------------");
    }

    public void BookLists() {
        manager.showBooks();
        System.out.println("--------------------");
    }

    public boolean AddBookList() {
        try {
            File file = new File("/home/keang/Documents/coding/Abstact/TheLibrary/data/FakeBooks.txt");
            try (Scanner input = new Scanner(file)) {
                String[] data; //isbn title.
                String rawData = "";
                String[] detail = new String[2];
                int quantity = 0; // quantity.
                while (input.hasNext()) {
                    rawData = input.nextLine();
                    data = rawData.split(",");
                    for (int i=0;i<data.length;i++) {
                        if (i<=1) {
                            detail[i] = data[i];
                        } else if (i==2) {
                            quantity = Integer.parseInt(data[i]);
                        }
                    }
                    manager.AddBook(detail[0], detail[1], quantity);  
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void GetBook() {
        String[] item = Login();
        Scanner sc = new Scanner(System.in);
        System.out.print(">Isbn: ");
        String isbn = sc.nextLine();
        System.out.print(">Title: ");
        String title = sc.nextLine();
        manager.Cancel(isbn, title, item); 
        System.out.println("--------------------");
    }

    public String [] Login() {
        String item [] = new String[3];
        Scanner sc = new Scanner(System.in);
        System.out.print("Id: ");
        item[0] = sc.nextLine().trim();
        System.out.print("Auth: ");
        item[1] = sc.nextLine().trim();
        System.out.print("Name: ");
        item[2] = sc.nextLine();
        System.out.println("--------------------");
        return item;
    }

    public void UserMenu() {
        String[] item = Login();
        menu2 : while (true) {
            System.out.println("ตัวเลือก\n1.ยืมหนังสือ\n2.ยกเลิกการยืมหนังสือ\n3.หนังสือที่ยีมอยู่\n0.กลับ");
            System.out.print(">>> ");
            Scanner sc = new Scanner(System.in);
            int choises = sc.nextInt();
            System.out.println("--------------------");
            switch (choises) {
                case 0 : 
                    break menu2; 
                case 1 : 
                    Borrow(item);
                    break;
                case 2 : 
                    Cancel(item);
                    break;
                case 3 : 
                    Borrowed(item);
                    break;
                default : 
                    System.out.println("โปรดเลือกตามตัวเลือก");
                    continue;
            }
        }
    }

//!Method for User.
    public void Borrow(String item[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print(">Isbn: ");
        String isbn = sc.nextLine();
        System.out.print(">Title: ");
        String title = sc.nextLine();
        boolean status = manager.Borrowing(isbn, title, item);
        System.out.println(status);
        System.out.println("--------------------");
    }

    public void Cancel(String item[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print(">Isbn: ");
        String isbn = sc.nextLine();
        System.out.print(">Title: ");
        String title = sc.nextLine();
        manager.Cancel(isbn, title, item);
        System.out.println("--------------------");
    }

    public void Borrowed(String item []) {
        manager.FindBorrowed(item);
    }

//!Method for developer.
    public void DeveloperMenu(){
        menu : while (true) {
            System.out.println("1.AddBorrowerList\n2.AddBookList\n0.Back");
            System.out.print(">>> ");
            Scanner sc = new Scanner(System.in);
            int choises = sc.nextInt();
            System.out.println("--------------------");
            switch (choises) {
                case 0 : 
                    break menu;
                case 1 :
                    AddBorrower();
                    break;
                case 2 :
                    AddBookList();
                default :
                    continue;
            }
        }
    }

    public boolean AddBorrower() {
        try {
            File file2 = new File("/home/keang/Documents/coding/Abstact/TheLibrary/data/Person.txt");
            try (Scanner input2 = new Scanner(file2)) {
                String[] data; //data split.
                String rawData = "";
                String[] isbn = {"111","222","333"};
                int n = 0;
                while (input2.hasNext()) {
                    rawData = input2.nextLine();
                    data = rawData.split(",");
                    if (n <= 2) {
                        manager.Borrowing(isbn[0], data);
                    } else if (n <= 3) {
                        manager.Borrowing(isbn[1], data);
                    } else if (n <= 5){
                        manager.Borrowing(isbn[2], data);
                    }
                    n += 1;
                }
            }
        return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//!anothor
    public void CheckError() {

    }
}