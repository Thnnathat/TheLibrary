//!Very hard code.
package Modules;
import java.util.Scanner;
import Modules.BooksLists.Head;
import Modules.BooksLists.Node;
import Modules.PersonList.PerNode;
import Modules.PersonList.PersonHead;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Manager {

    public Manager() {
        AddDataFile();
    }

    String parentPath = "/home/keang/Documents/coding/Abstact/TheLibrary/data/";
    BooksLists book = new BooksLists();
    Head head = book.CreateList();

    public boolean SetQueue(Node node) {
        if (node != null) {
            PerNode perNode;
            PersonHead per = node.per;
            perNode = per.First;
            while (node.Quantity >= node.Requests && perNode != null){
                perNode.Status = 1;     
                perNode = perNode.Next;
            }
            return true;
        }
        return false;
    }

    //บันทึกลงไฟล์
    public void SaveFile() {
        try {
            String[] data = book.SaveFile(head);
            Path file = Paths.get(parentPath+"data.txt");
            BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8);   

            for (int i = 0; i < data.length; i++) {
                writer.write(data[i]);
                writer.newLine();
            }

            writer.close();       
            System.out.println("บันทึกสำเร็จ");
        } catch (IOException e) {
            System.out.println("บันทึกไม่สำเร็จ");
        }
    }
    
    //เพิ่มข้อมูลในไฟล์ลงใน lists เรียก method ด้วย contructor.
    public boolean AddDataFile() {
        try {
            File file = new File(parentPath+"data.txt");
            boolean bool = false;
            try (Scanner input = new Scanner(file)) {
                String[] data; //isbn title.
                String rawData = "";
                String[] detail = new String[2];
                String[] item = new String[3];
                int status = 0;
                String[][] personItem;
                int number = 0;
                int quantity = 0; // quantity.
                int requests = 0;
                String stringStatus = "";

                while (input.hasNext()) {//ตรวจบรรทัด
                    rawData = input.nextLine();
                    data = rawData.split(",");//หั่น string ให้อยู่ใน Array.
                    number = (data.length-4)/4;
                    // System.out.println(data.length); //?ทดสอบโปรกรม
                    personItem = new String[number][4]; 
                    for (int i=0;i<data.length;i++) {
                        if (i<=1) {//นำแค่ isbn และ title ใส่ใน array (Index 0,1).
                            detail[i] = data[i];
                        } else if (i == 2) {
                            quantity = Integer.parseInt(data[i]);//จำนวนหนังสือ type int เนื่องจาก array ต้องมีข้อมูลประเภทเดียวกัน ต้องแยก Quantity และ reauests ออก.
                        } else if (i == 3){
                            requests = Integer.parseInt(data[i]);
                        } else if (i > 3) {
                            for (int j=0;j<number;j++) {
                                if (data[i] != null) {
                                    personItem[j][i-4] = data[i];
                                }
                            }
                        }
                    }
                    bool = this.AddBookLast(detail[0], detail[1], quantity, requests);
                    for (int i=0;i<personItem.length;i++) {
                        // System.out.println(personItem.length); //?ทดสอบ
                        for (int j=0;j<personItem[i].length;j++) {
                            if (personItem[i][j] != null) {
                                if (j < 3) {
                                    item[j] = personItem[i][j];
                                    // System.out.print(personItem[i][j]+" "); //?ทดสอบ
                                } else if (j == 4) {
                                    stringStatus = personItem[i][4];
                                    // System.out.println(stringStatus); //?ทดสอบ
                                    status = Integer.parseInt(stringStatus);
                                }
                            }
                        }
                        this.Borrowing(detail[0], item, status);
                    }
                }
                return bool;
            } 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean AddBook(String isbn, String title, int Quantity) {
        Node node = book.FindNode(head, isbn);
        if (node != null) {
            if(node.Detail[0].equalsIgnoreCase(isbn)) {
                node.Quantity += Quantity;
                SetQueue(node);
                return true;
            } 
            return false;
        } else {
            String[] Detail = {isbn, title};
            boolean bool = book.AddFirst(head, Detail, Quantity);
            return bool; 
        }
    }

    public boolean AddBookLast(String isbn, String title, int quantity, int requests) {
        Node node = book.FindNode(head, isbn);
        if (node != null) {
        } else {
            String[] Detail = {isbn, title};
            boolean bool = book.AddLast(head, Detail, quantity, requests);
            return bool; 
        }
        return false;
    }

    public boolean ReturnBook(String isbn, int Requests) {
        Node node = book.FindNode(head, isbn); //ถ้าไม่มีหนังสือเล่มที่จะคืนในคลัง Return null
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
                    System.out.print(node.Detail[i]+"\t");
                }
                System.out.println();
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
            PersonList person = node.person;
            PersonHead per = node.per;
            PerNode perNode = person.FindNode(per, item);
            if(node.Detail[0].equalsIgnoreCase(isbn) || node.Detail[1].equalsIgnoreCase(title)) { //isbn, title
                if (perNode == null) {
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

        //*Overload Borrowing method for bypass the question and add data file*/
        public boolean Borrowing(String isbn, String item[], int status) { //!Method for developer.
            Node node = book.FindNode(head, isbn);
            if (node != null) {
                PersonHead per = node.per;
                if(node.Detail[0].equalsIgnoreCase(isbn)) { //isbn, title
                    node.person.AddFirst(per, item, status);
                    return true;
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
                    if(perNode.Item[0].equalsIgnoreCase(item[0]) && perNode.Item[1].equalsIgnoreCase(item[1])) { 
                        boolean bool = person.DeleteBetween(per, item);//ลบ PersonNode คนที่เอามาคืนออก ถ้าคนนั้นๆ มีอยู่ในPerson(Node)จริง จะ return True.
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

    //TODO: รอทดสอบ
    public boolean GetBook(String isbn, String title, String item[]) {
        Node node = book.FindNode(head, isbn);
        if (node != null) {
            PersonList person = node.person;
            PersonHead per = node.per;
            PerNode perNode = person.FindNode(per, item);
            if(node.Detail[0].equalsIgnoreCase(isbn) || node.Detail[1].equalsIgnoreCase(title)){
                if (perNode != null) {
                    if(perNode.Item[0].equalsIgnoreCase(item[0]) && perNode.Item[1].equalsIgnoreCase(item[1]) && perNode.Status == 1) { //ถึงคิวถึงจะรับหนังสือได้
                        boolean bool = person.DeleteBetween(per, item);//ลบ PersonNode คนที่เอามาคืนออก ถ้าคนนั้นๆ มีอยู่ในPerson(Node)จริง จะ return True.
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
