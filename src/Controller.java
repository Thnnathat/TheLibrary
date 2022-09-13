package src;
import Modules.Manager;
import java.io.File;
import java.util.Scanner; 

public class Controller {
    Manager manager = new Manager();//สร้าง obj ของ Manager class.
    String parentPath = "/home/keang/Documents/coding/Abstact/TheLibrary/data/";
    public void ManagerMenu() {
        menu1 : while (true) {
            System.out.println("ตัวเลือก\n1.เพิ่มหนังสือ\n2.คืนหนังสือ\n3.บุคคลที่รอคิว\n4.ลบหนังสือ\n5.หนังสือทั้งหมด\n6.เพิ่มชุดหนังสือ\n7.รับหนังสือ\n8.บันทึกลงในไฟล์\n0.กลับ");
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
                        break;
                    case 8 : 
                        manager.SaveFile();
                        break;
                    case 9 :
                        manager.AddDataFile();
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
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print(">Isbn: ");
            String isbn = sc.nextLine();
            System.out.print(">Title: ");
            String title = sc.nextLine();
            System.out.print(">Quantity: ");
            int Quantity = sc.nextInt();
            boolean bool = manager.AddBook(isbn, title, Quantity); 
            if(bool) {
                System.out.println("เพิ่มหนังสือเรียบร้อย");
            } else {
                System.out.println("เพิ่มหนังสือไม่สำเร็จ");
            };
            System.out.println("--------------------"); 
        } catch (Exception e) {
        }
    }

    public void Back() {
        Scanner sc = new Scanner(System.in);
        System.out.print(">Isbn: ");
        String isbn = sc.nextLine();
        System.out.print(">Quantity: ");
        int Quantity = sc.nextInt();
        System.out.println("--------------------");
        boolean bool = manager.ReturnBook(isbn, Quantity);
        if (bool) {
            System.out.println("คืนหนังสือสำเร็จ");
        } else {
            System.out.println("คืนหนังสือไม่สำเร็จ");
        }
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
        boolean bool = manager.DeleteBooks(item);
        if (bool) {
            System.out.println("ลบหนังสือสำเร็จ");
        } else {
            System.out.println("ลบหนังสือไม่สำเร็จ");
        }
        System.out.println("--------------------");
    }

    public void BookLists() {
        manager.showBooks();
        System.out.println("--------------------");
    }

    public boolean AddBookList() {
        try {
            File file = new File(parentPath+"FakeBooks.txt");
            boolean bool = false;
            try (Scanner input = new Scanner(file)) {
                String[] data; //isbn title.
                String rawData = "";
                String[] detail = new String[2];
                int quantity = 0; // quantity.
                while (input.hasNext()) {//ตรวจบรรทัด
                    rawData = input.nextLine();
                    data = rawData.split(",");//หั่น string ให้อยู่ใน Array.
                    for (int i=0;i<data.length;i++) {
                        if (i<=1) {//นำแค่ isbn และ title ใส่ใน array (Index 0,1).
                            detail[i] = data[i];
                        } else if (i==2) {
                            quantity = Integer.parseInt(data[i]);//จำนวนหนังสือ type int เนื่องจาก array ต้องมีขอมูลประเภทเดียวกัน ต้องแยก Quantity ออก.
                        }  
                    }
                    bool = manager.AddBook(detail[0], detail[1], quantity);//เพิ่มหนังสือ isbn title quantity parameter. 
                }
            }
            return bool;
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
        boolean bool = manager.Cancel(isbn, title, item); 
        if (bool) {
            System.out.println("รับหนังสือแล้ว");
        } else {
            System.out.println("รับหนังสือไม่สำเร็จ");
        }
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
            System.out.println("ตัวเลือก\n1.ยืมหนังสือ\n2.ยกเลิกการยืมหนังสือ\n3.คิวหนังสือ\n0.กลับ");
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
        boolean bool = manager.Borrowing(isbn, title, item);
        if (bool) {
            System.out.println("ยืมหนังสือ/รอคิวยืมหนังสือสำเร็จ");
        } else {
            System.out.println("ยืมหนังสือ/รอคิวยืมหนังสือไม่สำเร็จ");
        }
        System.out.println("--------------------");
    }

    public void Cancel(String item[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print(">Isbn: ");
        String isbn = sc.nextLine();
        System.out.print(">Title: ");
        String title = sc.nextLine();
        boolean bool = manager.Cancel(isbn, title, item);
        if (bool) {
            System.out.println("ยกเลิกสำเร็จ");
        } else {
            System.out.println("ยกเลิกไม่สำเร็จ");
        }
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