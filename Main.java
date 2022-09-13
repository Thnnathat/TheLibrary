import java.util.Scanner;
import src.Controller;
class Main {
    public static void main(String[] args) {
        Controller controll = new Controller(); //สร้าง boj ของ Controller Class.
        try {
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
                        controll.UserMenu();
                        break;
                    case 3 :
                        controll.DeveloperMenu();
                    default :
                        System.out.println("โปรดป้อนตัวเลือก");
                        continue;
                }
            }
        } catch (Exception e) {
        }
        
        // WriteFile();
    }
}