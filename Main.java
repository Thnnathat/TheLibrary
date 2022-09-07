import java.util.Scanner;
import src.Controller;
import Modules.PersonList;
import Modules.Manager;
import Modules.PersonList.PersonHead;
class Main {
    public static void main(String[] args) {
        Controller controll = new Controller();
        menu : while (true) {
            System.out.println("เมนูหลัก\n1.ผู้จัดการ\n2.ผู้ใช้\n0.ออกจากโปรแกรม");
            System.out.print(">>> ");
            Scanner sc = new Scanner(System.in);
            int choises = sc.nextInt();
            System.out.println("--------------------");
            switch (choises) {
                case 0 : 
                    break menu;
                case 1 : 
                    controll.ManagerMenu();
                    break;
                case 2 :
                    controll.User();
                default :
                    System.out.println("โปรดเลือตามตัวเลือก");
                    continue;
            }
        }
        // String isbn = "9786162047930", title = "คัมภีร์ Python ฉบับสมบูรณ์";
        // int Quantity = 1;
        // book.addBook(isbn, title, Quantity);
        // TestPerson();
    }

    public static String [] InsertData() {
        try (Scanner sc = new Scanner(System.in)) {
            String item [] = new String[3];
            System.out.print("ใส่ไอดี: ");
            item[0] = sc.nextLine();
            System.out.print("ใส่รหัสยืนยันตัวตัน: ");
            item[1] = sc.nextLine();
            System.out.print("ใส่ชื่อ: ");
            item[2] = sc.nextLine();
            return item;
        }
    }

    public static void TestPerson() {
        PersonList person = new PersonList();
        PersonHead head = person.CreateList();
        
        while (true) {
            System.out.println("เลือกการทำงาน");
            System.out.println("1.เพิ่มผู้ใช้\n2.ลบผู้ใช้\n3.แสดงข้อมูล\n0.ออกจากโปรแกรม");
            System.out.print(">> ");
            Scanner sc = new Scanner(System.in);
            int choises = sc.nextInt();
            if (choises == 0) {
                break;
            }
            else if (choises == 1) {
                while (true) {
                    System.out.print("เพิ่มข้อมูล yes/no: ");
                    Scanner sc11 = new Scanner(System.in);
                    String question = sc11.nextLine();
                    if (question.equalsIgnoreCase("yes")) {
                        String item [] = InsertData();
                        person.AddLast(head, item, 0);
                    } else {
                        break;
                    }
                }
            }
            else if (choises == 2) {
                System.out.print("ลบข้อมูล yes/no: ");
                Scanner sc11 = new Scanner(System.in);
                String question = sc11.nextLine();
                if (question.equalsIgnoreCase("yes")) {
                    String item [] = InsertData();
                    person.DeleteBetween(head, item);
                } else {
                    break;
                }
            }
            else if (choises == 3) {
                person.Traverse(head);
            }
        }
    }
}